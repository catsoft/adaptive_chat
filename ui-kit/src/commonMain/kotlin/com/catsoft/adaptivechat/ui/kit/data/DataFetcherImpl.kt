package com.catsoft.adaptivechat.ui.kit.data

import com.catsoft.adaptivechat.ui.kit.api.data.ACData
import com.catsoft.adaptivechat.ui.kit.api.data.DataFetcher
import com.catsoft.adaptivechat.ui.kit.api.data.FetchPolicy
import com.catsoft.adaptivechat.ui.kit.api.data.getRawData
import com.catsoft.adaptivechat.ui.kit.api.datetime.currentDateTime
import com.catsoft.adaptivechat.ui.kit.api.datetime.currentTime
import com.catsoft.adaptivechat.ui.kit.data.cache.FetcherConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.merge
import kotlin.time.Clock

open class DataFetcherImpl<T>(
    protected val fetchFromNetwork: suspend (String) -> T?,
    protected val getFromMemory: (String) -> T?,
    protected val putToMemory: (String, T?) -> Unit,
    protected val getFromLocal: suspend (String) -> T?,
    protected val putToLocal: suspend (String, T?) -> Unit
) : DataFetcher<T> {

    private val lastUpdateTime = mutableMapOf<String, Long>()
    
    protected val _dataUpdates = MutableSharedFlow<Pair<String, T?>>(replay = 0)
    val dataUpdates = _dataUpdates.asSharedFlow()
    
    protected val _networkDataUpdates = MutableSharedFlow<Pair<String, T?>>(replay = 0)
    val networkDataUpdates = _networkDataUpdates.asSharedFlow()
    

    override fun getSingleData(policy: FetchPolicy): Flow<ACData<T>> = getData("", policy)

    override fun getData(param: String, policy: FetchPolicy): Flow<ACData<T>> {
        val originalFlow = flow<ACData<T>> {
            if (FetcherConfig.REQUEST_DELAY) {
                delay(1000)
            }

            var emittedCache = false

            when (policy) {
                FetchPolicy.CACHE_NETWORK_IF_EMPTY -> {
                    getFromMemory(param)?.let {
                        emit(ACData(content = it))
                        emittedCache = true
                    } ?: getFromLocal(param)?.let {
                        saveData(param, it)
                        emit(ACData(content = it))
                        emittedCache = true
                    }

                    if (!emittedCache) {
                        try {
                            emit(ACData.loading())
                            val network = fetchAndCache(param)
                            emit(ACData(content = network))
                        } catch (e: Exception) {
                            emit(ACData(error = e))
                        }
                    }
                }

                FetchPolicy.CACHE_FIRST -> {
                    val memoryData = getFromMemory(param) ?: getFromLocal(param)?.also { saveData(param, it) }
                    if (memoryData == null) {
                        emit(ACData.loading())
                    } else {
                        emit(ACData(content = memoryData))
                    }
                    
                    val fetchStartTime = currentTimeMillis()

                    try {
                        val network = fetchAndCache(param)
                        val lastUpdate = lastUpdateTime[param] ?: 0
                        if (lastUpdate <= fetchStartTime) {
                            emit(ACData(content = network))
                        }
                    } catch (e: Exception) {
                        emit(ACData(error = e))
                    }
                }

                FetchPolicy.NETWORK_FIRST -> {
                    try {
                        emit(ACData.loading())
                        val network = fetchAndCache(param)
                        emit(ACData(content = network))
                    } catch (e: Exception) {
                        val cached = getFromMemory(param)
                            ?: getFromLocal(param)?.also { putToMemory(param, it) }

                        if (cached != null) {
                            emit(ACData(content = cached))
                        } else {
                            emit(ACData(error = e))
                        }
                    }
                }

                FetchPolicy.NETWORK_ONLY -> {
                    try {
                        emit(ACData.loading())
                        emit(ACData(content = fetchAndCache(param)))
                    } catch (e: Exception) {
                        emit(ACData(error = e))
                    }
                }

                FetchPolicy.CACHE_ONLY -> {
                    val cached = getFromMemory(param) ?: getFromLocal(param)?.also { putToMemory(param, it) }
                    emit(ACData(content = cached))
                }
            }
        }.flowOn(Dispatchers.IO)

        val updateFlow = flow {
            _dataUpdates.collect { (key, data) ->
                if (key == param) {
                    emit(ACData(content = data))
                }
            }
        }

        return merge(originalFlow, updateFlow)
    }

    override suspend fun saveSingleData(data: T?): T? = saveData("", data)

    override suspend fun updateCachedData(param: String, transform: T?.() -> T?): T? {
        val cachedData = getRawData(param = param, FetchPolicy.CACHE_NETWORK_IF_EMPTY).firstOrNull()
        val transformedData = cachedData.transform()
        return saveData(param, transformedData)
    }

    override suspend fun saveData(param: String, data: T?): T? {
        lastUpdateTime[param] = currentTimeMillis()
        putToMemory(param, data)
        putToLocal(param, data)
        _dataUpdates.emit(param to data)
        return data
    }

    private suspend fun fetchAndCache(param: String): T? {
        val data = fetchFromNetwork(param)
        saveData(param, data)
        _networkDataUpdates.emit(param to data)
        if (FetcherConfig.REQUEST_DELAY) {
            delay(2000)
        }
        return data
    }
}

fun currentTimeMillis() = Clock.System.now().toEpochMilliseconds()
package com.catsoft.adaptivechat.ui.kit.data

import com.catsoft.adaptivechat.ui.kit.data.cache.cache.ACMemoryCache
import com.catsoft.adaptivechat.ui.kit.data.cache.cache.CacheData
import com.catsoft.adaptivechat.ui.kit.data.cache.cache.CacheDataDao
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json

open class SerializableDataFetcher<T>(
    private val serializer: KSerializer<T>,
    private val prefix: String,
    fetchFromNetwork: suspend (String) -> T?,
    protected val memoryCache: ACMemoryCache,
    protected val dao: CacheDataDao,
    putToLocal: suspend (String, T?) -> Unit = { param, data ->
        val json = data?.let { Json.encodeToString(serializer, data) }
        dao.insertData(CacheData(prefix + param, json))
    },
    getFromLocal: suspend (String) -> T? = { param ->
        try {
            dao.getData(prefix + param)?.content?.let { json ->
                Json.decodeFromString(serializer, json)
            }
        } catch (e: Throwable) {
            null
        }
    },
) : DataFetcherImpl<T>(
    fetchFromNetwork = fetchFromNetwork,
    getFromMemory = { param -> memoryCache.cache[prefix + param] as T? },
    putToMemory = { param, data -> memoryCache.cache[prefix + param] = data as Any? },
    getFromLocal = getFromLocal,
    putToLocal = putToLocal
)


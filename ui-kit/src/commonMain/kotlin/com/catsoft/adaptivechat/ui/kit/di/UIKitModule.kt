package com.catsoft.adaptivechat.ui.kit.di

import co.touchlab.kermit.Logger
import com.catsoft.adaptivechat.ui.kit.connectionStatus.ConnectivityProvider
import com.catsoft.adaptivechat.ui.kit.connectionStatus.ConnectivityViewModel
import com.catsoft.adaptivechat.ui.kit.data.cache.cache.ACMemoryCache
import com.catsoft.adaptivechat.ui.kit.data.cache.cache.CacheData
import com.catsoft.adaptivechat.ui.kit.data.cache.cache.CacheDataDao
import com.catsoft.adaptivechat.ui.kit.topBar.TopBarVisibilityState
import com.catsoft.adaptivechat.ui.kit.topBar.TopBarVisibilityStateImpl
import dev.jordond.connectivity.Connectivity
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module


val uiKitModule = module {
    singleOf(::TopBarVisibilityStateImpl) bind TopBarVisibilityState::class

    viewModelOf(::ConnectivityViewModel)

    singleOf(::ConnectivityProvider)

    single<Connectivity> {
        val provider = get<ConnectivityProvider>()
        provider.provide()
    }

    singleOf(::ACMemoryCache)

    // todo implement
    single<CacheDataDao> {
        object : CacheDataDao {
            override suspend fun getData(param: String): CacheData? {
                return null
            }

            override suspend fun insertData(data: CacheData) {
            }

            override suspend fun deleteAll() {
            }
        }
    }
}
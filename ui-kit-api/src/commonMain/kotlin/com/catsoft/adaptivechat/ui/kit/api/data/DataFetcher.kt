package com.catsoft.adaptivechat.ui.kit.api.data

import kotlinx.coroutines.flow.Flow

interface DataFetcher<T> {

    fun getSingleData(policy: FetchPolicy): Flow<CFData<T>>

    suspend fun saveSingleData(data: T?): T?

    suspend fun updateCachedData(param: String, transform: T?.() -> T?): T?

    fun getData(param: String, policy: FetchPolicy): Flow<CFData<T>>

    suspend fun saveData(param: String, data: T?): T?
}
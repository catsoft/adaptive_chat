package com.catsoft.adaptivechat.ui.kit.api.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest

fun <T> DataFetcher<T>.getRawData(param: String, policy: FetchPolicy): Flow<T> = this.getData(param, policy).getRawData()

suspend fun <T> DataFetcher<T>.collectRawData(param: String, policy: FetchPolicy, action: suspend (value: T) -> Unit): Unit =
    this.getData(param, policy).getRawData().collectLatest(action)

suspend fun <T> DataFetcher<T>.collectData(param: String, policy: FetchPolicy, action: suspend (value: ACData<T>) -> Unit): Unit =
    this.getData(param, policy).collectLatest(action)

suspend fun <T> DataFetcher<T>.collectSingleData(policy: FetchPolicy, action: suspend (value: ACData<T>) -> Unit): Unit = this.getSingleData(policy).collectLatest(action)

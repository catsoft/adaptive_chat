package com.catsoft.adaptivechat.logger

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch

fun <T> Flow<T>.catchAndLog(): Flow<T> = this.catch { logger().e("", it) }
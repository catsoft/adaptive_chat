package com.catsoft.adaptivechat.logger

fun <T> Flow<T>.catchAndLog(): Flow<T> = this.catch { timber().e("", it) }
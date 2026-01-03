package com.catsoft.adaptivechat.logger

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch

fun <T> Flow<T>.catchAndLog(): Flow<T> = this.catch { logger().e("", it) }


inline fun <R> runLogAndCatch(
    onError: (Throwable) -> Result<R> = {
        Result.failure(it)
    },
    block: () -> R,
): Result<R> {
    return try {
        Result.success(block())
    } catch (e: Throwable) {
        logger().i { "Error running block: runCatching" }
        logger().e(e) { e.message ?: "Error" }
        onError(e)
        Result.failure(e)
    }
}

inline fun <R : Any> runLogCatching(
    onError: (Throwable) -> Result<R> = {
        Result.failure(it)
    },
    block: () -> R,
): Result<R> = try {
    Result.success(block())
} catch (e: Throwable) {
    logger().i { "Error running block: runCatching" }
    logger().e(e) { e.message ?: "Error" }
    onError(e)
}

inline fun <R : Any?> runLogCatchingNullable(
    onError: (Throwable) -> Result<R?> = {
        Result.failure(it)
    },
    block: () -> R?,
): Result<R?> = try {
    Result.success(block())
} catch (e: Throwable) {
    logger().i { "Error running block: runCatching" }
    logger().e(e) { e.message ?: "Error" }
    onError(e)
}

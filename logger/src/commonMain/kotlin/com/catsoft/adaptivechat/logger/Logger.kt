package com.catsoft.adaptivechat.logger

import co.touchlab.kermit.Logger
import co.touchlab.kermit.Severity
import co.touchlab.kermit.StaticConfig
import co.touchlab.kermit.loggerConfigInit
import co.touchlab.kermit.platformLogWriter

/**
 * Global logger instance with Android-specific configuration.
 * Uses Logcat as the log writer.
 */
private val globalLogger: Logger by lazy {
    Logger(
        config = StaticConfig(
            logWriterList = listOf(platformLogWriter()),
            minSeverity = if (BuildConfig.DEBUG) Severity.Verbose else Severity.Info
        ),
        tag = "AdaptiveChat"
    )
}

/**
 * Provides a Logger instance with a tag derived from the class name.
 *
 * Usage:
 * ```kotlin
 * class MyViewModel {
 *     private val logger = timber()
 *
 *     fun someMethod() {
 *         logger.d { "Debug message" }
 *         logger.i { "Info message" }
 *         logger.w { "Warning message" }
 *         logger.e { "Error message" }
 *     }
 * }
 * ```
 */
fun <T : Any> T.timber(): Logger {
    val className = this::class.simpleName ?: "AdaptiveChat"
    return provideLogger().withTag(className)
}

/**
 * Provides a Logger instance with a default tag.
 * Use this for top-level functions or when you don't have a class instance.
 *
 * Usage:
 * ```kotlin
 * fun topLevelFunction() {
 *     timber().d { "Logging from top-level function" }
 * }
 * ```
 */
fun timber(): Logger = Unit.timber()

/**
 * Provides the global logger instance.
 * This can be overridden for testing or to configure a different logger.
 */
fun provideLogger(): Logger = globalLogger

/**
 * Initialize logger with custom configuration.
 * Call this in Application.onCreate() for custom setup.
 *
 * @param minSeverity Minimum severity level to log
 * @param tag Default tag for the logger
 */
fun initLogger(
    minSeverity: Severity = if (BuildConfig.DEBUG) Severity.Verbose else Severity.Info,
    tag: String = "AdaptiveChat"
) {
    loggerConfigInit(
        StaticConfig(
            logWriterList = listOf(platformLogWriter()),
            minSeverity = minSeverity
        )
    )
}

/**
 * Extension functions for common logging patterns
 */

/**
 * Log a debug message with throwable
 */
fun Logger.d(throwable: Throwable, message: () -> String) {
    d(throwable) { message() }
}

/**
 * Log an info message with throwable
 */
fun Logger.i(throwable: Throwable, message: () -> String) {
    i(throwable) { message() }
}

/**
 * Log a warning message with throwable
 */
fun Logger.w(throwable: Throwable, message: () -> String) {
    w(throwable) { message() }
}

/**
 * Log an error message with throwable
 */
fun Logger.e(throwable: Throwable, message: () -> String) {
    e(throwable) { message() }
}

/**
 * Log method entry with optional parameters
 */
fun Logger.entry(vararg params: Pair<String, Any?>) {
    if (params.isEmpty()) {
        d { "→ Entry" }
    } else {
        val paramsStr = params.joinToString { "${it.first}=${it.second}" }
        d { "→ Entry($paramsStr)" }
    }
}

/**
 * Log method exit with optional return value
 */
fun Logger.exit(returnValue: Any? = null) {
    if (returnValue != null) {
        d { "← Exit: $returnValue" }
    } else {
        d { "← Exit" }
    }
}

/**
 * Log a performance measurement
 */
inline fun <T> Logger.measure(operation: String, block: () -> T): T {
    val startTime = System.currentTimeMillis()
    d { "⏱️ Starting: $operation" }
    try {
        return block()
    } finally {
        val duration = System.currentTimeMillis() - startTime
        d { "⏱️ Finished: $operation (${duration}ms)" }
    }
}

/**
 * Log with thread information
 */
fun Logger.dWithThread(message: () -> String) {
    d { "[${Thread.currentThread().name}] ${message()}" }
}

/**
 * Log with thread information for info level
 */
fun Logger.iWithThread(message: () -> String) {
    i { "[${Thread.currentThread().name}] ${message()}" }
}

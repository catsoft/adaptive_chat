package com.catsoft.adaptivechat.logger

import co.touchlab.kermit.Logger
import co.touchlab.kermit.Severity
import co.touchlab.kermit.StaticConfig
import co.touchlab.kermit.loggerConfigInit
import co.touchlab.kermit.platformLogWriter
import kotlin.time.Clock

/**
 * Global logger instance with Android-specific configuration.
 * Uses Logcat as the log writer.
 */
private val globalLogger: Logger by lazy {
    Logger(
        config = StaticConfig(
            logWriterList = listOf(platformLogWriter()),
            minSeverity = LoggerConfig.severity
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
    minSeverity: Severity = LoggerConfig.severity,
    tag: String = "AdaptiveChat"
) {
    loggerConfigInit(
        logWriters = listOf(platformLogWriter()).toTypedArray(),
        minSeverity = minSeverity
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
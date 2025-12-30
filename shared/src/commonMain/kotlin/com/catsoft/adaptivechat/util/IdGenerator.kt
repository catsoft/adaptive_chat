package com.catsoft.adaptivechat.util

import kotlin.random.Random

/**
 * Utility for generating unique IDs across the application.
 * Note: This is a simple implementation using timestamp + random.
 * For production use, consider using UUID or a more robust solution.
 */
object IdGenerator {
    fun generate(): String {
        return "${System.currentTimeMillis()}-${Random.nextLong()}"
    }
}

package com.chatfuel.shared.uiKit.utils

import kotlin.math.ln
import kotlin.math.pow
import kotlin.math.roundToLong


fun humanReadableByteCountSI(size: Long): String {
    val unit = 1000.0
    if (size < unit) return "$size B"

    val exp = (ln(size.toDouble()) / ln(unit)).toInt().coerceAtLeast(1)
    val prefixes = "KMGTPE"
    val pre = "${prefixes[(exp - 1).coerceAtMost(prefixes.lastIndex)]}B"

    val value = size / unit.pow(exp.toDouble())
    return "${formatOneDecimal(value)} $pre"
}

private fun formatOneDecimal(value: Double): String {
    // Round to 1 decimal place without using platform-specific formatting
    val scaled = (value * 10.0).roundToLong()
    val sign = if (scaled < 0) "-" else ""
    val absScaled = kotlin.math.abs(scaled)
    val intPart = absScaled / 10
    val frac = absScaled % 10
    return "$sign$intPart.$frac"
}

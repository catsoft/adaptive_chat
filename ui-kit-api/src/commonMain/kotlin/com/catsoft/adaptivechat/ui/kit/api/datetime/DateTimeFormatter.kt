package com.catsoft.adaptivechat.ui.kit.api.datetime

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.Month

fun LocalDateTime.format(formatStyle: FormatStyle): String = getDateTimeAdapter().formatLocalDateTime(this, formatStyle)

fun LocalTime.format(formatStyle: FormatStyle): String = getDateTimeAdapter().formatLocalTime(this, formatStyle)

fun LocalDate.format(formatStyle: FormatStyle): String = getDateTimeAdapter().formatLocalDate(this, formatStyle)

fun LocalDateTime.formatShort(): String = this.format(FormatStyle.SHORT)

fun LocalTime.formatShort(): String = this.format(FormatStyle.SHORT)

fun LocalDate.formatShort(): String = this.format(FormatStyle.SHORT)


fun LocalDateTime.formatMedium(): String = this.format(FormatStyle.MEDIUM)

fun LocalTime.formatMedium(): String = this.format(FormatStyle.MEDIUM)

fun LocalDate.formatMedium(): String = this.format(FormatStyle.MEDIUM)


fun LocalDateTime.formatLong(): String = this.format(FormatStyle.LONG)

fun LocalTime.formatLong(): String = this.format(FormatStyle.LONG)

fun LocalDate.formatLong(): String = this.format(FormatStyle.LONG)

fun LocalDateTime.copy(date: LocalDate = this.date, time: LocalTime = this.time) = LocalDateTime(date, time)

fun LocalDate.copy(year: Int = this.year, month: Month = this.month, day: Int = this.day) =  LocalDate(year, month, day)

fun LocalTime.copy(hour: Int = this.hour, minute: Int = this.minute, second: Int = this.second, nanosecond: Int = this.nanosecond) =
    LocalTime(hour, minute, second, nanosecond)

expect fun getDateTimeAdapter(): DateTimeAdapter
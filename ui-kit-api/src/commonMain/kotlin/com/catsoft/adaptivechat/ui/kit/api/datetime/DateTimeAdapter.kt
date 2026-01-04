package com.catsoft.adaptivechat.ui.kit.api.datetime

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime

interface DateTimeAdapter {
    fun formatLocalDateTime(dateTime: LocalDateTime, formatStyle: FormatStyle): String
    fun formatLocalTime(time: LocalTime, formatStyle: FormatStyle): String
    fun formatLocalDate(date: LocalDate, formatStyle: FormatStyle): String
}


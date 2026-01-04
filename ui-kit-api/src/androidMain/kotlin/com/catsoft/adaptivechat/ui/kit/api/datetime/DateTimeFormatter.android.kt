package com.catsoft.adaptivechat.ui.kit.api.datetime

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.toJavaLocalDate
import kotlinx.datetime.toJavaLocalDateTime
import kotlinx.datetime.toJavaLocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle as AndroidFormatStyle


actual fun getDateTimeAdapter(): DateTimeAdapter = AndroidDateTimeAdapter()

class AndroidDateTimeAdapter : DateTimeAdapter {
    override fun formatLocalDateTime(dateTime: LocalDateTime, formatStyle: FormatStyle): String {
        val javaLocalDateTime = dateTime.toJavaLocalDateTime()
        val zonedDateTime = javaLocalDateTime.atZone(ZoneId.systemDefault())
        val formatter = DateTimeFormatter.ofLocalizedDateTime(formatStyle.toJavaFormatStyle(), formatStyle.toJavaFormatStyle())
        return zonedDateTime.format(formatter)
    }

    override fun formatLocalTime(time: LocalTime, formatStyle: FormatStyle): String = time.toJavaLocalTime().format(
        DateTimeFormatter.ofLocalizedTime(formatStyle.toJavaFormatStyle())
    )

    override fun formatLocalDate(date: LocalDate, formatStyle: FormatStyle): String = date.toJavaLocalDate().format(
        DateTimeFormatter.ofLocalizedDate(formatStyle.toJavaFormatStyle())
    )
}

fun FormatStyle.toJavaFormatStyle(): AndroidFormatStyle = when (this) {
    FormatStyle.SHORT -> AndroidFormatStyle.SHORT
    FormatStyle.MEDIUM -> AndroidFormatStyle.MEDIUM
    FormatStyle.LONG -> AndroidFormatStyle.LONG
}
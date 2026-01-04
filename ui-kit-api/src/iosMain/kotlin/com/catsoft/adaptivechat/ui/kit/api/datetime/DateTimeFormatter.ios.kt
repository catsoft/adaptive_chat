package com.catsoft.adaptivechat.ui.kit.api.datetime

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.toNSDate
import platform.Foundation.NSDateFormatter
import platform.Foundation.NSDateFormatterLongStyle
import platform.Foundation.NSDateFormatterMediumStyle
import platform.Foundation.NSDateFormatterNoStyle
import platform.Foundation.NSDateFormatterShortStyle


actual fun getDateTimeAdapter(): DateTimeAdapter = IosDateTimeAdapter()

class IosDateTimeAdapter : DateTimeAdapter {
    override fun formatLocalDateTime(dateTime: LocalDateTime, formatStyle: FormatStyle): String {
        return formatStyle.toNSFormatter(DateTimeComponent.DATE_AND_TIME)
            .stringFromDate(dateTime.toZonedInstant().toNSDate())
    }

    override fun formatLocalTime(time: LocalTime, formatStyle: FormatStyle): String {
        return formatStyle.toNSFormatter(DateTimeComponent.TIME_ONLY)
            .stringFromDate(time.toZonedLocalTime().toNSDate())
    }

    override fun formatLocalDate(date: LocalDate, formatStyle: FormatStyle): String {
        return formatStyle.toNSFormatter(DateTimeComponent.DATE_ONLY)
            .stringFromDate(date.toZonedLocalDate().toNSDate())
    }
}

fun FormatStyle.toIosFormatStyle() = when (this) {
    FormatStyle.SHORT -> NSDateFormatterShortStyle
    FormatStyle.MEDIUM -> NSDateFormatterMediumStyle
    FormatStyle.LONG -> NSDateFormatterLongStyle
}


enum class DateTimeComponent {
    DATE_ONLY, TIME_ONLY, DATE_AND_TIME
}

fun FormatStyle.toNSFormatter(component: DateTimeComponent = DateTimeComponent.DATE_AND_TIME): NSDateFormatter {
    return NSDateFormatter().apply {
        val iosStyle = this@toNSFormatter.toIosFormatStyle()
        when (component) {
            DateTimeComponent.DATE_ONLY -> {
                dateStyle = iosStyle
                timeStyle = NSDateFormatterNoStyle
            }

            DateTimeComponent.TIME_ONLY -> {
                dateStyle = NSDateFormatterNoStyle
                timeStyle = iosStyle
            }

            DateTimeComponent.DATE_AND_TIME -> {
                dateStyle = iosStyle
                timeStyle = iosStyle
            }
        }
    }
}
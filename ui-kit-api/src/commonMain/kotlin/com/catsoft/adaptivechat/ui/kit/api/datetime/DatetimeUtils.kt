package com.catsoft.adaptivechat.ui.kit.api.datetime

import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock
import kotlin.time.Instant


fun currentDateTime(): LocalDateTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
fun currentDate(): LocalDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
fun currentTime(): LocalTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).time


fun Instant.toZonedLocalDateTime(timeZone: TimeZone = TimeZone.currentSystemDefault()): LocalDateTime = this.toLocalDateTime(timeZone)

fun Instant.toUtcLocalDateTime(): LocalDateTime = this.toLocalDateTime(TimeZone.UTC)

fun LocalDateTime.toUtcInstant(): Instant = this.toInstant(TimeZone.UTC)

fun LocalDateTime.toZonedInstant(timeZone: TimeZone = TimeZone.currentSystemDefault()): Instant = this.toInstant(timeZone)

fun LocalDate.isToday(): Boolean = this == currentDate()

fun Instant.toZonedLocalDate(timeZone: TimeZone = TimeZone.currentSystemDefault()): LocalDate = this.toLocalDateTime(timeZone).date

fun Instant.toUtcLocalDate(): LocalDate = this.toLocalDateTime(TimeZone.UTC).date

fun LocalDateTime.toUtcLocalDate(): Instant = this.toInstant(TimeZone.UTC)

fun LocalDate.toZonedLocalDate(timeZone: TimeZone = TimeZone.currentSystemDefault()): Instant = LocalDateTime(this, LocalTime(0, 0)).toInstant(timeZone)


fun Instant.toZonedLocalTime(timeZone: TimeZone = TimeZone.currentSystemDefault()): LocalTime = this.toLocalDateTime(timeZone).time

fun Instant.toUtcLocalTime(): LocalTime = this.toLocalDateTime(TimeZone.UTC).time

fun LocalDateTime.toUtcLocalTime(): Instant = this.toInstant(TimeZone.UTC)

fun LocalTime.toZonedLocalTime(timeZone: TimeZone = TimeZone.currentSystemDefault()): Instant = LocalDateTime(LocalDate(1970, 1, 1), this).toInstant(timeZone)

fun LocalDate.toEpochMillis() = this.atStartOfDayIn(TimeZone.UTC).toEpochMilliseconds()


fun Long.toLocalDate(): LocalDate = Instant.fromEpochMilliseconds(this).toLocalDateTime(TimeZone.UTC).date



fun LocalDateTime.fromUtcToLocal(targetTimeZone: TimeZone = TimeZone.currentSystemDefault()): LocalDateTime = this.toInstant(TimeZone.UTC).toLocalDateTime(targetTimeZone)

fun LocalDateTime.fromLocalToUtc(sourceTimeZone: TimeZone = TimeZone.currentSystemDefault()): LocalDateTime = this.toInstant(sourceTimeZone).toLocalDateTime(TimeZone.UTC)

fun LocalDate.startOfDayDateTime(timeZone: TimeZone): LocalDateTime =
    LocalDateTime(this, LocalTime(0, 0, 0)).toInstant(timeZone).toLocalDateTime(TimeZone.UTC)

fun LocalDate.endOfDayDateTime(timeZone: TimeZone): LocalDateTime =
    LocalDateTime(this, LocalTime(23, 59, 59)).toInstant(timeZone).toLocalDateTime(TimeZone.UTC)


fun LocalDateTime.plus(value: Long, unit: DateTimeUnit.TimeBased): LocalDateTime {
    val timeZone = TimeZone.currentSystemDefault()
    return this.toInstant(timeZone)
        .plus(value, unit)
        .toLocalDateTime(timeZone)
}
fun LocalDateTime.minus(value: Long, unit: DateTimeUnit.TimeBased): LocalDateTime {
    val timeZone = TimeZone.currentSystemDefault()
    return this.toInstant(timeZone)
        .minus(value, unit)
        .toLocalDateTime(timeZone)
}
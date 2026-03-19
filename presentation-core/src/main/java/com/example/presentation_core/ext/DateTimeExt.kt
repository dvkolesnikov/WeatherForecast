package com.example.presentation_core.ext

import androidx.compose.ui.text.intl.Locale
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Date

fun Int.toTimeHHmm(timeZone: ZoneId): String {
    return ZonedDateTime.ofInstant(
        Instant.ofEpochSecond(this.toLong()),
        timeZone,
    ).format(
        DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)
    )
}

fun Int.toShortDate(timeZone: ZoneId): String {
    return ZonedDateTime.ofInstant(
        Instant.ofEpochSecond(this.toLong()),
        timeZone,
    ).format(
        DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)
    )

}

fun Int.toDateTime(timeZone: ZoneId): String {
    return ZonedDateTime.ofInstant(
        Instant.ofEpochSecond(this.toLong()),
        timeZone,
    ).format(
        DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
    )
}
package com.example.presentation_core.ext

import androidx.compose.ui.text.intl.Locale
import java.text.SimpleDateFormat
import java.util.Date

fun Int.toTimeHHmm(): String {
    return Date(this * 1000L).let {
        SimpleDateFormat("HH:mm", Locale.current.platformLocale).format(it)
    }
}

fun Int.toShortDate(): String {
    return Date(this * 1000L).let {
        SimpleDateFormat("dd MMM", Locale.current.platformLocale).format(it)
    }
}

fun Int.toDateTime(): String {
    return Date(this * 1000L).let {
        SimpleDateFormat("dd MMM yyyy HH:mm", Locale.current.platformLocale).format(it)
    }
}
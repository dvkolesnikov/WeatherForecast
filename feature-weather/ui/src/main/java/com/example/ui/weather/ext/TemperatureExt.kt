package com.example.ui.weather.ext

fun Float.formatTemperature(unit: String): String {
    return "%.1f".format(this) + unit
}
package com.example.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherResponse(
    @SerialName("lat")
    val latitude: Double,
    @SerialName("lon")
    val longitude: Double,
    @SerialName("timezone")
    val timeZone: String,
    @SerialName("timezone_offset")
    val utcOffsetSeconds: Int,
    @SerialName("current")
    val currentWeather: CurrentWeatherDto?,
    @SerialName("hourly")
    val hourly: List<HourlyWeatherDto>?,
    @SerialName("daily")
    val daily: List<DailyWeatherDto>?,
)

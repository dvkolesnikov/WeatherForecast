package com.example.domain.weather.model

import java.time.ZoneId

data class Weather(
    val currentWeather: CurrentWeather?,
    val hourlyWeather: List<HourlyWeather>,
    val dailyWeather: List<DailyWeather>,
    val timeZone: ZoneId,
    val temperatureUnit: String = "°C" // TODO it will be probably changed later,
)

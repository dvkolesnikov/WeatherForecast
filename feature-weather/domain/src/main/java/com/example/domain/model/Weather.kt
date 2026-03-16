package com.example.domain.model

data class Weather(
    val currentWeather: CurrentWeather?,
    val hourlyWeather: List<HourlyWeather>,
    val temperatureUnit: String = "°C" // TODO it will be probably changed later,
)

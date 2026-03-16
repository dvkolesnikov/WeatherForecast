package com.example.domain.model

data class Weather(
    val currentWeather: CurrentWeather?,
    val hourlyWeather: List<HourlyWeather>,
)

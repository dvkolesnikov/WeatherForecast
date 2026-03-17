package com.example.domain.weather.model

data class Weather(
    val currentWeather: CurrentWeather?,
    val hourlyWeather: List<HourlyWeather>,
    val dailyWeather: List<DailyWeather>,
    val temperatureUnit: String = "°C" // TODO it will be probably changed later,
)

package com.example.domain.model

data class CurrentWeather(
    val timeStamp: Int,
    val temperature: Float,
    val sunriseTimeStamp: Int,
    val sunsetTimeStamp: Int,
    val temperatureFeelsLike: Float,
    val pressure: Int,
    val humidity: Int,
    val windSpeed: Float,
)

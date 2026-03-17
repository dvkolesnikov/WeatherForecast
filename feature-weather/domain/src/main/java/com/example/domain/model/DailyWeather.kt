package com.example.domain.model

data class DailyWeather(
    val timeStamp: Int,
    val sunriseTimeStamp: Int,
    val sunsetTimeStamp: Int,
    val weatherIconUrls: List<String>,
    val precipitationProbability: Float,
    val minTemperature: Float,
    val maxTemperature: Float,
)

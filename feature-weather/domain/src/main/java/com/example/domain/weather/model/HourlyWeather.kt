package com.example.domain.weather.model

data class HourlyWeather(
    val timeStamp: Int,
    val temperature: Float,
    val temperatureFeelsLike: Float,
    val pressure: Int,
    val humidity: Int,
    val dewPointTemperature: Float,
    val uvIndex: Float,
    val cloudinessPercentage: Int,
    val visibilityDistance: Int,
    val windSpeed: Float,
    val windDirection: Int,
    val windGustsSpeed: Float,
    val precipitationProbability: Float,
    val weatherIconUrls: List<String>,
)

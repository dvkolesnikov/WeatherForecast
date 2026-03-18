package com.example.data.weather.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrentWeatherDto(
    @SerialName("dt")
    val currentTimeStamp: Int,
    @SerialName("sunrise")
    val sunriseTimeStamp: Int,
    @SerialName("sunset")
    val sunsetTimeStamp: Int,
    @SerialName("temp")
    val temperature: Float,
    @SerialName("feels_like")
    val temperatureFeelsLike: Float,
    @SerialName("pressure")
    val pressure: Int,
    @SerialName("humidity")
    val humidity: Int,
    @SerialName("dew_point")
    val dewPointTemperature: Float,
    @SerialName("uvi")
    val uvIndex: Float,
    @SerialName("clouds")
    val cloudinessPercentage: Int,
    @SerialName("visibility")
    val visibilityDistance: Int,
    @SerialName("wind_speed")
    val windSpeed: Float,
    @SerialName("wind_deg")
    val windDirection: Int,
    @SerialName("wind_gust")
    val windGustsSpeed: Float?,
    @SerialName("weather")
    val weatherConditions: List<WeatherConditionDto>
)

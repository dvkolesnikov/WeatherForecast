package com.example.data.weather.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HourlyWeatherDto(
    @SerialName("dt") // Time of the forecasted data, Unix, UTC
    val timeStamp: Int,
    @SerialName("temp") // Temperature. Units – default: kelvin, metric: Celsius, imperial: Fahrenheit. How to change units used
    val temperature: Float,
    @SerialName("feels_like") // Temperature. This accounts for the human perception of weather. Units – default: kelvin, metric: Celsius, imperial: Fahrenheit.
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
    val windGustsSpeed: Float,
    @SerialName("weather")
    val weatherConditions: List<WeatherConditionDto>,
    @SerialName("pop") // Probability of precipitation. The values of the parameter vary between 0 and 1, where 0 is equal to 0%, 1 is equal to 100%
    val precipitationProbability: Float,
)
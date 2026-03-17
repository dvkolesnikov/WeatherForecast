package com.example.data.weather.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DailyWeatherDto(
    @SerialName("dt")
    val timeStamp: Int,
    @SerialName("sunrise")
    val sunriseTimeStamp: Int,
    @SerialName("sunset")
    val sunsetTimeStamp: Int,
    @SerialName("moonrise")
    val moonriseTimeStamp: Int,
    @SerialName("moonset")
    val moonsetTimeStamp: Int,
    @SerialName("moon_phase")
    val moonPhase: Float,
    @SerialName("summary")
    val summaryString: String,
    @SerialName("humidity")
    val humidity: Int,
    @SerialName("dew_point")
    val dewPointTemperature: Float,
    @SerialName("uvi")
    val uvIndex: Float,
    @SerialName("clouds")
    val cloudinessPercentage: Int,
    @SerialName("wind_speed")
    val windSpeed: Float,
    @SerialName("wind_deg")
    val windDirection: Int,
    @SerialName("wind_gust")
    val windGustsSpeed: Float,
    @SerialName("weather")
    val weatherConditions: List<WeatherConditionDto>?,
    @SerialName("pop")
    val precipitationProbability: Float,
    @SerialName("temp")
    val dailyTemps: DailyTempsDto,
    @SerialName("feels_like")
    val dailyTempsFeeling: DailyTempsFeelingDto,
)

@Serializable
data class DailyTempsDto(
    @SerialName("day")
    val dayTemperature: Float,
    @SerialName("min")
    val minTemperature: Float,
    @SerialName("max")
    val maxTemperature: Float,
    @SerialName("night")
    val nightTemperature: Float,
    @SerialName("eve")
    val eveningTemperature: Float,
    @SerialName("morn")
    val morningTemperature: Float,
)

@Serializable
data class DailyTempsFeelingDto(
    @SerialName("day")
    val dayTemperature: Float,
    @SerialName("night")
    val nightTemperature: Float,
    @SerialName("eve")
    val eveningTemperature: Float,
    @SerialName("morn")
    val morningTemperature: Float,
)

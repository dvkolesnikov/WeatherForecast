package com.example.data.weather.mapper

import com.example.data.weather.dto.WeatherResponse
import com.example.domain.weather.model.Weather
import java.time.ZoneId

fun WeatherResponse.mapToDomain(baseUrl: String) = Weather(
    currentWeather = currentWeather?.mapToDomain(baseUrl),
    hourlyWeather = hourly?.map { it.mapToDomain(baseUrl) } ?: emptyList(),
    dailyWeather = daily?.map { it.mapToDomain(baseUrl) } ?: emptyList(),
    timeZone = runCatching { ZoneId.of(timeZone) }.getOrNull() ?: ZoneId.systemDefault()
)

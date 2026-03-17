package com.example.data.mapper

import com.example.data.dto.WeatherResponse
import com.example.domain.model.Weather

fun WeatherResponse.mapToDomain(baseUrl: String) = Weather(
    currentWeather = currentWeather?.mapToDomain(baseUrl),
    hourlyWeather = hourly?.map { it.mapToDomain(baseUrl) } ?: emptyList(),
)

package com.example.data.mapper

import com.example.data.dto.WeatherResponse
import com.example.domain.model.Weather

fun WeatherResponse.mapToDomain() = Weather(
    currentWeather = currentWeather?.mapToDomain(),
    hourlyWeather = hourly?.map { it.mapToDomain() } ?: emptyList(),
)

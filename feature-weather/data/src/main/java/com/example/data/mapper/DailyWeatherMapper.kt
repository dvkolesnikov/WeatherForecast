package com.example.data.mapper

import com.example.data.dto.DailyWeatherDto
import com.example.domain.model.DailyWeather

fun DailyWeatherDto.mapToDomain(baseUrl: String) = DailyWeather(
    timeStamp = timeStamp,
    sunriseTimeStamp = sunriseTimeStamp,
    sunsetTimeStamp = sunsetTimeStamp,
    minTemperature = dailyTemps.minTemperature,
    maxTemperature = dailyTemps.maxTemperature,
    precipitationProbability = precipitationProbability,
    weatherIconUrls = weatherConditions?.map { it.toIconUrl(baseUrl) } ?: emptyList(),
)

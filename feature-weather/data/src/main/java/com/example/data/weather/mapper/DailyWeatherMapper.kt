package com.example.data.weather.mapper

import com.example.data.weather.dto.DailyWeatherDto
import com.example.domain.weather.model.DailyWeather

fun DailyWeatherDto.mapToDomain(baseUrl: String) = DailyWeather(
    timeStamp = timeStamp,
    sunriseTimeStamp = sunriseTimeStamp,
    sunsetTimeStamp = sunsetTimeStamp,
    minTemperature = dailyTemps.minTemperature,
    maxTemperature = dailyTemps.maxTemperature,
    precipitationProbability = precipitationProbability,
    weatherIconUrls = weatherConditions?.map { it.toIconUrl(baseUrl) } ?: emptyList(),
)

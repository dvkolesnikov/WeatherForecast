package com.example.data.weather.mapper

import com.example.data.weather.dto.HourlyWeatherDto
import com.example.domain.weather.model.HourlyWeather

fun HourlyWeatherDto.mapToDomain(baseUrl: String): HourlyWeather {
    return HourlyWeather(
        timeStamp = timeStamp,
        temperature = temperature,
        temperatureFeelsLike = temperatureFeelsLike,
        pressure = pressure,
        humidity = humidity,
        dewPointTemperature = dewPointTemperature,
        uvIndex = uvIndex,
        cloudinessPercentage = cloudinessPercentage,
        visibilityDistance = visibilityDistance,
        windSpeed = windSpeed,
        windDirection = windDirection,
        windGustsSpeed = windGustsSpeed,
        precipitationProbability = precipitationProbability,
        weatherIconUrls = weatherConditions.map { it.toIconUrl(baseUrl) }
    )
}
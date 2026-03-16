package com.example.data.mapper

import com.example.data.dto.HourlyWeatherDto
import com.example.domain.model.HourlyWeather

fun HourlyWeatherDto.mapToDomain(): HourlyWeather {
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
    )
}
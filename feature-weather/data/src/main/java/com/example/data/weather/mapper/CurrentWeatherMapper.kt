package com.example.data.weather.mapper

import com.example.data.weather.dto.CurrentWeatherDto
import com.example.domain.weather.model.CurrentWeather

fun CurrentWeatherDto.mapToDomain(baseUrl: String): CurrentWeather {
    return CurrentWeather(
        temperature = temperature,
        timeStamp = currentTimeStamp,
        sunriseTimeStamp = sunriseTimeStamp,
        sunsetTimeStamp = sunsetTimeStamp,
        temperatureFeelsLike = temperatureFeelsLike,
        pressure = pressure,
        humidity = humidity,
        windSpeed = windSpeed,
        weatherIconUrls = weatherConditions.map { it.toIconUrl(baseUrl) }
    )
}

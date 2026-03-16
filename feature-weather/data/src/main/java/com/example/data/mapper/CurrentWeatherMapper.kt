package com.example.data.mapper

import com.example.data.dto.CurrentWeatherDto
import com.example.domain.model.CurrentWeather

fun CurrentWeatherDto.mapToDomain(): CurrentWeather {
    return CurrentWeather(
        temperature = temperature,
        timeStamp = currentTimeStamp,
        sunriseTimeStamp = sunriseTimeStamp,
        sunsetTimeStamp = sunsetTimeStamp,
        temperatureFeelsLike = temperatureFeelsLike,
        pressure = pressure,
        humidity = humidity,
        windSpeed = windSpeed,
    )
}

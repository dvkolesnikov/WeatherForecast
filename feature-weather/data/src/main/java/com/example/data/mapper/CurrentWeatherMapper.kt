package com.example.data.mapper

import com.example.data.dto.WeatherResponse
import com.example.domain.model.CurrentWeather

fun WeatherResponse.mapToCurrentWeather(): CurrentWeather {
    return CurrentWeather(
        temperature = currentWeather.temperature,
        timeStamp = currentWeather.currentTimeStamp,
        sunriseTimeStamp = currentWeather.sunriseTimeStamp,
        sunsetTimeStamp = currentWeather.sunsetTimeStamp,
        temperatureFeelsLike = currentWeather.temperatureFeelsLike,
        pressure = currentWeather.pressure,
        humidity = currentWeather.humidity,
        windSpeed = currentWeather.windSpeed,
    )
}

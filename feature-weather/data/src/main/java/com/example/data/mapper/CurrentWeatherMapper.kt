package com.example.data.mapper

import com.example.data.dto.WeatherResponse
import com.example.domain.model.CurrentWeather

fun WeatherResponse.mapToCurrentWeather(): CurrentWeather {
    return CurrentWeather(
        temperature = currentWeather.temperature,
    )
}

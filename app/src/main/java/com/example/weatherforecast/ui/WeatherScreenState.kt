package com.example.weatherforecast.ui

import com.example.domain.model.CurrentWeather
import com.example.domain.model.HourlyWeather

sealed class WeatherScreenState {
    data object Loading : WeatherScreenState()

    data class Error(val message: String) : WeatherScreenState()

    data class Success(
        val currentWeather: CurrentWeather?,
        val hourlyWeather: List<HourlyWeather>,
        val temperatureUnit: String,
    ) : WeatherScreenState()
}

package com.example.weatherforecast.ui

import com.example.domain.model.CurrentWeather

sealed class WeatherScreenState {
    data object Loading : WeatherScreenState()

    data class Error(val message: String) : WeatherScreenState()

    data class Success(
        val currentWeather: CurrentWeather,
    ) : WeatherScreenState()
}

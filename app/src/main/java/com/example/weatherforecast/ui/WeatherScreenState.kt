package com.example.weatherforecast.ui

import com.example.domain.weather.model.CurrentWeather
import com.example.domain.weather.model.DailyWeather
import com.example.domain.weather.model.HourlyWeather

sealed class WeatherScreenState {
    data object Loading : WeatherScreenState()

    data class Error(val message: String) : WeatherScreenState()

    data class Success(
        val currentWeather: CurrentWeather?,
        val hourlyWeather: List<HourlyWeather>,
        val dailyWeather: List<DailyWeather>,
        val temperatureUnit: String,
    ) : WeatherScreenState()
}

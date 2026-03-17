package com.example.domain.weather.repository

import com.example.domain.weather.model.Weather

interface WeatherRepository {

    suspend fun loadWeather(latitude: Double, longitude: Double): Result<Weather>
}

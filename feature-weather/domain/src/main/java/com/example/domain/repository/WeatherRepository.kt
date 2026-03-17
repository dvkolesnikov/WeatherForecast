package com.example.domain.repository

import com.example.domain.model.Weather

interface WeatherRepository {

    suspend fun loadWeather(latitude: Double, longitude: Double): Result<Weather>
}

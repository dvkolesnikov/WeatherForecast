package com.example.domain.repository

import com.example.domain.model.CurrentWeather

interface WeatherRepository {

    suspend fun loadCurrentWeather(latitude: Double, longitude: Double): Result<CurrentWeather>
}

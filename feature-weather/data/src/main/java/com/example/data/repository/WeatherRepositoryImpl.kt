package com.example.data.repository

import com.example.data.api.WeatherApi
import com.example.data.mapper.mapToCurrentWeather
import com.example.domain.model.CurrentWeather
import com.example.domain.repository.WeatherRepository

class WeatherRepositoryImpl(
    private val weatherApi: WeatherApi,
) : WeatherRepository {

    override suspend fun loadWeather(
        latitude: Double,
        longitude: Double,
    ): Result<CurrentWeather> {
        return weatherApi.loadWeather(
            latitude = latitude,
            longitude = longitude,
        ).map { it.mapToCurrentWeather() }
    }
}

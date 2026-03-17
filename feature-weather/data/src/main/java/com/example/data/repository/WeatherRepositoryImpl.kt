package com.example.data.repository

import com.example.data.api.WeatherApi
import com.example.data.mapper.mapToDomain
import com.example.domain.model.Weather
import com.example.domain.repository.WeatherRepository

class WeatherRepositoryImpl(
    private val weatherApi: WeatherApi,
) : WeatherRepository {

    override suspend fun loadWeather(
        latitude: Double,
        longitude: Double,
    ): Result<Weather> {
        return weatherApi.loadWeather(
            latitude = latitude,
            longitude = longitude,
        ).map { it.mapToDomain() }
    }
}

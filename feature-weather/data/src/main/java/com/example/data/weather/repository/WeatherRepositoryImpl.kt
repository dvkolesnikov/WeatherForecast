package com.example.data.weather.repository

import com.example.data.weather.api.WeatherApi
import com.example.data.weather.mapper.mapToDomain
import com.example.domain.weather.model.Weather
import com.example.domain.weather.repository.WeatherRepository
import com.example.domain_core.model.AppConfig

class WeatherRepositoryImpl(
    private val weatherApi: WeatherApi,
    private val appConfig: AppConfig,
) : WeatherRepository {

    override suspend fun loadWeather(
        latitude: Double,
        longitude: Double,
    ): Result<Weather> {
        return weatherApi.loadWeather(
            latitude = latitude,
            longitude = longitude,
        ).map { it.mapToDomain(baseUrl = appConfig.imagesBaseUrl) }
    }
}

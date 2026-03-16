package com.example.domain.usecase

import com.example.domain.model.Weather
import com.example.domain.repository.WeatherRepository

class GetWeatherUseCase(
    private val weatherRepository: WeatherRepository,
) {

    suspend operator fun invoke(
        latitude: Double,
        longitude: Double,
    ): Result<Weather> {
        return weatherRepository.loadWeather(latitude = latitude, longitude = longitude)
    }
}

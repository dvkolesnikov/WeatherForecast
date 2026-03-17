package com.example.domain.weather.usecase

import com.example.domain.weather.model.Weather
import com.example.domain.weather.repository.WeatherRepository

class GetWeatherUseCase(
    private val weatherRepository: WeatherRepository,
) {

    suspend operator fun invoke(
        latitude: Double,
        longitude: Double,
    ): Result<Weather> {
        return weatherRepository.loadWeather(latitude = latitude, longitude = longitude)
            .map { weather ->
                weather.copy(
                    hourlyWeather = weather.hourlyWeather
                        .take(24)
                        .sortedBy { it.timeStamp },
                    dailyWeather = weather.dailyWeather
                        .take(7)
                        .sortedBy { it.timeStamp }
                )
            }
    }
}

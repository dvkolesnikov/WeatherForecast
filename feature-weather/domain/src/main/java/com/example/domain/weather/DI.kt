package com.example.domain.weather

import com.example.domain.weather.usecase.GetWeatherUseCase
import org.koin.dsl.module

val featureWeatherDomainModule = module {
    factory { GetWeatherUseCase(weatherRepository = get()) }
}

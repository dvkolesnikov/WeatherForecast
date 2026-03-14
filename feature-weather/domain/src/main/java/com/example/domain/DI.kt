package com.example.domain

import com.example.domain.usecase.GetCurrentWeatherUseCase
import org.koin.dsl.module

val featureWeatherDomainModule = module {
    factory { GetCurrentWeatherUseCase(weatherRepository = get()) }
}

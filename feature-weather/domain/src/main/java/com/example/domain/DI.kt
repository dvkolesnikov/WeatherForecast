package com.example.domain

import com.example.domain.usecase.GetWeatherUseCase
import org.koin.dsl.module

val featureWeatherDomainModule = module {
    factory { GetWeatherUseCase(weatherRepository = get()) }
}

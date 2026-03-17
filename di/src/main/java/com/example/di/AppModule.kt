package com.example.di

import com.example.core_network.coreNetworkModule
import com.example.data.weather.featureWeatherDataModule
import com.example.domain.weather.featureWeatherDomainModule
import org.koin.dsl.module

val commonModule = module {
    includes(
        coreNetworkModule,

        featureWeatherDomainModule,
        featureWeatherDataModule,
    )
}

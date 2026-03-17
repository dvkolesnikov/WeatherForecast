package com.example.di

import com.example.core_network.coreNetworkModule
import com.example.data.geocoding.featureGeocodingDataModule
import com.example.data.weather.featureWeatherDataModule
import com.example.domain.geocoding.featureGeocodingDomainModule
import com.example.domain.weather.featureWeatherDomainModule
import org.koin.dsl.module

val commonModule = module {
    includes(
        coreNetworkModule,

        featureGeocodingDomainModule,
        featureGeocodingDataModule,
        featureWeatherDomainModule,
        featureWeatherDataModule,
    )
}

package com.example.domain.geocoding

import com.example.domain.geocoding.usecase.SearchCityUseCase
import org.koin.dsl.module

val featureGeocodingDomainModule = module {
    factory { SearchCityUseCase(geocodingRepository = get()) }
}

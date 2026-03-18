package com.example.domain.geocoding

import com.example.domain.geocoding.usecase.SearchCitiesByNameUseCase
import com.example.domain.geocoding.usecase.SearchCityNameByCoordinatesUseCase
import org.koin.dsl.module

val featureGeocodingDomainModule = module {
    factory { SearchCitiesByNameUseCase(geocodingRepository = get()) }
    factory { SearchCityNameByCoordinatesUseCase(geocodingRepository = get()) }
}

package com.example.domain.geocoding.usecase

import com.example.domain.geocoding.model.CityLocation
import com.example.domain.geocoding.repository.GeocodingRepository

class SearchCityNameByCoordinatesUseCase(
    private val geocodingRepository: GeocodingRepository,
) {

    suspend operator fun invoke(
        latitude: Double,
        longitude: Double,
    ): Result<List<CityLocation>> {
        return geocodingRepository.searchCitiesByCoordinates(
            latitude = latitude,
            longitude = longitude,
        )
    }
}

package com.example.domain.geocoding.usecase

import com.example.domain.geocoding.model.CityLocation
import com.example.domain.geocoding.repository.GeocodingRepository

class SearchCitiesByNameUseCase(
    private val geocodingRepository: GeocodingRepository,
) {

    suspend operator fun invoke(
        query: String,
        limit: Int = 5,
    ): Result<List<CityLocation>> {
        if (query.isBlank()) {
            return Result.success(emptyList())
        }

        return geocodingRepository.searchCitiesByName(
            query = query.trim(),
            limit = limit,
        )
    }
}

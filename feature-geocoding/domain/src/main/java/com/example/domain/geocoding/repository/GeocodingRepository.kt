package com.example.domain.geocoding.repository

import com.example.domain.geocoding.model.CityLocation

interface GeocodingRepository {

    suspend fun searchCitiesByName(
        query: String,
        limit: Int = 5,
    ): Result<List<CityLocation>>

    suspend fun searchCitiesByCoordinates(
        latitude: Double,
        longitude: Double,
        limit: Int = 1,
    ): Result<List<CityLocation>>
}

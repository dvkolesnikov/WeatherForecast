package com.example.domain.geocoding.repository

import com.example.domain.geocoding.model.CityLocation

interface GeocodingRepository {

    suspend fun searchCities(
        query: String,
        limit: Int = 5,
    ): Result<List<CityLocation>>
}

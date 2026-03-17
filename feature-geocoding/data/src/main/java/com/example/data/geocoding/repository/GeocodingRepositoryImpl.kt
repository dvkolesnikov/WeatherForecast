package com.example.data.geocoding.repository

import com.example.data.geocoding.api.GeocodingApi
import com.example.data.geocoding.mapper.mapToDomain
import com.example.domain.geocoding.model.CityLocation
import com.example.domain.geocoding.repository.GeocodingRepository

class GeocodingRepositoryImpl(
    private val geocodingApi: GeocodingApi,
) : GeocodingRepository {

    override suspend fun searchCities(
        query: String,
        limit: Int,
    ): Result<List<CityLocation>> {
        return geocodingApi.searchCities(
            query = query,
            limit = limit,
        ).map { cities -> cities.map { it.mapToDomain() } }
    }
}

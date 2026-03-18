package com.example.data.geocoding.api

import com.example.data.geocoding.dto.GeocodingLocationDto
import retrofit2.http.GET
import retrofit2.http.Query

interface GeocodingApi {

    @GET("/geo/1.0/direct")
    suspend fun searchCitiesByName(
        @Query("q") query: String,
        @Query("limit") limit: Int = 5,
    ): Result<List<GeocodingLocationDto>>

    @GET("/geo/1.0/reverse")
    suspend fun searchCityByCoordinates(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("limit") limit: Int = 1,
    ): Result<List<GeocodingLocationDto>>
}

package com.example.data.geocoding.mapper

import com.example.data.geocoding.dto.GeocodingLocationDto
import com.example.domain.geocoding.model.CityLocation

fun GeocodingLocationDto.mapToDomain() = CityLocation(
    name = name,
    latitude = latitude,
    longitude = longitude,
    country = country,
    state = state,
)

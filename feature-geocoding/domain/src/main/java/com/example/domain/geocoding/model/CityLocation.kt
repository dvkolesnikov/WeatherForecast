package com.example.domain.geocoding.model

data class CityLocation(
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val country: String,
    val state: String? = null,
)

package com.example.data.geocoding.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GeocodingLocationDto(
    @SerialName("name")
    val name: String,
    @SerialName("lat")
    val latitude: Double,
    @SerialName("lon")
    val longitude: Double,
    @SerialName("country")
    val country: String,
    @SerialName("state")
    val state: String? = null,
)

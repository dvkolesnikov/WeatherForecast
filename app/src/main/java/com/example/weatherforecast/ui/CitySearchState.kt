package com.example.weatherforecast.ui

import com.example.domain.geocoding.model.CityLocation

data class CitySearchState(
    val query: String = "",
    val cities: List<CityLocation> = emptyList(),
    val selectedCity: CityLocation? = null,
)

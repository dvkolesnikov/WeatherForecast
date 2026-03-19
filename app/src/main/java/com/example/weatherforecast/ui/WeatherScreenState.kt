package com.example.weatherforecast.ui

import com.example.domain.geocoding.model.CityLocation
import com.example.domain.weather.model.CurrentWeather
import com.example.domain.weather.model.DailyWeather
import com.example.domain.weather.model.HourlyWeather
import java.time.ZoneId
import java.util.TimeZone

sealed class WeatherScreenState(
    open val citySearchState: CitySearchState,
) {
    data class Loading(
        override val citySearchState: CitySearchState = CitySearchState(),
    ) : WeatherScreenState(citySearchState)

    data class Error(
        val message: String,
        override val citySearchState: CitySearchState = CitySearchState(),
    ) : WeatherScreenState(citySearchState)

    data class Success(
        val currentWeather: CurrentWeather?,
        val hourlyWeather: List<HourlyWeather>,
        val dailyWeather: List<DailyWeather>,
        val temperatureUnit: String,
        val timeZone: ZoneId,
        override val citySearchState: CitySearchState = CitySearchState(),
    ) : WeatherScreenState(citySearchState)
}

data class CitySearchState(
    val query: String = "",
    val cities: List<CityLocation> = emptyList(),
    val selectedCity: CityLocation? = null,
)


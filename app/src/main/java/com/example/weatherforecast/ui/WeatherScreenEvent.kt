package com.example.weatherforecast.ui

sealed class WeatherScreenEvent {

    data object RequestLocationPermission : WeatherScreenEvent()
}

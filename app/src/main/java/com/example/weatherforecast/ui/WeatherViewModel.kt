package com.example.weatherforecast.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.weather.usecase.GetWeatherUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val getWeatherUseCase: GetWeatherUseCase,
) : ViewModel() {

    private val _screenState = MutableStateFlow<WeatherScreenState>(WeatherScreenState.Loading)
    val screenState = _screenState.asStateFlow()

    init {
        loadWeather()
    }

    private fun loadWeather() {
        viewModelScope.launch {
            _screenState.value = WeatherScreenState.Loading
            getWeatherUseCase(
                latitude = 39.47,
                longitude = 0.37
            ).fold( // TODO get rid of hardcoded coordinates
                onSuccess = {
                    _screenState.value = WeatherScreenState.Success(
                        currentWeather = it.currentWeather,
                        temperatureUnit = it.temperatureUnit,
                        hourlyWeather = it.hourlyWeather,
                        dailyWeather = it.dailyWeather,
                    )
                },
                onFailure = {
                    _screenState.value =
                        WeatherScreenState.Error(it.message ?: it.toString()) // TODO improve
                }
            )
        }
    }
}

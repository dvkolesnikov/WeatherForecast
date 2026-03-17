package com.example.weatherforecast.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.geocoding.model.CityLocation
import com.example.domain.geocoding.usecase.SearchCityUseCase
import com.example.domain.weather.usecase.GetWeatherUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val getWeatherUseCase: GetWeatherUseCase,
    private val searchCityUseCase: SearchCityUseCase,
) : ViewModel() {

    private val _screenState = MutableStateFlow<WeatherScreenState>(WeatherScreenState.Loading())
    val screenState = _screenState.asStateFlow()

    private var searchJob: Job? = null
    private var weatherJob: Job? = null

    init {
        loadWeather(
            latitude = DEFAULT_LATITUDE,
            longitude = DEFAULT_LONGITUDE,
        )
    }

    fun onQueryChange(query: String) {
        updateCitySearchState { state ->
            state.copy(query = query, selectedCity = null)
        }
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            searchCityUseCase(query = query).fold(
                onSuccess = { cities ->
                    updateCitySearchState { state -> state.copy(cities = cities) }
                },
                onFailure = {
                    updateCitySearchState { state -> state.copy(cities = emptyList()) }
                },
            )
        }
    }

    fun onCitySelected(city: CityLocation) {
        updateCitySearchState { state ->
            state.copy(
                query = city.name,
                cities = emptyList(),
                selectedCity = city,
            )
        }
        loadWeather(
            latitude = city.latitude,
            longitude = city.longitude,
        )
    }

    private fun loadWeather(
        latitude: Double,
        longitude: Double,
    ) {
        weatherJob?.cancel()
        val citySearchState = _screenState.value.citySearchState
        weatherJob = viewModelScope.launch {
            _screenState.value = WeatherScreenState.Loading(citySearchState = citySearchState)
            getWeatherUseCase(
                latitude = latitude,
                longitude = longitude,
            ).fold(
                onSuccess = {
                    _screenState.value = WeatherScreenState.Success(
                        currentWeather = it.currentWeather,
                        temperatureUnit = it.temperatureUnit,
                        hourlyWeather = it.hourlyWeather,
                        dailyWeather = it.dailyWeather,
                        citySearchState = citySearchState,
                    )
                },
                onFailure = {
                    _screenState.value =
                        WeatherScreenState.Error(
                            message = it.message ?: it.toString(),
                            citySearchState = citySearchState,
                        )
                }
            )
        }
    }

    private fun updateCitySearchState(
        transform: (CitySearchState) -> CitySearchState,
    ) {
        _screenState.update { state ->
            when (state) {
                is WeatherScreenState.Loading -> state.copy(
                    citySearchState = transform(state.citySearchState),
                )

                is WeatherScreenState.Error -> state.copy(
                    citySearchState = transform(state.citySearchState),
                )

                is WeatherScreenState.Success -> state.copy(
                    citySearchState = transform(state.citySearchState),
                )
            }
        }
    }

    private companion object {
        const val DEFAULT_LATITUDE = 39.47
        const val DEFAULT_LONGITUDE = 0.37
    }
}

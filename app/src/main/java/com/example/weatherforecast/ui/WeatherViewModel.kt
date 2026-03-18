package com.example.weatherforecast.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.geocoding.model.CityLocation
import com.example.domain.geocoding.usecase.SearchCitiesByNameUseCase
import com.example.domain.geocoding.usecase.SearchCityNameByCoordinatesUseCase
import com.example.domain.weather.usecase.GetWeatherUseCase
import com.example.domain_core.model.Location
import com.example.domain_core.usecase.GetCurrentLocationUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val getWeatherUseCase: GetWeatherUseCase,
    private val searchCitiesByNameUseCase: SearchCitiesByNameUseCase,
    private val searchCityNameByCoordinatesUseCase: SearchCityNameByCoordinatesUseCase,
    private val getCurrentLocationUseCase: GetCurrentLocationUseCase,
) : ViewModel() {

    private val _screenState = MutableStateFlow<WeatherScreenState>(WeatherScreenState.Loading())
    val screenState = _screenState.asStateFlow()

    private val _screenEvents = MutableSharedFlow<WeatherScreenEvent>()
    val screenEvents = _screenEvents.asSharedFlow()

    private var searchJob: Job? = null
    private var weatherJob: Job? = null

    init {
        loadWeatherForCurrentLocation()
    }

    fun onQueryChange(query: String) {
        updateCitySearchState { state ->
            state.copy(query = query, selectedCity = null)
        }
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            searchCitiesByNameUseCase(query = query).fold(
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

    fun handleLocationPermissionGranted() {
        loadWeatherForCurrentLocation()
    }

    fun handleCurrentLocationClicked() {
        loadWeatherForCurrentLocation()
    }

    private fun loadWeatherForCurrentLocation() {
        viewModelScope.launch {
            getCurrentLocationUseCase().fold(
                onSuccess = {
                    loadWeather(latitude = it.latitude, longitude = it.longitude)
                    loadCityName(it)
                },
                onFailure = { error ->
                    if (error is SecurityException) {
                        _screenEvents.emit(WeatherScreenEvent.RequestLocationPermission)
                    } else {
                        showError(error)
                    }
                }
            )
        }
    }

    private fun loadCityName(location: Location) {
        viewModelScope.launch {
            searchCityNameByCoordinatesUseCase(
                latitude = location.latitude,
                longitude = location.longitude
            ).fold(
                onSuccess = { cityList ->
                    cityList.firstOrNull()?.let {
                        onCitySelected(it)
                    }
                },
                onFailure = { showError(it) }
            )
        }
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
                    showError(it)
                }
            )
        }
    }

    private fun showError(error: Throwable) {
        _screenState.update {
            WeatherScreenState.Error(
                message = error.message ?: error.toString(),
                citySearchState = it.citySearchState,
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
}

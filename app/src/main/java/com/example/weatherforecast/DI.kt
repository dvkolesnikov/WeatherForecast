package com.example.weatherforecast

import com.example.weatherforecast.ui.WeatherViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { WeatherViewModel(getWeatherUseCase = get()) }
}

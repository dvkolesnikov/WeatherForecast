package com.example.weatherforecast

import com.example.domain_core.model.AppConfig
import com.example.weatherforecast.ui.WeatherViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single {
        AppConfig(
            apiBaseUrl = BuildConfig.API_BASE_URL,
            imagesBaseUrl = BuildConfig.IMAGES_BASE_URL,
        )
    }
}

val viewModelModule = module {
    viewModel {
        WeatherViewModel(
            getWeatherUseCase = get(),
            searchCityUseCase = get(),
        )
    }
}


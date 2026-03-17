package com.example.data.weather

import com.example.data.weather.api.WeatherApi
import com.example.data.weather.repository.WeatherRepositoryImpl
import com.example.domain.weather.repository.WeatherRepository
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create

val featureWeatherDataModule = module {
    factory { provideWeatherApi(retrofit = get()) }
    single<WeatherRepository> {
        WeatherRepositoryImpl(
            weatherApi = get(),
            appConfig = get(),
        )
    }
}

private fun provideWeatherApi(retrofit: Retrofit) = retrofit.create<WeatherApi>()

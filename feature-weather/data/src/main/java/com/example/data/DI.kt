package com.example.data

import com.example.data.api.WeatherApi
import com.example.data.repository.WeatherRepositoryImpl
import com.example.domain.repository.WeatherRepository
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create

val featureWeatherDataModule = module {
    factory { provideWeatherApi(retrofit = get()) }
    single<WeatherRepository> {
        WeatherRepositoryImpl(
            weatherApi = get()
        )
    }
}

private fun provideWeatherApi(retrofit: Retrofit) = retrofit.create<WeatherApi>()

package com.example.data.geocoding

import com.example.data.geocoding.api.GeocodingApi
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create

val featureGeocodingDataModule = module {
    factory { provideGeocodingApi(retrofit = get()) }
}

private fun provideGeocodingApi(retrofit: Retrofit) = retrofit.create<GeocodingApi>()

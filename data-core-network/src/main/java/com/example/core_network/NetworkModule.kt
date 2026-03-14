package com.example.core_network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit

val coreNetworkModule = module {
    single { provideOkHttpClient() }
    single { provideJson() }
    single { provideRetrofit(client = get(), networkJson = get()) }
}

private fun provideOkHttpClient() = OkHttpClient.Builder()
    .addInterceptor(HttpLoggingInterceptor())
    .build()

private fun provideJson() = Json {
    ignoreUnknownKeys = true
}

private fun provideRetrofit(client: OkHttpClient, networkJson: Json) = Retrofit.Builder()
    .client(client)
    .baseUrl("https://api.openweathermap.org") // TODO move to app config
    .addConverterFactory(networkJson.asConverterFactory("application/json".toMediaType()))
    .build()
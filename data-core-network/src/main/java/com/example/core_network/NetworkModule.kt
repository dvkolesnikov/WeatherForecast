package com.example.core_network

import com.example.core_network.interceptor.AuthInterceptor
import com.skydoves.retrofit.adapters.result.ResultCallAdapterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

val coreNetworkModule = module {
    single { provideOkHttpClient() }
    factory { provideJson() }
    single { provideRetrofit(client = get(), json = get()) }
}

private fun provideOkHttpClient() = OkHttpClient.Builder()
    .addInterceptor(AuthInterceptor())
    // Logging interceptor must added last to make sure it reflects requests and responses as they are
    .addInterceptor(HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY // TODO disable in release build
    })
    .build()

private fun provideJson() = Json {
    explicitNulls = false
    ignoreUnknownKeys = true
}

private fun provideRetrofit(client: OkHttpClient, json: Json) = Retrofit.Builder()
    .client(client)
    .baseUrl("https://api.openweathermap.org") // TODO move to app config
    .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
    .addCallAdapterFactory(ResultCallAdapterFactory.create())
    .build()
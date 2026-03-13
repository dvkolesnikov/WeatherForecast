package com.example.core_network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit

val coreNetworkModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(client = get()) }
}

private fun provideOkHttpClient() = OkHttpClient.Builder()
    .addInterceptor(HttpLoggingInterceptor())
    .build()

private fun provideRetrofit(client: OkHttpClient) = Retrofit.Builder()
    .client(client)
    //.baseUrl() //TODO
    .build()
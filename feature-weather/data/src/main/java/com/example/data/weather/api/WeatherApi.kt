package com.example.data.weather.api

import com.example.data.weather.dto.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.Locale

interface WeatherApi {

    @GET("/data/3.0/onecall")
    suspend fun loadWeather(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("lang") lang: String = Locale.getDefault().language,
        @Query("exclude") exclusions: String = "minutely",
        @Query("units") units: String = "metric",
    ): Result<WeatherResponse>
}

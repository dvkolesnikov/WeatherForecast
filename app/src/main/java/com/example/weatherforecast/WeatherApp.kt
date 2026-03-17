package com.example.weatherforecast

import android.app.Application
import com.example.di.commonModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WeatherApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@WeatherApp)
            modules(
                appModule,
                commonModule,
                viewModelModule
            )
        }
    }
}
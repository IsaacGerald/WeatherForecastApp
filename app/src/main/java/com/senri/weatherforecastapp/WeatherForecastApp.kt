package com.senri.weatherforecastapp

import android.app.Application
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class WeatherForecastApp: Application() {

    override fun onCreate() {
        super.onCreate()
    }

}
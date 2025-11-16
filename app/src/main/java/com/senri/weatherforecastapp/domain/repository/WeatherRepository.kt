package com.senri.weatherforecastapp.domain.repository

import android.location.Location
import com.senri.weatherforecastapp.common.util.Resource
import com.senri.weatherforecastapp.domain.model.WeatherResponse
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {

     fun getWeatherForecast(
         location: Location?
    ): Flow<Resource<WeatherResponse>>
}
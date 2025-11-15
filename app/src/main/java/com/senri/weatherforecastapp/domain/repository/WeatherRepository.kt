package com.senri.weatherforecastapp.domain.repository

import com.senri.weatherforecastapp.common.util.Resource
import com.senri.weatherforecastapp.domain.model.WeatherResponse
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {

     fun getWeatherForecast(
        latitude: Double,
        longitude: Double,
    ): Flow<Resource<WeatherResponse>>
}
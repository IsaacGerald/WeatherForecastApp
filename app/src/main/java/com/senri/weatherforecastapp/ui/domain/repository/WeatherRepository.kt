package com.senri.weatherforecastapp.ui.domain.repository

import com.senri.weatherforecastapp.common.util.Resource
import com.senri.weatherforecastapp.ui.data.db.entity.WeatherResponseEntity
import com.senri.weatherforecastapp.ui.data.model.response.WeatherForecastResponse
import com.senri.weatherforecastapp.ui.domain.model.WeatherResponse
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {

    suspend fun getWeatherForecast(
        latitude: Long,
        longitude: Long,
    ): Flow<Resource<WeatherResponse>>
}
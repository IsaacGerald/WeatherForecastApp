package com.senri.weatherforecastapp.ui.data.network

import com.senri.weatherforecastapp.ui.data.model.response.WeatherForecastResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("forecast")
    suspend fun getWeatherForecast(
        @Query("lat") lat: Long,
        @Query("lon") lon: Long,
        @Query("appid") appId: String,
        @Query("units") units: String,
        @Query("lang") lang: String,
    ): WeatherForecastResponse

}
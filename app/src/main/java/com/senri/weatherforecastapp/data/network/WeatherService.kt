package com.senri.weatherforecastapp.data.network

import com.senri.weatherforecastapp.data.model.response.WeatherForecastResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("forecast")
    suspend fun getWeatherForecast(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appId: String,
        @Query("units") units: String,
        @Query("lang") lang: String,
    ): WeatherForecastResponse

}
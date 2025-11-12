package com.senri.weatherforecastapp.ui.domain.model


data class WeatherResponse(
    val id: Int? = null,
    val name: String? = null,
    val population: Int? = null,
    val sunrise: Int? = null,
    val sunset: Int? = null,
    val country: String? = null,
    val timezone: Int? = null,
    val lat: Long? = null,
    val lon: Long? = null,
    val weatherItem: List<WeatherItem> = emptyList()
)


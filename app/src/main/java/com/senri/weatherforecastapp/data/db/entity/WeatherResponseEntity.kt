package com.senri.weatherforecastapp.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.senri.weatherforecastapp.domain.model.WeatherResponse

@Entity(tableName = "weather_response_table")
data class WeatherResponseEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int? = null,
    val name: String? = null,
    val population: Int? = null,
    val sunrise: Int? = null,
    val sunset: Int? = null,
    val country: String? = null,
    val timezone: Int? = null,
    val lat: Long? = null,
    val lon: Long? = null,
)

fun WeatherResponseEntity.toWeatherResponse() = WeatherResponse(
    id = id,
    name = name,
    population = population,
    sunrise = sunrise,
    country = country,
    timezone = timezone,
    lat = lat,
    lon = lon
)


package com.senri.weatherforecastapp.data.model.response

import com.google.gson.annotations.SerializedName
import com.senri.weatherforecastapp.data.db.entity.WeatherResponseEntity
import kotlinx.serialization.SerialName

data class WeatherForecastResponse(
    val city: CityResponse? = null,
    val cnt: Int? = null,
    val cod: String? = null,
    @SerializedName("list")
    val data: List<WeatherForecastResponseData>? = null,
    val message: Int? = null
)

fun WeatherForecastResponse.toWeatherResponseEntity() = WeatherResponseEntity(
     id = city?.id,
     name = city?.name,
     population = city?.population,
     sunrise = city?.sunrise,
     sunset = city?.sunset,
     country = city?.country,
     timezone = city?.timezone,
     lat = city?.coord?.lat?.toLong(),
     lon = city?.coord?.lon?.toLong()
)
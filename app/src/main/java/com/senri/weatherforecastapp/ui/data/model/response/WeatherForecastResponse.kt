package com.senri.weatherforecastapp.ui.data.model.response

import com.senri.weatherforecastapp.ui.data.db.entity.WeatherResponseEntity
import kotlinx.serialization.SerialName

data class WeatherForecastResponse(
    val city: CityResponse? = null,
    val cnt: Int? = null,
    val cod: String? = null,
    @SerialName("list")
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
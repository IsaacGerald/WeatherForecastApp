package com.senri.weatherforecastapp.data.model.response

import com.senri.weatherforecastapp.data.db.entity.MainEntity
import kotlinx.serialization.SerialName

data class MainResponse(
    @SerialName("feels_like")
    val feelsLike: Double? = null,
    @SerialName("grnd_level")
    val grndLevel: Int? = null,
    val humidity: Int? = null,
    val pressure: Int? = null,
    @SerialName("sea_level")
    val seaLevel: Int? = null,
    @SerialName("")
    val temp: Double? = null,
    @SerialName("temp_kf")
    val tempKf: Double? = null,
    @SerialName("temp_max")
    val tempMax: Double? = null,
    @SerialName("temp_min")
    val tempMin: Double? = null
)

fun MainResponse.toMainEntity() = MainEntity(
    feelsLike = feelsLike,
    grndLevel = grndLevel,
    humidity = humidity,
    pressure = pressure,
    seaLevel = seaLevel,
    temp = temp,
    tempKf = tempKf,
    tempMax = tempMax,
    tempMin = tempMin
)
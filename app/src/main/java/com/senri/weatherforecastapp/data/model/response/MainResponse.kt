package com.senri.weatherforecastapp.data.model.response

import com.google.gson.annotations.SerializedName
import com.senri.weatherforecastapp.data.db.entity.MainEntity
import kotlinx.serialization.SerialName

data class MainResponse(
    @SerializedName("feels_like")
    val feelsLike: Double? = null,
    @SerializedName("grnd_level")
    val grndLevel: Int? = null,
    val humidity: Int? = null,
    val pressure: Int? = null,
    @SerializedName("sea_level")
    val seaLevel: Int? = null,
    val temp: Double? = null,
    @SerializedName("temp_kf")
    val tempKf: Double? = null,
    @SerializedName("temp_max")
    val tempMax: Double? = null,
    @SerializedName("temp_min")
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
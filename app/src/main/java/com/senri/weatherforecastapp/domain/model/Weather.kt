package com.senri.weatherforecastapp.domain.model

import com.senri.weatherforecastapp.data.db.entity.WeatherEntity
import kotlinx.serialization.Serializable

@Serializable
data class Weather(
    val description: String? = null,
    val icon: String? = null,
    val id: Int? = null,
    val main: String? = null
)

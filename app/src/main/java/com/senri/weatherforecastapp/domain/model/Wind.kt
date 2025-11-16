package com.senri.weatherforecastapp.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Wind(
    val deg: Int? = null,
    val gust: Double? = null,
    val speed: Double? = null
)

package com.senri.weatherforecastapp.ui.domain.model

import com.senri.weatherforecastapp.ui.data.db.entity.MainEntity
import kotlinx.serialization.SerialName

data class Main(
    val feelsLike: Double? = null,
    val grndLevel: Int? = null,
    val humidity: Int? = null,
    val pressure: Int? = null,
    val seaLevel: Int? = null,
    val temp: Double? = null,
    val tempKf: Double? = null,
    val tempMax: Double? = null,
    val tempMin: Double? = null
)


package com.senri.weatherforecastapp.ui.data.db.entity

import androidx.room.Entity
import com.senri.weatherforecastapp.ui.domain.model.Weather

data class WeatherEntity(
    val description: String? = null,
    val icon: String? = null,
    val id: Int? = null,
    val main: String? = null,
    val weatherItemDt: Int = -1
)

fun WeatherEntity.toWeather() = Weather(
    description = description,
    icon = icon,
    id = id,
    main = main,
)


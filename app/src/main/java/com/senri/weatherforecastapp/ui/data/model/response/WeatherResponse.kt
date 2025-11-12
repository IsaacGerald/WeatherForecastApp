package com.senri.weatherforecastapp.ui.data.model.response

import com.senri.weatherforecastapp.ui.data.db.entity.WeatherEntity

data class WeatherResponse(
    val description: String? = null,
    val icon: String? = null,
    val id: Int? = null,
    val main: String? = null
)

fun WeatherResponse.toWeatherEntity() = WeatherEntity(
    description = description,
    icon = icon,
    id = id,
    main = main,
    weatherItemDt = -1
)
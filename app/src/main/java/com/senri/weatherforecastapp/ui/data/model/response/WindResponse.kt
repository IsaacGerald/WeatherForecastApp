package com.senri.weatherforecastapp.ui.data.model.response

import com.senri.weatherforecastapp.ui.data.db.entity.WindEntity

data class WindResponse(
    val deg: Int? = null,
    val gust: Double? = null,
    val speed: Double? = null
)

fun WindResponse.toWindEntity() = WindEntity(
    deg = deg,
    gust = gust,
    speed = speed
)
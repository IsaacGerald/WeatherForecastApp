package com.senri.weatherforecastapp.data.model.response

import com.senri.weatherforecastapp.data.db.entity.WindEntity

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
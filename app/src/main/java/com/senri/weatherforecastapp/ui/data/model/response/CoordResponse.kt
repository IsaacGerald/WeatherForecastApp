package com.senri.weatherforecastapp.ui.data.model.response

import com.senri.weatherforecastapp.ui.data.db.entity.CoordEntity

data class CoordResponse(
    val lat: Double? = null,
    val lon: Double? = null
)

fun CoordResponse.toCoordEntity() = CoordEntity(
    lat = lat,
    lon = lon
)
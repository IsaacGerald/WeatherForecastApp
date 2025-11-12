package com.senri.weatherforecastapp.ui.data.db.entity

import androidx.room.Entity
import com.senri.weatherforecastapp.ui.domain.model.Coord

@Entity(tableName = "coord_table")
data class CoordEntity(
    val lat: Double? = null,
    val lon: Double? = null
)

fun CoordEntity.toCoord() = Coord(
    lat = lat,
    lon = lon
)


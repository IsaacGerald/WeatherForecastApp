package com.senri.weatherforecastapp.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.senri.weatherforecastapp.domain.model.Coord

@Entity(tableName = "coord_table")
data class CoordEntity(
    val lat: Double? = null,
    val lon: Double? = null,
    @PrimaryKey(autoGenerate = true)
    val id: Long = -1
)

fun CoordEntity.toCoord() = Coord(
    lat = lat,
    lon = lon
)


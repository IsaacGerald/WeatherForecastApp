package com.senri.weatherforecastapp.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.senri.weatherforecastapp.domain.model.Wind

@Entity(tableName = "wind_table")
data class WindEntity(
    val deg: Int? = null,
    val gust: Double? = null,
    val speed: Double? = null,
    @PrimaryKey(autoGenerate = false)
    val weatherItemDt: Int = -1
)

fun WindEntity.toWind() = Wind(
    deg = deg,
    gust = gust,
    speed = speed,
)
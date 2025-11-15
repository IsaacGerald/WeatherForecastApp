package com.senri.weatherforecastapp.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wind_table")
data class WindEntity(
    val deg: Int? = null,
    val gust: Double? = null,
    val speed: Double? = null,
    @PrimaryKey(autoGenerate = false)
    val weatherItemDt: Int = -1
)
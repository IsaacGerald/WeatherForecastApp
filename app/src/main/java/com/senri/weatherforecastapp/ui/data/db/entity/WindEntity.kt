package com.senri.weatherforecastapp.ui.data.db.entity

import androidx.room.Entity

@Entity(tableName = "wind_table")
data class WindEntity(
    val deg: Int? = null,
    val gust: Double? = null,
    val speed: Double? = null,
    val weatherItemDt: Int = -1
)
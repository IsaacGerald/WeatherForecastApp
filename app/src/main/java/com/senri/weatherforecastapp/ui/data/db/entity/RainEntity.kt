package com.senri.weatherforecastapp.ui.data.db.entity

import androidx.room.Entity
import com.senri.weatherforecastapp.ui.domain.model.Rain

@Entity(tableName = "rain_table")
data class RainEntity(
    val threeH: Double? = null,
    val weatherItemDt: Int = -1
)

fun RainEntity.toRain() = Rain(
    threeH = threeH
)


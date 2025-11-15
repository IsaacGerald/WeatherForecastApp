package com.senri.weatherforecastapp.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.senri.weatherforecastapp.domain.model.Rain

@Entity(tableName = "rain_table")
data class RainEntity(
    val threeH: Double? = null,
    @PrimaryKey(autoGenerate = false)
    val weatherItemDt: Int = -1
)

fun RainEntity.toRain() = Rain(
    threeH = threeH
)


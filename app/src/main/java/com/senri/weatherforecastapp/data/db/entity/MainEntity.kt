package com.senri.weatherforecastapp.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.senri.weatherforecastapp.domain.model.Main

@Entity(tableName = "main_table")
data class MainEntity(
    val feelsLike: Double? = null,
    val grndLevel: Int? = null,
    val humidity: Int? = null,
    val pressure: Int? = null,
    val seaLevel: Int? = null,
    val temp: Double? = null,
    val tempKf: Double? = null,
    val tempMax: Double? = null,
    val tempMin: Double? = null,
    @PrimaryKey(autoGenerate = false)
    val weatherItemDt: Int = -1
)

fun MainEntity.toMain() = Main(
    feelsLike = feelsLike,
    grndLevel = grndLevel,
    humidity = humidity,
    pressure = pressure,
    seaLevel = seaLevel,
    temp = temp,
    tempKf = tempKf,
    tempMin = tempMin,
)


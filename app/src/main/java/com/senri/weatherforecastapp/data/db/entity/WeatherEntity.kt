package com.senri.weatherforecastapp.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.senri.weatherforecastapp.domain.model.Weather

@Entity(tableName = "weather_table")
data class WeatherEntity(
    val description: String? = null,
    val icon: String? = null,
    @PrimaryKey(autoGenerate = false)
    val id: Int? = null,
    val main: String? = null,
    val weatherItemDt: Int = -1
)

fun WeatherEntity.toWeather() = Weather(
    description = description,
    icon = icon,
    id = id,
    main = main,
)


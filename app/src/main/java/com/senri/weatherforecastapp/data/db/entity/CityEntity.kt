package com.senri.weatherforecastapp.data.db.entity

import androidx.room.Entity
import com.senri.weatherforecastapp.domain.model.City

@Entity(tableName = "city_table")
data class CityEntity(
    val coord: CoordEntity? = null,
    val country: String? = null,
    val id: Int? = null,
    val name: String? = null,
    val population: Int? = null,
    val sunrise: Int? = null,
    val sunset: Int? = null,
    val timezone: Int? = null
)

fun CityEntity.toCity() = City(
    coord = coord?.toCoord(),
    country = country,
    id = id,
    name = name,
    population = population,
    sunrise = sunrise,
    sunset = sunset,
    timezone = timezone
)


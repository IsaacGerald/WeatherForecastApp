package com.senri.weatherforecastapp.ui.data.model.response

import com.senri.weatherforecastapp.ui.data.db.entity.CityEntity

data class CityResponse(
    val coord: CoordResponse? = null,
    val country: String? = null,
    val id: Int? = null,
    val name: String? = null,
    val population: Int? = null,
    val sunrise: Int? = null,
    val sunset: Int? = null,
    val timezone: Int? = null
)

fun CityResponse.toCityEntity() = CityEntity(
    coord = coord?.toCoordEntity(),
    country = country,
    id = id,
    name = name,
    population = population,
    sunrise = sunrise,
    sunset = sunset,
    timezone = timezone
)
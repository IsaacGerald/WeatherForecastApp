package com.senri.weatherforecastapp.ui.domain.model

import com.senri.weatherforecastapp.ui.data.db.entity.WeatherEntity

data class Weather(
    val description: String? = null,
    val icon: String? = null,
    val id: Int? = null,
    val main: String? = null
)

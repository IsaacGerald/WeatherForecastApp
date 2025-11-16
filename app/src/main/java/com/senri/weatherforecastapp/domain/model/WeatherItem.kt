package com.senri.weatherforecastapp.domain.model

import com.google.gson.annotations.SerializedName
import com.senri.weatherforecastapp.data.model.response.CloudsResponse
import com.senri.weatherforecastapp.data.model.response.MainResponse
import com.senri.weatherforecastapp.data.model.response.RainResponse
import com.senri.weatherforecastapp.data.model.response.SysResponse
import com.senri.weatherforecastapp.data.model.response.WeatherResponse
import com.senri.weatherforecastapp.data.model.response.WindResponse
import kotlinx.serialization.Serializable

@Serializable

data class WeatherItem(
    val clouds: Clouds? = null,
    val dt: Int? = null,
    val dtTxt: String? = null,
    val main: Main? = null,
    val pop: Double? = null,
    val rain: Rain? = null,
    val sys: Sys? = null,
    val visibility: Int? = null,
    val weather: List<Weather>? = null,
    val wind: Wind? = null
)




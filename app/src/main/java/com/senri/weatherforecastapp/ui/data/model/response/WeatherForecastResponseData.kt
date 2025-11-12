package com.senri.weatherforecastapp.ui.data.model.response

import com.google.gson.annotations.SerializedName
import com.senri.weatherforecastapp.ui.data.db.entity.WeatherItemEntity

data class WeatherForecastResponseData(
    val clouds: CloudsResponse? = null,
    val dt: Int? = null,
    @SerializedName("dt_txt")
    val dtTxt: String? = null,
    val main: MainResponse? = null,
    val pop: Double? = null,
    val rain: RainResponse? = null,
    val sys: SysResponse? = null,
    val visibility: Int? = null,
    val weather: List<WeatherResponse>? = null,
    val wind: WindResponse? = null
)

fun WeatherForecastResponseData.toWeatherItemEntity() = WeatherItemEntity(
    dt = dt ?: -1,
    dtTxt = dtTxt,
    pop = pop,
    visibility = visibility,
    responseId = -1

)
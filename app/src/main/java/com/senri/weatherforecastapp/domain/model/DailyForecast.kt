package com.senri.weatherforecastapp.domain.model

import com.senri.weatherforecastapp.common.toDate
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
data class DailyForecastModel(
    val foreCastDate: String,
    val weatherItem: List<WeatherItem>
){

    val date: Date get() {
       return foreCastDate.toDate() ?: Date()
    }
}

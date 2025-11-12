package com.senri.weatherforecastapp.ui.data.db.entity.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.senri.weatherforecastapp.ui.data.db.entity.WeatherItemEntity
import com.senri.weatherforecastapp.ui.data.db.entity.WeatherResponseEntity
import com.senri.weatherforecastapp.ui.data.model.response.Weather

data class WeatherResponseWithItem(
    @Embedded val weatherResponseEntity: WeatherResponseEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "responseId"
    )
    val weatherItemEntity: List<WeatherItemEntity>
)



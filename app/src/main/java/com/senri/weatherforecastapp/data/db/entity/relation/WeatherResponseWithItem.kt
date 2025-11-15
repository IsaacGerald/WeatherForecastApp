package com.senri.weatherforecastapp.data.db.entity.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.senri.weatherforecastapp.data.db.entity.WeatherItemEntity
import com.senri.weatherforecastapp.data.db.entity.WeatherResponseEntity

data class WeatherResponseWithItem(
    @Embedded val weatherResponseEntity: WeatherResponseEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "responseId",
        entity = WeatherItemEntity::class
    )
    val weatherItemEntity: List<WeatherItemAndDetails>
)



package com.senri.weatherforecastapp.data.db.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.senri.weatherforecastapp.domain.model.WeatherItem

@Entity(tableName = "weather_item_table",
    foreignKeys = [
        ForeignKey(
            entity = WeatherResponseEntity::class,
            parentColumns = ["id"],
            childColumns = ["responseId"],
            onDelete = ForeignKey.CASCADE
        )
    ])
data class WeatherItemEntity(
    @PrimaryKey(autoGenerate = false)
    val dt: Int,
    val responseId: Int,
    val dtTxt: String? = null,
    val pop: Double? = null,
    val visibility: Int? = null,
    val weather: List<WeatherEntity>? = null,
)





package com.senri.weatherforecastapp.ui.data.db.entity

import androidx.room.Entity
import com.senri.weatherforecastapp.ui.domain.model.Clouds

@Entity(tableName = "clouds_table")
data class CloudsEntity(
    val all: Int? = null,
    val weatherItemDt: Int = -1
)

fun CloudsEntity.toCloud() = Clouds(
    all = all
)


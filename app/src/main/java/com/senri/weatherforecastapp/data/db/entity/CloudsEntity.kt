package com.senri.weatherforecastapp.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.senri.weatherforecastapp.domain.model.Clouds

@Entity(tableName = "clouds_table")
data class CloudsEntity(
    val all: Int? = null,
    @PrimaryKey(autoGenerate = false)
    val weatherItemDt: Int = -1
)

fun CloudsEntity.toCloud() = Clouds(
    all = all
)


package com.senri.weatherforecastapp.domain.model

import com.senri.weatherforecastapp.data.db.entity.CloudsEntity
import kotlinx.serialization.Serializable

@Serializable
data class Clouds(
    val all: Int? = null
)


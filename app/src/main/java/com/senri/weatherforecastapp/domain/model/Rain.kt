package com.senri.weatherforecastapp.domain.model

import com.senri.weatherforecastapp.data.db.entity.RainEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Rain(
    val threeH: Double? = null
)

package com.senri.weatherforecastapp.data.model.response

import com.senri.weatherforecastapp.data.db.entity.RainEntity
import kotlinx.serialization.SerialName

data class RainResponse(
    @SerialName("3h")
    val threeH: Double? = null
)

fun RainResponse.toRainEntity() = RainEntity(
    threeH = threeH
)
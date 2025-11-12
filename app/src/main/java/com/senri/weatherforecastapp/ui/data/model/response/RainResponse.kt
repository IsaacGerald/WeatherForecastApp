package com.senri.weatherforecastapp.ui.data.model.response

import com.senri.weatherforecastapp.ui.data.db.entity.RainEntity
import kotlinx.serialization.SerialName

data class RainResponse(
    @SerialName("3h")
    val threeH: Double? = null
)

fun RainResponse.toRainEntity() = RainEntity(
    threeH = threeH
)
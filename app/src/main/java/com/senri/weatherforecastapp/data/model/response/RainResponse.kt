package com.senri.weatherforecastapp.data.model.response

import com.google.gson.annotations.SerializedName
import com.senri.weatherforecastapp.data.db.entity.RainEntity
import kotlinx.serialization.SerialName

data class RainResponse(
    @SerializedName("3h")
    val threeH: Double? = null
)

fun RainResponse.toRainEntity() = RainEntity(
    threeH = threeH
)
package com.senri.weatherforecastapp.domain.model

import com.senri.weatherforecastapp.data.db.entity.SysEntity
import kotlinx.serialization.Serializable

@Serializable
data class Sys(
    val pod: String? = null
)

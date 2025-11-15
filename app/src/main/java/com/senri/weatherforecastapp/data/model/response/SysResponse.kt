package com.senri.weatherforecastapp.data.model.response

import com.senri.weatherforecastapp.data.db.entity.SysEntity

data class SysResponse(
    val pod: String? = null
)

fun SysResponse.toSysEntity() = SysEntity(
    pod = pod
)
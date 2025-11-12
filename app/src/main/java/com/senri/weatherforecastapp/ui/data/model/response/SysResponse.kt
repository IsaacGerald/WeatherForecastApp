package com.senri.weatherforecastapp.ui.data.model.response

import com.senri.weatherforecastapp.ui.data.db.entity.SysEntity

data class SysResponse(
    val pod: String? = null
)

fun SysResponse.toSysEntity() = SysEntity(
    pod = pod
)
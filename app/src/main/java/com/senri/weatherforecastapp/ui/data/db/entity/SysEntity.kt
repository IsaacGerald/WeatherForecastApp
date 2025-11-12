package com.senri.weatherforecastapp.ui.data.db.entity

import androidx.room.Entity
import com.senri.weatherforecastapp.ui.domain.model.Sys

@Entity(tableName = "sys_table")
data class SysEntity(
    val pod: String? = null,
    val weatherItemDt: Int = -1
)

fun SysEntity.toSys() = Sys(
    pod = pod
)


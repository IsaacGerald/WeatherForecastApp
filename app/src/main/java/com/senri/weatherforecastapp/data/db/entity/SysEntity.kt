package com.senri.weatherforecastapp.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.senri.weatherforecastapp.domain.model.Sys

@Entity(tableName = "sys_table")
data class SysEntity(
    val pod: String? = null,
    @PrimaryKey(autoGenerate = false)
    val weatherItemDt: Int = -1
)

fun SysEntity.toSys() = Sys(
    pod = pod
)


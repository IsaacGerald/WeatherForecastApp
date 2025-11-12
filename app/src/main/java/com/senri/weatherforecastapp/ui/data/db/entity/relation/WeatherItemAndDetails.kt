package com.senri.weatherforecastapp.ui.data.db.entity.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.senri.weatherforecastapp.ui.data.db.entity.CloudsEntity
import com.senri.weatherforecastapp.ui.data.db.entity.MainEntity
import com.senri.weatherforecastapp.ui.data.db.entity.RainEntity
import com.senri.weatherforecastapp.ui.data.db.entity.SysEntity
import com.senri.weatherforecastapp.ui.data.db.entity.WeatherEntity
import com.senri.weatherforecastapp.ui.data.db.entity.WeatherItemEntity
import com.senri.weatherforecastapp.ui.data.db.entity.WindEntity

data class WeatherItemAndDetails(
    @Embedded val weatherItemEntity: WeatherItemEntity,

    @Relation(
        parentColumn = "dt",
        entityColumn = "weatherItemDt"
    )
    val cloudsEntity: CloudsEntity,

    @Relation(
        parentColumn = "dt",
        entityColumn = "weatherItemDt"
    )
    val mainEntity: MainEntity,

    @Relation(
        parentColumn = "dt",
        entityColumn = "weatherItemDt"
    )
    val rainEntity: RainEntity,

    @Relation(
        parentColumn = "dt",
        entityColumn = "weatherItemDt"
    )
    val sysEntity: SysEntity,

    @Relation(
        parentColumn = "dt",
        entityColumn = "weatherItemDt"
    )
    val windEntity: WindEntity

)



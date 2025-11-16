package com.senri.weatherforecastapp.data.db.entity.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.senri.weatherforecastapp.data.db.entity.CloudsEntity
import com.senri.weatherforecastapp.data.db.entity.MainEntity
import com.senri.weatherforecastapp.data.db.entity.RainEntity
import com.senri.weatherforecastapp.data.db.entity.SysEntity
import com.senri.weatherforecastapp.data.db.entity.WeatherEntity
import com.senri.weatherforecastapp.data.db.entity.WeatherItemEntity
import com.senri.weatherforecastapp.data.db.entity.WindEntity
import com.senri.weatherforecastapp.data.db.entity.toCloud
import com.senri.weatherforecastapp.data.db.entity.toMain
import com.senri.weatherforecastapp.data.db.entity.toRain
import com.senri.weatherforecastapp.data.db.entity.toSys
import com.senri.weatherforecastapp.data.db.entity.toWeather
import com.senri.weatherforecastapp.data.db.entity.toWind
import com.senri.weatherforecastapp.domain.model.WeatherItem

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
    val rainEntity: RainEntity? = null,

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


fun WeatherItemAndDetails.toWeatherItem() = WeatherItem(
    dt = weatherItemEntity.dt,
    dtTxt = weatherItemEntity.dtTxt,
    pop = weatherItemEntity.pop,
    visibility = weatherItemEntity.visibility,
    weather = weatherItemEntity.weather?.map { it.toWeather() },
    clouds = cloudsEntity.toCloud(),
    rain = rainEntity?.toRain(),
    sys = sysEntity.toSys(),
    wind = windEntity.toWind(),
    main = mainEntity.toMain()
)






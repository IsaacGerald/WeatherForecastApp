package com.senri.weatherforecastapp.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.senri.weatherforecastapp.data.db.entity.CloudsEntity
import com.senri.weatherforecastapp.data.db.entity.MainEntity
import com.senri.weatherforecastapp.data.db.entity.RainEntity
import com.senri.weatherforecastapp.data.db.entity.SysEntity
import com.senri.weatherforecastapp.data.db.entity.WeatherEntity
import com.senri.weatherforecastapp.data.db.entity.WeatherItemEntity
import com.senri.weatherforecastapp.data.db.entity.WeatherResponseEntity
import com.senri.weatherforecastapp.data.db.entity.WindEntity
import com.senri.weatherforecastapp.data.db.entity.relation.WeatherResponseWithItem

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeatherResponse(response: WeatherResponseEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeatherItems(items: List<WeatherItemEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertClouds(clouds: List<CloudsEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMain(mainInfo: List<MainEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRain(clouds: List<RainEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSys(sys: List<SysEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeather(weather: List<WeatherEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWind(wind: List<WindEntity>)


    @Transaction
    @Query("SELECT * FROM weather_response_table")
    suspend fun getWeatherResponseWithItems(): List<WeatherResponseWithItem>

    @Query("DELETE FROM weather_response_table WHERE id = :id")
    suspend fun deleteWeatherResponse(id: String)

    @Query("DELETE FROM weather_response_table")
    suspend fun deleteAllWeatherResponse()



}
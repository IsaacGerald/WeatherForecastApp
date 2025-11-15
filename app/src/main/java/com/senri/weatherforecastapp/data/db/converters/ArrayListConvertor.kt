package com.senri.weatherforecastapp.data.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.senri.weatherforecastapp.data.db.entity.WeatherEntity
import java.lang.reflect.Type

class ArrayListConvertor {

    @TypeConverter
    fun fromWeatherList(value: List<WeatherEntity>?): String {

        return Gson().toJson(value)
    }

    @TypeConverter
    fun toWeatherList(value: String): List<WeatherEntity> {
        return try {
            val type: Type = object : TypeToken<List<WeatherEntity>>() {}.type
            Gson().fromJson(value, type) //using extension function
        } catch (e: Exception) {
            listOf()
        }
    }


}
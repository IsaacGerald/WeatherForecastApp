package com.senri.weatherforecastapp.ui.data.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.senri.weatherforecastapp.ui.data.model.response.Weather
import java.lang.reflect.Type

class ArrayListConvertor {

    @TypeConverter
    fun fromWeatherList(value: List<Weather>): String {

        return Gson().toJson(value)
    }

    @TypeConverter
    fun toWeatherList(value: String): List<Weather> {
        return try {
            val type: Type = object : TypeToken<List<Weather>>() {}.type
            Gson().fromJson(value, type) //using extension function
        } catch (e: Exception) {
            listOf()
        }
    }


}
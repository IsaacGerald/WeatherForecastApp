package com.senri.weatherforecastapp.data.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.senri.weatherforecastapp.data.db.entity.CoordEntity
import java.lang.reflect.Type


class ObjectConverter {

    @TypeConverter
    fun fromCoord(value: CoordEntity): String {

        return Gson().toJson(value)
    }

    @TypeConverter
    fun toCoord(value: String): CoordEntity? {
        return try {
            val type: Type = object : TypeToken<CoordEntity>() {}.type
            Gson().fromJson(value, type) //using extension function
        } catch (e: Exception) {
            null
        }
    }


}
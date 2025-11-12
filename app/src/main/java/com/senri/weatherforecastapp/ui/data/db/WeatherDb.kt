package com.senri.weatherforecastapp.ui.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.senri.weatherforecastapp.ui.data.db.converters.ArrayListConvertor
import com.senri.weatherforecastapp.ui.data.db.dao.WeatherDao
import kotlinx.coroutines.CoroutineScope

@Database(exportSchema = false, version = 1, entities = [])
@TypeConverters(ArrayListConvertor::class)
abstract class WeatherDb: RoomDatabase() {

    abstract fun weatherDao(): WeatherDao

    companion object {
        val TAG = WeatherDb::class.java.simpleName

        const val DB_NAME = "weather_db"

        @Volatile
        private var INSTANCE: WeatherDb? = null

        // Makes sure no threads making the same thing at the same time
        private val LOCK = Any()

        operator fun invoke(
            context: Context,
            coroutineScope: CoroutineScope,
        ) = INSTANCE ?: synchronized(LOCK) {
            INSTANCE ?: buildDatabase(context, coroutineScope).also { INSTANCE = it }
        }

        private fun buildDatabase(
            context: Context,
            coroutineScope: CoroutineScope,
        ): WeatherDb {

            val instance = Room.databaseBuilder(
                context.applicationContext,
                WeatherDb::class.java,
                DB_NAME
            )
                //.addMigrations( MIGRATION_1_2, MIGRATION_2_3 )
                .fallbackToDestructiveMigration()

                .build()
            INSTANCE = instance
            return instance
        }
    }
}
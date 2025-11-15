package com.senri.weatherforecastapp.di

import android.app.Application
import com.senri.weatherforecastapp.data.db.WeatherDb
import com.senri.weatherforecastapp.data.network.WeatherService
import com.senri.weatherforecastapp.data.repository.WeatherRepositoryImpl
import com.senri.weatherforecastapp.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {


    @Singleton
    @Provides
    fun providesWeatherService(retrofit: Retrofit): WeatherService {
        return retrofit.create(WeatherService::class.java)
    }

    @Singleton
    @Provides
    fun providesCoroutine(): CoroutineScope {
        return CoroutineScope(Dispatchers.IO)
    }

    @Singleton
    @Provides
    fun providesWeatherDb(@ApplicationContext application: Application, coroutineScope: CoroutineScope): WeatherDb {
        return WeatherDb.invoke(application, coroutineScope)
    }

    @Singleton
    @Provides
    fun providesWeatherRepository(weatherService: WeatherService, weatherDb: WeatherDb): WeatherRepository {
        return WeatherRepositoryImpl(weatherService, weatherDb.weatherDao())
    }

}
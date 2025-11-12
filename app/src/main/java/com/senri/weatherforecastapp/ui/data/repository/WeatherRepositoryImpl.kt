package com.senri.weatherforecastapp.ui.data.repository

import com.senri.weatherforecastapp.common.Constants
import com.senri.weatherforecastapp.common.util.Resource
import com.senri.weatherforecastapp.ui.data.db.dao.WeatherDao
import com.senri.weatherforecastapp.ui.data.db.entity.toWeatherResponse
import com.senri.weatherforecastapp.ui.data.model.response.toCloudsEntity
import com.senri.weatherforecastapp.ui.data.model.response.toMainEntity
import com.senri.weatherforecastapp.ui.data.model.response.toRainEntity
import com.senri.weatherforecastapp.ui.data.model.response.toSysEntity
import com.senri.weatherforecastapp.ui.data.model.response.toWeatherItemEntity
import com.senri.weatherforecastapp.ui.data.model.response.toWindEntity
import com.senri.weatherforecastapp.ui.data.network.WeatherService
import com.senri.weatherforecastapp.ui.domain.model.WeatherResponse
import com.senri.weatherforecastapp.ui.domain.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.Dispatcher
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherService: WeatherService,
    private val weatherDao: WeatherDao
) : WeatherRepository {


    override suspend fun getWeatherForecast(
        latitude: Long,
        longitude: Long
    ): Flow<Resource<WeatherResponse>> = flow {

        try {
            val data = fetchWeatherForecastFromDb()

            emit(Resource.Loading(data = data))

            val response = weatherService.getWeatherForecast(
                lat = latitude,
                lon = longitude,
                appId = Constants.APP_ID,
                units = "metric",
                lang = "en"
            )

            val weatherItemEntity = response.data?.map { weather ->
                weather.toWeatherItemEntity()
            } ?: emptyList()

            val cloudsEntity = response.data?.mapNotNull { weather ->
                weather.clouds?.toCloudsEntity()?.copy(weatherItemDt = weather.dt ?: -1)
            } ?: emptyList()

            val mainEntity = response.data?.mapNotNull { weather ->
                weather.main?.toMainEntity()?.copy(weatherItemDt = weather.dt ?: -1)
            } ?: emptyList()

            val rainEntity = response.data?.mapNotNull { weather ->
                weather.rain?.toRainEntity()?.copy(weatherItemDt = weather.dt ?: -1)
            } ?: emptyList()

            val sysEntity = response.data?.mapNotNull { weather ->
                weather.sys?.toSysEntity()?.copy(weatherItemDt = weather.dt ?: -1)
            } ?: emptyList()

            val windEntity = response.data?.mapNotNull { weather ->
                weather.wind?.toWindEntity()?.copy(weatherItemDt = weather.dt ?: -1)
            } ?: emptyList()

            weatherDao.deleteAllWeatherResponse()

            weatherDao.insertWeatherItems(weatherItemEntity)
            weatherDao.insertClouds(cloudsEntity)
            weatherDao.insertMain(mainEntity)
            weatherDao.insertRain(rainEntity)
            weatherDao.insertSys(sysEntity)
            weatherDao.insertWind(windEntity)

            val newData = fetchWeatherForecastFromDb()
            emit(Resource.Success(data = newData))


        } catch (e: Exception) {
            emit(Resource.Error(message = e.message ?: ""))
        }


    }.flowOn(Dispatchers.IO)

    private suspend fun fetchWeatherForecastFromDb(): WeatherResponse? {
        val entity = weatherDao.getWeatherResponseWithItems().firstOrNull()
        val data = entity?.weatherResponseEntity?.toWeatherResponse()
//            ?.copy(weatherItem = entity.weatherItemEntity)
        return data
    }

}



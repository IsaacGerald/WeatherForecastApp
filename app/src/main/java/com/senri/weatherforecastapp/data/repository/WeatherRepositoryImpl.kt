package com.senri.weatherforecastapp.data.repository

import androidx.compose.material3.TimeInput
import com.senri.weatherforecastapp.common.Constants
import com.senri.weatherforecastapp.common.util.Resource
import com.senri.weatherforecastapp.data.db.dao.WeatherDao
import com.senri.weatherforecastapp.data.db.entity.relation.toWeatherItem
import com.senri.weatherforecastapp.data.db.entity.toWeatherResponse
import com.senri.weatherforecastapp.data.model.response.toCloudsEntity
import com.senri.weatherforecastapp.data.model.response.toMainEntity
import com.senri.weatherforecastapp.data.model.response.toRainEntity
import com.senri.weatherforecastapp.data.model.response.toSysEntity
import com.senri.weatherforecastapp.data.model.response.toWeatherItemEntity
import com.senri.weatherforecastapp.data.model.response.toWeatherResponseEntity
import com.senri.weatherforecastapp.data.model.response.toWindEntity
import com.senri.weatherforecastapp.data.network.WeatherService
import com.senri.weatherforecastapp.domain.model.WeatherResponse
import com.senri.weatherforecastapp.domain.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.Dispatcher
import timber.log.Timber
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherService: WeatherService,
    private val weatherDao: WeatherDao
) : WeatherRepository {


    override fun getWeatherForecast(
        latitude: Double,
        longitude: Double
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

            val weatherResponseEntity = response.toWeatherResponseEntity()


            val weatherItemEntity = response.data?.map { weather ->
                weather.toWeatherItemEntity().copy(responseId = weatherResponseEntity.id ?: -1)
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

            weatherDao.insertWeatherResponse(weatherResponseEntity)
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

        val weatherItem = entity?.weatherItemEntity?.map { it.toWeatherItem() } ?: emptyList()
        val weatherResponse = entity?.weatherResponseEntity?.toWeatherResponse()?.copy(weatherItem = weatherItem)

        return weatherResponse
    }

}



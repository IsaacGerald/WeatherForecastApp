package com.senri.weatherforecastapp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senri.weatherforecastapp.common.util.Resource
import com.senri.weatherforecastapp.domain.model.WeatherItem
import com.senri.weatherforecastapp.domain.usecase.GetWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import timber.log.Timber
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class WeatherForecastViewmodel @Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase
) : ViewModel() {

    private var _weatherForecastState = MutableStateFlow(WeatherForecastState())
    var weatherForecastState = _weatherForecastState.asStateFlow()


    fun getWeatherForecast(lat: Double, lon: Double) {
            getWeatherUseCase(lat, lon).onEach { resource ->
                when (resource) {
                    is Resource.Error -> {
                        Timber.d("ERROR: ${resource.message}")
                        _weatherForecastState.update {
                            WeatherForecastState(errorMessage = resource.message ?: "")
                        }
                    }

                    is Resource.Loading -> {
                        Timber.d("LOADING.. ${resource.data}")
                        _weatherForecastState.update {
                            it.copy(loading = false)
                        }
                    }

                    is Resource.Success -> {
                        Timber.d("SUCCESS: DATA ${resource.data}")
                        if (resource.data?.weatherItem?.isNotEmpty() == true){
                            val groupedWeatherItem = groupByDate(resource.data.weatherItem)
                            val todayWeatherForecast = getTodayWeatherForecast(groupedWeatherItem)
                            val futureWeatherForecast = getFutureForecast(groupedWeatherItem)
                            val currentWeather = getCurrentWeather(todayWeatherForecast)

                            Timber.d("SUCCESS: GROUP WEATHER ITEM: $groupedWeatherItem")
                            Timber.d("SUCCESS: TODAY FORECAST: $todayWeatherForecast")
                            Timber.d("SUCCESS: FUTURE FORECAST: $futureWeatherForecast")
                            Timber.d("SUCCESS: CURRENT WEATHER: $currentWeather")

                            _weatherForecastState.update {
                                WeatherForecastState(
                                    loading = false,
                                    currentWeather = currentWeather,
                                    todayWeatherForecast = todayWeatherForecast,
                                    futureWeatherForecast = futureWeatherForecast
                                    )
                            }
                        }

                    }

                }
            }.launchIn(viewModelScope)

        }




    fun Long.toDate(): Date {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = this * 1000  // convert seconds → millis

        // Normalize to midnight so same date = same key
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)


       return calendar.time
    }

    fun groupByDate(weatherItem: List<WeatherItem>): Map<Date, List<WeatherItem>> {
        val calendar = Calendar.getInstance()
        return weatherItem.groupBy { item ->
            calendar.timeInMillis = (item.dt?.toLong() ?: 0) * 1000  // convert seconds → millis

            // Normalize to midnight so same date = same key
            calendar.set(Calendar.HOUR_OF_DAY, 0)
            calendar.set(Calendar.MINUTE, 0)
            calendar.set(Calendar.SECOND, 0)
            calendar.set(Calendar.MILLISECOND, 0)


            calendar.time
        }

    }

    fun getTodayWeatherForecast(mappedDates: Map<Date, List<WeatherItem>>): List<WeatherItem> {
       return mappedDates.filter { (key, value) ->
           key.atMidnight() == todayAtMidnight()
       }.values.flatten()
    }

    fun getFutureForecast(mappedDates: Map<Date, List<WeatherItem>>): List<WeatherItem> {
        return mappedDates.filter { (key, value) ->
            key.atMidnight() > todayAtMidnight()
        }.values.flatten()
    }

    fun getCurrentWeather(todayWeatherForecast: List<WeatherItem>): WeatherItem? {
      val now = Date()
        return todayWeatherForecast.filter { (it.dt?.toLong() ?: 0L).toDate() <= now }
            .maxByOrNull { (it.dt?.toLong() ?: 0L).toDate().time }

    }

    fun Date.atMidnight(): Date {
        val cal = Calendar.getInstance()
        cal.time = this
        cal.set(Calendar.HOUR_OF_DAY, 0)
        cal.set(Calendar.MINUTE, 0)
        cal.set(Calendar.SECOND, 0)
        cal.set(Calendar.MILLISECOND, 0)
        return cal.time
    }

    fun todayAtMidnight(): Date {
        val cal = Calendar.getInstance()
        cal.set(Calendar.HOUR_OF_DAY, 0)
        cal.set(Calendar.MINUTE, 0)
        cal.set(Calendar.SECOND, 0)
        cal.set(Calendar.MILLISECOND, 0)
        return cal.time
    }


}



data class WeatherForecastState(
    var loading: Boolean = false,
    var currentWeather: WeatherItem? = null,
    var todayWeatherForecast: List<WeatherItem> = emptyList(),
    var futureWeatherForecast: List<WeatherItem> = emptyList(),
    var errorMessage: String = ""

)
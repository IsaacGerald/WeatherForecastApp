package com.senri.weatherforecastapp.presentation.viewmodel

import android.app.Application
import android.location.Location
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senri.weatherforecastapp.common.atMidnight
import com.senri.weatherforecastapp.common.dateToString
import com.senri.weatherforecastapp.common.toDate
import com.senri.weatherforecastapp.common.todayAtMidnight
import com.senri.weatherforecastapp.common.tomorrowAtMidnight
import com.senri.weatherforecastapp.common.util.LocationService
import com.senri.weatherforecastapp.common.util.LocationState
import com.senri.weatherforecastapp.common.util.Resource
import com.senri.weatherforecastapp.domain.model.DailyForecastModel
import com.senri.weatherforecastapp.domain.model.WeatherItem
import com.senri.weatherforecastapp.domain.model.WeatherResponse
import com.senri.weatherforecastapp.domain.usecase.GetWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class WeatherForecastViewmodel @Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase,
    private val application: Application
) : ViewModel() {

    private val locationService = LocationService(application.applicationContext)

    private var _weatherForecastState = MutableStateFlow(WeatherForecastState())
    var weatherForecastState = _weatherForecastState.asStateFlow()


    private val _locationState = MutableStateFlow<LocationState>(LocationState.Idle)
    val locationState: StateFlow<LocationState> = _locationState

    private val _isTracking = MutableStateFlow(false)
    val isTracking: StateFlow<Boolean> = _isTracking

    init {
        viewModelScope.launch {
            locationService.locationState.collect { state ->
                _locationState.value = state
            }
        }
    }


    fun getWeatherForecast(location: Location?) {
        getWeatherUseCase(location).onEach { resource ->
            when (resource) {
                is Resource.Error -> {
//                    _weatherForecastState.update {
//                        WeatherForecastState(errorMessage = resource.message ?: "")
//                    }
                }

                is Resource.Loading -> {
                    if (resource.data?.weatherItem?.isNotEmpty() == true) {
                        updateUiState(resource.data, isLoading = true)
                    } else {
                        _weatherForecastState.update {
                            it.copy(loading = false)
                        }
                    }
                }

                is Resource.Success -> {
                    updateUiState(resource.data ?: WeatherResponse(), false)

                }

            }
        }.launchIn(viewModelScope)

    }

    private fun updateUiState(
        data: WeatherResponse,
        isLoading: Boolean
    ) {
        val groupedWeatherItem = groupByDate(data.weatherItem)
        val todayWeatherForecast = getTodayWeatherForecast(groupedWeatherItem)
        val futureWeatherForecast = getFutureForecast(groupedWeatherItem)
        val currentWeather = getCurrentWeather(todayWeatherForecast)

        Timber.d("SUCCESS: GROUP WEATHER ITEM: $groupedWeatherItem")
        Timber.d("SUCCESS: TODAY FORECAST: $todayWeatherForecast")
        Timber.d("SUCCESS: FUTURE FORECAST: $futureWeatherForecast")
        Timber.d("SUCCESS: CURRENT WEATHER: $currentWeather")

        _weatherForecastState.update {
            WeatherForecastState(
                loading = isLoading,
                weatherResponse = data,
                currentWeather = currentWeather,
                todayWeatherForecast = todayWeatherForecast,
                futureWeatherForecast = futureWeatherForecast
            )
        }
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
        return mappedDates.filter { (key, _) ->
            key.atMidnight() == todayAtMidnight()
        }.values.flatten()
    }

    fun getFutureForecast(mappedDates: Map<Date, List<WeatherItem>>): List<DailyForecastModel> {
        val data = mappedDates.filter { (key, _) ->
            key.atMidnight() >= tomorrowAtMidnight()
        }
        return data.map { (key, value) ->
            DailyForecastModel(foreCastDate = key.dateToString(), weatherItem = value)
        }
    }

    fun getCurrentWeather(todayWeatherForecast: List<WeatherItem>): WeatherItem? {
        val now = Date()
        return todayWeatherForecast.filter { (it.dt?.toLong() ?: 0L).toDate() <= now }
            .maxByOrNull { (it.dt?.toLong() ?: 0L).toDate().time }

    }


    fun startLocationTracking() {
        _isTracking.value = true
        locationService.startLocationUpdates()
    }

    fun stopLocationTracking() {
        _isTracking.value = false
        locationService.stopLocationUpdates()
    }

    fun gettingLocation(){
        _weatherForecastState.update {
            it.copy(loading = true)
        }
    }

    override fun onCleared() {
        super.onCleared()
        stopLocationTracking()
    }

}


data class WeatherForecastState(
    var loading: Boolean = false,
    var weatherResponse: WeatherResponse? = null,
    var currentWeather: WeatherItem? = null,
    var todayWeatherForecast: List<WeatherItem> = emptyList(),
    var futureWeatherForecast: List<DailyForecastModel> = emptyList(),
    var errorMessage: String = ""

)
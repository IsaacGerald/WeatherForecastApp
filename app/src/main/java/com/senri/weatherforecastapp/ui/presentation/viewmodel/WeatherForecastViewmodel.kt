package com.senri.weatherforecastapp.ui.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senri.weatherforecastapp.common.util.Resource
import com.senri.weatherforecastapp.ui.domain.model.WeatherItem
import com.senri.weatherforecastapp.ui.domain.usecase.GetWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class WeatherForecastViewmodel @Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase
) : ViewModel() {

    private var _weatherForecastState = MutableStateFlow(WeatherForecastState())
    var weatherForecastState = _weatherForecastState.asStateFlow()



    fun getWeatherForecast(lat: Long, lon: Long) {
        viewModelScope.launch {
            getWeatherUseCase(lat, lon).onEach { resource ->
                when (resource) {
                    is Resource.Error -> {
                         _weatherForecastState.update {
                             WeatherForecastState(errorMessage = resource.message ?: "")
                         }
                    }

                    is Resource.Loading -> {
                         _weatherForecastState.update {
                             it.copy(loading = false)
                         }
                    }

                    is Resource.Success -> {

                    }

                }
            }
            withContext(Dispatchers.Main) {

            }
        }
    }


}


data class WeatherForecastState(
    var loading: Boolean = false,
    var currentWeather: WeatherItem? = null,
    var todayWeatherForecast: List<WeatherItem> = emptyList(),
    var weatherForecast: List<WeatherItem> = emptyList(),
    var errorMessage: String = ""

)
package com.senri.weatherforecastapp.ui.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.senri.weatherforecastapp.ui.domain.usecase.GetWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WeatherForecastViewmodel @Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase
): ViewModel() {



}
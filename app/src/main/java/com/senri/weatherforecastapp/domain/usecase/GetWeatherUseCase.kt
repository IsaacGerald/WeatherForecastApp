package com.senri.weatherforecastapp.domain.usecase

import android.location.Location
import com.senri.weatherforecastapp.common.util.Resource
import com.senri.weatherforecastapp.domain.model.WeatherResponse
import com.senri.weatherforecastapp.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetWeatherUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {

     operator fun invoke(location: Location?): Flow<Resource<WeatherResponse>> {
        return weatherRepository.getWeatherForecast(location)
    }
}
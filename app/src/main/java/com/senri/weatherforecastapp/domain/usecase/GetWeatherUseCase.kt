package com.senri.weatherforecastapp.domain.usecase

import com.senri.weatherforecastapp.common.util.Resource
import com.senri.weatherforecastapp.domain.model.WeatherResponse
import com.senri.weatherforecastapp.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetWeatherUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {

     operator fun invoke(lat: Double, long: Double ): Flow<Resource<WeatherResponse>> {
        return weatherRepository.getWeatherForecast(latitude = lat, longitude = long)
    }
}
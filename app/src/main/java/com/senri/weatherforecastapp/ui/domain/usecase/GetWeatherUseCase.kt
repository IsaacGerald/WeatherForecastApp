package com.senri.weatherforecastapp.ui.domain.usecase

import com.senri.weatherforecastapp.common.util.Resource
import com.senri.weatherforecastapp.ui.domain.model.WeatherResponse
import com.senri.weatherforecastapp.ui.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetWeatherUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {

    suspend operator fun invoke(lat: Long, long: Long ): Flow<Resource<WeatherResponse>> {
        return weatherRepository.getWeatherForecast(latitude = lat, longitude = long)
    }
}
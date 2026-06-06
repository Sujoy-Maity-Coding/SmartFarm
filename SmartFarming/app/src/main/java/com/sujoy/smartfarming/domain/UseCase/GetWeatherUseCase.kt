package com.sujoy.smartfarming.domain.UseCase

import com.sujoy.smartfarming.domain.model.WeatherInfo
import com.sujoy.smartfarming.domain.repo.WeatherRepository
import javax.inject.Inject

class GetWeatherUseCase @Inject constructor(
    private val repository:
    WeatherRepository
) {

    suspend operator fun invoke(
        latitude: Double,
        longitude: Double
    ): WeatherInfo {

        return repository
            .getWeather(
                latitude,
                longitude
            )
    }
}
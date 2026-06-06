package com.sujoy.smartfarming.data.repo

import com.sujoy.smartfarming.common.Constants
import com.sujoy.smartfarming.data.mapper.toDomain
import com.sujoy.smartfarming.data.remote.api.WeatherApi
import com.sujoy.smartfarming.domain.model.WeatherInfo
import com.sujoy.smartfarming.domain.repo.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi
) : WeatherRepository {

    override suspend fun getWeather(
        latitude: Double,
        longitude: Double
    ): WeatherInfo {

        return api
            .getWeather(
                latitude,
                longitude,
                Constants.WEATHER_API_KEY
            )
            .toDomain()
    }
}
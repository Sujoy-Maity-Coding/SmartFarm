package com.sujoy.smartfarming.domain.repo

import com.sujoy.smartfarming.domain.model.WeatherInfo

interface WeatherRepository {

    suspend fun getWeather(
        latitude: Double,
        longitude: Double
    ): WeatherInfo
}
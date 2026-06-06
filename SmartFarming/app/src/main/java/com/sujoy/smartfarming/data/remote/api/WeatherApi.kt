package com.sujoy.smartfarming.data.remote.api

import com.sujoy.smartfarming.data.remote.dto.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("data/2.5/weather")
    suspend fun getWeather(

        @Query("lat")
        latitude: Double,

        @Query("lon")
        longitude: Double,

        @Query("appid")
        apiKey: String,

        @Query("units")
        units: String = "metric"
    ): WeatherResponse
}
package com.sujoy.smartfarming.data.mapper

import com.sujoy.smartfarming.data.remote.dto.WeatherResponse
import com.sujoy.smartfarming.domain.model.WeatherInfo

fun WeatherResponse.toDomain(): WeatherInfo {

    return WeatherInfo(

        temperature = main.temp,

        humidity = main.humidity,

        windSpeed = wind.speed
    )
}
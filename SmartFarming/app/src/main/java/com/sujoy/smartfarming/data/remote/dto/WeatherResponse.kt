package com.sujoy.smartfarming.data.remote.dto

import com.google.gson.annotations.SerializedName

data class WeatherResponse(

    @SerializedName("main")
    val main: MainDto,

    @SerializedName("wind")
    val wind: WindDto
)

data class MainDto(

    @SerializedName("temp")
    val temp: Double,

    @SerializedName("humidity")
    val humidity: Int
)

data class WindDto(

    @SerializedName("speed")
    val speed: Double
)
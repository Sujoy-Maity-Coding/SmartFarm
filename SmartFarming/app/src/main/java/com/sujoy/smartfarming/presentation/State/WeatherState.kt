package com.sujoy.smartfarming.presentation.State

data class WeatherState(

    val temperature: Double = 0.0,

    val humidity: Int = 0,

    val windSpeed: Double = 0.0,

    val isLoading: Boolean = false,

    val error: String? = null
)
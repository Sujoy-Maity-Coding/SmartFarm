package com.sujoy.smartfarming.presentation.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sujoy.smartfarming.LocationTracker
import com.sujoy.smartfarming.domain.UseCase.GetWeatherUseCase
import com.sujoy.smartfarming.presentation.State.WeatherState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(

    private val getWeatherUseCase: GetWeatherUseCase,

    private val locationTracker: LocationTracker

) : ViewModel() {

    init {

        Log.d(
            "WEATHER_TEST",
            "ViewModel Created"
        )

        getWeather()
    }

    private val _weatherState =
        MutableStateFlow(
            WeatherState()
        )

    val weatherState =
        _weatherState.asStateFlow()

    private fun getWeather() {

        viewModelScope.launch {

            try {

                val location =
                    locationTracker.getLocation()

                Log.d(
                    "WEATHER_TEST",
                    "Location = ${location?.latitude}, ${location?.longitude}"
                )

                if (location != null) {

                    val weather =

                        getWeatherUseCase(

                            latitude =
                            location.latitude,

                            longitude =
                            location.longitude
                        )

                    _weatherState.value =
                        WeatherState(

                            temperature =
                            weather.temperature,

                            humidity =
                            weather.humidity,

                            windSpeed =
                            weather.windSpeed
                        )
                }

            } catch (e: Exception) {

                Log.e(
                    "WEATHER_TEST",
                    e.stackTraceToString()
                )
            }
        }
    }
}
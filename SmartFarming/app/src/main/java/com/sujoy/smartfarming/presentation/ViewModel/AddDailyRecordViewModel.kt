package com.sujoy.smartfarming.presentation.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sujoy.smartfarming.LocationTracker
import com.sujoy.smartfarming.common.ResultState
import com.sujoy.smartfarming.domain.UseCase.GetWeatherUseCase
import com.sujoy.smartfarming.domain.UseCase.SaveDailyRecordUseCase
import com.sujoy.smartfarming.domain.model.DailyRecord
import com.sujoy.smartfarming.domain.model.GrowthStage
import com.sujoy.smartfarming.presentation.State.AddDailyRecordUiState
import com.sujoy.smartfarming.presentation.State.DashboardUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddDailyRecordViewModel @Inject constructor(
    private val saveDailyRecordUseCase: SaveDailyRecordUseCase,
    private val getWeatherUseCase: GetWeatherUseCase,
    private val locationTracker: LocationTracker
) : ViewModel() {
    init {

        loadWeather()
    }
    private val _uiState =
        MutableStateFlow(
            AddDailyRecordUiState()
        )

    val uiState =
        _uiState.asStateFlow()
    private val _saveState =
        MutableStateFlow<ResultState<Unit>>(
            ResultState.Idle
        )

    val saveState =
        _saveState.asStateFlow()

    private var currentFarmId = 0

    fun setFarmId(
        farmId: Int
    ) {
        currentFarmId = farmId
    }

    private fun loadWeather() {

        viewModelScope.launch {

            try {

                val location =
                    locationTracker.getLocation()

                if (location != null) {

                    val weather =

                        getWeatherUseCase(

                            latitude =
                            location.latitude,

                            longitude =
                            location.longitude
                        )

                    _uiState.update {

                        it.copy(

                            temperature =
                            weather.temperature,

                            humidity =
                            weather.humidity,

                            windSpeed =
                            weather.windSpeed
                        )
                    }
                }

            } catch (_: Exception) {

            }
        }
    }
    fun updateWaterGiven(
        value: String
    ) {

        _uiState.update {
            it.copy(
                waterGiven = value
            )
        }
    }
    fun updateSoilMoisture(
        value: String
    ) {

        _uiState.update {
            it.copy(
                soilMoisture = value
            )
        }
    }
    fun saveRecord() {

        viewModelScope.launch {

            try {

                val water =
                    uiState.value.waterGiven
                        .toDoubleOrNull()

                val moisture =
                    uiState.value.soilMoisture
                        .toIntOrNull()

                if (water == null) {

                    _saveState.value =
                        ResultState.Error(
                            "Enter valid water amount"
                        )

                    return@launch
                }

                if (moisture == null) {

                    _saveState.value =
                        ResultState.Error(
                            "Enter valid moisture"
                        )

                    return@launch
                }

                _saveState.value =
                    ResultState.Loading

                saveDailyRecordUseCase(

                    DailyRecord(

                        farmId =
                        currentFarmId,

                        waterGiven =
                        uiState.value.waterGiven
                            .toDouble(),

                        soilMoisture =
                        uiState.value.soilMoisture
                            .toInt(),

                        growthStage =
                        uiState.value.growthStage,

                        temperature =
                        uiState.value.temperature,

                        humidity =
                        uiState.value.humidity,

                        windSpeed =
                        uiState.value.windSpeed,

                        timestamp =
                        System.currentTimeMillis()
                    )
                )

                _saveState.value =
                    ResultState.Success(Unit)

            } catch (e: Exception) {

                _saveState.value =
                    ResultState.Error(
                        e.message
                            ?: "Unknown Error"
                    )
            }
        }
    }
    fun updateGrowthStage(
        stage: GrowthStage
    ) {

        _uiState.update {

            it.copy(
                growthStage = stage
            )
        }
    }
}
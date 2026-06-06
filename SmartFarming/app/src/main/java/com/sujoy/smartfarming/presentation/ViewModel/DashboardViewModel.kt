package com.sujoy.smartfarming.presentation.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sujoy.smartfarming.domain.UseCase.GetDailyRecordsUseCase
import com.sujoy.smartfarming.domain.UseCase.GetFarmByIdUseCase
import com.sujoy.smartfarming.domain.UseCase.PredictIrrigationUseCase
import com.sujoy.smartfarming.domain.model.IrrigationPrediction
import com.sujoy.smartfarming.presentation.State.DashboardUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
//    private val getFarmUseCase: GetFarmUseCase,
    private val getFarmByIdUseCase: GetFarmByIdUseCase,
    private val getDailyRecordsUseCase: GetDailyRecordsUseCase,
    private val predictIrrigationUseCase: PredictIrrigationUseCase
) : ViewModel(){

    private val _uiState =
        MutableStateFlow(
            DashboardUiState()
        )

    val uiState =
        _uiState.asStateFlow()

    private val _predictionState =
        MutableStateFlow<IrrigationPrediction?>(
            null
        )

    val predictionState =
        _predictionState.asStateFlow()

//    private fun getFarm() {
//
//        viewModelScope.launch {
//
//            getFarmUseCase()
//                .collect { farm ->
//
//                    _uiState.update {
//
//                        it.copy(
//                            farm = farm
//                        )
//                    }
//                }
//        }
//    }
//    private fun getRecords() {
//
//        viewModelScope.launch {
//
//            getDailyRecordsUseCase()
//                .collect { records ->
//
//                    _uiState.update {
//
//                        it.copy(
//                            records = records
//                        )
//                    }
//
//                    // Prediction Logic
//                    val farm =
//                        _uiState.value.farm
//
//                    if (
//                        records.isNotEmpty() &&
//                        farm != null
//                    ) {
//
//                        _predictionState.value =
//                            predictIrrigationUseCase(
//
//                                records.first(),
//                                farm
//                            )
//                    }
//                }
//        }
//    }
    fun loadFarm(
        farmId: Int
    ) {

        viewModelScope.launch {

            getFarmByIdUseCase(
                farmId
            ).collect { farm ->

                _uiState.update {

                    it.copy(
                        farm = farm
                    )
                }
                updatePrediction()
            }
        }

        viewModelScope.launch {

            getDailyRecordsUseCase(
                farmId
            ).collect { records ->

                _uiState.update {

                    it.copy(
                        records = records
                    )
                }
                updatePrediction()
            }
        }
    }
    private fun updatePrediction() {

        val farm = _uiState.value.farm
        val records = _uiState.value.records

        Log.d(
            "PREDICTION",
            "Farm = $farm"
        )

        Log.d(
            "PREDICTION",
            "Records Count = ${records.size}"
        )

        if (
            farm != null &&
            records.isNotEmpty()
        ) {

            val prediction =

                predictIrrigationUseCase(
                    records.first(),
                    farm
                )

            Log.d(
                "PREDICTION",
                prediction.toString()
            )

            _predictionState.value =
                prediction
        }
    }
}
package com.sujoy.smartfarming.presentation.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sujoy.smartfarming.common.ResultState
import com.sujoy.smartfarming.domain.UseCase.SaveFarmUseCase
import com.sujoy.smartfarming.domain.model.Farm
import com.sujoy.smartfarming.presentation.State.FarmSetupUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FarmSetupViewModel @Inject constructor(
    private val saveFarmUseCase: SaveFarmUseCase
) : ViewModel() {
    private val _uiState =
        MutableStateFlow(
            FarmSetupUiState()
        )

    val uiState =
        _uiState.asStateFlow()

    private val _saveFarmState =
        MutableStateFlow<ResultState<Unit>>(
            ResultState.Idle
        )

    val saveFarmState =
        _saveFarmState.asStateFlow()

    fun updateCropName(
        value: String
    ) {

        _uiState.update {
            it.copy(
                cropName = value
            )
        }
    }

    fun updateSoilType(
        value: String
    ) {

        _uiState.update {
            it.copy(
                soilType = value
            )
        }
    }

    fun updateLandArea(
        value: String
    ) {

        _uiState.update {
            it.copy(
                landArea = value
            )
        }
    }

    fun updateSowingDate(millis: Long) {
        _uiState.update { it.copy(sowingDate = millis) }
    }

    fun saveFarm() {

        viewModelScope.launch {

            try {
                _saveFarmState.value =
                    ResultState.Loading
                if (uiState.value.cropName.isBlank()) {
                    _saveFarmState.value =
                        ResultState.Error(
                            "Enter crop name"
                        )
                    return@launch
                }

                if (uiState.value.soilType.isBlank()) {
                    _saveFarmState.value =
                        ResultState.Error(
                            "Select soil type"
                        )
                    return@launch
                }

                val landArea =
                    uiState.value.landArea.toDoubleOrNull()

                if (landArea == null) {
                    _saveFarmState.value =
                        ResultState.Error(
                            "Enter valid land area"
                        )
                    return@launch
                }
                val farm = Farm(

                    cropName =
                    uiState.value.cropName,

                    soilType =
                    uiState.value.soilType,

                    landArea =
                    uiState.value.landArea
                        .toDouble(),

                    sowingDate =
                    uiState.value.sowingDate
                )

                saveFarmUseCase(
                    farm
                )

                _saveFarmState.value =
                    ResultState.Success(
                        Unit
                    )

            } catch (e: Exception) {

                _saveFarmState.value =
                    ResultState.Error(
                        e.message
                            ?: "Unknown Error"
                    )
            }
        }
    }
}
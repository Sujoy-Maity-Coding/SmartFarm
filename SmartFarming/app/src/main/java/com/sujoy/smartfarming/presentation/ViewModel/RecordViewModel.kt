package com.sujoy.smartfarming.presentation.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sujoy.smartfarming.domain.UseCase.GetDailyRecordsUseCase
import com.sujoy.smartfarming.presentation.State.RecordsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecordsViewModel @Inject constructor(

    private val getDailyRecordsUseCase:
    GetDailyRecordsUseCase

) : ViewModel() {

    private val _uiState =
        MutableStateFlow(
            RecordsUiState()
        )

    val uiState =
        _uiState.asStateFlow()

    fun loadRecords(
        farmId: Int
    ) {

        viewModelScope.launch {

            getDailyRecordsUseCase(
                farmId
            ).collect { records ->

                _uiState.update {

                    it.copy(
                        records = records
                    )
                }
            }
        }
    }
}
package com.sujoy.smartfarming.presentation.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sujoy.smartfarming.domain.UseCase.DeleteFarmUseCase
import com.sujoy.smartfarming.domain.UseCase.GetFarmsUseCase
import com.sujoy.smartfarming.domain.model.Farm
import com.sujoy.smartfarming.presentation.State.FarmListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FarmListViewModel
@Inject constructor(

    private val getFarmsUseCase:
    GetFarmsUseCase,
    private val deleteFarmUseCase:
    DeleteFarmUseCase

) : ViewModel() {

    private val _uiState =
        MutableStateFlow(
            FarmListUiState()
        )

    val uiState =
        _uiState.asStateFlow()

    init {

        getFarms()
    }

    private fun getFarms() {

        viewModelScope.launch {

            getFarmsUseCase()
                .collect { farms ->

                    _uiState.update {

                        it.copy(
                            farms = farms
                        )
                    }
                }
        }
    }
    fun deleteFarm(
        farm: Farm
    ) {

        viewModelScope.launch {

            deleteFarmUseCase(
                farm
            )
        }
    }
}
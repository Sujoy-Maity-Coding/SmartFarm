package com.sujoy.smartfarming.presentation.State

import com.sujoy.smartfarming.domain.model.Farm

data class FarmListUiState(

    val farms: List<Farm> =
        emptyList()
)
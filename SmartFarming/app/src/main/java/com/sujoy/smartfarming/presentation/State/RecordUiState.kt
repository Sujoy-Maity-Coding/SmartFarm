package com.sujoy.smartfarming.presentation.State

import com.sujoy.smartfarming.domain.model.DailyRecord

data class RecordsUiState(

    val records: List<DailyRecord> =
        emptyList(),

    val isLoading: Boolean =
        false
)
package com.sujoy.smartfarming.presentation.State

import com.sujoy.smartfarming.domain.model.DailyRecord
import com.sujoy.smartfarming.domain.model.Farm

data class DashboardUiState(

    val farm: Farm? = null,

    val records: List<DailyRecord> =
        emptyList()

)
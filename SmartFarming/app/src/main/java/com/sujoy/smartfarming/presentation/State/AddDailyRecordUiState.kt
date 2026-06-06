package com.sujoy.smartfarming.presentation.State

import com.sujoy.smartfarming.domain.model.GrowthStage

data class AddDailyRecordUiState(

    val waterGiven: String = "",

    val soilMoisture: String = "",
    val growthStage: GrowthStage = GrowthStage.SEEDLING,

    val temperature: Double = 0.0,

    val humidity: Int = 0,

    val windSpeed: Double = 0.0
)
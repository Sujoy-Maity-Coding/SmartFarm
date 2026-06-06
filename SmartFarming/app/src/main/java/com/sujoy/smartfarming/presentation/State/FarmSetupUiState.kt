package com.sujoy.smartfarming.presentation.State

data class FarmSetupUiState(

    val cropName: String = "",

    val soilType: String = "",

    val landArea: String = "",

    val sowingDate: Long =
        System.currentTimeMillis()

)
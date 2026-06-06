package com.sujoy.smartfarming.presentation.navigation

import kotlinx.serialization.Serializable

sealed class SmartFarmingRoutes {

    @Serializable
    object FarmSetupScreen : SmartFarmingRoutes()

//    @Serializable
//    object DashboardScreen : SmartFarmingRoutes()
    @Serializable
    data class DashboardScreen(
        val farmId: Int
    ) : SmartFarmingRoutes()

    @Serializable
    object SplashScreen : SmartFarmingRoutes()

//    @Serializable
//    object AddDailyRecordScreen : SmartFarmingRoutes()

    @Serializable
    data class AddDailyRecordScreen(
        val farmId: Int
    ) : SmartFarmingRoutes()

    @Serializable
    object PredictionScreen : SmartFarmingRoutes()

    @Serializable
    data class RecordDetailsScreen(
        val recordId: Int
    ) : SmartFarmingRoutes()

    @Serializable
    object FarmListScreen :
        SmartFarmingRoutes()

    @Serializable
    data class RecordsScreen(
        val farmId: Int
    ) : SmartFarmingRoutes()
}
package com.sujoy.smartfarming.presentation.navigation

import kotlinx.serialization.Serializable

sealed class SmartFarmingSubNavigation {

    @Serializable
    object SetupNavigation :
        SmartFarmingSubNavigation()

    @Serializable
    object MainNavigation :
        SmartFarmingSubNavigation()
}
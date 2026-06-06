package com.sujoy.smartfarming.presentation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.sujoy.smartfarming.presentation.componenets.RecentRecord.RecordsScreen
import com.sujoy.smartfarming.presentation.screens.AddDailyRecordScreen
import com.sujoy.smartfarming.presentation.screens.DashboardScreen
import com.sujoy.smartfarming.presentation.screens.FarmListScreen
import com.sujoy.smartfarming.presentation.screens.FarmSetupScreen
import com.sujoy.smartfarming.presentation.screens.SplashScreen

@Composable
fun SmartFarmingApp(modifier: Modifier) {

//    val viewModel:
//            StartDestinationViewModel =
//        hiltViewModel()
//
//    val state by viewModel.state
//        .collectAsStateWithLifecycle()
//
//    if (state.isLoading) {
//
//        Box(
//            modifier = Modifier.fillMaxSize(),
//            contentAlignment =
//            Alignment.Center
//        ) {
//
//            CircularProgressIndicator()
//        }
//
//        return
//    }
//
//    val startDestination =
//
//        if (state.hasFarm) {
//
//            SmartFarmingRoutes.DashboardScreen
//
//        } else {
//
//            SmartFarmingRoutes.FarmListScreen
//        }
//
    val navController =
        rememberNavController()

    NavHost(
        navController = navController,
        startDestination = SmartFarmingRoutes.SplashScreen
    ) {

        composable<
                SmartFarmingRoutes.FarmSetupScreen
                > {

            FarmSetupScreen(

                onFarmSaved = {

//                    navController.navigate(
//                        SmartFarmingRoutes.DashboardScreen
//                    ) {
//
//                        popUpTo(
//                            SmartFarmingRoutes.FarmSetupScreen
//                        ) {
//                            inclusive = true
//                        }
//                    }
                    navController.popBackStack()
                }
            )
        }
        composable<SmartFarmingRoutes.SplashScreen> {
            SplashScreen(
                onFinished = {
                    navController.navigate(SmartFarmingRoutes.FarmListScreen) {
                        popUpTo(SmartFarmingRoutes.SplashScreen) { inclusive = true }
                    }
                }
            )
        }
        composable<SmartFarmingRoutes.FarmListScreen> {
            FarmListScreen(navController = navController)
        }

        composable<
                SmartFarmingRoutes.DashboardScreen
                > {

            val args =

                it.toRoute<
                        SmartFarmingRoutes
                        .DashboardScreen
                        >()

            DashboardScreen(

                farmId =
                args.farmId,

                onRecordsClick = {

                    navController.navigate(

                        SmartFarmingRoutes
                            .RecordsScreen(
                                args.farmId
                            )
                    )
                },

                onAddRecordClick = {

                    navController.navigate(

                        SmartFarmingRoutes
                            .AddDailyRecordScreen(
                                args.farmId
                            )
                    )
                }
            )
        }

        composable<
                SmartFarmingRoutes
                .AddDailyRecordScreen
                > {

            val args =

                it.toRoute<
                        SmartFarmingRoutes
                        .AddDailyRecordScreen
                        >()

            AddDailyRecordScreen(

                farmId =
                args.farmId,

                onBack = {

                    navController.popBackStack()
                }
            )
        }

        composable<
                SmartFarmingRoutes.PredictionScreen
                > {

//            PredictionScreen()
        }

        composable<
                SmartFarmingRoutes
                .RecordDetailsScreen
                > {

            val args =

                it.toRoute<
                        SmartFarmingRoutes
                        .RecordDetailsScreen
                        >()

// RecordDetailsScreen(
//     recordId =
//     args.recordId
// )
        }
        composable<
                SmartFarmingRoutes
                .RecordsScreen
                > {

            val args =

                it.toRoute<
                        SmartFarmingRoutes
                        .RecordsScreen
                        >()

            RecordsScreen(

                farmId =
                args.farmId,

                onBack = {

                    navController
                        .popBackStack()
                }
            )
        }
    }
}
package com.sujoy.smartfarming.presentation.screens

import android.Manifest
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sujoy.smartfarming.presentation.ViewModel.DashboardViewModel
import com.sujoy.smartfarming.presentation.ViewModel.WeatherViewModel
import com.sujoy.smartfarming.presentation.componenets.Graph.MoistureTrendCard
import com.sujoy.smartfarming.presentation.componenets.HeroHeader
import com.sujoy.smartfarming.presentation.componenets.Prediction.PredictionCard
import com.sujoy.smartfarming.presentation.componenets.Record.RecentRecordsCard
import com.sujoy.smartfarming.presentation.componenets.TodayStatsCard
import com.sujoy.smartfarming.ui.theme.GreenDeep
import com.sujoy.smartfarming.ui.theme.GreenPrimary

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DashboardScreen(

    farmId: Int,

    onRecordsClick:
        () -> Unit,

    viewModel:
    DashboardViewModel =
        hiltViewModel(),

    onAddRecordClick:
        () -> Unit
) {
    LaunchedEffect(farmId) { viewModel.loadFarm(farmId) }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted -> Log.d("LOCATION", "Granted = $granted") }
    LaunchedEffect(Unit) { permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION) }

    val weatherViewModel: WeatherViewModel = hiltViewModel()
    val weatherState by weatherViewModel.weatherState.collectAsStateWithLifecycle()
    val uiState      by viewModel.uiState.collectAsStateWithLifecycle()
    val prediction   by viewModel.predictionState.collectAsStateWithLifecycle()

    Scaffold(
        containerColor = GreenDeep,
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddRecordClick,
                containerColor = GreenPrimary,
                contentColor = GreenDeep,
                shape = CircleShape,
                modifier = Modifier.shadow(12.dp, CircleShape)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add record")
            }
        }
    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            verticalArrangement = Arrangement.spacedBy(0.dp)
        ) {

            // ── 1. Hero header ────────────────────────────────────────────
            item { HeroHeader(uiState, weatherState) }

            // ── 2. AI Prediction card ─────────────────────────────────────
            item {
                prediction?.let {
                    Spacer(Modifier.height(20.dp))
                    PredictionCard(it, uiState, weatherState,
                        Modifier.padding(horizontal = 16.dp))
                }
            }

            // ── 3. Today's stats ─────────────────────────────────────────
            item {
                Spacer(Modifier.height(16.dp))
                TodayStatsCard(uiState,
                    Modifier.padding(horizontal = 16.dp))
            }

            // ── 4. Moisture trend ────────────────────────────────────────
            item {
                Spacer(Modifier.height(16.dp))
                MoistureTrendCard(uiState,
                    Modifier.padding(horizontal = 16.dp))
            }

            // ── 5. Recent records (enriched) ──────────────────────────────
            item {
                Spacer(Modifier.height(16.dp))
                RecentRecordsCard(uiState,onRecordsClick,
                    Modifier.padding(horizontal = 16.dp))
                Spacer(Modifier.height(88.dp)) // FAB clearance
            }
        }
    }
}
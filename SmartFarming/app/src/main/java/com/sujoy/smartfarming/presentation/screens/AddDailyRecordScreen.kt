package com.sujoy.smartfarming.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sujoy.smartfarming.R
import com.sujoy.smartfarming.common.ResultState
import com.sujoy.smartfarming.domain.model.GrowthStage
import com.sujoy.smartfarming.presentation.ViewModel.AddDailyRecordViewModel
import com.sujoy.smartfarming.presentation.componenets.DailyRecord.DarkTextFieldDaily
import com.sujoy.smartfarming.presentation.componenets.DailyRecord.MoistureStatusBar
import com.sujoy.smartfarming.presentation.componenets.DailyRecord.RecordFormCard
import com.sujoy.smartfarming.presentation.componenets.DailyRecord.RecordHeader
import com.sujoy.smartfarming.presentation.componenets.DailyRecord.StageChip
import com.sujoy.smartfarming.presentation.componenets.DailyRecord.WeatherSnapshotCard
import com.sujoy.smartfarming.ui.theme.AmberColor
import com.sujoy.smartfarming.ui.theme.BlueColor
import com.sujoy.smartfarming.ui.theme.GreenDeep
import com.sujoy.smartfarming.ui.theme.GreenPrimary
import com.sujoy.smartfarming.ui.theme.RedColor

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AddDailyRecordScreen(
    farmId: Int,
    onBack: () -> Unit,
    viewModel: AddDailyRecordViewModel = hiltViewModel()
) {
    val uiState   by viewModel.uiState.collectAsStateWithLifecycle()
    val saveState by viewModel.saveState.collectAsStateWithLifecycle()

    LaunchedEffect(saveState) { if (saveState is ResultState.Success) onBack() }
    LaunchedEffect(farmId)    { viewModel.setFarmId(farmId) }

    // Derive moisture status once
    val moisture     = uiState.soilMoisture.toIntOrNull() ?: 0
    val moistureColor = when {
        moisture >= 70 -> GreenPrimary
        moisture >= 40 -> AmberColor
        else           -> RedColor
    }
    val moistureLabel = when {
        moisture >= 70 -> "Healthy"
        moisture >= 40 -> "Moderate"
        else           -> "Low"
    }
    val moistureIcon = when {
        moisture >= 70 -> R.drawable.pure_water
        moisture >= 40 -> R.drawable.moisturizing
        else           -> R.drawable.alert
    }

    Scaffold(containerColor = GreenDeep) { padding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            verticalArrangement = Arrangement.spacedBy(0.dp)
        ) {

            // ── Hero header ───────────────────────────────────────────
            item { RecordHeader(onBack = onBack) }

            item {
                Column(
                    modifier = Modifier.padding(
                        start = 16.dp, end = 16.dp, top = 20.dp, bottom = 32.dp
                    ),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {

                    // 1 · Weather snapshot ────────────────────────────
                    WeatherSnapshotCard(uiState)

                    // 2 · Water given ─────────────────────────────────
                    RecordFormCard(
                        stepNumber = "01",
                        title      = "Water Given",
                        subtitle   = "How much water was supplied today?"
                    ) {
                        DarkTextFieldDaily(
                            value         = uiState.waterGiven,
                            onValueChange = { viewModel.updateWaterGiven(it) },
                            label         = "Amount in Liters",
                            image          = R.drawable.opacity,
                            keyboardType  = KeyboardType.Decimal,
                            accentColor   = BlueColor
                        )
                    }

                    // 3 · Growth stage ────────────────────────────────
                    RecordFormCard(
                        stepNumber = "02",
                        title      = "Growth Stage",
                        subtitle   = "Current plant development phase"
                    ) {
                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement   = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            GrowthStage.entries.forEach { stage ->
                                StageChip(
                                    label    = stage.name
                                        .lowercase()
                                        .replaceFirstChar { it.uppercase() },
                                    selected = uiState.growthStage == stage,
                                    onClick  = { viewModel.updateGrowthStage(stage) }
                                )
                            }
                        }
                    }

                    // 4 · Soil moisture ───────────────────────────────
                    RecordFormCard(
                        stepNumber = "03",
                        title      = "Soil Moisture",
                        subtitle   = "Current moisture reading (%)"
                    ) {
                        DarkTextFieldDaily(
                            value         = uiState.soilMoisture,
                            onValueChange = { viewModel.updateSoilMoisture(it) },
                            label         = "Percentage (0–100)",
                            image          = R.drawable.water,
                            keyboardType  = KeyboardType.Number,
                            accentColor   = GreenPrimary
                        )

                        // Live status indicator
                        if (uiState.soilMoisture.isNotEmpty()) {
                            Spacer(Modifier.height(12.dp))
                            MoistureStatusBar(
                                moisture = moisture,
                                color = moistureColor,
                                label  = moistureLabel,
                                image  = moistureIcon
                            )
                        }
                    }

                    // ── Save button ───────────────────────────────────
                    val isLoading = saveState is ResultState.Loading

                    Button(
                        onClick  = { viewModel.saveRecord() },
                        enabled  = !isLoading,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        shape  = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = GreenPrimary,
                            contentColor   = GreenDeep
                        )
                    ) {
                        if (isLoading) {
                            CircularProgressIndicator(
                                modifier    = Modifier.size(20.dp),
                                color       = GreenDeep,
                                strokeWidth = 2.dp
                            )
                        } else {
                            Icon(painter = painterResource(id = R.drawable.tap), contentDescription = null,
                                modifier = Modifier.size(20.dp))
                            Spacer(Modifier.width(8.dp))
                            Text(
                                "Save Daily Record",
                                fontWeight    = FontWeight.Bold,
                                fontSize      = 15.sp,
                                letterSpacing = 0.3.sp
                            )
                        }
                    }
                }
            }
        }
    }
}
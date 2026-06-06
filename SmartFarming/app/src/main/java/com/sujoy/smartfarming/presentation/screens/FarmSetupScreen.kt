package com.sujoy.smartfarming.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.sujoy.smartfarming.R
import com.sujoy.smartfarming.common.ResultState
import com.sujoy.smartfarming.presentation.ViewModel.FarmSetupViewModel
import com.sujoy.smartfarming.presentation.componenets.FarmSetup.DarkTextField
import com.sujoy.smartfarming.presentation.componenets.FarmSetup.FormCard
import com.sujoy.smartfarming.presentation.componenets.FarmSetup.SetupHeader
import com.sujoy.smartfarming.presentation.componenets.FarmSetup.SoilChip
import com.sujoy.smartfarming.presentation.componenets.date.SowingDateField
import com.sujoy.smartfarming.ui.theme.GreenDeep
import com.sujoy.smartfarming.ui.theme.GreenPrimary
import com.sujoy.smartfarming.ui.theme.InputBg
import com.sujoy.smartfarming.ui.theme.InputBorder
import com.sujoy.smartfarming.ui.theme.TextMuted
import com.sujoy.smartfarming.ui.theme.TextPrimary
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FarmSetupScreen(
    viewModel: FarmSetupViewModel = hiltViewModel(),
    onFarmSaved: () -> Unit
) {
    val uiState   by viewModel.uiState.collectAsStateWithLifecycle()
    val saveState by viewModel.saveFarmState.collectAsStateWithLifecycle()

    val soilTypes = listOf("Clay", "Sandy", "Loamy", "Silty")

    val formattedDate = remember(uiState.sowingDate) {
        SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(Date(uiState.sowingDate))
    }

    LaunchedEffect(saveState) {
        if (saveState is ResultState.Success) onFarmSaved()
    }

    Scaffold(containerColor = GreenDeep) { padding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            verticalArrangement = Arrangement.spacedBy(0.dp)
        ) {

            // ── Hero header ───────────────────────────────────────────
            item { SetupHeader() }

            // ── Form body ─────────────────────────────────────────────
            item {
                Column(
                    modifier = Modifier.padding(
                        start = 16.dp, end = 16.dp, top = 20.dp, bottom = 32.dp
                    ),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {

                    // 1 · Crop name
                    FormCard(
                        stepNumber = "01",
                        title = "Crop Information",
                        subtitle = "What crop are you growing?"
                    ) {
                        DarkTextField(
                            value = uiState.cropName,
                            onValueChange = { viewModel.updateCropName(it) },
                            label = "Crop Name",
                            image = R.drawable.plant
                        )
                    }

                    // 2 · Soil type
                    FormCard(
                        stepNumber = "02",
                        title = "Soil Type",
                        subtitle = "Select the soil composition"
                    ) {
                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement   = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            soilTypes.forEach { soil ->
                                SoilChip(
                                    label    = soil,
                                    selected = uiState.soilType == soil,
                                    onClick  = { viewModel.updateSoilType(soil) }
                                )
                            }
                        }
                    }

                    // 3 · Land area
                    FormCard(
                        stepNumber = "03",
                        title = "Farm Area",
                        subtitle = "Total cultivable land"
                    ) {
                        DarkTextField(
                            value = uiState.landArea,
                            onValueChange = { viewModel.updateLandArea(it) },
                            label = "Area in Acres",
                            image = R.drawable.spatial,
                            keyboardType = KeyboardType.Decimal
                        )
                    }

                    // 4 · Sowing date
                    FormCard(
                        stepNumber = "04",
                        title      = "Sowing Date",
                        subtitle   = "When did you plant the seeds?"
                    ) {
                        SowingDateField(
                            selectedDateMs = uiState.sowingDate,
                            onDateSelected = { viewModel.updateSowingDate(it) }
                        )
                    }

                    Spacer(Modifier.height(6.dp))

                    // ── Save button ───────────────────────────────────
                    val isLoading = saveState is ResultState.Loading
                    Button(
                        onClick  = { viewModel.saveFarm() },
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
                                modifier = Modifier.size(20.dp),
                                color    = GreenDeep,
                                strokeWidth = 2.dp
                            )
                        } else {
                            Icon(painter = painterResource(id = R.drawable.farm),
                                contentDescription = null,
                                modifier = Modifier.size(20.dp))
                            Spacer(Modifier.width(8.dp))
                            Text(
                                "Create Digital Farm",
                                fontWeight = FontWeight.Bold,
                                fontSize   = 15.sp,
                                letterSpacing = 0.3.sp
                            )
                        }
                    }
                }
            }
        }
    }
}
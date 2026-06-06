package com.sujoy.smartfarming.presentation.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.sujoy.smartfarming.presentation.ViewModel.FarmListViewModel
import com.sujoy.smartfarming.presentation.componenets.FarmList.EmptyState
import com.sujoy.smartfarming.presentation.componenets.FarmList.FarmCard
import com.sujoy.smartfarming.presentation.componenets.FarmList.FarmListHeader
import com.sujoy.smartfarming.presentation.navigation.SmartFarmingRoutes
import com.sujoy.smartfarming.ui.theme.GreenDeep
import com.sujoy.smartfarming.ui.theme.GreenPrimary
import com.sujoy.smartfarming.ui.theme.TextPrimary

@OptIn(ExperimentalFoundationApi::class, ExperimentalLayoutApi::class)
@Composable
fun FarmListScreen(
    navController: NavController,
    viewModel: FarmListViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        containerColor = GreenDeep,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(SmartFarmingRoutes.FarmSetupScreen) },
                containerColor = GreenPrimary,
                contentColor = GreenDeep,
                shape = CircleShape,
                modifier = Modifier
                    .size(58.dp)
                    .shadow(16.dp, CircleShape)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Farm",
                    modifier = Modifier.size(26.dp))
            }
        }
    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            verticalArrangement = Arrangement.spacedBy(0.dp)
        ) {

            // ── Hero ─────────────────────────────────────────────────
            item { FarmListHeader(farmCount = uiState.farms.size) }

            // ── "Your Farms" label row ────────────────────────────────
            if (uiState.farms.isNotEmpty()) {
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp, vertical = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            "Your Farms",
                            color = TextPrimary,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            letterSpacing = (-0.3).sp
                        )
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(20.dp))
                                .background(GreenPrimary.copy(0.15f))
                                .border(1.dp, GreenPrimary.copy(0.3f), RoundedCornerShape(20.dp))
                                .padding(horizontal = 12.dp, vertical = 4.dp)
                        ) {
                            Text(
                                "${uiState.farms.size} Active",
                                color = GreenPrimary,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }
            }

            // ── Empty state ───────────────────────────────────────────
            if (uiState.farms.isEmpty()) {
                item {
                    Spacer(Modifier.height(40.dp))
                    EmptyState(modifier = Modifier.padding(horizontal = 20.dp))
                }
            } else {
                // ── Farm cards ────────────────────────────────────────
                items(uiState.farms) { farm ->
                    FarmCard(
                        farm = farm,
                        modifier = Modifier
                            .padding(horizontal = 16.dp, vertical = 6.dp)
                            .clickable(
                                onClick = {
                                    navController.navigate(
                                        SmartFarmingRoutes.DashboardScreen(farm.id)
                                    )
                                }
                            ),
                        onDelete = { viewModel.deleteFarm(farm) }
                    )
                }
                item { Spacer(Modifier.height(88.dp)) }
            }
        }
    }
}
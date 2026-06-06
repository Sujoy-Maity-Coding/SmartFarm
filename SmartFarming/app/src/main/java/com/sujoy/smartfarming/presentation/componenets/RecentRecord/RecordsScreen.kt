package com.sujoy.smartfarming.presentation.componenets.RecentRecord

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sujoy.smartfarming.presentation.State.DashboardUiState
import com.sujoy.smartfarming.presentation.Utils.SectionLabel
import com.sujoy.smartfarming.presentation.ViewModel.DashboardViewModel
import com.sujoy.smartfarming.presentation.ViewModel.RecordsViewModel
import com.sujoy.smartfarming.ui.theme.CardBg
import com.sujoy.smartfarming.ui.theme.CardBorder
import com.sujoy.smartfarming.ui.theme.GreenDeep
import com.sujoy.smartfarming.ui.theme.GreenPrimary
import com.sujoy.smartfarming.ui.theme.TextMuted
import com.sujoy.smartfarming.ui.theme.TextPrimary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecordsScreen(
    farmId: Int,
    onBack: () -> Unit,
    viewModel: RecordsViewModel = hiltViewModel()
) {
    LaunchedEffect(farmId) { viewModel.loadRecords(farmId) }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val listState = rememberLazyListState()

    // Filter state
    var selectedFilter by remember { mutableStateOf(RecordFilter.ALL) }

    val filteredRecords = remember(uiState.records, selectedFilter) {
        when (selectedFilter) {
            RecordFilter.ALL      -> uiState.records
            RecordFilter.HEALTHY  -> uiState.records.filter { it.soilMoisture >= 70 }
            RecordFilter.MODERATE -> uiState.records.filter { it.soilMoisture in 40..69 }
            RecordFilter.LOW      -> uiState.records.filter { it.soilMoisture < 40 }
        }
    }

    // Summary stats derived from all records
    val avgMoisture  = uiState.records.map { it.soilMoisture }.average()
        .takeIf { it.isFinite() }?.toInt() ?: 0
    val totalWater   = uiState.records.sumOf { it.waterGiven }
    val avgTemp      = uiState.records.map { it.temperature }.average()
        .takeIf { it.isFinite() }

    Scaffold(containerColor = GreenDeep) { padding ->

        LazyColumn(
            state    = listState,
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            verticalArrangement = Arrangement.spacedBy(0.dp)
        ) {

            // ── Header ────────────────────────────────────────────────
            item {
                RecordsHeader(
                    onBack       = onBack,
                    recordCount  = uiState.records.size
                )
            }

            // ── Summary stats row ─────────────────────────────────────
            if (uiState.records.isNotEmpty()) {
                item {
                    Spacer(Modifier.height(20.dp))
                    SummaryStatsRow(
                        avgMoisture = avgMoisture,
                        totalWater  = totalWater,
                        avgTemp     = avgTemp,
                        modifier    = Modifier.padding(horizontal = 16.dp)
                    )
                }
            }

            // ── Filter chips ──────────────────────────────────────────
            item {
                Spacer(Modifier.height(16.dp))
                FilterRow(
                    selected  = selectedFilter,
                    onChange  = { selectedFilter = it },
                    counts    = RecordFilter.entries.associateWith { filter ->
                        when (filter) {
                            RecordFilter.ALL      -> uiState.records.size
                            RecordFilter.HEALTHY  -> uiState.records.count { it.soilMoisture >= 70 }
                            RecordFilter.MODERATE -> uiState.records.count { it.soilMoisture in 40..69 }
                            RecordFilter.LOW      -> uiState.records.count { it.soilMoisture < 40 }
                        }
                    }
                )
            }

            // ── Section label ─────────────────────────────────────────
            item {
                Spacer(Modifier.height(16.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = if (selectedFilter == RecordFilter.ALL)
                            "All Records" else "${selectedFilter.label} Records",
                        color = TextPrimary,
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp
                    )
                    Text(
                        "${filteredRecords.size} entries",
                        color = TextMuted, fontSize = 12.sp
                    )
                }
                Spacer(Modifier.height(10.dp))
            }

            // ── Empty state ───────────────────────────────────────────
            if (filteredRecords.isEmpty()) {
                item {
                    RecordsEmptyState(
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
            } else {
                // ── Record cards ──────────────────────────────────────
                itemsIndexed(filteredRecords) { index, record ->
                    RecordDetailCard(
                        record   = record,
                        index    = uiState.records.size - uiState.records.indexOf(record),
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 5.dp)
                    )
                }
            }

            item { Spacer(Modifier.height(40.dp)) }
        }
    }
}
package com.sujoy.smartfarming.presentation.componenets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.sujoy.smartfarming.R
import com.sujoy.smartfarming.presentation.State.DashboardUiState
import com.sujoy.smartfarming.presentation.Utils.SectionLabel
import com.sujoy.smartfarming.presentation.Utils.calculateGrowthDays
import com.sujoy.smartfarming.ui.theme.CardBg
import com.sujoy.smartfarming.ui.theme.CardBorder
import com.sujoy.smartfarming.ui.theme.GreenPrimary

@Composable
fun TodayStatsCard(uiState: DashboardUiState, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = CardBg),
        border = BorderStroke(1.dp, CardBorder)
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            SectionLabel("TODAY'S STATS")
            Spacer(Modifier.height(14.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.fillMaxWidth()) {
                StatTile(
                    value = "${uiState.records.firstOrNull()?.soilMoisture ?: 0}%",
                    label = "Soil Moisture",
                    image = R.drawable.water,
                    color = GreenPrimary,
                    modifier = Modifier.weight(1f)
                )
                StatTile(
                    value = "${uiState.records.firstOrNull()?.waterGiven ?: 0} L",
                    label = "Last Supply",
                    image = R.drawable.opacity,
                    color = Color(0xFF38BDF8),
                    modifier = Modifier.weight(1f)
                )
                StatTile(
                    value = "Day ${calculateGrowthDays(uiState.farm?.sowingDate ?: System.currentTimeMillis())}",
                    label = "Since Sowing",
                    image = R.drawable.calendar,
                    color = Color(0xFFFBBF24),
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}
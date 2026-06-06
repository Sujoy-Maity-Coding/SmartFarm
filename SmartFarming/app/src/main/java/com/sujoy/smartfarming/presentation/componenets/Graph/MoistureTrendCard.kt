package com.sujoy.smartfarming.presentation.componenets.Graph

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sujoy.smartfarming.presentation.State.DashboardUiState
import com.sujoy.smartfarming.presentation.Utils.SectionLabel
import com.sujoy.smartfarming.ui.theme.CardBg
import com.sujoy.smartfarming.ui.theme.CardBorder
import com.sujoy.smartfarming.ui.theme.GreenPrimary

@Composable
fun MoistureTrendCard(uiState: DashboardUiState, modifier: Modifier = Modifier) {
    val records = uiState.records.takeLast(7)
    val dayLabels = listOf("M", "T", "W", "T", "F", "S", "S")
    val maxMoisture = records.maxOfOrNull { it.soilMoisture } ?: 100

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = CardBg),
        border = BorderStroke(1.dp, CardBorder)
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                SectionLabel("MOISTURE TREND (7 DAYS)")
                if (records.isNotEmpty()) {
                    val avg = records.map { it.soilMoisture }.average().toInt()
                    Text("Avg $avg%", color = GreenPrimary, fontSize = 12.sp, fontWeight = FontWeight.Medium)
                }
            }
            Spacer(Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.Bottom
            ) {
                if (records.isEmpty()) {
                    repeat(7) { i ->
                        MoistureBarImproved(moisture = 0, dayLabel = dayLabels.getOrElse(i) { "—" }, isToday = false, maxMoisture = 100)
                    }
                } else {
                    records.forEachIndexed { i, record ->
                        MoistureBarImproved(
                            moisture = record.soilMoisture,
                            dayLabel = dayLabels.getOrElse(i) { "—" },
                            isToday = i == records.lastIndex,
                            maxMoisture = maxMoisture
                        )
                    }
                }
            }
        }
    }
}
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
import com.sujoy.smartfarming.R
import com.sujoy.smartfarming.presentation.State.DashboardUiState
import com.sujoy.smartfarming.presentation.Utils.SectionLabel
import com.sujoy.smartfarming.presentation.ViewModel.DashboardViewModel
import com.sujoy.smartfarming.ui.theme.AmberColor
import com.sujoy.smartfarming.ui.theme.BlueColor
import com.sujoy.smartfarming.ui.theme.CardBg
import com.sujoy.smartfarming.ui.theme.CardBorder
import com.sujoy.smartfarming.ui.theme.GreenDeep
import com.sujoy.smartfarming.ui.theme.GreenPrimary
import com.sujoy.smartfarming.ui.theme.TextMuted
import com.sujoy.smartfarming.ui.theme.TextPrimary

@Composable
fun SummaryStatsRow(
    avgMoisture: Int,
    totalWater: Double,
    avgTemp: Double?,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        SummaryTile(
            icon     = R.drawable.moisturizing,
            value    = "$avgMoisture%",
            label    = "Avg Moisture",
            color    = GreenPrimary,
            modifier = Modifier.weight(1f)
        )
        SummaryTile(
            icon     = R.drawable.opacity,
            value    = "${String.format("%.1f", totalWater)} L",
            label    = "Total Water",
            color    = BlueColor,
            modifier = Modifier.weight(1f)
        )
        SummaryTile(
            icon     = R.drawable.smart_temperature,
            value    = avgTemp?.let { "${String.format("%.1f", it)}°C" } ?: "—",
            label    = "Avg Temp",
            color    = AmberColor,
            modifier = Modifier.weight(1f)
        )
    }
}
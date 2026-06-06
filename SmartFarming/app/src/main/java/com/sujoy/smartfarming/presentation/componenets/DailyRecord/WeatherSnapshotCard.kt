package com.sujoy.smartfarming.presentation.componenets.DailyRecord

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
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
import com.sujoy.smartfarming.presentation.State.AddDailyRecordUiState
import com.sujoy.smartfarming.presentation.ViewModel.AddDailyRecordViewModel
import com.sujoy.smartfarming.ui.theme.AmberColor
import com.sujoy.smartfarming.ui.theme.BlueColor
import com.sujoy.smartfarming.ui.theme.CardBorder
import com.sujoy.smartfarming.ui.theme.GreenDark
import com.sujoy.smartfarming.ui.theme.GreenDeep
import com.sujoy.smartfarming.ui.theme.GreenPrimary
import com.sujoy.smartfarming.ui.theme.RedColor
import com.sujoy.smartfarming.ui.theme.TextPrimary
import com.sujoy.smartfarming.ui.theme.TextSecondary

@Composable
fun WeatherSnapshotCard(uiState: AddDailyRecordUiState) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape    = RoundedCornerShape(20.dp),
        colors   = CardDefaults.cardColors(containerColor = Color(0xFF0B1F2E)),
        border   = BorderStroke(1.dp, BlueColor.copy(0.25f))
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Box(Modifier.size(5.dp).background(BlueColor, CircleShape))
                Text("CURRENT WEATHER", color = BlueColor.copy(0.8f),
                    fontWeight = FontWeight.Bold, fontSize = 11.sp, letterSpacing = 1.sp)
            }

            Spacer(Modifier.height(14.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                WeatherMetric(
                    image  = R.drawable.temp_fall,
                    value = "${uiState.temperature}°C",
                    label = "Temp",
                    color = AmberColor
                )
                VerticalDivider(
                    modifier = Modifier.height(40.dp),
                    color    = CardBorder
                )
                WeatherMetric(
                    image = R.drawable.smart_temperature,
                    value = "${uiState.humidity}%",
                    label = "Humidity",
                    color = BlueColor
                )
                VerticalDivider(
                    modifier = Modifier.height(40.dp),
                    color    = CardBorder
                )
                WeatherMetric(
                    image = R.drawable.wind,
                    value = "${uiState.windSpeed} m/s",
                    label = "Wind",
                    color = GreenPrimary
                )
            }
        }
    }
}
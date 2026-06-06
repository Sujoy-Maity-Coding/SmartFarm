package com.sujoy.smartfarming.presentation.componenets.RecentRecord

import androidx.compose.animation.core.EaseOutCubic
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sujoy.smartfarming.R
import com.sujoy.smartfarming.domain.model.DailyRecord
import com.sujoy.smartfarming.presentation.State.DashboardUiState
import com.sujoy.smartfarming.presentation.Utils.SectionLabel
import com.sujoy.smartfarming.presentation.ViewModel.DashboardViewModel
import com.sujoy.smartfarming.ui.theme.AmberColor
import com.sujoy.smartfarming.ui.theme.BlueColor
import com.sujoy.smartfarming.ui.theme.CardBg
import com.sujoy.smartfarming.ui.theme.CardBorder
import com.sujoy.smartfarming.ui.theme.GreenDeep
import com.sujoy.smartfarming.ui.theme.GreenPrimary
import com.sujoy.smartfarming.ui.theme.RedColor
import com.sujoy.smartfarming.ui.theme.TextMuted
import com.sujoy.smartfarming.ui.theme.TextPrimary
import com.sujoy.smartfarming.ui.theme.TextSecondary
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun RecordDetailCard(
    record: DailyRecord,
    index: Int,
    modifier: Modifier = Modifier
) {
    val moistureColor = when {
        record.soilMoisture >= 70 -> GreenPrimary
        record.soilMoisture >= 40 -> AmberColor
        else                      -> RedColor
    }
    val moistureLabel = when {
        record.soilMoisture >= 70 -> "Healthy"
        record.soilMoisture >= 40 -> "Moderate"
        else                      -> "Low"
    }

    val dateStr = remember(record.timestamp) {
        SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(Date(record.timestamp))
    }
    val timeStr = remember(record.timestamp) {
        SimpleDateFormat("hh:mm a", Locale.getDefault()).format(Date(record.timestamp))
    }

    // Moisture bar progress animation
    val animMoisture by animateFloatAsState(
        targetValue   = record.soilMoisture / 100f,
        animationSpec = tween(700, easing = EaseOutCubic),
        label         = "moisture_$index"
    )

    Card(
        modifier = modifier.fillMaxWidth(),
        shape    = RoundedCornerShape(18.dp),
        colors   = CardDefaults.cardColors(containerColor = CardBg),
        border   = BorderStroke(1.dp, CardBorder)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            // ── Top row: index + date/time + stage badge ──────────────
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    // Record number circle
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .background(GreenPrimary.copy(0.1f), CircleShape)
                            .border(1.dp, GreenPrimary.copy(0.25f), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("#$index", color = GreenPrimary,
                            fontSize = 11.sp, fontWeight = FontWeight.Bold)
                    }

                    Column {
                        Text(dateStr, color = TextPrimary,
                            fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
                        Text(timeStr, color = TextMuted, fontSize = 11.sp)
                    }
                }

                // Growth stage badge
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(GreenPrimary.copy(0.1f))
                        .border(1.dp, GreenPrimary.copy(0.25f), RoundedCornerShape(8.dp))
                        .padding(horizontal = 9.dp, vertical = 4.dp)
                ) {
                    Text(
                        record.growthStage.name
                            .lowercase().replaceFirstChar { it.uppercase() },
                        color = GreenPrimary, fontSize = 11.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            Spacer(Modifier.height(14.dp))
            HorizontalDivider(color = CardBorder, thickness = 0.5.dp)
            Spacer(Modifier.height(14.dp))

            // ── 4-metric grid ─────────────────────────────────────────
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                MetricBox(
                    icon  = R.drawable.water,
                    value = "${record.waterGiven} L",
                    label = "Water Given",
                    color = BlueColor,
                    modifier = Modifier.weight(1f)
                )
                MetricBox(
                    icon  = R.drawable.temp_fall,
                    value = "${record.temperature}°C",
                    label = "Temperature",
                    color = AmberColor,
                    modifier = Modifier.weight(1f)
                )
                MetricBox(
                    icon  = R.drawable.smart_temperature,
                    value = "${record.humidity}%",
                    label = "Humidity",
                    color = BlueColor.copy(0.8f),
                    modifier = Modifier.weight(1f)
                )
                MetricBox(
                    icon  = R.drawable.wind,
                    value = "${record.windSpeed}",
                    label = "Wind m/s",
                    color = TextSecondary,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(Modifier.height(14.dp))

            // ── Moisture status + animated bar ────────────────────────
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .background(moistureColor, CircleShape)
                    )
                    Text("$moistureLabel Moisture",
                        color = moistureColor, fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold)
                }
                Text("${record.soilMoisture}%",
                    color = moistureColor, fontSize = 13.sp,
                    fontWeight = FontWeight.Bold)
            }

            Spacer(Modifier.height(6.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(5.dp)
                    .clip(RoundedCornerShape(3.dp))
                    .background(CardBorder)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(animMoisture)
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(3.dp))
                        .background(
                            Brush.horizontalGradient(
                                listOf(moistureColor.copy(0.5f), moistureColor)
                            )
                        )
                )
            }
        }
    }
}
package com.sujoy.smartfarming.presentation.componenets

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sujoy.smartfarming.R
import com.sujoy.smartfarming.presentation.State.DashboardUiState
import com.sujoy.smartfarming.presentation.State.WeatherState
import com.sujoy.smartfarming.ui.theme.GreenDark
import com.sujoy.smartfarming.ui.theme.GreenDeep
import com.sujoy.smartfarming.ui.theme.GreenPrimary
import com.sujoy.smartfarming.ui.theme.TextPrimary
import com.sujoy.smartfarming.ui.theme.TextSecondary

@Composable
fun HeroHeader(uiState: DashboardUiState, weatherState: WeatherState) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(GreenDark, GreenDeep)
                )
            )
    ) {
        // Subtle mesh circles for visual depth
        Canvas(modifier = Modifier.matchParentSize()) {
            drawCircle(
                color = GreenPrimary.copy(alpha = 0.06f),
                radius = 180.dp.toPx(),
                center = Offset(size.width * 0.85f, -20f)
            )
            drawCircle(
                color = GreenPrimary.copy(alpha = 0.04f),
                radius = 120.dp.toPx(),
                center = Offset(size.width * 0.1f, size.height * 1.2f)
            )
        }

        Column(modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 20.dp, bottom = 24.dp)) {
            // Top row: farm name + icons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.plant),
                            contentDescription = null,
                            tint = GreenPrimary,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(Modifier.width(6.dp))
                        Text(
                            text = uiState.farm?.cropName ?: "My Farm",
                            color = TextPrimary,
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = (-0.5).sp
                        )
                    }
                    Spacer(Modifier.height(2.dp))
                    Text(
                        text = "${uiState.farm?.soilType ?: "—"} · ${uiState.farm?.landArea ?: "—"} ha",
                        color = TextSecondary,
                        fontSize = 13.sp
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = {}) {
                        Icon(painter = painterResource(id = R.drawable.notification), contentDescription = "Notifications",
                            tint = TextSecondary, modifier = Modifier.size(22.dp))
                    }
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .background(GreenPrimary.copy(alpha = 0.15f), CircleShape)
                            .border(1.dp, GreenPrimary.copy(alpha = 0.3f), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(painter = painterResource(id = R.drawable.user), contentDescription = "Profile",
                            tint = GreenPrimary, modifier = Modifier.size(20.dp))
                    }
                }
            }

            Spacer(Modifier.height(20.dp))

            // Weather pill row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.Black.copy(alpha = 0.25f))
                    .padding(vertical = 14.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                WeatherPill(image = R.drawable.temp_fall, value = "${weatherState.temperature}°C", label = "Temp")
                WeatherDivider()
                WeatherPill(image = R.drawable.smart_temperature, value = "${weatherState.humidity}%", label = "Humidity")
                WeatherDivider()
                WeatherPill(image = R.drawable.wind, value = "${weatherState.windSpeed} km/h", label = "Wind")
                WeatherDivider()
                WeatherPill(image = R.drawable.clear_sky, value = "Sunny", label = "Weather")
            }
        }
    }
}
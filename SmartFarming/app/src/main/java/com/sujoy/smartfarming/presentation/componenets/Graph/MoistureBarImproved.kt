package com.sujoy.smartfarming.presentation.componenets.Graph

import androidx.compose.animation.core.EaseOutCubic
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sujoy.smartfarming.ui.theme.CardBorder
import com.sujoy.smartfarming.ui.theme.GreenPrimary
import com.sujoy.smartfarming.ui.theme.TextSecondary

@Composable
fun MoistureBarImproved(moisture: Int, dayLabel: String, isToday: Boolean, maxMoisture: Int) {
    val maxBarHeight = 80.dp
    val barHeight: Dp = if (maxMoisture > 0)
        (moisture.toFloat() / maxMoisture * maxBarHeight.value).coerceAtLeast(6f).dp
    else 6.dp

    // Animate height on first render
    val animHeight by animateDpAsState(targetValue = barHeight, animationSpec = tween(600, easing = EaseOutCubic), label = "barH")

    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Bottom) {
        // Moisture % label on hover / always for today
        if (isToday) {
            Text("$moisture%", color = GreenPrimary, fontSize = 10.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(2.dp))
        } else {
            Spacer(Modifier.height(14.dp))
        }

        Box(
            modifier = Modifier
                .width(24.dp)
                .height(animHeight)
                .clip(RoundedCornerShape(6.dp))
                .background(
                    if (isToday)
                        Brush.verticalGradient(listOf(GreenPrimary, GreenPrimary.copy(0.5f)))
                    else if (moisture >= 60)
                        Brush.verticalGradient(listOf(GreenPrimary.copy(0.7f), GreenPrimary.copy(0.2f)))
                    else
                        Brush.verticalGradient(listOf(CardBorder, CardBorder.copy(0.5f)))
                )
        )
        Spacer(Modifier.height(6.dp))
        Text(
            text = dayLabel,
            color = if (isToday) GreenPrimary else TextSecondary.copy(alpha = 0.5f),
            fontSize = 11.sp,
            fontWeight = if (isToday) FontWeight.Bold else FontWeight.Normal
        )
    }
}
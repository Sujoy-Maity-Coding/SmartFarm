package com.sujoy.smartfarming.presentation.componenets.FarmList

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sujoy.smartfarming.R
import com.sujoy.smartfarming.ui.theme.CardBg
import com.sujoy.smartfarming.ui.theme.CardBorder
import com.sujoy.smartfarming.ui.theme.GreenPrimary
import com.sujoy.smartfarming.ui.theme.TextMuted
import com.sujoy.smartfarming.ui.theme.TextPrimary

@Composable
fun EmptyState(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .background(CardBg)
            .border(1.dp, CardBorder, RoundedCornerShape(24.dp))
            .padding(48.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Box(
            modifier = Modifier
                .size(72.dp)
                .background(
                    Brush.radialGradient(listOf(GreenPrimary.copy(0.2f), GreenPrimary.copy(0.02f))),
                    RoundedCornerShape(20.dp)
                )
                .border(1.dp, GreenPrimary.copy(0.3f), RoundedCornerShape(20.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(painter = painterResource(R.drawable.farm),
                contentDescription = null, tint = GreenPrimary,
                modifier = Modifier.size(34.dp))
        }

        Text("No Farms Yet", color = TextPrimary, fontWeight = FontWeight.Bold, fontSize = 20.sp)

        Text(
            "Tap + to add your first farm and\nstart smart irrigation planning.",
            color = TextMuted,
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            lineHeight = 22.sp
        )
    }
}
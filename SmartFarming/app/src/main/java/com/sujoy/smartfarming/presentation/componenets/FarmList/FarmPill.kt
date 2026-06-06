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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sujoy.smartfarming.R
import com.sujoy.smartfarming.ui.theme.GreenPrimary
import com.sujoy.smartfarming.ui.theme.TextSecondary

@Composable
fun FarmPill(
    image: Int,
    label: String
) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(GreenPrimary.copy(0.08f))
            .border(1.dp, GreenPrimary.copy(0.2f), RoundedCornerShape(10.dp))
            .padding(horizontal = 10.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Icon(painter = painterResource(id = image), contentDescription = null,
            tint = GreenPrimary, modifier = Modifier.size(13.dp))
        Text(label, color = TextSecondary, fontSize = 12.sp,
            fontWeight = FontWeight.Medium)
    }
}
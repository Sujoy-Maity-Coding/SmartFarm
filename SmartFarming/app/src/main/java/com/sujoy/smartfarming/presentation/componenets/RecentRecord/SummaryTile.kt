package com.sujoy.smartfarming.presentation.componenets.RecentRecord

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sujoy.smartfarming.ui.theme.TextMuted
import com.sujoy.smartfarming.ui.theme.TextPrimary

@Composable
fun SummaryTile(
    icon: Int,
    value: String,
    label: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(color.copy(0.08f))
            .border(1.dp, color.copy(0.2f), RoundedCornerShape(16.dp))
            .padding(12.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Icon(painter = painterResource(id = icon), contentDescription = null,
            tint = color, modifier = Modifier.size(16.dp))
        Spacer(Modifier.height(8.dp))
        Text(value, color = TextPrimary, fontWeight = FontWeight.Bold,
            fontSize = 16.sp, letterSpacing = (-0.3).sp)
        Text(label, color = TextMuted, fontSize = 11.sp)
    }
}
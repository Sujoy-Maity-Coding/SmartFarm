package com.sujoy.smartfarming.presentation.componenets.Record

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sujoy.smartfarming.R
import com.sujoy.smartfarming.domain.model.DailyRecord
import com.sujoy.smartfarming.ui.theme.GreenPrimary
import com.sujoy.smartfarming.ui.theme.TextPrimary
import com.sujoy.smartfarming.ui.theme.TextSecondary
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun RecordRow(record: DailyRecord) {
    val dateStr = remember(record.timestamp) {
        SimpleDateFormat("dd MMM, hh:mm a", Locale.getDefault()).format(Date(record.timestamp))
    }
    val moistureColor = when {
        record.soilMoisture >= 70 -> GreenPrimary
        record.soilMoisture >= 40 -> Color(0xFFFBBF24)
        else -> Color(0xFFF87171)
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Stage badge
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(GreenPrimary.copy(0.1f), RoundedCornerShape(10.dp))
                .border(1.dp, GreenPrimary.copy(0.2f), RoundedCornerShape(10.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.plant),
                contentDescription = null,
                tint = GreenPrimary,
                modifier = Modifier.size(18.dp)
            )
        }

        Spacer(Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    record.growthStage.name.lowercase().replaceFirstChar { it.uppercase() },
                    color = TextPrimary,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp
                )
                Spacer(Modifier.width(6.dp))
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(moistureColor.copy(0.15f))
                        .padding(horizontal = 6.dp, vertical = 1.dp)
                ) {
                    Text("${record.soilMoisture}%", color = moistureColor, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                }
            }
            Text(dateStr, color = TextSecondary.copy(0.6f), fontSize = 11.sp)
        }

        // Right side metrics
        Column(horizontalAlignment = Alignment.End) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(painter = painterResource(id = R.drawable.water), contentDescription = null,
                    tint = Color(0xFF38BDF8), modifier = Modifier.size(12.dp))
                Spacer(Modifier.width(3.dp))
                Text("${record.waterGiven} L", color = Color(0xFF38BDF8),
                    fontSize = 13.sp, fontWeight = FontWeight.Medium)
            }
            Spacer(Modifier.height(2.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(painter = painterResource(id = R.drawable.smart_temperature), contentDescription = null,
                    tint = Color(0xFFFBBF24), modifier = Modifier.size(12.dp))
                Spacer(Modifier.width(3.dp))
                Text("${record.temperature}°C", color = Color(0xFFFBBF24).copy(0.8f), fontSize = 11.sp)
            }
        }
    }
}
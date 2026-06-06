package com.sujoy.smartfarming.presentation.componenets.FarmSetup

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sujoy.smartfarming.ui.theme.CardBg
import com.sujoy.smartfarming.ui.theme.CardBorder
import com.sujoy.smartfarming.ui.theme.GreenPrimary
import com.sujoy.smartfarming.ui.theme.TextMuted
import com.sujoy.smartfarming.ui.theme.TextPrimary

@Composable
fun FormCard(
    stepNumber: String,
    title: String,
    subtitle: String,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape  = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = CardBg),
        border = BorderStroke(1.dp, CardBorder)
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            // Step header
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(28.dp)
                        .background(GreenPrimary.copy(0.15f), CircleShape)
                        .border(1.dp, GreenPrimary.copy(0.4f), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(stepNumber, color = GreenPrimary,
                        fontSize = 10.sp, fontWeight = FontWeight.Bold,
                        letterSpacing = 0.5.sp)
                }
                Column {
                    Text(title, color = TextPrimary,
                        fontWeight = FontWeight.SemiBold, fontSize = 15.sp)
                    Text(subtitle, color = TextMuted, fontSize = 12.sp)
                }
            }

            Spacer(Modifier.height(14.dp))
            HorizontalDivider(color = CardBorder, thickness = 0.5.dp)
            Spacer(Modifier.height(14.dp))

            content()
        }
    }
}
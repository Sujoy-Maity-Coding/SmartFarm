package com.sujoy.smartfarming.presentation.componenets.FarmList

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sujoy.smartfarming.R
import com.sujoy.smartfarming.ui.theme.GreenDark
import com.sujoy.smartfarming.ui.theme.GreenDeep
import com.sujoy.smartfarming.ui.theme.GreenPrimary
import com.sujoy.smartfarming.ui.theme.TextPrimary
import com.sujoy.smartfarming.ui.theme.TextSecondary

@Composable
fun FarmListHeader(farmCount: Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Brush.verticalGradient(listOf(GreenDark, GreenDeep))
            )
    ) {
        // Decorative circles
        Canvas(Modifier.matchParentSize()) {
            drawCircle(GreenPrimary.copy(0.06f), 200.dp.toPx(),
                Offset(size.width * 0.9f, -30f)
            )
            drawCircle(GreenPrimary.copy(0.04f), 130.dp.toPx(),
                Offset(size.width * 0.05f, size.height * 1.3f))
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 20.dp, bottom = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icon + title
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .background(
                            Brush.radialGradient(
                                listOf(GreenPrimary.copy(0.25f), GreenPrimary.copy(0.05f))
                            ),
                            RoundedCornerShape(14.dp)
                        )
                        .border(1.dp, GreenPrimary.copy(0.35f), RoundedCornerShape(14.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.farm),
                        contentDescription = null,
                        tint = GreenPrimary,
                        modifier = Modifier.size(26.dp)
                    )
                }

                Column {
                    Text(
                        "My Smart Farms",
                        color = TextPrimary,
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp,
                        letterSpacing = (-0.5).sp
                    )
                    Text(
                        if (farmCount > 0) "$farmCount farms monitored"
                        else "Manage your digital twins",
                        color = TextSecondary,
                        fontSize = 13.sp
                    )
                }
            }

            // Action icons
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                HeaderIconBtn(icon = painterResource(id = R.drawable.notification))
                Box(
                    modifier = Modifier
                        .size(38.dp)
                        .background(GreenPrimary.copy(0.15f), CircleShape)
                        .border(1.dp, GreenPrimary.copy(0.3f), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(painter = painterResource(id = R.drawable.user), contentDescription = "Profile",
                        tint = GreenPrimary, modifier = Modifier.size(20.dp))
                }
            }
        }
    }
}
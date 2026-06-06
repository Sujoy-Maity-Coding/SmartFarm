package com.sujoy.smartfarming.presentation.componenets.FarmSetup

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.sujoy.smartfarming.R
import com.sujoy.smartfarming.common.ResultState
import com.sujoy.smartfarming.presentation.ViewModel.FarmSetupViewModel
import com.sujoy.smartfarming.presentation.componenets.FarmSetup.FormCard
import com.sujoy.smartfarming.presentation.componenets.FarmSetup.SetupHeader
import com.sujoy.smartfarming.ui.theme.GreenDark
import com.sujoy.smartfarming.ui.theme.GreenDeep
import com.sujoy.smartfarming.ui.theme.GreenPrimary
import com.sujoy.smartfarming.ui.theme.TextMuted
import com.sujoy.smartfarming.ui.theme.TextPrimary
import com.sujoy.smartfarming.ui.theme.TextSecondary
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun SetupHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Brush.verticalGradient(listOf(GreenDark, GreenDeep)))
    ) {
        Canvas(Modifier.matchParentSize()) {
            drawCircle(GreenPrimary.copy(0.07f), 180.dp.toPx(),
                Offset(size.width * 0.88f, -20f)
            )
            drawCircle(GreenPrimary.copy(0.04f), 100.dp.toPx(),
                Offset(size.width * 0.08f, size.height * 1.4f))
        }

        Column(
            modifier = Modifier.padding(
                start = 20.dp, end = 20.dp, top = 20.dp, bottom = 28.dp
            )
        ) {
            // Back nav hint + step label
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Icon(painter = painterResource(id = R.drawable.left), contentDescription = null,
                    tint = TextSecondary, modifier = Modifier.size(14.dp))
                Text("New Farm Setup", color = TextSecondary, fontSize = 12.sp,
                    letterSpacing = 0.8.sp, fontWeight = FontWeight.Medium)
            }

            Spacer(Modifier.height(14.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                // Icon badge
                Box(
                    modifier = Modifier
                        .size(52.dp)
                        .background(
                            Brush.radialGradient(
                                listOf(GreenPrimary.copy(0.28f), GreenPrimary.copy(0.05f))
                            ),
                            RoundedCornerShape(16.dp)
                        )
                        .border(1.dp, GreenPrimary.copy(0.4f), RoundedCornerShape(16.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(painter = painterResource(id = R.drawable.farm), contentDescription = null,
                        tint = GreenPrimary, modifier = Modifier.size(28.dp))
                }

                Column {
                    Text(
                        "Setup Digital Twin",
                        color = TextPrimary,
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp,
                        letterSpacing = (-0.5).sp
                    )
                    Text(
                        "Fill in 4 simple steps to get started",
                        color = TextSecondary,
                        fontSize = 13.sp
                    )
                }
            }

            Spacer(Modifier.height(20.dp))

            // Progress step dots
            Row(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                listOf("Crop", "Soil", "Area", "Date").forEachIndexed { i, label ->
                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(GreenPrimary.copy(if (i == 0) 0.2f else 0.07f))
                            .border(1.dp, GreenPrimary.copy(if (i == 0) 0.5f else 0.15f),
                                RoundedCornerShape(20.dp))
                            .padding(horizontal = 10.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(5.dp)
                                .background(
                                    if (i == 0) GreenPrimary else GreenPrimary.copy(0.4f),
                                    CircleShape
                                )
                        )
                        Text("${i + 1}. $label",
                            color = if (i == 0) GreenPrimary else TextMuted,
                            fontSize = 11.sp, fontWeight = FontWeight.Medium)
                    }
                }
            }
        }
    }
}
package com.sujoy.smartfarming.presentation.componenets.DailyRecord

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sujoy.smartfarming.R
import com.sujoy.smartfarming.common.ResultState
import com.sujoy.smartfarming.domain.model.GrowthStage
import com.sujoy.smartfarming.presentation.ViewModel.AddDailyRecordViewModel
import com.sujoy.smartfarming.ui.theme.AmberColor
import com.sujoy.smartfarming.ui.theme.BlueColor
import com.sujoy.smartfarming.ui.theme.GreenDark
import com.sujoy.smartfarming.ui.theme.GreenDeep
import com.sujoy.smartfarming.ui.theme.GreenPrimary
import com.sujoy.smartfarming.ui.theme.RedColor
import com.sujoy.smartfarming.ui.theme.TextPrimary
import com.sujoy.smartfarming.ui.theme.TextSecondary

@Composable
fun RecordHeader(onBack: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Brush.verticalGradient(listOf(GreenDark, GreenDeep)))
    ) {
        Canvas(Modifier.matchParentSize()) {
            drawCircle(GreenPrimary.copy(0.07f), 170.dp.toPx(),
                Offset(size.width * 0.88f, -20f))
            drawCircle(GreenPrimary.copy(0.04f), 90.dp.toPx(),
                Offset(size.width * 0.05f, size.height * 1.5f)
            )
        }

        Column(
            modifier = Modifier.padding(
                start = 20.dp, end = 20.dp, top = 20.dp, bottom = 28.dp
            )
        ) {
            // Back button
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(GreenPrimary.copy(0.1f))
                    .border(1.dp, GreenPrimary.copy(0.2f), RoundedCornerShape(10.dp))
                    .clickable { onBack() }
                    .padding(horizontal = 10.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(painter = painterResource(id = R.drawable.left), contentDescription = "Back",
                    tint = TextSecondary, modifier = Modifier.size(13.dp))
                Text("Back", color = TextSecondary, fontSize = 12.sp)
            }

            Spacer(Modifier.height(16.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(52.dp)
                        .background(
                            Brush.radialGradient(
                                listOf(BlueColor.copy(0.28f), BlueColor.copy(0.04f))
                            ),
                            RoundedCornerShape(16.dp)
                        )
                        .border(1.dp, BlueColor.copy(0.4f), RoundedCornerShape(16.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(painter = painterResource(id = R.drawable.water), contentDescription = null,
                        tint = BlueColor, modifier = Modifier.size(26.dp))
                }

                Column {
                    Text(
                        "Daily Record",
                        color         = TextPrimary,
                        fontWeight    = FontWeight.Bold,
                        fontSize      = 22.sp,
                        letterSpacing = (-0.5).sp
                    )
                    Text(
                        "Track today's irrigation & soil",
                        color    = TextSecondary,
                        fontSize = 13.sp
                    )
                }
            }
        }
    }
}
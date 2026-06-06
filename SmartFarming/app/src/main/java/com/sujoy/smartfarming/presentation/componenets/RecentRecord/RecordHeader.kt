package com.sujoy.smartfarming.presentation.componenets.RecentRecord

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sujoy.smartfarming.R
import com.sujoy.smartfarming.presentation.State.DashboardUiState
import com.sujoy.smartfarming.presentation.Utils.SectionLabel
import com.sujoy.smartfarming.presentation.ViewModel.DashboardViewModel
import com.sujoy.smartfarming.ui.theme.CardBg
import com.sujoy.smartfarming.ui.theme.CardBorder
import com.sujoy.smartfarming.ui.theme.GreenDark
import com.sujoy.smartfarming.ui.theme.GreenDeep
import com.sujoy.smartfarming.ui.theme.GreenPrimary
import com.sujoy.smartfarming.ui.theme.TextMuted
import com.sujoy.smartfarming.ui.theme.TextPrimary
import com.sujoy.smartfarming.ui.theme.TextSecondary

@Composable
fun RecordsHeader(onBack: () -> Unit, recordCount: Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Brush.verticalGradient(listOf(GreenDark, GreenDeep)))
    ) {
        Canvas(Modifier.matchParentSize()) {
            drawCircle(GreenPrimary.copy(0.07f), 170.dp.toPx(),
                Offset(size.width * 0.88f, -20f)
            )
            drawCircle(GreenPrimary.copy(0.04f), 90.dp.toPx(),
                Offset(0.08f * size.width, size.height * 1.5f))
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
                Text("Dashboard", color = TextSecondary, fontSize = 12.sp)
            }

            Spacer(Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(52.dp)
                            .background(
                                Brush.radialGradient(
                                    listOf(GreenPrimary.copy(0.28f), GreenPrimary.copy(0.04f))
                                ),
                                RoundedCornerShape(16.dp)
                            )
                            .border(1.dp, GreenPrimary.copy(0.4f), RoundedCornerShape(16.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(painter = painterResource(id = R.drawable.services), contentDescription = null,
                            tint = GreenPrimary, modifier = Modifier.size(26.dp))
                    }

                    Column {
                        Text("Irrigation Records",
                            color = TextPrimary, fontWeight = FontWeight.Bold,
                            fontSize = 22.sp, letterSpacing = (-0.5).sp)
                        Text("Complete field history",
                            color = TextSecondary, fontSize = 13.sp)
                    }
                }

                // Record count badge
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(GreenPrimary.copy(0.15f))
                        .border(1.dp, GreenPrimary.copy(0.3f), RoundedCornerShape(12.dp))
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Text("$recordCount\nrecords",
                        color = GreenPrimary, fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        lineHeight = 16.sp)
                }
            }
        }
    }
}
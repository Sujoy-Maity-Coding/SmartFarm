package com.sujoy.smartfarming.presentation.componenets.FarmList

import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sujoy.smartfarming.R
import com.sujoy.smartfarming.domain.model.Farm
import com.sujoy.smartfarming.ui.theme.CardBg
import com.sujoy.smartfarming.ui.theme.CardBorder
import com.sujoy.smartfarming.ui.theme.DangerRed
import com.sujoy.smartfarming.ui.theme.GreenPrimary
import com.sujoy.smartfarming.ui.theme.TextMuted
import com.sujoy.smartfarming.ui.theme.TextPrimary
import com.sujoy.smartfarming.ui.theme.TextSecondary
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FarmCard(
    farm: Farm,
    modifier: Modifier = Modifier,
    onDelete: () -> Unit
) {
    // Pulse dot animation for "Online" badge
    val infiniteTransition = rememberInfiniteTransition(label = "dot")
    val dotAlpha by infiniteTransition.animateFloat(
        initialValue = 1f, targetValue = 0.3f,
        animationSpec = infiniteRepeatable(tween(900, easing = EaseInOut), RepeatMode.Reverse),
        label = "dotAlpha"
    )

    val growthDays = remember(farm.sowingDate) {
        ((System.currentTimeMillis() - farm.sowingDate) / 86_400_000L).toInt().coerceAtLeast(0)
    }
    val sowingDateStr = remember(farm.sowingDate) {
        SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(Date(farm.sowingDate))
    }

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = CardBg),
        border = BorderStroke(1.dp, CardBorder)
    ) {
        Column(modifier = Modifier.padding(18.dp)) {

            // ── Top row: crop name + online badge ────────────────────
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .background(
                                Brush.radialGradient(
                                    listOf(GreenPrimary.copy(0.22f), GreenPrimary.copy(0.04f))
                                ),
                                RoundedCornerShape(14.dp)
                            )
                            .border(1.dp, GreenPrimary.copy(0.3f), RoundedCornerShape(14.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.plant),
                            contentDescription = null,
                            tint = GreenPrimary,
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    Column {
                        Text(
                            farm.cropName.replaceFirstChar { it.uppercase() },
                            color = TextPrimary,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            letterSpacing = (-0.4).sp
                        )
                        Text(
                            "Digital twin active",
                            color = TextSecondary.copy(0.6f),
                            fontSize = 12.sp
                        )
                    }
                }

                // Online badge with animated dot
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(GreenPrimary.copy(0.1f))
                        .border(1.dp, GreenPrimary.copy(0.25f), RoundedCornerShape(20.dp))
                        .padding(horizontal = 10.dp, vertical = 5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(7.dp)
                            .background(GreenPrimary.copy(dotAlpha), CircleShape)
                    )
                    Text("Active", color = GreenPrimary, fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold)
                }
            }

            Spacer(Modifier.height(16.dp))

            // ── Meta pills ────────────────────────────────────────────
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FarmPill(image = R.drawable.soil, label = farm.soilType)
                FarmPill(image = R.drawable.calendar, label = sowingDateStr)
                FarmPill(image = R.drawable.spatial, label = "${farm.landArea} acres")
                FarmPill(image = R.drawable.opacity, label = "Day $growthDays")
            }

            Spacer(Modifier.height(16.dp))
            HorizontalDivider(color = CardBorder, thickness = 0.5.dp)
            Spacer(Modifier.height(12.dp))

            // ── Footer ────────────────────────────────────────────────
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(painter = painterResource(id = R.drawable.tap), contentDescription = null,
                        tint = TextMuted, modifier = Modifier.size(14.dp))
                    Text("Tap to view predictions", color = TextMuted, fontSize = 12.sp)
                }

                // Delete button — subtle red ghost
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .background(DangerRed.copy(0.08f))
                        .border(1.dp, DangerRed.copy(0.2f), RoundedCornerShape(10.dp))
                        .clickable { onDelete() }
                        .padding(horizontal = 10.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(Icons.Default.Delete, contentDescription = "Delete",
                        tint = DangerRed, modifier = Modifier.size(14.dp))
                    Text("Delete", color = DangerRed, fontSize = 12.sp,
                        fontWeight = FontWeight.Medium)
                }
            }
        }
    }
}
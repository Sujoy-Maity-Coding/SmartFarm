package com.sujoy.smartfarming.presentation.screens

import androidx.compose.animation.core.*
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sujoy.smartfarming.R
import com.sujoy.smartfarming.ui.theme.GreenDark
import com.sujoy.smartfarming.ui.theme.GreenDeep
import com.sujoy.smartfarming.ui.theme.GreenPrimary
import com.sujoy.smartfarming.ui.theme.TextMuted
import com.sujoy.smartfarming.ui.theme.TextPrimary
import com.sujoy.smartfarming.ui.theme.TextSecondary
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onFinished: () -> Unit) {

    // ── Animation states ──────────────────────────────────────────────────────
    var startAnim by remember { mutableStateOf(false) }

    // Logo scale+fade in
    val logoScale by animateFloatAsState(
        targetValue   = if (startAnim) 1f else 0.4f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow),
        label         = "logoScale"
    )
    val logoAlpha by animateFloatAsState(
        targetValue   = if (startAnim) 1f else 0f,
        animationSpec = tween(600, easing = EaseOut),
        label         = "logoAlpha"
    )

    // Text slides up + fades in (delayed)
    val textOffsetY by animateFloatAsState(
        targetValue   = if (startAnim) 0f else 40f,
        animationSpec = tween(700, delayMillis = 300, easing = EaseOutCubic),
        label         = "textOffset"
    )
    val textAlpha by animateFloatAsState(
        targetValue   = if (startAnim) 1f else 0f,
        animationSpec = tween(700, delayMillis = 300, easing = EaseOut),
        label         = "textAlpha"
    )

    // Tagline delayed further
    val tagAlpha by animateFloatAsState(
        targetValue   = if (startAnim) 1f else 0f,
        animationSpec = tween(600, delayMillis = 600, easing = EaseOut),
        label         = "tagAlpha"
    )

    // Loading bar fill
    val loadProgress by animateFloatAsState(
        targetValue   = if (startAnim) 1f else 0f,
        animationSpec = tween(2000, delayMillis = 500, easing = EaseInOut),
        label         = "loadProgress"
    )

    // Pulse ring on logo
    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val pulseScale by infiniteTransition.animateFloat(
        initialValue  = 1f,
        targetValue   = 1.18f,
        animationSpec = infiniteRepeatable(
            tween(1200, easing = EaseInOut), RepeatMode.Reverse
        ),
        label         = "pulse"
    )
    val pulseAlpha by infiniteTransition.animateFloat(
        initialValue  = 0.5f,
        targetValue   = 0f,
        animationSpec = infiniteRepeatable(
            tween(1200, easing = EaseInOut), RepeatMode.Reverse
        ),
        label         = "pulseAlpha"
    )

    // Floating dot animation
    val dot1Y by infiniteTransition.animateFloat(
        initialValue  = 0f,
        targetValue   = -14f,
        animationSpec = infiniteRepeatable(
            tween(1400, easing = EaseInOut), RepeatMode.Reverse
        ),
        label = "dot1"
    )
    val dot2Y by infiniteTransition.animateFloat(
        initialValue  = 0f,
        targetValue   = -10f,
        animationSpec = infiniteRepeatable(
            tween(1100, delayMillis = 200, easing = EaseInOut), RepeatMode.Reverse
        ),
        label = "dot2"
    )
    val dot3Y by infiniteTransition.animateFloat(
        initialValue  = 0f,
        targetValue   = -16f,
        animationSpec = infiniteRepeatable(
            tween(1600, delayMillis = 400, easing = EaseInOut), RepeatMode.Reverse
        ),
        label = "dot3"
    )

    LaunchedEffect(Unit) {
        startAnim = true
        delay(2800)
        onFinished()
    }

    // ── UI ────────────────────────────────────────────────────────────────────
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colorStops = arrayOf(
                        0.0f to GreenDark,
                        0.45f to GreenDeep,
                        1.0f to Color(0xFF061A0E)
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {

        // ── Background mesh circles ───────────────────────────────────────────
        Canvas(modifier = Modifier.fillMaxSize()) {
            // Large diffuse glow top-right
            drawCircle(
                brush  = Brush.radialGradient(
                    colors = listOf(GreenPrimary.copy(0.12f), Color.Transparent),
                    radius = 320.dp.toPx()
                ),
                radius = 320.dp.toPx(),
                center = Offset(size.width * 0.85f, size.height * 0.12f)
            )
            // Smaller glow bottom-left
            drawCircle(
                brush  = Brush.radialGradient(
                    colors = listOf(GreenPrimary.copy(0.08f), Color.Transparent),
                    radius = 200.dp.toPx()
                ),
                radius = 200.dp.toPx(),
                center = Offset(size.width * 0.1f, size.height * 0.85f)
            )
            // Tiny accent dot top-left
            drawCircle(
                color  = GreenPrimary.copy(0.15f),
                radius = 60.dp.toPx(),
                center = Offset(size.width * 0.08f, size.height * 0.2f)
            )
        }

        // ── Main content column ───────────────────────────────────────────────
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {

            Spacer(Modifier.weight(1f))

            // ── Logo area ─────────────────────────────────────────────────────
            Box(contentAlignment = Alignment.Center) {

                // Outer pulse ring
                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .scale(pulseScale)
                        .alpha(pulseAlpha * logoAlpha)
                        .clip(CircleShape)
                        .background(GreenPrimary.copy(0.15f))
                )

                // Inner ring
                Box(
                    modifier = Modifier
                        .size(96.dp)
                        .scale(logoScale)
                        .alpha(logoAlpha)
                        .clip(CircleShape)
                        .background(GreenPrimary.copy(0.12f))
                        .then(
                            Modifier.gradientBorder(
                                width = 1.5.dp,
                                brush = Brush.linearGradient(
                                    listOf(GreenPrimary.copy(0.8f), GreenPrimary.copy(0.2f))
                                ),
                                shape = CircleShape
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    // Icon box
                    Box(
                        modifier = Modifier
                            .size(70.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .background(
                                Brush.radialGradient(
                                    listOf(GreenPrimary.copy(0.4f), GreenPrimary.copy(0.05f))
                                )
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.farm),
                            contentDescription = null,
                            tint     = GreenPrimary,
                            modifier = Modifier.size(38.dp)
                        )
                    }
                }
            }

            Spacer(Modifier.height(32.dp))

            // ── App name ──────────────────────────────────────────────────────
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .alpha(textAlpha)
                    .offset(y = textOffsetY.dp)
            ) {
                Text(
                    text       = "SmartFarm",
                    color      = TextPrimary,
                    fontSize   = 36.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = (-1).sp
                )
                Spacer(Modifier.height(4.dp))
                // Subtitle with green accent word
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("AI-Powered  ", color = TextMuted, fontSize = 14.sp)
                    Text("Irrigation", color = GreenPrimary,
                        fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
                    Text("  Intelligence", color = TextMuted, fontSize = 14.sp)
                }
            }

            Spacer(Modifier.height(12.dp))

            // ── Tagline ───────────────────────────────────────────────────────
            Text(
                text      = "Grow smarter. Save water. Farm better.",
                color     = TextSecondary.copy(0.6f),
                fontSize  = 13.sp,
                textAlign = TextAlign.Center,
                modifier  = Modifier
                    .alpha(tagAlpha)
                    .padding(horizontal = 48.dp)
            )

            Spacer(Modifier.weight(1f))

            // ── Loading section ───────────────────────────────────────────────
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .alpha(tagAlpha)
                    .padding(bottom = 56.dp)
            ) {
                // Animated loading dots
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 20.dp)
                ) {
                    LoadingDot(offsetY = dot1Y, delay = 0)
                    LoadingDot(offsetY = dot2Y, delay = 150)
                    LoadingDot(offsetY = dot3Y, delay = 300)
                }

                // Progress track
                Box(
                    modifier = Modifier
                        .width(160.dp)
                        .height(3.dp)
                        .clip(RoundedCornerShape(2.dp))
                        .background(GreenPrimary.copy(0.12f))
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(loadProgress)
                            .fillMaxHeight()
                            .clip(RoundedCornerShape(2.dp))
                            .background(
                                Brush.horizontalGradient(
                                    listOf(GreenPrimary.copy(0.5f), GreenPrimary)
                                )
                            )
                    )
                }

                Spacer(Modifier.height(14.dp))

                Text(
                    "Initialising digital farm...",
                    color    = TextMuted,
                    fontSize = 11.sp,
                    letterSpacing = 0.5.sp
                )
            }
        }

        // ── Version tag ───────────────────────────────────────────────────────
        Text(
            "v1.0.0",
            color    = TextMuted.copy(alpha = 0.4f),
            fontSize = 10.sp,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp)
                .alpha(tagAlpha)
        )
    }
}

@Composable
private fun LoadingDot(offsetY: Float, delay: Int) {
    Box(
        modifier = Modifier
            .offset(y = offsetY.dp)
            .size(7.dp)
            .background(GreenPrimary, CircleShape)
    )
}

// Helper extension for border on Modifier
private fun Modifier.gradientBorder(
    width: Dp,
    brush: Brush,
    shape: Shape
): Modifier {
    return this.then(
        Modifier.border(
            width = width,
            brush = brush,
            shape = shape
        )
    )
}
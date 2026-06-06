package com.sujoy.smartfarming.presentation.componenets.RecentRecord

import androidx.compose.ui.graphics.Color

enum class RecordFilter(val label: String, val color: Color) {
    ALL     ("All",      Color(0xFF22C55E)),
    HEALTHY ("Healthy",  Color(0xFF22C55E)),
    MODERATE("Moderate", Color(0xFFFBBF24)),
    LOW     ("Low",      Color(0xFFF87171))
}
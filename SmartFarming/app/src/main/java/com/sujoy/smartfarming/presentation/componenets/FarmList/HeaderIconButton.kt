package com.sujoy.smartfarming.presentation.componenets.FarmList

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.sujoy.smartfarming.ui.theme.TextSecondary

@Composable
fun HeaderIconBtn(icon: Painter) {
    IconButton(onClick = {}) {
        Icon(icon, contentDescription = null, tint = TextSecondary,
            modifier = Modifier.size(22.dp))
    }
}
package com.sujoy.smartfarming.presentation.componenets.date

import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.sujoy.smartfarming.R
import com.sujoy.smartfarming.ui.theme.GreenPrimary
import com.sujoy.smartfarming.ui.theme.InputBg
import com.sujoy.smartfarming.ui.theme.InputBorder
import com.sujoy.smartfarming.ui.theme.TextMuted
import com.sujoy.smartfarming.ui.theme.TextPrimary
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SowingDateField(
    selectedDateMs: Long,
    onDateSelected: (Long) -> Unit
) {
    var showPicker by remember { mutableStateOf(false) }

    val formattedDate = remember(selectedDateMs) {
        SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(Date(selectedDateMs))
    }

    // ── Date field row ────────────────────────────────────────────────────────
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(InputBg)
            .border(1.dp, InputBorder, RoundedCornerShape(14.dp))
            .clickable { showPicker = true }
            .padding(horizontal = 16.dp, vertical = 14.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.calendar),
                contentDescription = null,
                tint = GreenPrimary,
                modifier = Modifier.size(18.dp)
            )
            Column {
                Text("Selected date", color = TextMuted, fontSize = 11.sp)
                Text(
                    formattedDate,
                    color = TextPrimary,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp
                )
            }
        }

        Box(
            modifier = Modifier
                .size(32.dp)
                .background(GreenPrimary.copy(0.12f), CircleShape)
                .border(1.dp, GreenPrimary.copy(0.3f), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.planning),
                contentDescription = "Change date",
                tint = GreenPrimary,
                modifier = Modifier.size(16.dp)
            )
        }
    }

    // ── Date Picker Dialog ────────────────────────────────────────────────────
    if (showPicker) {
        DarkDatePickerDialog(
            initialDateMs  = selectedDateMs,
            onDismiss      = { showPicker = false },
            onConfirm      = { millis ->
                onDateSelected(millis)
                showPicker = false
            }
        )
    }
}
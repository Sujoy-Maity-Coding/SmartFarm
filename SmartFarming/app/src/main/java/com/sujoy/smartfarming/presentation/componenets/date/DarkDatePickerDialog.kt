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
import com.sujoy.smartfarming.ui.theme.CardBg
import com.sujoy.smartfarming.ui.theme.CardBorder
import com.sujoy.smartfarming.ui.theme.GreenDark
import com.sujoy.smartfarming.ui.theme.GreenDeep
import com.sujoy.smartfarming.ui.theme.GreenPrimary
import com.sujoy.smartfarming.ui.theme.InputBg
import com.sujoy.smartfarming.ui.theme.InputBorder
import com.sujoy.smartfarming.ui.theme.TextMuted
import com.sujoy.smartfarming.ui.theme.TextPrimary
import com.sujoy.smartfarming.ui.theme.TextSecondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DarkDatePickerDialog(
    initialDateMs: Long,
    onDismiss: () -> Unit,
    onConfirm: (Long) -> Unit
) {
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = initialDateMs,
        // Disallow future dates — sowing can't be in the future
        selectableDates = object : SelectableDates {
            override fun isSelectableDate(utcTimeMillis: Long): Boolean =
                utcTimeMillis <= System.currentTimeMillis()
        }
    )

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            shape = RoundedCornerShape(24.dp),
            color = CardBg,
            border = BorderStroke(1.dp, CardBorder)
        ) {
            Column {

                // ── Dialog title bar ──────────────────────────────────
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Brush.horizontalGradient(listOf(GreenDark, CardBg))
                        )
                        .padding(horizontal = 20.dp, vertical = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .background(
                                    GreenPrimary.copy(0.15f), CircleShape
                                )
                                .border(1.dp, GreenPrimary.copy(0.35f), CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.planning),
                                contentDescription = null,
                                tint = GreenPrimary,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                        Column {
                            Text(
                                "Sowing Date",
                                color = TextPrimary,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                            Text(
                                "Select when you planted the seeds",
                                color = TextMuted,
                                fontSize = 11.sp
                            )
                        }
                    }

                    // Close X
                    Box(
                        modifier = Modifier
                            .size(30.dp)
                            .background(CardBorder, CircleShape)
                            .clickable { onDismiss() },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            Icons.Rounded.Close,
                            contentDescription = "Close",
                            tint = TextSecondary,
                            modifier = Modifier.size(15.dp)
                        )
                    }
                }

                // ── DatePicker with custom colours ────────────────────
                DatePicker(
                    state = datePickerState,
                    showModeToggle = true,
                    colors = DatePickerDefaults.colors(
                        containerColor               = CardBg,
                        titleContentColor            = TextSecondary,
                        headlineContentColor         = GreenPrimary,
                        weekdayContentColor          = TextMuted,
                        subheadContentColor          = TextSecondary,
                        navigationContentColor       = TextSecondary,
                        yearContentColor             = TextPrimary,
                        currentYearContentColor      = GreenPrimary,
                        selectedYearContentColor     = GreenDeep,
                        selectedYearContainerColor   = GreenPrimary,
                        dayContentColor              = TextPrimary,
                        disabledDayContentColor      = TextMuted.copy(0.3f),
                        selectedDayContentColor      = GreenDeep,
                        disabledSelectedDayContentColor = TextMuted,
                        selectedDayContainerColor    = GreenPrimary,
                        todayContentColor            = GreenPrimary,
                        todayDateBorderColor         = GreenPrimary,
                        dayInSelectionRangeContentColor = TextPrimary,
                        dayInSelectionRangeContainerColor = GreenPrimary.copy(0.15f),
                        dividerColor                 = CardBorder,
                        dateTextFieldColors          = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor      = GreenPrimary,
                            unfocusedBorderColor    = InputBorder,
                            focusedContainerColor   = InputBg,
                            unfocusedContainerColor = InputBg,
                            cursorColor             = GreenPrimary,
                            focusedTextColor        = TextPrimary,
                            unfocusedTextColor      = TextPrimary,
                            focusedLabelColor       = GreenPrimary,
                            unfocusedLabelColor     = TextMuted
                        )
                    )
                )

                // ── Action buttons ────────────────────────────────────
                HorizontalDivider(color = CardBorder, thickness = 0.5.dp)

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.End)
                ) {
                    // Cancel
                    OutlinedButton(
                        onClick = onDismiss,
                        shape  = RoundedCornerShape(12.dp),
                        border = BorderStroke(1.dp, CardBorder),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = TextSecondary
                        )
                    ) {
                        Text("Cancel", fontSize = 13.sp)
                    }

                    // Confirm
                    Button(
                        onClick = {
                            val millis = datePickerState.selectedDateMillis
                            if (millis != null) onConfirm(millis)
                        },
                        enabled = datePickerState.selectedDateMillis != null,
                        shape  = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = GreenPrimary,
                            contentColor   = GreenDeep
                        )
                    ) {
                        Icon(
                            Icons.Rounded.Check,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(Modifier.width(6.dp))
                        Text("Confirm", fontWeight = FontWeight.Bold, fontSize = 13.sp)
                    }
                }
            }
        }
    }
}
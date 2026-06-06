package com.sujoy.smartfarming.presentation.componenets.DailyRecord

import androidx.compose.material3.Icon
import androidx.compose.ui.res.painterResource

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
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.graphics.Color
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
import com.sujoy.smartfarming.ui.theme.InputBg
import com.sujoy.smartfarming.ui.theme.InputBorder
import com.sujoy.smartfarming.ui.theme.RedColor
import com.sujoy.smartfarming.ui.theme.TextMuted
import com.sujoy.smartfarming.ui.theme.TextPrimary
import com.sujoy.smartfarming.ui.theme.TextSecondary

@Composable
fun DarkTextFieldDaily(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    image: Int,
    keyboardType: KeyboardType = KeyboardType.Text,
    accentColor: Color = GreenPrimary
) {
    OutlinedTextField(
        value           = value,
        onValueChange   = onValueChange,
        label           = { Text(label, color = TextMuted, fontSize = 13.sp) },
        leadingIcon     = {
            Icon(painter = painterResource(id = image), contentDescription = null,
                tint = accentColor, modifier = Modifier.size(18.dp))
        },
        singleLine      = true,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        modifier        = Modifier.fillMaxWidth(),
        shape           = RoundedCornerShape(14.dp),
        colors          = OutlinedTextFieldDefaults.colors(
            focusedBorderColor      = accentColor,
            unfocusedBorderColor    = InputBorder,
            focusedContainerColor   = InputBg,
            unfocusedContainerColor = InputBg,
            cursorColor             = accentColor,
            focusedTextColor        = TextPrimary,
            unfocusedTextColor      = TextPrimary,
            focusedLabelColor       = accentColor,
            unfocusedLabelColor     = TextMuted
        )
    )
}
package com.sujoy.smartfarming.presentation.componenets.FarmSetup

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.material.icons.rounded.Check
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.sujoy.smartfarming.R
import com.sujoy.smartfarming.common.ResultState
import com.sujoy.smartfarming.presentation.ViewModel.FarmSetupViewModel
import com.sujoy.smartfarming.presentation.componenets.FarmSetup.FormCard
import com.sujoy.smartfarming.presentation.componenets.FarmSetup.SetupHeader
import com.sujoy.smartfarming.ui.theme.GreenDeep
import com.sujoy.smartfarming.ui.theme.GreenPrimary
import com.sujoy.smartfarming.ui.theme.InputBg
import com.sujoy.smartfarming.ui.theme.InputBorder
import com.sujoy.smartfarming.ui.theme.TextSecondary
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun SoilChip(label: String, selected: Boolean, onClick: () -> Unit) {
    val bgColor     = if (selected) GreenPrimary.copy(0.2f) else InputBg
    val borderColor = if (selected) GreenPrimary.copy(0.6f) else InputBorder
    val textColor   = if (selected) GreenPrimary            else TextSecondary

    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(bgColor)
            .border(1.dp, borderColor, RoundedCornerShape(12.dp))
            .clickable { onClick() }
            .padding(horizontal = 14.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        if (selected) {
            Icon(Icons.Rounded.Check, contentDescription = null,
                tint = GreenPrimary, modifier = Modifier.size(14.dp))
        }
        Text(label, color = textColor,
            fontSize = 13.sp, fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal)
    }
}
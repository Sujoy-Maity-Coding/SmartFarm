package com.sujoy.smartfarming.presentation.componenets.RecentRecord

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
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
import com.sujoy.smartfarming.ui.theme.GreenDeep
import com.sujoy.smartfarming.ui.theme.GreenPrimary
import com.sujoy.smartfarming.ui.theme.TextMuted
import com.sujoy.smartfarming.ui.theme.TextPrimary

@Composable
fun RecordsEmptyState(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(CardBg)
            .border(1.dp, CardBorder, RoundedCornerShape(20.dp))
            .padding(48.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Box(
            modifier = Modifier
                .size(64.dp)
                .background(
                    Brush.radialGradient(
                        listOf(GreenPrimary.copy(0.2f), GreenPrimary.copy(0.02f))
                    ),
                    RoundedCornerShape(18.dp)
                )
                .border(1.dp, GreenPrimary.copy(0.3f), RoundedCornerShape(18.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.services), contentDescription = null,
                tint = GreenPrimary, modifier = Modifier.size(30.dp))
        }
        Text("No Records Found", color = TextPrimary,
            fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Text("No records match the selected filter.",
            color = TextMuted, fontSize = 13.sp,
            textAlign = TextAlign.Center)
    }
}

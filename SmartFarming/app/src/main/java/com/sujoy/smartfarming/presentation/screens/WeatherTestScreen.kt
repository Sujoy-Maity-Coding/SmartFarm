package com.sujoy.smartfarming.presentation.screens

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.sujoy.smartfarming.presentation.ViewModel.WeatherViewModel

@Composable
fun WeatherTestScreen(
    modifier: Modifier
) {
    val viewModel: WeatherViewModel =
    hiltViewModel()

    LaunchedEffect(Unit) {

        Log.d(
            "WEATHER_TEST",
            "Screen Opened"
        )
    }

    Text("Weather Screen")
}
package com.sujoy.smartfarming

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sujoy.smartfarming.presentation.navigation.SmartFarmingApp
import com.sujoy.smartfarming.presentation.screens.WeatherTestScreen
import com.sujoy.smartfarming.ui.theme.SmartFarmingTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SmartFarmingTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SmartFarmingApp(modifier = Modifier.padding(innerPadding))
//                    WeatherTestScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
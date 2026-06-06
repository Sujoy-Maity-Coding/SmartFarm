package com.sujoy.smartfarming.presentation.componenets

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

@Composable
fun WeatherItem(
    value: String,
    title: String
) {

    Column(
        horizontalAlignment =
        Alignment.CenterHorizontally
    ) {

        Text(
            value,
            color = Color.White,
            fontWeight =
            FontWeight.Bold
        )

        Text(
            title,
            color = Color(0xFFBBF7D0)
        )
    }
}
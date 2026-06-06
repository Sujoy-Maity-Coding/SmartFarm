package com.sujoy.smartfarming.presentation.componenets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sujoy.smartfarming.presentation.State.WeatherState

@Composable
fun WeatherCard(

    weatherState: WeatherState

) {

    ElevatedCard(

        modifier =
        Modifier.fillMaxWidth(),

        shape =
        RoundedCornerShape(24.dp)

    ) {

        Column(

            modifier =
            Modifier.padding(20.dp)

        ) {

            Text(

                text = "🌤 Current Weather",

                style =
                MaterialTheme
                    .typography
                    .titleLarge
            )

            Spacer(
                Modifier.height(12.dp)
            )

            Text(
                text =
                "🌡 ${weatherState.temperature}°C"
            )

            Text(
                text =
                "💧 Humidity ${weatherState.humidity}%"
            )

            Text(
                text =
                "🌬 Wind ${weatherState.windSpeed} m/s"
            )
        }
    }
}
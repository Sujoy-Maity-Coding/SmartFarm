package com.sujoy.smartfarming.presentation.componenets.Graph

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.patrykandpatrick.vico.compose.cartesian.CartesianChartHost
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberLineCartesianLayer
import com.patrykandpatrick.vico.compose.cartesian.rememberCartesianChart
import com.patrykandpatrick.vico.core.cartesian.data.CartesianChartModelProducer
import com.patrykandpatrick.vico.core.cartesian.data.lineSeries

@Composable
fun TestGraph() {

    val modelProducer = remember {
        CartesianChartModelProducer()
    }

    LaunchedEffect(Unit) {

        modelProducer.runTransaction {

            lineSeries {

                series(
                    50,
                    60,
                    55,
                    70,
                    65,
                    80
                )
            }
        }
    }

    Card(

        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)

    ) {

        CartesianChartHost(

            chart = rememberCartesianChart(
                rememberLineCartesianLayer()
            ),

            modelProducer = modelProducer,

            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        )
    }
}
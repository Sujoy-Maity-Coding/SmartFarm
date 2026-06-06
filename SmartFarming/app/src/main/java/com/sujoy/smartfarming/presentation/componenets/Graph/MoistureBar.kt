package com.sujoy.smartfarming.presentation.componenets.Graph

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.sujoy.smartfarming.ui.theme.Border
import com.sujoy.smartfarming.ui.theme.PrimaryGreen

@Composable
fun MoistureBar(
    moisture: Int
) {

    val barHeight =

        ((moisture.coerceIn(
            10,
            100
        )) * 1.2)
            .dp

    Column(

        horizontalAlignment =
        Alignment.CenterHorizontally

    ) {

        Box(

            modifier =
            Modifier
                .width(22.dp)
                .height(barHeight)
                .clip(
                    RoundedCornerShape(
                        6.dp
                    )
                )
                .background(

                    if (
                        moisture >= 60
                    )
                        PrimaryGreen
                    else
                        Border
                )
        )
    }
}
package com.sujoy.smartfarming.domain.UseCase

import android.util.Log
import com.sujoy.smartfarming.domain.model.DailyRecord
import com.sujoy.smartfarming.domain.model.Farm
import com.sujoy.smartfarming.domain.model.GrowthStage
import com.sujoy.smartfarming.domain.model.IrrigationPrediction
import javax.inject.Inject

class PredictIrrigationUseCase @Inject constructor() {

    operator fun invoke(
        record: DailyRecord,
        farm: Farm
    ): IrrigationPrediction {

        var moistureLoss = 5.0

        // Weather
        when {
            record.temperature > 40 -> moistureLoss += 5
            record.temperature > 35 -> moistureLoss += 3
            record.temperature > 30 -> moistureLoss += 2
        }

        when {
            record.humidity < 30 -> moistureLoss += 3
            record.humidity < 50 -> moistureLoss += 2
            record.humidity < 70 -> moistureLoss += 1
        }

        when {
            record.windSpeed > 15 -> moistureLoss += 3
            record.windSpeed > 8 -> moistureLoss += 2
            record.windSpeed > 4 -> moistureLoss += 1
        }

        // Growth Stage
        val growthFactor = when (record.growthStage) {

            GrowthStage.SEEDLING -> 0.8

            GrowthStage.VEGETATIVE -> 1.0

            GrowthStage.FLOWERING -> 1.3

            GrowthStage.FRUITING -> 1.5

            GrowthStage.HARVESTING -> 0.7
        }

        moistureLoss *= growthFactor

        // Soil Type
        val soilFactor = when (
            farm.soilType.lowercase()
        ) {

            "sandy" -> 1.5

            "loamy" -> 1.0

            "clay" -> 0.7

            "silty" -> 0.9

            else -> 1.0
        }

        moistureLoss *= soilFactor

        // Crop Type
        val cropFactor = when (
            farm.cropName.lowercase()
        ) {

            "rice" -> 1.4

            "sugarcane" -> 1.5

            "banana" -> 1.4

            "tomato" -> 1.2

            "potato" -> 1.1

            "wheat" -> 0.9

            "mustard" -> 0.8

            else -> 1.0
        }

        moistureLoss *= cropFactor

        val days =
            (record.soilMoisture / moistureLoss)
                .toInt()
                .coerceAtLeast(1)

        val recommendedWater = when {

            record.soilMoisture < 30 -> 25.0

            record.soilMoisture < 50 -> 20.0

            record.soilMoisture < 70 -> 15.0

            else -> 10.0
        }

        return IrrigationPrediction(

            nextIrrigationDays = days,

            recommendedWater =
            recommendedWater,

            message =
            "Weather + Crop + Soil + Growth Stage"
        )
    }
}
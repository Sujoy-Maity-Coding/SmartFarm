package com.sujoy.smartfarming.domain.model

//data class Farm(
//    val cropName: String,
//    val soilType: String,
//    val landArea: Double,
//    val sowingDate: Long
//)

data class Farm(

    val id: Int = 0,

    val cropName: String,

    val soilType: String,

    val landArea: Double,

    val sowingDate: Long
)

//data class DailyRecord(
//
//    val waterGiven: Double,
//
//    val soilMoisture: Int,
//    val growthStage: GrowthStage,
//
//    val temperature: Double,
//
//    val humidity: Int,
//
//    val windSpeed: Double,
//
//    val timestamp: Long
//)

data class DailyRecord(

    val farmId: Int,

    val waterGiven: Double,

    val soilMoisture: Int,

    val temperature: Double,

    val humidity: Int,

    val windSpeed: Double,

    val growthStage: GrowthStage,

    val timestamp: Long
)

data class IrrigationPrediction(
    val nextIrrigationDays: Int,
    val recommendedWater: Double,
    val message: String
)

data class WeatherInfo(

    val temperature: Double,

    val humidity: Int,

    val windSpeed: Double
)
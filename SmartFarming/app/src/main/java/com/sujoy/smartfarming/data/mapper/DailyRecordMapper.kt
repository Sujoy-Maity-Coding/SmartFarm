package com.sujoy.smartfarming.data.mapper

import com.sujoy.smartfarming.data.local.entity.DailyRecordEntity
import com.sujoy.smartfarming.domain.model.DailyRecord
import com.sujoy.smartfarming.domain.model.GrowthStage

fun DailyRecordEntity.toDomain(): DailyRecord {
    return DailyRecord(
        farmId = farmId,
        waterGiven = waterGiven,

        soilMoisture = soilMoisture,

        temperature = temperature,

        humidity = humidity,

        windSpeed = windSpeed,

        growthStage =
        GrowthStage.valueOf(
            growthStage
        ),

        timestamp = timestamp
    )
}

fun DailyRecord.toEntity(): DailyRecordEntity {

    return DailyRecordEntity(
        farmId = farmId,
        waterGiven = waterGiven,

        soilMoisture = soilMoisture,

        temperature = temperature,

        humidity = humidity,

        windSpeed = windSpeed,

        growthStage =
        growthStage.name,

        timestamp = timestamp
    )
}
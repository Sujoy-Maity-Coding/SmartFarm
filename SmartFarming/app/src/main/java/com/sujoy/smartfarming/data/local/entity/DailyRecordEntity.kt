package com.sujoy.smartfarming.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

//@Entity(
//    tableName = "daily_records"
//)
//data class DailyRecordEntity(
//
//    @PrimaryKey(autoGenerate = true)
//    val id: Int = 0,
//
//    val waterGiven: Double,
//
//    val soilMoisture: Int,
//
//    val temperature: Double,
//
//    val humidity: Int,
//
//    val windSpeed: Double,
//
//    val growthStage: String,
//
//    val timestamp: Long
//)

@Entity(
    tableName = "daily_records"
)
data class DailyRecordEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val farmId: Int,

    val waterGiven: Double,

    val soilMoisture: Int,

    val temperature: Double,

    val humidity: Int,

    val windSpeed: Double,

    val growthStage: String,

    val timestamp: Long
)
package com.sujoy.smartfarming.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

//@Entity(tableName = "farm")
//data class FarmEntity(
//
//    @PrimaryKey
//    val id: Int = 1,
//
//    val cropName: String,
//
//    val soilType: String,
//
//    val landArea: Double,
//
//    val sowingDate: Long
//)

@Entity(tableName = "farm")
data class FarmEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val cropName: String,

    val soilType: String,

    val landArea: Double,

    val sowingDate: Long
)
package com.sujoy.smartfarming.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sujoy.smartfarming.data.local.dao.DailyRecordDao
import com.sujoy.smartfarming.data.local.dao.FarmDao
import com.sujoy.smartfarming.data.local.entity.DailyRecordEntity
import com.sujoy.smartfarming.data.local.entity.FarmEntity

@Database(
    entities = [
        FarmEntity::class,
        DailyRecordEntity::class
    ],
    version = 5,
    exportSchema = false
)
abstract class SmartIrrigationDatabase :
    RoomDatabase() {

    abstract fun farmDao(): FarmDao

    abstract fun dailyRecordDao():
            DailyRecordDao
}
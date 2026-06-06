package com.sujoy.smartfarming.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.sujoy.smartfarming.data.local.entity.DailyRecordEntity
import kotlinx.coroutines.flow.Flow

//@Dao
//interface DailyRecordDao {
//
//    @Insert
//    suspend fun insertRecord(
//        record: DailyRecordEntity
//    )
//
//    @Query(
//        """
//        SELECT * FROM daily_records
//        ORDER BY timestamp DESC
//        """
//    )
//    fun getRecords():
//            Flow<List<DailyRecordEntity>>
//}

@Dao
interface DailyRecordDao {

    @Insert
    suspend fun insertRecord(
        record: DailyRecordEntity
    )

    @Query(
        "SELECT * FROM daily_records WHERE farmId = :farmId ORDER BY timestamp DESC"
    )
    fun getRecordsByFarm(
        farmId: Int
    ): Flow<List<DailyRecordEntity>>
}
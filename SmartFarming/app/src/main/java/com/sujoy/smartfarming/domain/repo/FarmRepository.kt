package com.sujoy.smartfarming.domain.repo

import com.sujoy.smartfarming.domain.model.DailyRecord
import com.sujoy.smartfarming.domain.model.Farm
import com.sujoy.smartfarming.domain.model.IrrigationPrediction
import kotlinx.coroutines.flow.Flow

//interface FarmRepository {
//
//    suspend fun saveFarm(farm: Farm)
//
//    fun getFarm(): Flow<Farm?>
//
//    suspend fun saveDailyRecord(record: DailyRecord)
//
//    fun getDailyRecords(): Flow<List<DailyRecord>>
//}

interface FarmRepository {

    suspend fun saveFarm(
        farm: Farm
    )

    fun getFarms():
            Flow<List<Farm>>

    fun getFarmById(
        farmId: Int
    ): Flow<Farm?>

    suspend fun saveDailyRecord(
        record: DailyRecord
    )

    fun getDailyRecords(
        farmId: Int
    ): Flow<List<DailyRecord>>

    suspend fun deleteFarm(
        farm: Farm
    )
}
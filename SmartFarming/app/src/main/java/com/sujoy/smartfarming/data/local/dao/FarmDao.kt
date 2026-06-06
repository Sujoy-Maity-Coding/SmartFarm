package com.sujoy.smartfarming.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sujoy.smartfarming.data.local.entity.FarmEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FarmDao {

    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    suspend fun saveFarm(
        farm: FarmEntity
    )

    @Query(
        "SELECT * FROM farm"
    )
    fun getFarms(): Flow<List<FarmEntity>>

    @Query(
        "SELECT * FROM farm WHERE id = :farmId"
    )
    fun getFarmById(
        farmId: Int
    ): Flow<FarmEntity?>
    @Delete
    suspend fun deleteFarm(
        farm: FarmEntity
    )
}
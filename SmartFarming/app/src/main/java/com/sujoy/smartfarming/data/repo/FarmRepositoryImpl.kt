package com.sujoy.smartfarming.data.repo

import com.sujoy.smartfarming.data.local.dao.DailyRecordDao
import com.sujoy.smartfarming.data.local.dao.FarmDao
import com.sujoy.smartfarming.data.mapper.toEntity
import com.sujoy.smartfarming.domain.model.DailyRecord
import com.sujoy.smartfarming.domain.model.Farm
import com.sujoy.smartfarming.domain.repo.FarmRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import com.sujoy.smartfarming.data.mapper.toDomain

class FarmRepositoryImpl @Inject constructor(
    private val farmDao: FarmDao,
    private val dailyRecordDao: DailyRecordDao
) : FarmRepository {

    override suspend fun saveFarm(
        farm: Farm
    ) {
        farmDao.saveFarm(
            farm.toEntity()
        )
    }

//    override fun getFarm(): Flow<Farm?> {
//        return farmDao
//            .getFarm()
//            .map { entity ->
//                entity?.toDomain()
//            }
//    }
    override fun getFarms():
            Flow<List<Farm>> {

        return farmDao
            .getFarms()
            .map { farms ->

                farms.map {

                    it.toDomain()
                }
            }
    }

    override fun getFarmById(
        farmId: Int
    ): Flow<Farm?> {

        return farmDao
            .getFarmById(
                farmId
            )
            .map {

                it?.toDomain()
            }
    }
    override suspend fun saveDailyRecord(
        record: DailyRecord
    ) {
        dailyRecordDao.insertRecord(
            record.toEntity()
        )
    }

//    override fun getDailyRecords():
//            Flow<List<DailyRecord>> {
//
//        return dailyRecordDao
//            .getRecords()
//            .map { list ->
//                list.map {
//                    it.toDomain()
//                }
//            }
//    }
    override fun getDailyRecords(
        farmId: Int
    ): Flow<List<DailyRecord>> {

        return dailyRecordDao
            .getRecordsByFarm(
                farmId
            )
            .map { records ->

                records.map {

                    it.toDomain()
                }
            }
    }
    override suspend fun deleteFarm(
        farm: Farm
    ) {

        farmDao.deleteFarm(
            farm.toEntity()
        )
    }
}
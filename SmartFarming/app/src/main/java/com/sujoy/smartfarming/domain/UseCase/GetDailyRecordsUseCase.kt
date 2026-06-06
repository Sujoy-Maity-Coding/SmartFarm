package com.sujoy.smartfarming.domain.UseCase

import com.sujoy.smartfarming.domain.model.DailyRecord
import com.sujoy.smartfarming.domain.repo.FarmRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

//class GetDailyRecordsUseCase @Inject constructor(
//    private val repository: FarmRepository
//) {
//
//    operator fun invoke():
//            Flow<List<DailyRecord>> {
//
//        return repository.getDailyRecords()
//    }
//}
class GetDailyRecordsUseCase @Inject constructor(
    private val repository: FarmRepository
) {

    operator fun invoke(
        farmId: Int
    ): Flow<List<DailyRecord>> {

        return repository
            .getDailyRecords(
                farmId
            )
    }
}
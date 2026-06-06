package com.sujoy.smartfarming.domain.UseCase

import com.sujoy.smartfarming.domain.model.DailyRecord
import com.sujoy.smartfarming.domain.repo.FarmRepository
import javax.inject.Inject

class SaveDailyRecordUseCase @Inject constructor(
    private val repository: FarmRepository
) {

    suspend operator fun invoke(
        record: DailyRecord
    ) {
        repository.saveDailyRecord(record)
    }
}
package com.sujoy.smartfarming.domain.UseCase

import com.sujoy.smartfarming.domain.model.Farm
import com.sujoy.smartfarming.domain.repo.FarmRepository
import javax.inject.Inject

class SaveFarmUseCase @Inject constructor(
    private val repository: FarmRepository
) {

    suspend operator fun invoke(
        farm: Farm
    ) {
        repository.saveFarm(farm)
    }
}
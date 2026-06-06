package com.sujoy.smartfarming.domain.UseCase

import com.sujoy.smartfarming.domain.repo.FarmRepository
import javax.inject.Inject

class GetFarmByIdUseCase
@Inject constructor(

    private val repository:
    FarmRepository

) {

    operator fun invoke(
        farmId: Int
    ) = repository
        .getFarmById(
            farmId
        )
}
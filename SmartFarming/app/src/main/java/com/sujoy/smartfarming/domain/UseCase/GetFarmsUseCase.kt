package com.sujoy.smartfarming.domain.UseCase

import com.sujoy.smartfarming.domain.repo.FarmRepository
import javax.inject.Inject

class GetFarmsUseCase
@Inject constructor(

    private val repository:
    FarmRepository

) {

    operator fun invoke() =

        repository.getFarms()
}
package com.edufelip.catapp.domain.usecase

import com.edufelip.catapp.domain.model.Cat
import com.edufelip.catapp.domain.repository.CatRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCatsListUseCase @Inject constructor(
    private val catRepository: CatRepository
) {
    suspend operator fun invoke(): Flow<List<Cat>> {
        return catRepository.getCatList()
    }
}
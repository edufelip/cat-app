package com.edufelip.catapp.domain.repository

import com.edufelip.catapp.domain.model.Cat
import kotlinx.coroutines.flow.Flow

interface CatRepository {
    suspend fun getCatList(): Flow<List<Cat>>
    suspend fun getCatDetail(id: String): Flow<Cat>
}
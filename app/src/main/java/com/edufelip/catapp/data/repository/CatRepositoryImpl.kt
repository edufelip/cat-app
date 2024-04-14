package com.edufelip.catapp.data.repository

import com.edufelip.catapp.data.source.RemoteCatDataSource
import com.edufelip.catapp.domain.model.Cat
import com.edufelip.catapp.domain.repository.CatRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CatRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteCatDataSource
) : CatRepository {
    override suspend fun getCatList(): Flow<List<Cat>> {
        return flow {
            emit(remoteDataSource.getCatList())
        }.map { catList ->
            catList
                .filter { it.id == null }
                .mapIndexed { index, cat ->
                    cat.copy(
                        name = "Cat ${index.plus(1)}"
                    ).toDomain()
                }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getCatDetail(id: String): Flow<Cat> {
        return flow {
            emit(remoteDataSource.getCatDetail(id))
        }.map {
            it.toDomain()
        }.flowOn(Dispatchers.IO)
    }
}
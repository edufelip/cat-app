package com.edufelip.catapp.data.di

import com.edufelip.catapp.data.repository.CatRepositoryImpl
import com.edufelip.catapp.data.source.RemoteCatDataSource
import com.edufelip.catapp.domain.repository.CatRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun providesCatRepository(
        remoteCatDataSource: RemoteCatDataSource,
    ): CatRepository = CatRepositoryImpl(remoteCatDataSource)
}

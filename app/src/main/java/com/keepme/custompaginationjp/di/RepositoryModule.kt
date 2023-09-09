package com.keepme.custompaginationjp.di

import com.keepme.custompaginationjp.repository.Repository
import com.keepme.custompaginationjp.repository.RepositoryImpl
import com.keepme.custompaginationjp.services.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun providerPosRepository(
        apiService: ApiService
    ): Repository {
        return RepositoryImpl(apiService)
    }
}
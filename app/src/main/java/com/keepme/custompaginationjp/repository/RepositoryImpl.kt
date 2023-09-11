package com.keepme.custompaginationjp.repository

import com.keepme.custompaginationjp.model.CustomersModel
import com.keepme.custompaginationjp.services.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class RepositoryImpl @Inject constructor(private val api: ApiService) : Repository {

    override suspend fun getCustomers(page: Int): Flow<CustomersModel> = flow {
        kotlinx.coroutines.delay(3000)
        try {
            emit(api.getCustomers(page, "Token b4612ff19f25f37bd46bc35d6f8a092d1d0b51bd"))
        } catch (error: Exception) {
            emit(CustomersModel("false", error.message, emptyList()))
        }
    }.flowOn(Dispatchers.IO)

}
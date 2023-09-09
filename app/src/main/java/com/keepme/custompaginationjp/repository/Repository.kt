package com.keepme.custompaginationjp.repository

import com.keepme.custompaginationjp.model.CustomersModel
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun getCustomers(page: Int): Flow<CustomersModel>
}
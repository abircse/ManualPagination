package com.keepme.custompaginationjp.services

import com.keepme.custompaginationjp.model.CustomersModel
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {

    @GET("customer/")
    suspend fun getCustomers(
        @Query("page") page: Int,
        @Header("Authorization") authorization: String
    ): CustomersModel
}
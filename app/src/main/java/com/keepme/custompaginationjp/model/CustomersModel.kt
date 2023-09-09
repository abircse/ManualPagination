package com.keepme.custompaginationjp.model


import com.google.gson.annotations.SerializedName

data class CustomersModel(
    @SerializedName("status")
    var status: String? = "",
    @SerializedName("message")
    var message: String? = "",
    @SerializedName("data")
    var customerList: List<Data> = listOf()
) {
    data class Data(
        @SerializedName("id")
        var id: Int? = 0,
        @SerializedName("name")
        var name: String? = "",
        @SerializedName("email")
        var email: String? = "",
        @SerializedName("phone")
        var phone: String? = "",
        @SerializedName("address")
        var address: String? = "",
        @SerializedName("created_at")
        var createdAt: String? = "",
        @SerializedName("updated_at")
        var updatedAt: String? = ""
    )
}
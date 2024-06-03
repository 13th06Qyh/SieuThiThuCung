package com.example.sttc.model

data class Bill(
    val maBill: Int,
    val iduser: Int,
    val diachi: String,
    val status: String,
    val created_at: String,
    val updated_at: String,
    val ship: String,
    val pay: String,
    val info: String,
    val situation: String
)

data class BillData(
    val bills: List<Bill>
)

data class AddBillRequest(
    val iduser: Int,
    val diachi: String,
    val status: String,
    val ship: String,
    val pay: String,
    val info: String,
    val situation: String,
    val carts: Carts
)

data class AddBillResponse(
    val token: String,
    val bill: Bill
)

data class ErrorAddBillResponse(
    val message: String,
    val errors: String
)

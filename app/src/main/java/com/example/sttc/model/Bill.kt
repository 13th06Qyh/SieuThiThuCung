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
    val otp: String,
    val diachi: String,
    val status: String,
    val ship: String,
    val pay: String,
    val info: String,
    val carts: List<PayData>
)

data class AddBillResponse(
    val token: String,
    val bills: Bill
)

data class ErrorAddBillResponse(
    val message: String,
    val error: String
)

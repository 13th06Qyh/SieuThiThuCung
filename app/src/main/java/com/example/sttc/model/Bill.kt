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
    val bills: List<billShow>
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



//Thu
data class billShow(
    val maBill: Int,
    val tensp: String,
    val soluong: Int,
    val buyprice: Int,
    val maSP: Int,
    val tagname: String,
    val proname: String,
    val image: String,
    val created_at: String,
    val status: String,
    val updated_at: String
)

data class billDetail(
    val maBill: Int,
    val diachi : String,
    val created_at : String,
    val updated_at : String,
    val pay : String,
    val tensp : String,
    val soluong : Int,
    val buyprice : Int,
    val username : String,
    val maSP : Int ,
    val tagname : String ,
    val sdt : Int ,
    val proname : String ,
    val situation : String

)

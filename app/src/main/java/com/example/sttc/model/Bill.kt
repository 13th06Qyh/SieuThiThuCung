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
    val bills: List<Bill>,
    val billCancel : List<billShow> ,
    val billShip : List<billShow> ,
    val billHistory : List<billShow> ,
    val billDetail : List<billDetail>
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
data class billShow(
    val maBill: Int,
    val tensp : String,
    val soluong : Int,
    val buyprice : Int,
    val username : String,
    val maSP : Int ,
    val tagname : String
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

package com.example.sttc.model

data class Carts(
    val maCart: Int,
    val idsp: Int,
    val iduser: Int,
    val sanpham: Sanpham,
    val proname: String,
    val tagname: String,
    val image: String
)

data class CartData(
    val productCount: Int,
    val carts: List<Carts>
)

data class DeleteRequest(
    val maCart: Int
)

data class DeleteResponse(
    val message: String
)

data class AddRequest(
    val idsp: Int
)

data class AddResponse(
    val token: String,
    val carts: Carts
)
data class ErrorAddResponse(
    val message: String,
    val error: String
)

data class PayData(
    val id: Int,
    val image: String,
    val name: String,
    val oneprice: Int,
    val tag: String,
    val quantity: Int
)



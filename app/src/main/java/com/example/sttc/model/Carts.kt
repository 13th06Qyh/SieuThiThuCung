package com.example.sttc.model

data class Carts(
    val maCart: Int,
    val idsp: Int,
    val iduser: Int,
    val created_at: String,
    val updated_at: String
)

data class CartData(
    val carts: List<Carts>
)

data class DeleteRequest(
    val maCart: Int
)

data class DeleteResponse(
    val message: String,
    val carts: Carts
)

data class AddRequest(
    val maCart: Int,
    val idsp: Int,
    val iduser: Int
)

data class AddResponse(
    val token: String,
    val carts: Carts
)
data class ErrorAddResponse(
    val message: String,
    val errors: String
)

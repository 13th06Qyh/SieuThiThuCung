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
    val tag: String,
    val price: Int,
    val quantity: Int
) {
    companion object {
        fun fromString(serialized: String): PayData {
            val parts = serialized.split("|")
            return PayData(
                id = parts[0].toInt(),
                image = parts[1],
                name = parts[2],
                tag = parts[3],
                price = parts[4].toInt(),
                quantity = parts[5].toInt()
            )
        }
    }

    fun toStringRepresentation(): String {
        return "$id|$image|$name|$tag|$price|$quantity"
    }
}



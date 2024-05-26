package com.example.sttc.model

import com.example.sttc.view.System.Bill
import com.example.sttc.view.System.Product

data class ProductData(
    val sanphams: List<Sanpham>,
    val tags: List<Tag>,
    val types: List<Type>,
    val animals: List<Animal>,
    val provides: List<Provide>
)

data class UserData(
    val users: List<User>
)




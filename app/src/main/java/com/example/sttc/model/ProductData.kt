package com.example.sttc.model

import com.example.sttc.view.System.Bill
import com.example.sttc.view.System.Product

data class ProductData(
    val animals: List<Animal>,
    val provides: List<Provide>,
    val sanphams: List<Sanpham>,
    val tags: List<Tag>,
    val types: List<Type>
)

data class ProductDogTA(
    val animals: Animal,
    val provides: Provide,
    val sanphams: Sanpham,
    val tags: Tag,
    val types: Type
)


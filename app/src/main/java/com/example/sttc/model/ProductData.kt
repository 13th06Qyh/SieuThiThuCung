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

data class Search(
    val sanpham: Sanpham,
    val proname: String,
    val tagname: String,
    val image: String
)
data class SearchData(
    val productCount: Int,
    val sanphams: List<Search>
)






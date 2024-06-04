package com.example.sttc.model

data class Blogs(
    val maBlog: Int,
    val idanimal: Int,
    val title: String,
    val noidung : String ,
    val created_at: String,
    val updated_at: String ,


)
data class BlogsData(
    val blogs : List<Blogs> ,
    val animals: List<Animal>,
    val blogDetail : List<Blogs>
)

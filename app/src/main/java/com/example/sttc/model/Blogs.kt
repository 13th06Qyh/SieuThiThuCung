package com.example.sttc.model

data class Blogs(
    val id: Int,
    val title: String,
    val noiDung : String ,
    val created_at: String,
    val updated_at: String ,
    val idAnimal: List<Animal>,
    val imageBlogs: List<ImageBlogs>,
)
data class BlogsData(
    val blogs : List<Blogs>
)

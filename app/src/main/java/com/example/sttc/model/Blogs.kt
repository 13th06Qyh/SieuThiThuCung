package com.example.sttc.model

data class Blogs(
    val id: Int,
    val idImage: Int,
    val title: String,
    val noiDung : String ,
    val created_at: String,
    val updated_at: String ,
    val imageBlogs: List<ImageBlogs>,
)
data class ApiBlogs(
    val blogs: List<Blogs> = emptyList() ,
)

package com.example.sttc.model

data class ImageBlogs(
    val maIB: Int,
    val idBlog: Int,
    val image: String,
    val created_at: String,
    val updated_at: String
)
// Extension function to extract the file name from the image URL
fun String.extractFileName(): String {
    return this.substringAfterLast('/')
}

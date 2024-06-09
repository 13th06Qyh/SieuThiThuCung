package com.example.sttc.model

data class Comments(
    val maBL: Int,
    val iduser: Int,
    val idblog: Int,
    var noidungbl: String,
    val created_at: String,
    val updated_at: String
)
data class CommentWithUser(
    val id: Int,
    val username : String,
    val maBL: Int ,
    val iduser: Int,
    val idblog: Int,
    var noidungbl: String,
    val created_at: String,
    val updated_at: String
)
data class CommentData(
    val comments : List<CommentWithUser>,
)
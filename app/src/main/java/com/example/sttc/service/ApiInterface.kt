package com.example.sttc.service

import androidx.lifecycle.LiveData
import com.example.sttc.model.Blogs
import com.example.sttc.model.BlogsData
import com.example.sttc.model.ImageBlogs
import com.example.sttc.model.ImageSP
import com.example.sttc.model.ProductData
import retrofit2.Call

import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {

    @GET("admin/sanpham")
    fun getProduct():  Call<ProductData>
    @GET("admin/sanpham/{id}/images")
    fun getImagesByProductId(@Path("id") productId: Int): Call<List<ImageSP>>

    @GET("admin/sanpham/infosp/{id}")
    fun getProductById(@Path("id") productId: Int): Call<ProductData>


    // blog
    @GET("admin/blogs")
    fun getListBlogs(): Call<BlogsData>
    @GET("admin/blogs/{id}/images")
    fun getImagesByBlogId(@Path("id") productId: Int): Call<List<ImageBlogs>>

    @GET("admin/blogs/detailBlog/{id}")
    fun getBlogDetailById(@Path("id") blogId: Int): Call<BlogsData>
}
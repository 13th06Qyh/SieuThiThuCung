package com.example.sttc.service

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
}
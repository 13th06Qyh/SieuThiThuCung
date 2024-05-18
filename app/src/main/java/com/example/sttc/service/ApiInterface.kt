package com.example.sttc.service

import com.example.sttc.model.ProductData
import retrofit2.Call

import retrofit2.http.GET

interface ApiInterface {

    @GET("sanpham")
    fun getProduct():  Call<ProductData>
}
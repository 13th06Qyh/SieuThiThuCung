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

    @GET("admin/sanpham/shopchota")
    fun getProductDogTA():  Call<ProductData>

    @GET("admin/sanpham/shopchoqa")
    fun getProductDogQA():  Call<ProductData>

    @GET("admin/sanpham/shopchodc")
    fun getProductDogDC():  Call<ProductData>

    @GET("admin/sanpham/shopmeota")
    fun getProductCatTA():  Call<ProductData>

    @GET("admin/sanpham/shopmeoqa")
    fun getProductCatQA():  Call<ProductData>

    @GET("admin/sanpham/shopmeodc")
    fun getProductCatDC():  Call<ProductData>

    @GET("admin/sanpham/shopchim")
    fun getProductBird():  Call<ProductData>

    @GET("admin/sanpham/shopchuot")
    fun getProductHamster():  Call<ProductData>

    @GET("admin/sanpham/shopca")
    fun getProductFish():  Call<ProductData>
}
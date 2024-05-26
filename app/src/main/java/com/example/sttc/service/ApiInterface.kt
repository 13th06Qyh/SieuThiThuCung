package com.example.sttc.service

import com.example.sttc.model.ImageSP
import com.example.sttc.model.LoginRequest
import com.example.sttc.model.LoginResponse
import com.example.sttc.model.ProductData
import com.example.sttc.model.SignupRequest
import com.example.sttc.model.SignupResponse
import com.example.sttc.model.UpdateAddressRequest
import com.example.sttc.model.UpdateAddressResponse
import com.example.sttc.model.UpdateMailRequest
import com.example.sttc.model.UpdateMailResponse
import com.example.sttc.model.UpdateNameRequest
import com.example.sttc.model.UpdateNameResponse
import com.example.sttc.model.UpdatePhoneRequest
import com.example.sttc.model.UpdatePhoneResponse
import com.example.sttc.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiInterface {

    @POST("admin/account/login")
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>

    @POST("admin/account/signup")
    fun signup(@Body signupRequest: SignupRequest): Call<SignupResponse>

    @POST("admin/account/{id}/updateName")
    fun updateName(@Header("Authorization") token: String, @Path("id") id: Int, @Body updateNameRequest: UpdateNameRequest): Call<UpdateNameResponse>

    @POST("admin/account/{id}/updateMail")
    fun updateMail(@Header("Authorization") token: String, @Path("id") id: Int, @Body updateMailRequest: UpdateMailRequest): Call<UpdateMailResponse>

    @POST("admin/account/{id}/updatePhone")
    fun updatePhone(@Header("Authorization") token: String, @Path("id") id: Int, @Body updatePhoneRequest: UpdatePhoneRequest): Call<UpdatePhoneResponse>

    @POST("admin/account/{id}/updateAddress")
    fun updateAddress(@Header("Authorization") token: String, @Path("id") id: Int, @Body updateAddressRequest: UpdateAddressRequest): Call<UpdateAddressResponse>


    @GET("admin/sanpham")
    fun getProduct():  Call<ProductData>
    @GET("admin/sanpham/{id}/images")
    fun getImagesByProductId(@Path("id") productId: Int): Call<List<ImageSP>>

    @GET("admin/sanpham/infosp/{id}")
    fun getProductById(@Path("id") productId: Int): Call<ProductData>

}
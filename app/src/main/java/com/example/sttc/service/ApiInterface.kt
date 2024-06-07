package com.example.sttc.service

import androidx.lifecycle.LiveData
import com.example.sttc.model.Blogs
import com.example.sttc.model.BlogsData
import com.example.sttc.model.ImageBlogs
import com.example.sttc.model.AddBillRequest
import com.example.sttc.model.AddBillResponse
import com.example.sttc.model.AddRequest
import com.example.sttc.model.AddResponse
import com.example.sttc.model.CartData
import com.example.sttc.model.Carts
import com.example.sttc.model.DeleteRequest
import com.example.sttc.model.DeleteResponse
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
import com.example.sttc.model.UpdateOTPRequest
import com.example.sttc.model.UpdateOTPResponse
import com.example.sttc.model.UpdatePassRequest
import com.example.sttc.model.UpdatePassResponse
import com.example.sttc.model.UpdatePhoneRequest
import com.example.sttc.model.UpdatePhoneResponse
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

    @POST("admin/account/{id}/changePass")
    fun changePass(@Header("Authorization") token: String, @Path("id") id: Int, @Body updatePassRequest: UpdatePassRequest): Call<UpdatePassResponse>

    @POST("admin/account/{id}/createOTP")
    fun createOTP(@Header("Authorization") token: String, @Path("id") id: Int, @Body updateOTPRequest: UpdateOTPRequest): Call<UpdateOTPResponse>

    @GET("admin/sanpham")
    fun getProduct():  Call<ProductData>
    @GET("admin/sanpham/{id}/images")
    fun getImagesByProductId(@Path("id") productId: Int): Call<List<ImageSP>>

    @GET("admin/sanpham/infosp/{id}")
    fun getProductById(@Path("id") productId: Int): Call<ProductData>

    @GET("admin/cart")
    fun getCart():  Call<CartData>

    @POST("admin/cart/deletesptocart/{id}")
    fun deleteSPtoCart(@Path("id") cartId: Int, @Body deleteRequest: DeleteRequest): Call<DeleteResponse>

    @GET("admin/cart/addsptocart/{id}")
    fun addCart(@Path("id") cartId: Int, @Body addRequest: AddRequest): Call<AddResponse>

    @POST("admin/bill/buy")
    fun buy(@Body addBillRequest: AddBillRequest): Call<AddBillResponse>

    // blog
    @GET("admin/blogs")
    fun getListBlogs(): Call<BlogsData>
    @GET("admin/blogs/{id}/images")
    fun getImagesByBlogId(@Path("id") productId: Int): Call<List<ImageBlogs>>

    @GET("admin/blogs/detailBlog/{id}")
    fun getBlogDetailById(@Path("id") blogId: Int): Call<BlogsData>
}
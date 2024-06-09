package com.example.sttc.service

import androidx.lifecycle.LiveData
import com.example.sttc.model.Blogs
import com.example.sttc.model.BlogsData
import com.example.sttc.model.ImageBlogs
import com.example.sttc.model.AddBillRequest
import com.example.sttc.model.AddBillResponse
import com.example.sttc.model.AddRequest
import com.example.sttc.model.AddResponse
import com.example.sttc.model.BillData
import com.example.sttc.model.CartData
import com.example.sttc.model.Carts
import com.example.sttc.model.CommentData
import com.example.sttc.model.Comments
import com.example.sttc.model.DeleteRequest
import com.example.sttc.model.DeleteResponse
import com.example.sttc.model.DeleteResponseNow
import com.example.sttc.model.ImageSP
import com.example.sttc.model.LoginRequest
import com.example.sttc.model.LoginResponse
import com.example.sttc.model.NowData
import com.example.sttc.model.NowRequest
import com.example.sttc.model.NowResponse
import com.example.sttc.model.ProductData
import com.example.sttc.model.Search
import com.example.sttc.model.SearchData
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
import com.example.sttc.view.System.Key
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

    @GET("admin/cart/{iduser}")
    fun getCart(@Path("iduser") userId: Int):  Call<CartData>

    @POST("admin/cart/deletesptocart/{id}")
    fun deleteSPtoCart(@Path("id") cartId: Int): Call<DeleteResponse>

    @POST("admin/cart/addsptocart/{idsp}/{iduser}")
    fun addCart(@Header("Authorization") token: String, @Path("idsp") productId: Int, @Path("iduser") userId: Int, @Body addRequest: AddRequest): Call<AddResponse>

    @POST("admin/bill/buy/{iduser}")
    fun buy(@Path("iduser") userId: Int, @Body addBillRequest: AddBillRequest): Call<AddBillResponse>

    @GET("admin/sanpham/buynow/{iduser}")
    fun now(@Path("iduser") userId: Int):  Call<NowData>

    @POST("admin/sanpham/deletesptonow/{id}")
    fun deleteSPtoNow(@Path("id") nowId: Int): Call<DeleteResponseNow>




    //---------------------------------- bill----------------------------
    @POST("admin/bill/buy")
    fun buy(@Body addBillRequest: AddBillRequest): Call<AddBillResponse>
    @GET ("admin/bill/billCancel/{id}")
    fun getBillCancel(@Path("id") userId: Int): Call<BillData>

    @GET ("admin/bill/billShip/{id}")
    fun getBillShip(@Path("id") userId: Int): Call<BillData>
    @GET ("admin/bill/billHistory/{id}")
    fun getBillHistory(@Path("id") userId: Int): Call<BillData>
    @GET ("admin/bill/billDetail/{id}")
    fun getBillDetail(@Path("id") billId: Int): Call<BillData>



    //---------------------------------- blog-----------------------------
    @GET("admin/blogs")
    fun getListBlogs(): Call<BlogsData>
    @GET("admin/blogs/{id}/images")
    fun getImagesByBlogId(@Path("id") productId: Int): Call<List<ImageBlogs>>

    @GET("admin/blogs/detailBlog/{id}")
    fun getBlogDetailById(@Path("id") blogId: Int): Call<BlogsData>
    @GET("admin/blogs/{id}/comments")
    fun getCommentsByBlogId(@Path("id") blogId: Int): Call<CommentData>
    @POST("admin/blogs/{id}/comments")
    fun createCmt(@Path("id") blogId: Int, @Body comment: Comments): Call<Comments>

    @POST("admin/sanpham/addsptonow/{idsp}/{iduser}")
    fun addNow(@Header("Authorization") token: String, @Path("idsp") productId: Int, @Path("iduser") userId: Int, @Body addRequest: NowRequest): Call<NowResponse>

    @POST("admin/sanpham/search")
    fun search(@Body keyWord: Key): Call<SearchData>
}
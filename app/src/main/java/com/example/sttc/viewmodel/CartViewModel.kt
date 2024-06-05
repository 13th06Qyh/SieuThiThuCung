package com.example.sttc.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.sttc.model.AddRequest
import com.example.sttc.model.AddResponse
import com.example.sttc.model.CartData
import com.example.sttc.model.Carts
import com.example.sttc.model.DeleteRequest
import com.example.sttc.model.DeleteResponse
import com.example.sttc.model.ErrorAddResponse
import com.example.sttc.service.ApiService
import com.example.sttc.service.ApiService.apiService
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CartViewModel(context: Context) : ViewModel() {

    private val _cart = MutableLiveData<List<Carts>>()
    val cart = _cart.asFlow()

    private val _delete = MutableLiveData<Result<Carts>>()
    val delete = _delete.asFlow()

    private val _add = MutableLiveData<Result<String>>()
    val add = _add.asFlow()

    private var lastFetchTime: Long = 0
    private val context = context

    init {
        fetchCart()
    }

    fun getUserIdFromSharedPreferences(): Int? {
        val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        val idUser = sharedPreferences.getInt("iduser", -1)
        return if (idUser == -1) null else idUser
    }


    fun fetchCart() {
        val iduser = getUserIdFromSharedPreferences() ?: return
        viewModelScope.launch {
            if (System.currentTimeMillis() - lastFetchTime < 60000) {
                // Nếu lần tải trước đó chưa quá 60 giây, không tải lại
                return@launch
            }
            try {
                // Gửi yêu cầu để lấy dữ liệu giỏ hàng
                val call: Call<CartData> = apiService.getCart(iduser)
                call.enqueue(object : Callback<CartData> {
                    override fun onResponse(
                        call: Call<CartData>,
                        response: Response<CartData>
                    ) {
                        if (response.isSuccessful) {

                            val cartData = response.body()
                            _cart.value = cartData?.carts
                            Log.d("CartViewModel", "Fetched Cart Data: ${cartData?.carts}")
                            Log.e("Response Category", cartData.toString())
                            lastFetchTime = System.currentTimeMillis()

                        } else {
                            if (response.code() == 429) {
                                Log.e(
                                    "API Cart Error",
                                    "Error: Too Many Requests (429), retrying in 60 seconds"
                                )
                                viewModelScope.launch(Dispatchers.IO) {
                                    delay(60000) // Wait for 60 seconds before retrying
                                    fetchCart()
                                }
                            } else {
                                Log.e("API Cart Error", "Error: ${response.code()}")
                            }
                        }
                    }

                    override fun onFailure(call: Call<CartData>, t: Throwable) {
                        Log.e("API Cart Error", "Error: ${t.message}")
                        t.printStackTrace()
                    }
                })
            } catch (e: Exception) {
                Log.e("API Cart Error", "Error: ${e.message}")
                e.printStackTrace()
            }
        }

    }

    fun deleteCart(cartId: Int) {
        val iduser = getUserIdFromSharedPreferences() ?: return
        viewModelScope.launch {
            if (System.currentTimeMillis() - lastFetchTime < 60000) {
                // Nếu lần tải trước đó chưa quá 60 giây, không tải lại
                return@launch
            }
            try {
                // Gửi yêu cầu để xóa sản phẩm khỏi giỏ hàng
                val deleteRequest = DeleteRequest(cartId)
                apiService.deleteSPtoCart(cartId, iduser, deleteRequest)
                    .enqueue(object : Callback<DeleteResponse> {
                        override fun onResponse(
                            call: Call<DeleteResponse>,
                            response: Response<DeleteResponse>
                        ) {
                            if (response.isSuccessful) {
                                response.body()?.let { deleteResponse ->
                                    val carts = deleteResponse.carts
                                    _delete.value = Result.success(carts)
                                    fetchCart() // Cập nhật lại giỏ hàng sau khi xóa thành công
                                } ?: run {
                                    _delete.value = Result.failure(Exception("No cart found"))
                                }
                            } else {
                                if (response.code() == 429) {
                                    Log.e(
                                        "API CartDelete Error",
                                        "Error: Too Many Requests (429), retrying in 60 seconds"
                                    )
                                    viewModelScope.launch(Dispatchers.IO) {
                                        delay(60000) // Wait for 60 seconds before retrying
                                        deleteCart(cartId)
                                    }
                                } else {
                                    Log.e("API CartDelete Error", "Error: ${response.code()}")
                                }
                            }
                        }

                        override fun onFailure(call: Call<DeleteResponse>, t: Throwable) {
                            Log.e("API CartDelete Error", "Error: ${t.message}")
                            t.printStackTrace()
                        }
                    })
            } catch (e: Exception) {
                Log.e("API CartDelete Error", "Error: ${e.message}")
                e.printStackTrace()
            }
        }
    }


    private fun getTokenFromSharedPreferences(): String? {
        val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        return sharedPreferences.getString("token", null)
    }

    fun addCart(idsp: Int, iduser: Int) {
        val token = getTokenFromSharedPreferences()
        if (token == null) {
            _add.value = Result.failure(Exception("No token found"))
            return
        }
        val addRequest = AddRequest(idsp)
        apiService.addCart("Bearer $token", idsp, iduser, addRequest)
            .enqueue(object : Callback<AddResponse> {
                override fun onResponse(call: Call<AddResponse>, response: Response<AddResponse>) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            _add.value = Result.success(it.token)
//                        fetchCart(iduser)
                        } ?: run {
                            _add.value = Result.failure(Exception("No token found"))
                        }
                    } else {
                        val errorBody = response.errorBody()?.string()
                        Log.e("API CartAdd Error", "Error body: $errorBody")
                        val errorResponse = try {
                            if (!errorBody.isNullOrEmpty()) {
                                Gson().fromJson(errorBody, ErrorAddResponse::class.java)
                            } else {
                                ErrorAddResponse("CartAdd failed", "Unknown error")
                            }
                        } catch (e: JsonSyntaxException) {
                            ErrorAddResponse("CartAdd failed", "Malformed error response")
                        }
                        _add.value = Result.failure(Exception(errorResponse.error))
                    }
                }


                override fun onFailure(call: Call<AddResponse>, t: Throwable) {
                    _add.value = Result.failure(t)
                }
            })
    }


}
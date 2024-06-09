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
import com.example.sttc.model.DeleteResponseNow
import com.example.sttc.model.ErrorAddResponse
import com.example.sttc.model.ErrorNowAddResponse
import com.example.sttc.model.Now
import com.example.sttc.model.NowData
import com.example.sttc.model.NowRequest
import com.example.sttc.model.NowResponse
import com.example.sttc.model.Sanpham
import com.example.sttc.model.User
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

    private val _now = MutableLiveData<List<Now>>()
    val now = _now.asFlow()

    private val _addN = MutableLiveData<Result<String>>()
    val addN = _addN.asFlow()

    private val _count = MutableLiveData<Int>()
    val count = _count.asFlow()

    private val _delete = MutableLiveData<Result<String>>()
    val delete = _delete.asFlow()

    private val _deleteN = MutableLiveData<Result<String>>()
    val deleteN = _deleteN.asFlow()

    private val _add = MutableLiveData<Result<String>>()
    val add = _add.asFlow()

    private var lastFetchTime: Long = 0
    private val context = context

    init {
        fetchCart()
        now()
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
                            _count.value = cartData?.productCount
                            Log.d("CartViewModel", "Fetched Cart Data: ${cartData?.carts}")
                            Log.d("CartViewModelCount", "Fetched Cart Data: ${cartData?.productCount}")

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

    fun now() {
        val iduser = getUserIdFromSharedPreferences() ?: return
        viewModelScope.launch {
            if (System.currentTimeMillis() - lastFetchTime < 60000) {
                // Nếu lần tải trước đó chưa quá 60 giây, không tải lại
                return@launch
            }
            try {
                // Gửi yêu cầu để lấy dữ liệu giỏ hàng
                val call: Call<NowData> = apiService.now(iduser)
                call.enqueue(object : Callback<NowData> {
                    override fun onResponse(
                        call: Call<NowData>,
                        response: Response<NowData>
                    ) {
                        if (response.isSuccessful) {

                            val cartData = response.body()
                            _now.value = cartData?.nows
                            Log.d("NowDataViewModel", "Fetched NowData Data: ${cartData?.nows}")

                            Log.e("Response Category", cartData.toString())
                            lastFetchTime = System.currentTimeMillis()

                        } else {
                            if (response.code() == 429) {
                                Log.e(
                                    "API NowData Error",
                                    "Error: Too Many Requests (429), retrying in 60 seconds"
                                )
                                viewModelScope.launch(Dispatchers.IO) {
                                    delay(60000) // Wait for 60 seconds before retrying
                                    fetchCart()
                                }
                            } else {
                                Log.e("API NowData Error", "Error: ${response.code()}")
                            }
                        }
                    }

                    override fun onFailure(call: Call<NowData>, t: Throwable) {
                        Log.e("API NowData Error", "Error: ${t.message}")
                        t.printStackTrace()
                    }
                })
            } catch (e: Exception) {
                Log.e("API NowData Error", "Error: ${e.message}")
                e.printStackTrace()
            }
        }

    }

    fun addNow(idsp: Int, iduser: Int) {
        val token = getTokenFromSharedPreferences()
        if (token == null) {
            _addN.value = Result.failure(Exception("No token found"))
            return
        }
        val addRequest = NowRequest(idsp)
        apiService.addNow("Bearer $token", idsp, iduser, addRequest)
            .enqueue(object : Callback<NowResponse> {
                override fun onResponse(call: Call<NowResponse>, response: Response<NowResponse>) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            _addN.value = Result.success(it.token)
//                        fetchCart(iduser)
                        } ?: run {
                            _addN.value = Result.failure(Exception("No token found"))
                        }
                    } else {
                        val errorBody = response.errorBody()?.string()
                        Log.e("API NowData Error", "Error body: $errorBody")
                        val errorResponse = try {
                            if (!errorBody.isNullOrEmpty()) {
                                Gson().fromJson(errorBody, ErrorNowAddResponse::class.java)
                            } else {
                                ErrorNowAddResponse("NowData failed", "Unknown error")
                            }
                        } catch (e: JsonSyntaxException) {
                            ErrorNowAddResponse("NowData failed", "Malformed error response")
                        }
                        _addN.value = Result.failure(Exception(errorResponse.error))
                    }
                }


                override fun onFailure(call: Call<NowResponse>, t: Throwable) {
                    _addN.value = Result.failure(t)
                }
            })
    }

    fun deleteNow(nowId: Int) {
        Log.d("E", "Attempting to delete cart with ID: $nowId")
        viewModelScope.launch {
            if (System.currentTimeMillis() - lastFetchTime < 60000) {
                // Nếu lần tải trước đó chưa quá 60 giây, không tải lại
                return@launch
            }
            val call: Call<DeleteResponseNow> = apiService.deleteSPtoNow(nowId)
            call.enqueue(object : Callback<DeleteResponseNow> {
                override fun onResponse(
                    call: Call<DeleteResponseNow>,
                    response: Response<DeleteResponseNow>
                ) {
                    Log.d("Run", "Chạy: $nowId")
                    if (response.isSuccessful) {
                        response.body()?.let {
                            _deleteN.value = Result.success(it.message)
                            Log.d("CartViewModelDelete", "Deleted cart with ID: $nowId")
                            fetchCart() // Cập nhật lại giỏ hàng sau khi xóa thành công
                        } ?: run {
                            _deleteN.value = Result.failure(Exception("No cart found"))
                        }
                    } else {
                        if (response.code() == 429) {
                            Log.e(
                                "API CartDelete Error",
                                "Error: Too Many Requests (429), retrying in 60 seconds"
                            )
                            viewModelScope.launch(Dispatchers.IO) {
                                delay(60000) // Wait for 60 seconds before retrying
                                deleteNow(nowId)
                            }
                        } else {
                            Log.e("API CartDelete Error", "Error: ${response.code()}")
                        }
                    }
                }

                override fun onFailure(call: Call<DeleteResponseNow>, t: Throwable) {
                    Log.e("API CartDelete Error", "Error: ${t.message}")
                    t.printStackTrace()
                }
            })
        }
    }

    fun deleteCart(cartId: Int) {
        Log.d("E", "Attempting to delete cart with ID: $cartId")
        viewModelScope.launch {
            if (System.currentTimeMillis() - lastFetchTime < 60000) {
                // Nếu lần tải trước đó chưa quá 60 giây, không tải lại
                return@launch
            }
            val call: Call<DeleteResponse> = apiService.deleteSPtoCart(cartId)
            call.enqueue(object : Callback<DeleteResponse> {
                override fun onResponse(
                    call: Call<DeleteResponse>,
                    response: Response<DeleteResponse>
                ) {
                    Log.d("Run", "Chạy: $cartId")
                    if (response.isSuccessful) {
                        response.body()?.let {
                            _delete.value = Result.success(it.message)
                            Log.d("CartViewModelDelete", "Deleted cart with ID: $cartId")
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
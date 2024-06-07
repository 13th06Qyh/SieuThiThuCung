package com.example.sttc.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import com.example.sttc.model.AddBillRequest
import com.example.sttc.model.AddBillResponse
import com.example.sttc.model.Bill
import com.example.sttc.model.Carts
import com.example.sttc.model.ErrorAddBillResponse
import com.example.sttc.model.ErrorAddResponse
import com.example.sttc.model.PayData
import com.example.sttc.service.ApiService
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BillViewModel(context: Context) : ViewModel() {

    private val _bill = MutableLiveData<List<Bill>>()
    val cart = _bill.asFlow()

    private val _buy = MutableLiveData<Result<String>>()
    val buy = _buy.asFlow()

    private var lastFetchTime: Long = 0
    private val context = context

    init {
        fetchBill()
    }

    fun fetchBill() {
        //

    }

    fun getUserIdFromSharedPreferences(): Int? {
        val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        val idUser = sharedPreferences.getInt("iduser", -1)
        return if (idUser == -1) null else idUser
    }

    fun buy(diachi: String, status: String, ship: String, pay: String, info: String, situation: String, carts: PayData) {
        val token = getTokenFromSharedPreferences()
        if (token == null) {
            _buy.value = Result.failure(Exception("No token found"))
            return
        }
        val iduser = getUserIdFromSharedPreferences() ?: return
        val addBillRequest = AddBillRequest(diachi, status, ship, pay, info, situation, carts)
        ApiService.apiService.buy(iduser, addBillRequest).enqueue(object : Callback<AddBillResponse> {
            override fun onResponse(call: Call<AddBillResponse>, response: Response<AddBillResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        _buy.value = Result.success(it.token)
//                        fetchBill()
                    } ?: run {
                        _buy.value = Result.failure(Exception("No token found"))
                    }
                }else {
                    val errorBody = response.errorBody()?.string()
                    Log.e("API BillAdd Error", "Error body: $errorBody")
                    val errorResponse = try {
                        if (!errorBody.isNullOrEmpty()) {
                            Gson().fromJson(errorBody, ErrorAddBillResponse::class.java)
                        } else {
                            ErrorAddBillResponse("BillAdd failed", "Unknown error")
                        }
                    } catch (e: JsonSyntaxException) {
                        ErrorAddBillResponse("BillAdd failed", "Malformed error response")
                    }
                    _buy.value = Result.failure(Exception(errorResponse.error))
                }
            }


            override fun onFailure(call: Call<AddBillResponse>, t: Throwable) {
                _buy.value = Result.failure(t)
            }
        })
    }

    private fun getTokenFromSharedPreferences(): String? {
        val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        return sharedPreferences.getString("token", null)
    }



}
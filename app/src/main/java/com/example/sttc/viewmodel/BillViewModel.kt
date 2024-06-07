package com.example.sttc.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.sttc.model.AddBillRequest
import com.example.sttc.model.AddBillResponse
import com.example.sttc.model.Bill
import com.example.sttc.model.BillData
import com.example.sttc.model.Carts
import com.example.sttc.model.ErrorAddResponse
import com.example.sttc.model.billCancel
import com.example.sttc.service.ApiService
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BillViewModel : ViewModel() {

    private val _bill = MutableLiveData<List<Bill>>()
    val cart = _bill.asFlow()
    private val _billCancel = MutableLiveData<List<billCancel>>()
    val billCancel = _billCancel.asFlow()

    private val _buy = MutableLiveData<Result<String>>()
    val buy = _buy.asFlow()

    private var lastFetchTime: Long = 0

    init {
        fetchBill()
    }

    fun fetchBill() {
        //

    }

    fun buy(
        iduser: Int,
        diachi: String,
        status: String,
        ship: String,
        pay: String,
        info: String,
        situation: String,
        carts: Carts
    ) {
        val addBillRequest =
            AddBillRequest(iduser, diachi, status, ship, pay, info, situation, carts)
        ApiService.apiService.buy(addBillRequest).enqueue(object : Callback<AddBillResponse> {
            override fun onResponse(
                call: Call<AddBillResponse>,
                response: Response<AddBillResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        _buy.value = Result.success(it.token)
//                        fetchBill()
                    } ?: run {
                        _buy.value = Result.failure(Exception("No token found"))
                    }
                } else {
                    val errorBody = response.errorBody()?.string()
                    Log.e("API BillAdd Error", "Error body: $errorBody")
                    val errorResponse = try {
                        if (!errorBody.isNullOrEmpty()) {
                            Gson().fromJson(errorBody, ErrorAddResponse::class.java)
                        } else {
                            ErrorAddResponse("BillAdd failed", "Unknown error")
                        }
                    } catch (e: JsonSyntaxException) {
                        ErrorAddResponse("BillAdd failed", "Malformed error response")
                    }
                    _buy.value = Result.failure(Exception(errorResponse.errors))
                }
            }

            override fun onFailure(call: Call<AddBillResponse>, t: Throwable) {
                _buy.value = Result.failure(t)
            }
        })
    }

    fun fetchBillCancel(idUser: Int) {
        viewModelScope.launch {
            try {
                val call: Call<BillData> = ApiService.apiService.getBillCancel(idUser)
                call.enqueue(object : Callback<BillData> {
                    override fun onResponse(
                        call: Call<BillData>,
                        response: Response<BillData>
                    ) {
                        if (response.isSuccessful) {
                            val billCancelData = response.body()
                            _billCancel.value = billCancelData?.billCancel.orEmpty()
                            Log.e("dsBillcancel",  _billCancel.value.toString())
                        }
                    }
                    override fun onFailure(call: Call<BillData>, t: Throwable) {
                        Log.e("API Error", "Error: ${t.message}")
                        t.printStackTrace()
                    }
                })
            } catch (e: Exception) {
                Log.e("API Error", "Error: ${e.message}")
                e.printStackTrace()
            }
        }
    }
}
package com.example.sttc.viewmodel

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
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
import com.example.sttc.model.billDetail
import com.example.sttc.model.billShow
import com.example.sttc.model.ErrorAddBillResponse
import com.example.sttc.model.PayData
import com.example.sttc.service.ApiService
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await

class BillViewModel(context: Context) : ViewModel() {

    private val _bill = MutableLiveData<List<Bill>>()
    val cart = _bill.asFlow()

    private val _billShow = MutableStateFlow<List<billShow>>(emptyList())
    val billShow: StateFlow<List<billShow>> = _billShow.asStateFlow()



    private val _billDetail = MutableStateFlow<List<billDetail>>(emptyList())
    val billDetail: StateFlow<List<billDetail>> = _billDetail.asStateFlow()

    private val _buy = MutableLiveData<Result<String>>()
    val buy = _buy.asFlow()

    private var lastFetchTime: Long = 0
    private val context = context

    init {
        fetchBill()
    }

    fun fetchBill() {
        // Implementation
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
    fun getUserIdFromSharedPreferences(): Int? {
        val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        val idUser = sharedPreferences.getInt("iduser", -1)
        return if (idUser == -1) null else idUser
    }

    fun buy(otp: String, diachi: String, status: String, ship: String, pay: String, info: String, carts: List<PayData>) {
        val token = getTokenFromSharedPreferences()
        if (token == null) {
            _buy.value = Result.failure(Exception("No token found"))
            return
        }
        val iduser = getUserIdFromSharedPreferences() ?: return
        val addBillRequest = AddBillRequest(otp, diachi, status, ship, pay, info, carts)
        ApiService.apiService.buy(iduser, addBillRequest).enqueue(object : Callback<AddBillResponse> {
            override fun onResponse(call: Call<AddBillResponse>, response: Response<AddBillResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        _buy.value = Result.success(it.token)
                    } ?: run {
                        _buy.value = Result.failure(Exception("No token found"))
                    }
                } else {
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
    fun fetchBillCancel(idUser: Int) {
        viewModelScope.launch {
            try {
                val call: Call<BillData> = ApiService.apiService.getBillCancel(idUser)
                call.enqueue(object : Callback<BillData> {
                    override fun onResponse(call: Call<BillData>, response: Response<BillData>) {
                        if (response.isSuccessful) {
                            val billCancelData = response.body()
                            Log.e("API Response billCancelData", billCancelData.toString())

                            val billMap = mutableMapOf<Int, billShow>()

                            billCancelData?.billCancel?.forEach { item ->
                                val maBill = item.maBill
                                if (billMap.containsKey(maBill)) {
                                    val existingItem = billMap[maBill]!!
                                    billMap[maBill] = existingItem.copy(
                                        soluong = existingItem.soluong + item.soluong,
                                        buyprice = existingItem.buyprice + item.buyprice
                                    )
                                } else {
                                    billMap[maBill] = item
                                }
                            }
                            val filteredBillCancelData = billMap.values.toList()
                            Log.e("dsBillcancel", filteredBillCancelData.toString())

                            _billShow.value = filteredBillCancelData
                            Log.e("StateFlow Update", _billShow.value.toString())
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
    fun fetchBillShip(idUser: Int) {
        viewModelScope.launch {
            try {
                val call: Call<BillData> = ApiService.apiService.getBillShip(idUser)
                call.enqueue(object : Callback<BillData> {
                    override fun onResponse(call: Call<BillData>, response: Response<BillData>) {
                        if (response.isSuccessful) {
                            val billShipData = response.body()
                            Log.e("API Response billShipData", billShipData.toString())

                            val billMap = mutableMapOf<Int, billShow>()

                            billShipData?.billShip?.forEach { item ->
                                val maBill = item.maBill
                                if (billMap.containsKey(maBill)) {
                                    val existingItem = billMap[maBill]!!
                                    billMap[maBill] = existingItem.copy(
                                        soluong = existingItem.soluong + item.soluong,
                                        buyprice = existingItem.buyprice + item.buyprice
                                    )
                                } else {
                                    billMap[maBill] = item
                                }
                            }
                            val filteredBillShipData = billMap.values.toList()
                            Log.e("dsBillShip", filteredBillShipData.toString())

                            _billShow.value = filteredBillShipData
                            Log.e("StateFlow Update", _billShow.value.toString())
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

    fun fetchBillHistory(idUser: Int) {
        viewModelScope.launch {
            try {
                val call: Call<BillData> = ApiService.apiService.getBillHistory(idUser)
                call.enqueue(object : Callback<BillData> {
                    override fun onResponse(call: Call<BillData>, response: Response<BillData>) {
                        if (response.isSuccessful) {
                            val billHistoryData = response.body()
                            Log.e("API Response billCancelData", billHistoryData.toString())

                            val billMap = mutableMapOf<Int, billShow>()

                            billHistoryData?.billHistory?.forEach { item ->
                                val maBill = item.maBill
                                if (billMap.containsKey(maBill)) {
                                    val existingItem = billMap[maBill]!!
                                    billMap[maBill] = existingItem.copy(
                                        soluong = existingItem.soluong + item.soluong,
                                        buyprice = existingItem.buyprice + item.buyprice
                                    )
                                } else {
                                    billMap[maBill] = item
                                }
                            }
                            val filteredBillCancelData = billMap.values.toList()
                            Log.e("dsBillHistory", filteredBillCancelData.toString())

                            _billShow.value = filteredBillCancelData
                            Log.e("StateFlow Update", _billShow.value.toString())
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
    fun fetchBillDetail(billId: Int) {
        viewModelScope.launch {
            try {
                val call: Call<BillData> = ApiService.apiService.getBillDetail(billId)
                call.enqueue(object : Callback<BillData> {
                    override fun onResponse(call: Call<BillData>, response: Response<BillData>) {
                        if (response.isSuccessful) {
                            val billDetailData = response.body()
                            _billDetail.value = billDetailData?.billDetail ?: emptyList()
                            Log.e("dsbilldetail", _billDetail.value.toString())
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


package com.example.sttc.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.sttc.model.AddBillRequest
import com.example.sttc.model.AddBillResponse
import com.example.sttc.model.Bill
import com.example.sttc.model.BillData
import com.example.sttc.model.ErrorAddBillResponse
import com.example.sttc.model.PayData
import com.example.sttc.model.billDetail
import com.example.sttc.model.billShow
import com.example.sttc.service.ApiService
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BillViewModel(context: Context) : ViewModel() {

    private val _bill = MutableStateFlow<List<billShow>>(emptyList())
    val bill = _bill.asStateFlow()

    private val _count = MutableLiveData<Int>()
    val count = _count.asFlow()

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
        //

    }

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



    //------------------------Thu------------------------
    fun fetchBillCancel() {
        val iduser = getUserIdFromSharedPreferences() ?: return
        viewModelScope.launch {
            if (System.currentTimeMillis() - lastFetchTime < 60000) {
                // Nếu lần tải trước đó chưa quá 60 giây, không tải lại
                return@launch
            }
            try {
                val call: Call<BillData> = ApiService.apiService.getBillCancel(iduser)
                call.enqueue(object : Callback<BillData> {
                    override fun onResponse(call: Call<BillData>, response: Response<BillData>) {
                        if (response.isSuccessful) {
                            val billCancelData = response.body()
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

                            _bill.value = filteredBillCancelData
                            Log.e("API Response billCancelData", billCancelData.toString())

                            Log.d("billCancelDataViewModel", "Fetched billCancelData Data: ${billCancelData?.bills}")
                            lastFetchTime = System.currentTimeMillis()

                        }
                    }

                    override fun onFailure(call: Call<BillData>, t: Throwable) {
                        Log.e("API billCancelData Error", "Error: ${t.message}")
                        t.printStackTrace()
                    }
                })
            } catch (e: Exception) {
                Log.e("API billCancelData Error", "Error: ${e.message}")
                e.printStackTrace()
            }
        }
    }
    fun fetchBillShip() {
        val iduser = getUserIdFromSharedPreferences() ?: return
        viewModelScope.launch {
            if (System.currentTimeMillis() - lastFetchTime < 60000) {
                // Nếu lần tải trước đó chưa quá 60 giây, không tải lại
                return@launch
            }
            try {
                val call: Call<BillData> = ApiService.apiService.getBillShip(iduser)
                call.enqueue(object : Callback<BillData> {
                    override fun onResponse(call: Call<BillData>, response: Response<BillData>) {
                        if (response.isSuccessful) {
                            val billShipData = response.body()
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
                            _bill.value = filteredBillShipData
                            Log.e("API Response billShipData", billShipData.toString())

                            Log.d("billShipDataViewModel", "Fetched billShipData Data: ${billShipData?.bills}")
                            lastFetchTime = System.currentTimeMillis()
                        } else {
                            if (response.code() == 429) {
                                Log.e(
                                    "API billShipData Error",
                                    "Error: Too Many Requests (429), retrying in 60 seconds"
                                )
                                viewModelScope.launch(Dispatchers.IO) {
                                    delay(60000) // Wait for 60 seconds before retrying
                                    fetchBillShip()
                                }
                            } else {
                                Log.e("API billShipData Error", "Error: ${response.code()}")
                            }
                        }
                    }

                    override fun onFailure(call: Call<BillData>, t: Throwable) {
                        Log.e("API billShipData Error", "Error: ${t.message}")
                        t.printStackTrace()
                    }
                })
            } catch (e: Exception) {
                Log.e("API billShipData Error", "Error: ${e.message}")
                e.printStackTrace()
            }
        }
    }

    fun fetchBillHistory() {
        val iduser = getUserIdFromSharedPreferences() ?: return
        viewModelScope.launch {
            if (System.currentTimeMillis() - lastFetchTime < 60000) {
                // Nếu lần tải trước đó chưa quá 60 giây, không tải lại
                return@launch
            }
            try {
                val call: Call<BillData> = ApiService.apiService.getBillHistory(iduser)
                call.enqueue(object : Callback<BillData> {
                    override fun onResponse(call: Call<BillData>, response: Response<BillData>) {
                        if (response.isSuccessful) {
                            val billHistoryData = response.body()
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

                            _bill.value = filteredBillCancelData
                            Log.e("API Response BillHistory", billHistoryData.toString())

                            Log.d("BillHistoryDataViewModel", "Fetched BillHistory Data: ${billHistoryData?.bills}")
                            lastFetchTime = System.currentTimeMillis()

                        } else {
                            if (response.code() == 429) {
                                Log.e(
                                    "API BillHistory Error",
                                    "Error: Too Many Requests (429), retrying in 60 seconds"
                                )
                                viewModelScope.launch(Dispatchers.IO) {
                                    delay(60000) // Wait for 60 seconds before retrying
                                    fetchBillHistory()
                                }
                            } else {
                                Log.e("API BillHistory Error", "Error: ${response.code()}")
                            }
                        }
                    }

                    override fun onFailure(call: Call<BillData>, t: Throwable) {
                        Log.e("API BillHistory Error", "Error: ${t.message}")
                        t.printStackTrace()
                    }
                })
            } catch (e: Exception) {
                Log.e("API BillHistory Error", "Error: ${e.message}")
                e.printStackTrace()
            }
        }
    }
    fun fetchBillDetail(billId: Int) {
        viewModelScope.launch {
            if (System.currentTimeMillis() - lastFetchTime < 60000) {
                // Nếu lần tải trước đó chưa quá 60 giây, không tải lại
                return@launch
            }
            try {
                val call: Call<BillData> = ApiService.apiService.getBillDetail(billId)
                call.enqueue(object : Callback<BillData> {
                    override fun onResponse(call: Call<BillData>, response: Response<BillData>) {
                        if (response.isSuccessful) {
                            val billDetailData = response.body()
                            _billDetail.value = billDetailData?.billDetail ?: emptyList()
                            Log.e("dsbilldetail", _billDetail.value.toString())
                        } else {
                            if (response.code() == 429) {
                                Log.e(
                                    "API dsbilldetail Error",
                                    "Error: Too Many Requests (429), retrying in 60 seconds"
                                )
                                viewModelScope.launch(Dispatchers.IO) {
                                    delay(60000) // Wait for 60 seconds before retrying
                                    fetchBillDetail(billId)
                                }
                            } else {
                                Log.e("API dsbilldetail Error", "Error: ${response.code()}")
                            }
                        }
                    }

                    override fun onFailure(call: Call<BillData>, t: Throwable) {
                        Log.e("API dsbilldetail Error", "Error: ${t.message}")
                        t.printStackTrace()
                    }
                })
            } catch (e: Exception) {
                Log.e("API dsbilldetail Error", "Error: ${e.message}")
                e.printStackTrace()
            }
        }
    }



}
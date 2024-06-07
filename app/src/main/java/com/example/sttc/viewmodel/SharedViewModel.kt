package com.example.sttc.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import com.example.sttc.model.AddBillRequest
import com.example.sttc.model.AddBillResponse
import com.example.sttc.model.Bill
import com.example.sttc.model.Carts
import com.example.sttc.model.ErrorAddResponse
import com.example.sttc.model.PayData
import com.example.sttc.service.ApiService
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SharedViewModel : ViewModel() {
    private val _selectedProducts = MutableLiveData<List<PayData>>()
    val selectedProducts = _selectedProducts.asFlow()

    fun setSelectedProducts(products: List<PayData>) {
        _selectedProducts.value = products
    }
}

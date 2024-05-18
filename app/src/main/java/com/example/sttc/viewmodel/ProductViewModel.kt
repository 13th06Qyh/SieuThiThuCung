package com.example.sttc.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.sttc.model.ProductData
import com.example.sttc.model.Sanpham
import com.example.sttc.service.ApiService.apiService
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductViewModel : ViewModel() {

    private val _products = MutableLiveData<List<Sanpham>>()
    val products = _products.asFlow()



    init {
        fetchProduct()
    }
    fun fetchProduct() {
        viewModelScope.launch {
            val call: Call<ProductData> = apiService.getProduct()
            call.enqueue(object : Callback<ProductData> {
                override fun onResponse(
                    call: Call<ProductData>,
                    response: Response<ProductData>
                ) {
                    if (response.isSuccessful) {

                        val productData = response.body()
                        _products.value = productData?.sanphams

                        Log.e("Response Category",productData.toString())


                    } else {
                        Log.e("API Error", "Error: ${response.code()}")
                    }
                }
                override fun onFailure(call: Call<ProductData>, t: Throwable) {
                    Log.e("API Error", "Error: ${t.message}")
                    t.printStackTrace()
                }
            })
        }


    }
}

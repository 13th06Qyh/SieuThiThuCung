package com.example.sttc.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.sttc.model.ImageSP
import com.example.sttc.model.ProductData
import com.example.sttc.model.Sanpham
import com.example.sttc.model.Tag
import com.example.sttc.service.ApiService.apiService
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

class ProductViewModel : ViewModel() {

    private val _products = MutableLiveData<List<Sanpham>>()
    val products = _products.asFlow()

    private val _productDogTA = MutableLiveData<List<Sanpham>>()
    val productDogTA = _productDogTA.asFlow()

    private val _images = MutableLiveData<Map<Int, List<ImageSP>>>()
    val images = _images.asFlow()

    private val _tag = MutableLiveData<List<Tag>>()
    val tag = _tag.asFlow()

    private var lastFetchTime: Long = 0
    init {
        fetchProduct()
        fetchTag()
    }

    fun fetchProduct() {
        viewModelScope.launch {
            if (System.currentTimeMillis() - lastFetchTime < 60000) {
                // Nếu lần tải trước đó chưa quá 60 giây, không tải lại
                return@launch
            }
            try {
                // Gửi yêu cầu để lấy dữ liệu sản phẩm
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
                            lastFetchTime = System.currentTimeMillis()

                        } else {
                            if (response.code() == 429) {
                                Log.e("API Error", "Error: Too Many Requests (429), retrying in 60 seconds")

//                                delay(60000)
                                fetchProduct()
                            } else {
                                Log.e("API Error", "Error: ${response.code()}")
                            }
                        }
                    }
                    override fun onFailure(call: Call<ProductData>, t: Throwable) {
                        Log.e("API Error", "Error: ${t.message}")
                        t.printStackTrace()
                    }
                })
            } catch (e: Exception) {
                Log.e("API Error", "Error: ${e.message}")
                e.printStackTrace()
            }
//            val call: Call<ProductData> = apiService.getProduct()
//            call.enqueue(object : Callback<ProductData> {
//                override fun onResponse(
//                    call: Call<ProductData>,
//                    response: Response<ProductData>
//                ) {
//                    if (response.isSuccessful) {
//
//                        val productData = response.body()
//                        _products.value = productData?.sanphams
//
//                        Log.e("Response Category",productData.toString())
//
//
//                    } else {
//                        Log.e("API Error", "Error: ${response.code()}")
//                    }
//                }
//                override fun onFailure(call: Call<ProductData>, t: Throwable) {
//                    Log.e("API Error", "Error: ${t.message}")
//                    t.printStackTrace()
//                }
//            })
        }


    }

    fun fetchImages(productId: Int) {
        viewModelScope.launch {
            val call: Call<List<ImageSP>> = apiService.getImagesByProductId(productId)
            call.enqueue(object : Callback<List<ImageSP>> {
                override fun onResponse(
                    call: Call<List<ImageSP>>,
                    response: Response<List<ImageSP>>
                ) {
                    if (response.isSuccessful) {
                        val images = response.body()
                        _images.value = _images.value.orEmpty() + (productId to (images ?: emptyList()))
                        Log.e("Response Images", images.toString())
                    } else {
                        Log.e("API Error", "Error: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<List<ImageSP>>, t: Throwable) {
                    Log.e("API Error", "Error: ${t.message}")
                    t.printStackTrace()
                }
            })
        }
    }

    fun fetchTag() {
        viewModelScope.launch {
            val call: Call<ProductData> = apiService.getProduct()
            call.enqueue(object : Callback<ProductData> {
                override fun onResponse(
                    call: Call<ProductData>,
                    response: Response<ProductData>
                ) {
                    if (response.isSuccessful) {

                        val tagData = response.body()
                        _tag.value = tagData?.tags

                        Log.e("Response Category",tagData.toString())


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

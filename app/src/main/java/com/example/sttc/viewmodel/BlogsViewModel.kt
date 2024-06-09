package com.example.sttc.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.sttc.model.Blogs
import com.example.sttc.model.BlogsData
import com.example.sttc.model.ImageBlogs
import com.example.sttc.model.ImageSP
import com.example.sttc.model.ProductData
import com.example.sttc.service.ApiService.apiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class BlogsViewModel : ViewModel() {
    private val _blog = MutableStateFlow<List<Blogs>>(emptyList())
    val blogs: StateFlow<List<Blogs>> = _blog

    private val _imagesBlogs = MutableStateFlow<Map<Int, List<ImageBlogs>>>(emptyMap())
    val imagesBlogs: StateFlow<Map<Int, List<ImageBlogs>>> = _imagesBlogs

    init {
        fetchBlogs()

    }

    fun fetchBlogs() {
        viewModelScope.launch {
            try {
                val call: Call<BlogsData> = apiService.getListBlogs()
                call.enqueue(object : Callback<BlogsData> {
                    override fun onResponse(call: Call<BlogsData>, response: Response<BlogsData>) {
                        if (response.isSuccessful) {

                            val blogData = response.body()
                            _blog.value = response.body()?.blogs.orEmpty()

                            Log.e("BlogData fetchBlogs ", blogData.toString())

                        } else {
                            if (response.code() == 429) {
                                Log.e(
                                    "API Error",
                                    "Error: Too Many Requests (429), retrying in 60 seconds"
                                )
                                viewModelScope.launch(Dispatchers.IO) {
                                    delay(60000) // Wait for 60 seconds before retrying
                                    fetchBlogs()
                                }
                            } else {
                                Log.e("API Error", "Error: ${response.code()}")
                            }
                        }
                    }

                    override fun onFailure(call: Call<BlogsData>, t: Throwable) {
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

    fun fetchImages(blogId: Int) {

        viewModelScope.launch {
            val call: Call<List<ImageBlogs>> = apiService.getImagesByBlogId(blogId)
            call.enqueue(object : Callback<List<ImageBlogs>> {
                override fun onResponse(
                    call: Call<List<ImageBlogs>>,
                    response: Response<List<ImageBlogs>>
                ) {
                    if (response.isSuccessful) {
                        val images = response.body()
                        _imagesBlogs.value =
                            _imagesBlogs.value.orEmpty() + (blogId to (images ?: emptyList()))
                        Log.e("image blog", images.toString())
                    } else {
                        Log.e("Response Images", "Error: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<List<ImageBlogs>>, t: Throwable) {
                    Log.e("API Error fetchImages ", "Error: ${t.message}")
                    t.printStackTrace()
                }
            })
        }
    }
    fun fetchBlogDetailById(id: Int) {
        viewModelScope.launch {
            try {
                val call: Call<BlogsData> = apiService.getBlogDetailById(id)
                call.enqueue(object : Callback<BlogsData> {
                    override fun onResponse(
                        call: Call<BlogsData>,
                        response: Response<BlogsData>
                    ) {
                        if (response.isSuccessful) {
                            val blogData = response.body()
                            _blog.value = response.body()?.blogs.orEmpty()
                            Log.e("getBlogDetailById", blogData.toString())
                        } else {
                            if (response.code() == 429) {
                                Log.e(
                                    "API getBlogDetailById ",
                                    "Error: Too Many Requests (429), retrying in 60 seconds"
                                )
                                viewModelScope.launch(Dispatchers.IO) {
                                    delay(60000) // Wait for 60 seconds before retrying
                                    fetchBlogs()
                                }
                            } else {
                                Log.e("API Error", "Error: ${response.code()}")
                            }
                        }
                    }

                    override fun onFailure(call: Call<BlogsData>, t: Throwable) {
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
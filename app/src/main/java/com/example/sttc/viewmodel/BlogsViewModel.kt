package com.example.sttc.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.sttc.model.Blogs
import com.example.sttc.model.BlogsData
import com.example.sttc.model.ImageBlogs
import com.example.sttc.model.ProductData
import com.example.sttc.service.ApiService.apiService
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class BlogsViewModel : ViewModel(){
    private val _blog = MutableLiveData<List<Blogs>>()
    val blogs = _blog.asFlow()

    private val _imagesBlogs = MutableLiveData<List<ImageBlogs>>()
    val imagesBlogs = _imagesBlogs.asFlow()
    private var lastFetchTime: Long = 0
    init {
        fetchBlogs()
    }
    fun fetchBlogs(){
        viewModelScope.launch{
            try {
                val call : Call<BlogsData> = apiService.getListBlogs()
                call.enqueue(object : Callback<BlogsData>{
                    override fun onResponse(call: Call<BlogsData>, response: Response<BlogsData>) {
                        if (response.isSuccessful) {

                            val blogData = response.body()
                            _blog.value = blogData?.blogs

                            Log.e("BlogData fetchBlogs ", blogData.toString())

                        } else {
                            if (response.code() == 429) {
                                Log.e(
                                    "API Error",
                                    "Error: Too Many Requests (429), retrying in 60 seconds"
                                )
                                fetchBlogs()
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
            }catch (e: Exception){
                Log.e("API Error", "Error: ${e.message}")
                e.printStackTrace()
            }
        }
    }
    fun fetchBlogById(blogId: Int) {
        viewModelScope.launch {
            try {
                val call: Call<BlogsData> = apiService.getBlogById(blogId)
                call.enqueue(object : Callback<BlogsData> {
                    override fun onResponse(
                        call: Call<BlogsData>,
                        response: Response<BlogsData>
                    ) {
                        if (response.isSuccessful) {

                            val blogData = response.body()
                            _blog.value = blogData?.blogs

                            Log.e("Response Category", blogData.toString())
                            lastFetchTime = System.currentTimeMillis()

                        } else {
                            if (response.code() == 429) {
                                Log.e(
                                    "API Error",
                                    "Error: Too Many Requests (429), retrying in 60 seconds"
                                )
                                fetchBlogs()
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
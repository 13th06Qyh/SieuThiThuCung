package com.example.sttc.model.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.sttc.model.ApiBlogs
import com.example.sttc.model.Blogs
import com.example.sttc.service.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

interface BlogRepository {
    fun fetchNewBlogs() //
    fun getAllBlogs(): LiveData<List<Blogs>>
}

class BlogRepositoryImpl @Inject constructor() : BlogRepository {
    private val blogsLiveData = MutableLiveData<List<Blogs>>()
    override fun fetchNewBlogs() {
        ApiService.apiService.getListBlogs().enqueue(object : Callback<ApiBlogs> {
            override fun onResponse(call: Call<ApiBlogs>, response: Response<ApiBlogs>) {
                if (response.isSuccessful) {
                    blogsLiveData.postValue(response.body()?.blogs ?: emptyList())
                }
            }
            override fun onFailure(call: Call<ApiBlogs>, t: Throwable) {
            }
        })
    }

    override fun getAllBlogs(): LiveData<List<Blogs>> {
        return blogsLiveData
    }
}


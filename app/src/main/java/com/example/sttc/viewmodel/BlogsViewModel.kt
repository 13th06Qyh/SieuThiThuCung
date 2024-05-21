package com.example.sttc.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sttc.model.Blogs
import com.example.sttc.model.repository.BlogRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BlogsViewModel @Inject constructor(
    private val blogRepo: BlogRepository
) : ViewModel() {

    private val _isLoading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }

    val isLoading: LiveData<Boolean> get() = _isLoading

    val blogs: LiveData<List<Blogs>> by lazy {
        blogRepo.getAllBlogs()
    }

    fun fetchBlogs() {
        if (_isLoading.value == false)
            viewModelScope.launch(Dispatchers.IO) {
                _isLoading.postValue(true)
                blogRepo.fetchNewBlogs()
                _isLoading.postValue(false)
            }
    }
}
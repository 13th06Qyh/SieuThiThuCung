package com.example.sttc.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.sttc.model.Notice
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Date

class NotificationViewModel(context: Context) : ViewModel() {
    private val context = context
    private val gson = Gson()
    private val noticeList = mutableListOf<Notice>()
    private val _notice = MutableStateFlow<List<Notice>>(emptyList())
    val notice = _notice.asStateFlow()

    init {
        readFromSharedPreferences() // Read initial data from shared preferences
        if (noticeList.isEmpty()) {
            initializeNotifications() // Initialize with a default notification if empty
        }
    }

    fun updateNotice(message: String) {
        val idUser = getUserIdFromSharedPreferences() ?: return
        val date = Date()
        val newNotice = Notice(idUser, message, date)
        noticeList.add(newNotice)
        _notice.value = noticeList.toList() // Update flow with new state
        saveToSharedPreferences() // Save updated list to shared preferences
    }

    fun deleteNotice(id: Int) {
        // Xóa notice với userId bằng id từ danh sách
        noticeList.removeAll { it.userid == id }

        // Cập nhật flow với danh sách đã thay đổi
        _notice.value = noticeList.toList()

        // Cập nhật SharedPreferences
        saveToSharedPreferences()
    }

    private fun saveToSharedPreferences() {
        val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putString("notice", gson.toJson(noticeList))
            apply()
        }
    }

    private fun readFromSharedPreferences() {
        val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        val noticeJson = sharedPreferences.getString("notice", null)
        if (noticeJson != null) {
            val noticesArray = gson.fromJson(noticeJson, Array<Notice>::class.java)
            noticeList.addAll(noticesArray)
            _notice.value = noticeList.toList() // Set initial state from shared preferences
        }
    }

    private fun initializeNotifications() {
        val idUser = getUserIdFromSharedPreferences() ?: return
        noticeList.add(Notice(idUser, "Chúc bạn một ngày tốt lành!", Date()))
        _notice.value = noticeList.toList() // Set initial state
    }

    private fun getUserIdFromSharedPreferences(): Int? {
        val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        val idUser = sharedPreferences.getInt("iduser", -1)
        return if (idUser == -1) null else idUser
    }
}


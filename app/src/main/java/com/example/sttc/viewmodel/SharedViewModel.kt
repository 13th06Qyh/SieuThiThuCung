package com.example.sttc.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import com.example.sttc.model.PayData
import com.example.sttc.view.System.BankData
import com.example.sttc.view.System.OtherAddress
import com.google.gson.Gson

class SharedViewModel(context: Context) : ViewModel() {
    private val context = context

    private val _bankDataInfoFlow = MutableLiveData<BankData?>(null)
    val bankDataInfoFlow = _bankDataInfoFlow.asFlow()

    private val _otherAddressInfoFlow = MutableLiveData<OtherAddress?>(null)
    val otherAddressInfoFlow = _otherAddressInfoFlow.asFlow()

    private val _selectedProducts = MutableLiveData<List<PayData>>()
    val selectedProducts = _selectedProducts.asFlow()

    private val _selectedOtherAddress = MutableLiveData<OtherAddress>()
    val selectedOtherAddress = _selectedOtherAddress.asFlow()

    private val _selectedBankData = MutableLiveData<BankData>()
    val selectedBankData = _selectedBankData.asFlow()

    init {
        readBankDataInfoFromSharedPreferences()
        readOtherAddressInfoFromSharedPreferences()
    }
    fun setSelectedProducts(products: List<PayData>) {
        _selectedProducts.value = products
    }

    fun setSelectedBankData(bankData: BankData) {
        _selectedBankData.value = bankData
        saveBankInfoToSharedPreferences(bankData)
        updateBankDataInfo(bankData)
        Log.d("SharedViewModel", "setSelectedBankData: $bankData")
    }

    private fun readBankDataInfoFromSharedPreferences() {
        val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        val bankDataJson = sharedPreferences.getString("bankdata", null)
        val bankData = getBankDataInfoFromJson(bankDataJson)
        _bankDataInfoFlow.value = bankData
    }

    // Function to update user info
    fun updateBankDataInfo(newBankData: BankData) {
        val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("bankdata", Gson().toJson(newBankData))
        editor.apply()
        _bankDataInfoFlow.value = newBankData
    }

    private fun getBankDataInfoFromJson(bankDataJson: String?): BankData? {
        return if (bankDataJson != null) Gson().fromJson(bankDataJson, BankData::class.java) else null
    }

    private fun saveBankInfoToSharedPreferences(bankData: BankData) {
        val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putString("bankdata", Gson().toJson(bankData)) // Convert user object to JSON string
            apply()
        }
    }

    fun setSelectedOtherAddress(otherAddress: OtherAddress) {
        _selectedOtherAddress.value = otherAddress
        saveOtherAddressInfoToSharedPreferences(otherAddress)
        updateOtherAddressInfo(otherAddress)
        Log.d("SharedViewModelOtherAddress", "setSelectedOtherAddress: $otherAddress")
    }

    private fun readOtherAddressInfoFromSharedPreferences() {
        val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        val otherAddressJson = sharedPreferences.getString("otheraddress", null)
        val otherAddress = getOtherAddressInfoFromJson(otherAddressJson)
        _otherAddressInfoFlow.value = otherAddress
    }

    // Function to update user info
    fun updateOtherAddressInfo(newOtherAddress: OtherAddress) {
        val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("otheraddress", Gson().toJson(newOtherAddress))
        editor.apply()
        _otherAddressInfoFlow.value = newOtherAddress
    }

    private fun getOtherAddressInfoFromJson(otherAddressJson: String?): OtherAddress? {
        return if (otherAddressJson != null) Gson().fromJson(otherAddressJson, OtherAddress::class.java) else null
    }

    private fun saveOtherAddressInfoToSharedPreferences(otherAddress: OtherAddress) {
        val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putString("otheraddress", Gson().toJson(otherAddress)) // Convert user object to JSON string
            apply()
        }
    }



}

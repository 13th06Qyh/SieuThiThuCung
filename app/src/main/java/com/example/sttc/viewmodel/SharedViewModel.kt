package com.example.sttc.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.sttc.model.PayData
import com.example.sttc.model.Search
import com.example.sttc.model.SearchData
import com.example.sttc.service.ApiService
import com.example.sttc.view.System.BankData
import com.example.sttc.view.System.Check
import com.example.sttc.view.System.Key
import com.example.sttc.view.System.Keyword
import com.example.sttc.view.System.OtherAddress
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SharedViewModel(context: Context) : ViewModel() {
    private val context = context

    private var lastFetchTime: Long = 0

    private val _productDT = MutableLiveData<List<Search>>()
    val productDT = _productDT.asFlow()

    private val _countD = MutableLiveData<Int>()
    val countD = _countD.asFlow()

    private val _bankDataInfoFlow = MutableLiveData<BankData?>(null)
    val bankDataInfoFlow = _bankDataInfoFlow.asFlow()

    private val _otherAddressInfoFlow = MutableLiveData<OtherAddress?>(null)
    val otherAddressInfoFlow = _otherAddressInfoFlow.asFlow()

    private val _keywordInfoFlow = MutableLiveData<Keyword?>(null)
    val keywordInfoFlow = _keywordInfoFlow.asFlow()

    private val _checkInfoFlow = MutableLiveData<Check?>(null)
    val checkInfoFlow = _checkInfoFlow.asFlow()

    private val _selectedCheck = MutableLiveData<Check>()
    val selectedCheck = _selectedCheck.asFlow()

    private val _selectedKeyword = MutableLiveData<Keyword>()
    val selectedKeyword = _selectedKeyword.asFlow()

    private val _selectedProducts = MutableLiveData<List<PayData>>()
    val selectedProducts = _selectedProducts.asFlow()

    private val _selectedOtherAddress = MutableLiveData<OtherAddress>()
    val selectedOtherAddress = _selectedOtherAddress.asFlow()

    private val _selectedBankData = MutableLiveData<BankData>()
    val selectedBankData = _selectedBankData.asFlow()

    init {
        readBankDataInfoFromSharedPreferences()
        readOtherAddressInfoFromSharedPreferences()
        readCheckInfoFromSharedPreferences()
        readKeywordInfoFromSharedPreferences()
        search()
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

    fun setSelectedCheck(check: Check) {
        _selectedCheck.value = check
        saveCheckInfoToSharedPreferences(check)
        updateCheckInfo(check)
        Log.d("SharedViewModelCheck", "setSelectedCheck: $check")
    }

    private fun readCheckInfoFromSharedPreferences() {
        val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        val checkJson = sharedPreferences.getString("check", null)
        val check = getCheckInfoFromJson(checkJson)
        _checkInfoFlow.value = check
    }

    // Function to update user info
    fun updateCheckInfo(newCheck: Check) {
        val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("check", Gson().toJson(newCheck))
        editor.apply()
        _checkInfoFlow.value = newCheck
    }

    private fun getCheckInfoFromJson(checkJson: String?): Check? {
        return if (checkJson != null) Gson().fromJson(checkJson, Check::class.java) else null
    }

    private fun saveCheckInfoToSharedPreferences(check: Check) {
        val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putString("check", Gson().toJson(check)) // Convert user object to JSON string
            apply()
        }
    }

    fun setSelectedKeyword(keyword: Keyword) {
        _selectedKeyword.value = keyword
        saveKeywordInfoToSharedPreferences(keyword)
        updateKeywordInfo(keyword)
        Log.d("SharedViewModelKeyword", "setSelectedKeyword: $keyword")
    }

    private fun readKeywordInfoFromSharedPreferences() {
        val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        val keywordJson = sharedPreferences.getString("keyword", null)
        val keyword = getKeywordInfoFromJson(keywordJson)
        _keywordInfoFlow.value = keyword
    }

    private fun getKeywordInfoFromJson(keywordJson: String?): Keyword? {
        return if (keywordJson != null) Gson().fromJson(keywordJson, Keyword::class.java) else null
    }

    // Function to update user info
    fun updateKeywordInfo(newKeyword: Keyword) {
        val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("keyword", Gson().toJson(newKeyword))
        editor.apply()
        _keywordInfoFlow.value = newKeyword
    }
    private fun saveKeywordInfoToSharedPreferences(keyword: Keyword) {
        val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putString("keyword", Gson().toJson(keyword)) // Convert keyword object to JSON string
            apply()
        }
    }

    fun search() {
        viewModelScope.launch {
            val keywordJson = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
                .getString("keyword", null)
            val keywordObject = getKeywordInfoFromJson(keywordJson)

            if (keywordObject == null) {
                Log.e("Search", "No keyword found in SharedPreferences")
                return@launch
            }

            val keyword = keywordObject.keyword // Extract the keyword string

            Log.d("SearchViewModel", "Searching for: $keyword")

            if (System.currentTimeMillis() - lastFetchTime < 60000) {
                // If the last fetch was less than 60 seconds ago, do not fetch again
                return@launch
            }

            val key = Key(keyword)
            try {
                // Send the request to fetch search data
                val call: Call<SearchData> = ApiService.apiService.search(key)
                call.enqueue(object : Callback<SearchData> {
                    override fun onResponse(
                        call: Call<SearchData>,
                        response: Response<SearchData>
                    ) {
                        if (response.isSuccessful) {
                            val searchData = response.body()
                            _productDT.value = searchData?.sanphams
                            _countD.value = searchData?.productCount
                            Log.d("SearchViewModel", "Fetched Search Data: ${searchData?.sanphams}")
                            Log.d("SearchViewModelCount", "Fetched Search Data: ${searchData?.productCount}")

                            lastFetchTime = System.currentTimeMillis()
                        } else {
                            if (response.code() == 429) {
                                Log.e(
                                    "API Search Error",
                                    "Error: Too Many Requests (429), retrying in 60 seconds"
                                )
                                viewModelScope.launch(Dispatchers.IO) {
                                    delay(60000) // Wait for 60 seconds before retrying
                                    search()
                                }
                            } else {
                                Log.e("API Search3 Error", "Error: ${response.code()}")
                            }
                        }
                    }

                    override fun onFailure(call: Call<SearchData>, t: Throwable) {
                        Log.e("API Search Error", "Error: ${t.message}")
                        t.printStackTrace()
                    }
                })
            } catch (e: Exception) {
                Log.e("API Search Error", "Error: ${e.message}")
                e.printStackTrace()
            }
        }
    }

}

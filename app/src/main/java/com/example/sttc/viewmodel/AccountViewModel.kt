package com.example.sttc.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import com.example.sttc.model.ErrorResponse
import com.example.sttc.model.ErrorSignupResponse
import com.example.sttc.model.SignupResponse
import com.example.sttc.model.LoginRequest
import com.example.sttc.model.LoginResponse
import com.example.sttc.model.SignupRequest
import com.example.sttc.model.UpdateAddressRequest
import com.example.sttc.model.UpdateAddressResponse
import com.example.sttc.model.UpdateMailRequest
import com.example.sttc.model.UpdateMailResponse
import com.example.sttc.model.UpdateNameRequest
import com.example.sttc.model.UpdateNameResponse
import com.example.sttc.model.UpdateOTPRequest
import com.example.sttc.model.UpdateOTPResponse
import com.example.sttc.model.UpdatePassRequest
import com.example.sttc.model.UpdatePassResponse
import com.example.sttc.model.UpdatePhoneRequest
import com.example.sttc.model.UpdatePhoneResponse
import com.example.sttc.model.User
import com.example.sttc.service.ApiService.apiService
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.stream.JsonReader
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.StringReader

class AccountViewModel(context: Context) : ViewModel() {
    private val _userInfoFlow = MutableLiveData<User?>(null)
    val userInfoFlow = _userInfoFlow.asFlow()

    private val _request = MutableLiveData<String>(null)
    val request = _request.asFlow()

    private val _update= MutableLiveData<Result<Pair<String, String>>>()
    val update= _update.asFlow()

    private val _create= MutableLiveData<Result<String>>()
    val create= _create.asFlow()

    private val _loginResult = MutableLiveData<Result<String>>()
    val loginResult = _loginResult.asFlow()

    private val _signupResult = MutableLiveData<Result<String>>()
    val signupResult = _signupResult.asFlow()

    private val context = context

    private fun readUserInfoFromSharedPreferences() {
        val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        val userJson = sharedPreferences.getString("user", null)
        val user = getUserInfoFromJson(userJson)
        _userInfoFlow.value = user
    }

    // Function to update user info
    fun updateUserInfo(newUser: User) {
        val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("user", Gson().toJson(newUser))
        editor.apply()
        _userInfoFlow.value = newUser
    }

    private fun getUserInfoFromJson(userJson: String?): User? {
        return if (userJson != null) Gson().fromJson(userJson, User::class.java) else null
    }

    init {
        readUserInfoFromSharedPreferences()
    }
    fun login(username: String, password: String) {
        val loginRequest = LoginRequest(username, password)
        apiService.login(loginRequest).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let {loginResponse ->
                        val token = loginResponse.token
                        val user = loginResponse.user
                        // Save token and user info to SharedPreferences
                        saveUserInfoToSharedPreferences(token, user)
                        updateUserInfo(user)
                        _loginResult.value = Result.success(token)
                    } ?: run {
                        _loginResult.value = Result.failure(Exception("No token found"))
                    }
                } else {
                    val errorBody = response.errorBody()?.string()
                    Log.e("API Error", "Error body: $errorBody")
                    val errorResponse = try {
                        if (!errorBody.isNullOrEmpty()) {
                            Gson().fromJson(errorBody, ErrorResponse::class.java)
                        } else {
                            ErrorResponse("Login failed", "Unknown error")
                        }
                    } catch (e: JsonSyntaxException) {
                        ErrorResponse("Login failed", "Malformed error response")
                    }
                    _loginResult.value = Result.failure(Exception(errorResponse.error))
                }

            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                _loginResult.value = Result.failure(t)
            }
        })
    }

    fun updateName(username: String, id: Int) {
        val token = getTokenFromSharedPreferences()
        if (token == null) {
            _update.value = Result.failure(Exception("No token found"))
            return
        }
        val updateNameRequest = UpdateNameRequest(username, id)
        apiService.updateName("Bearer $token", id, updateNameRequest).enqueue(object : Callback<UpdateNameResponse> {
            override fun onResponse(call: Call<UpdateNameResponse>, response: Response<UpdateNameResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let { updatedResponse ->
                        val user = updatedResponse.user
                        saveUserInfoToSharedPreferences(token, user)
                        updateUserInfo(user)
                        val type = Pair(token, "updateName")
                        _update.value = Result.success(type)
                    } ?: run {
                        _update.value = Result.failure(Exception("No user found"))
                    }
                } else {
                    handleErrorResponse(response, "updateName")
                }
            }

            override fun onFailure(call: Call<UpdateNameResponse>, t: Throwable) {
                _update.value = Result.failure(t)
            }
        })
    }

    fun updateMail(email: String, id: Int) {
        val token = getTokenFromSharedPreferences()
        if (token == null) {
            _update.value = Result.failure(Exception("No token found"))
            return
        }
        val updateMailRequest = UpdateMailRequest(email, id)
        apiService.updateMail("Bearer $token", id, updateMailRequest).enqueue(object : Callback<UpdateMailResponse> {
            override fun onResponse(call: Call<UpdateMailResponse>, response: Response<UpdateMailResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let { updatedResponse ->
                        val user = updatedResponse.user
                        saveUserInfoToSharedPreferences(token, user)
                        updateUserInfo(user)
                        val type = Pair(token, "updateMail")
                        _update.value = Result.success(type)
                    } ?: run {
                        _update.value = Result.failure(Exception("No user found"))
                    }
                } else {
                    handleErrorResponse(response, "updateMail")
                }
            }

            override fun onFailure(call: Call<UpdateMailResponse>, t: Throwable) {
                _update.value = Result.failure(t)
            }
        })
    }

    fun updatePhone(sdt: Int, id: Int) {
        val token = getTokenFromSharedPreferences()
        if (token == null) {
            _update.value = Result.failure(Exception("No token found"))
            return
        }
        val updatePhoneRequest = UpdatePhoneRequest(sdt, id)
        apiService.updatePhone("Bearer $token", id, updatePhoneRequest).enqueue(object : Callback<UpdatePhoneResponse> {
            override fun onResponse(call: Call<UpdatePhoneResponse>, response: Response<UpdatePhoneResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let { updatedResponse ->
                        val user = updatedResponse.user
                        saveUserInfoToSharedPreferences(token, user)
                        updateUserInfo(user)
                        val type = Pair(token, "updatePhone")
                        _update.value = Result.success(type)
                    } ?: run {
                        _update.value = Result.failure(Exception("No user found"))
                    }
                } else {
                    handleErrorResponse(response, "updatePhone")
                }
            }

            override fun onFailure(call: Call<UpdatePhoneResponse>, t: Throwable, ) {
                _update.value = Result.failure(t)
            }
        })
    }

    fun updateAddress(diachi: String, id: Int) {
        val token = getTokenFromSharedPreferences()
        if (token == null) {
            _update.value = Result.failure(Exception("No token found"))
            return
        }
        val updateAddressRequest = UpdateAddressRequest(diachi, id)
        apiService.updateAddress("Bearer $token", id, updateAddressRequest).enqueue(object : Callback<UpdateAddressResponse> {
            override fun onResponse(call: Call<UpdateAddressResponse>, response: Response<UpdateAddressResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let { updatedResponse ->
                        val user = updatedResponse.user
                        saveUserInfoToSharedPreferences(token, user)
                        updateUserInfo(user)
                        val type = Pair(token, "updateAddress")
                        _update.value = Result.success(type)
                    } ?: run {
                        _update.value = Result.failure(Exception("No user found"))
                    }
                } else {
                    handleErrorResponse(response, "updateAddress")
                }
            }

            override fun onFailure(call: Call<UpdateAddressResponse>, t: Throwable) {
                _update.value = Result.failure(t)
            }
        })
    }

    fun changePass(currentpassword: String, newpassword: String, confirmpassword: String, id: Int) {
        val token = getTokenFromSharedPreferences()
        if (token == null) {
            _update.value = Result.failure(Exception("No token found"))
            return
        }
        val updatePassRequest = UpdatePassRequest(currentpassword, newpassword, confirmpassword, id)
        apiService.changePass("Bearer $token", id, updatePassRequest).enqueue(object : Callback<UpdatePassResponse> {
            override fun onResponse(call: Call<UpdatePassResponse>, response: Response<UpdatePassResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let { updatedResponse ->
                        val user = updatedResponse.user
                        saveUserInfoToSharedPreferences(token, user)
                        updateUserInfo(user)
                        val type = Pair(token, "changePass")
                        _update.value = Result.success(type)
                        Log.d("token", updatedResponse.message)
                    } ?: run {
                        _update.value = Result.failure(Exception("No user found"))
                    }
                } else {
                    val errorBody = response.errorBody()?.string()
                    Log.e("API ChangePass Error", "Error body: $errorBody")

                    // Parse JSON error body
                    val errorMessage = try {
                        val jsonObject = JSONObject(errorBody)
                        // Directly fetch the error message using the "error" key
                        jsonObject.getString("error")
                    } catch (e: JSONException) {
                        "Unknown error occurred"
                    }
                    Log.e("API ChangePass Error", "Parsed error message: $errorMessage")
                    _update.value = Result.failure(Exception(errorMessage))
                }
            }

            override fun onFailure(call: Call<UpdatePassResponse>, t: Throwable) {
                _update.value = Result.failure(t)
            }
        })
    }

    fun getUserIdFromSharedPreferences(): Int? {
        val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        val idUser = sharedPreferences.getInt("iduser", -1)
        return if (idUser == -1) null else idUser
    }

    fun createOTP(otp: String) {
        val token = getTokenFromSharedPreferences()
        if (token == null) {
            _create.value = Result.failure(Exception("No token found"))
            return
        }
        val iduser = getUserIdFromSharedPreferences() ?: return
        val updateOTPRequest = UpdateOTPRequest(otp)
        apiService.createOTP("Bearer $token", iduser, updateOTPRequest).enqueue(object : Callback<UpdateOTPResponse> {
            override fun onResponse(call: Call<UpdateOTPResponse>, response: Response<UpdateOTPResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let { updatedResponse ->
                        val user = updatedResponse.user
                        saveUserInfoToSharedPreferences(token, user)
                        updateUserInfo(user)
                        _create.value = Result.success(token)
                    } ?: run {
                        _create.value = Result.failure(Exception("No user found"))
                    }
                } else {
                    val errorBody = response.errorBody()?.string()
                    Log.e("API CreateOTP Error", "Error body: $errorBody")

                    // Parse JSON error body
                    val errorMessage = try {
                        val jsonObject = JSONObject(errorBody)
                        val errorsObject = jsonObject.getJSONObject("errors")

                        val errorMessages = mutableListOf<String>()

                        // Iterate through all fields in the errors object
                        errorsObject.keys().forEach { key ->
                            val errorArray = errorsObject.getJSONArray(key)
                            for (i in 0 until errorArray.length()) {
                                errorMessages.add(errorArray.getString(i))
                            }
                        }

                        // Join all error messages into a single string
                        errorMessages.joinToString(separator = "\n")

                    } catch (e: JSONException) {
                        "Unknown error occurred"
                    }
                    Log.e("API CreateOTP Error", "Parsed error message: $errorMessage")

                    _create.value = Result.failure(Exception(errorMessage))
                }
            }

            override fun onFailure(call: Call<UpdateOTPResponse>, t: Throwable) {
                _create.value = Result.failure(t)
            }
        })
    }

    private fun saveUserInfoToSharedPreferences(token: String, user: User) {
        val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putString("token", token)
            putString("user", Gson().toJson(user)) // Convert user object to JSON string
            putInt("iduser", user.id) // Lưu iduser vào SharedPreferences
            apply()
        }
    }

    fun getTokenFromSharedPreferences(): String? {
        val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        return sharedPreferences.getString("token", null)
    }

    fun logout() {
        // Xử lý việc đăng xuất ở đây, chẳng hạn là xóa token và thông tin user từ SharedPreferences
        val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            remove("token")
            remove("user")
            remove("iduser")
            remove("bankdata")
            remove("otheraddress")
            remove("now")
            remove("check")
            remove("keyword")
            apply()
        }
    }

    fun signup(username: String, email: String, sdt: Int, password: String) {
        val signupRequest = SignupRequest(username, email, sdt, password)
        apiService.signup(signupRequest).enqueue(object : Callback<SignupResponse> {
            override fun onResponse(call: Call<SignupResponse>, response: Response<SignupResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        _signupResult.value = Result.success(it.token)
                    } ?: run {
                        _signupResult.value = Result.failure(Exception("No token found"))
                    }
                } else {
                    val errorBody = response.errorBody()?.string()
                    Log.e("API Signup Error", "Error body: $errorBody")

                    // Parse JSON error body
                    val errorMessage = try {
                        val jsonObject = JSONObject(errorBody)
                        val errorsObject = jsonObject.getJSONObject("errors")

                        val errorMessages = mutableListOf<String>()

                        // Iterate through all fields in the errors object
                        errorsObject.keys().forEach { key ->
                            val errorArray = errorsObject.getJSONArray(key)
                            for (i in 0 until errorArray.length()) {
                                errorMessages.add(errorArray.getString(i))
                            }
                        }

                        // Join all error messages into a single string
                        errorMessages.joinToString(separator = "\n")

                    } catch (e: JSONException) {
                        "Unknown error occurred"
                    }
                    Log.e("API SignUP Error", "Parsed error message: $errorMessage")

                _signupResult.value = Result.failure(Exception(errorMessage))
                }
            }


            override fun onFailure(call: Call<SignupResponse>, t: Throwable) {
                _signupResult.value = Result.failure(t)
            }
        })
    }

     fun handleErrorResponse(response: Response<*>, requestType: String) {
         _request.value = requestType
        val errorBody = response.errorBody()?.string()
        Log.e("API Update Error", "Error body: $errorBody")

        // Parse JSON error body
        val errorMessage = try {
            val jsonObject = JSONObject(errorBody)
            val errorsObject = jsonObject.getJSONObject("errors")

            val errorMessages = mutableListOf<String>()

            // Iterate through all fields in the errors object
            errorsObject.keys().forEach { key ->
                val errorArray = errorsObject.getJSONArray(key)
                for (i in 0 until errorArray.length()) {
                    errorMessages.add(errorArray.getString(i))
                }
            }

            // Join all error messages into a single string
            errorMessages.joinToString(separator = "\n")

        } catch (e: JSONException) {
            "Unknown error occurred"
        }
        Log.e("API Update Error", "Parsed error message: $errorMessage")
         _update.value = Result.failure(Exception(errorMessage))
    }

}




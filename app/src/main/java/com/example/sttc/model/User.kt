package com.example.sttc.model

data class User(
    val id: Int,
    var username: String,
    var email: String,
    var sdt: Int,
    val role: String,
    val created_at: String,
    val updated_at: String,
    val blacklist: Any?,
    val note: Any?,
    var diachi: String,
    var otp: String
)

data class LoginRequest(
    val username: String,
    val password: String
)

data class LoginResponse(
    val token: String,
    val user: User
)

data class ErrorResponse(
    val message: String,
    val error: String
)

data class UpdateNameRequest(
    val username: String,
    val id: Int
)

data class UpdateNameResponse(
    val message: String,
    val user: User
)

data class UpdateMailRequest(
    val email: String,
    val id: Int
)

data class UpdateMailResponse(
    val message: String,
    val user: User
)

data class UpdatePhoneRequest(
    val sdt: Int,
    val id: Int
)

data class UpdatePhoneResponse(
    val message: String,
    val user: User
)

data class UpdateAddressRequest(
    val diachi: String,
    val id: Int
)

data class UpdateAddressResponse(
    val message: String,
    val user: User
)

data class UpdatePassRequest(
    val currentpassword: String,
    val newpassword: String,
    val confirmpassword: String,
    val id: Int
)

data class UpdatePassResponse(
    val message: String,
    val user: User
)

data class UpdateOTPRequest(
    val otp: String
)

data class UpdateOTPResponse(
    val message: String,
    val user: User
)

data class SignupRequest(
    val username: String,
    val email: String,
    val sdt: Int,
    val password: String
)

data class SignupResponse(
    val token: String,
    val user: User
)
data class ErrorSignupResponse(
    val errors: String
)




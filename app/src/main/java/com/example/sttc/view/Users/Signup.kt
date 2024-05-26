package com.example.sttc.view.Users

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.sttc.R
import com.example.sttc.ui.theme.STTCTheme
import com.example.sttc.viewmodel.AccountViewModel
import retrofit2.HttpException

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpForm(
    navController: NavController,
    accountViewModel: AccountViewModel,
) {
    val username = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val phone = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    val signupResult by accountViewModel.signupResult.collectAsState(null)
    var showError by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    Box(modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 1.dp)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(100.dp)
                    .clickable { navController.navigate("home") },

                )

            Text(
                text = "Pet Shop là thiên đường thú cưng\n___",
                style = TextStyle(
                    fontFamily = FontFamily.Cursive, // Chọn font chữ
                    fontSize = 15.sp, // Kích thước chữ
                    color = Color.Black, // Màu chữ
                    fontStyle = FontStyle.Italic // Kiểu chữ
                ),
                textAlign = TextAlign.Center, // Căn giữa đoạn văn bản
                modifier = Modifier.paddingFromBaseline(0.dp, 20.dp)
            )

            Text(
                text = "Bạn chưa có tài khoản?",
                style = TextStyle(
                    fontFamily = FontFamily.Serif,
                    fontSize = 27.sp,
                    color = Color(0xFF003399),
                    fontWeight = FontWeight.Bold
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.paddingFromBaseline(5.dp, 23.dp)
            )

            TextField(
                colors = TextFieldDefaults.textFieldColors(
                    disabledTextColor = Color.Gray,
                    containerColor = Color(0xffccf2ff),
                    focusedIndicatorColor = Color.Green,
                    unfocusedIndicatorColor = Color(0xff3385ff),
                    disabledIndicatorColor = Color.Gray,
                    cursorColor = Color.Blue,
                    errorCursorColor = Color.Red
                ),
                value = username.value,
                onValueChange = { username.value = it },
                placeholder = { Text("Họ và tên") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                leadingIcon = {
                    Icon(imageVector = Icons.Filled.Person, contentDescription = "AcountIcon")
                }
            )

            TextField(
                modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 0.dp),
                colors = TextFieldDefaults.textFieldColors(
                    disabledTextColor = Color.Gray,
                    containerColor = Color(0xffe6f9ff),
                    focusedIndicatorColor = Color.Green,
                    unfocusedIndicatorColor = Color(0xff3385ff),
                    disabledIndicatorColor = Color.Gray,
                    cursorColor = Color.Blue,
                    errorCursorColor = Color.Red
                ),
                value = email.value,
                onValueChange = { email.value = it },
                placeholder = { Text("Email") },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.MailOutline, contentDescription = "EmailIcon")
                }
            )

            TextField(
                modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 0.dp),
                colors = TextFieldDefaults.textFieldColors(
                    disabledTextColor = Color.Gray,
                    containerColor = Color(0xffccf2ff),
                    focusedIndicatorColor = Color.Green,
                    unfocusedIndicatorColor = Color(0xff3385ff),
                    disabledIndicatorColor = Color.Gray,
                    cursorColor = Color.Blue,
                    errorCursorColor = Color.Red
                ),
                value = phone.value,
                onValueChange = { phone.value = it },
                placeholder = { Text("Số điện thoại") },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Call, contentDescription = "PhoneIcon")
                }
            )

            TextField(
                modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 0.dp),
                colors = TextFieldDefaults.textFieldColors(
                    disabledTextColor = Color.Gray,
                    containerColor = Color(0xffe6f9ff),
                    focusedIndicatorColor = Color.Green,
                    unfocusedIndicatorColor = Color(0xff3385ff),
                    disabledIndicatorColor = Color.Gray,
                    cursorColor = Color.Blue,
                    errorCursorColor = Color.Red
                ),
                value = password.value,
                onValueChange = { password.value = it },
                placeholder = { Text("Mật khẩu") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = PasswordVisualTransformation(),
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Lock, contentDescription = "PassIcon")
                }
            )

            if (showError) {
                Text(
                    text = errorMessage,
                    color = Color.Red,
                    modifier = Modifier.padding(16.dp),
                    style = TextStyle(
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    )
                )
            }

            Button(
                onClick = {
                    val name = username.value
                    val mail = email.value
                    val sdt = phone.value.toIntOrNull()
                    val pass = password.value

                    val emailPattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"

                    when {
                        name.isEmpty() || mail.isEmpty() || sdt == null || pass.isEmpty() -> {
                            showError = true
                            errorMessage = "Vui lòng nhập đầy đủ thông tin!"
                        }

                        !mail.matches(emailPattern.toRegex()) -> {
                            showError = true
                            errorMessage = "Email không hợp lệ!"
                        }

                        pass.length < 8 -> {
                            showError = true
                            errorMessage = "Mật khẩu phải dài hơn 7 kí tự!"
                        }

                        else -> {
                            accountViewModel.signup(name, mail, sdt, pass)
                            showError = false
                        }
                    }

                },
                modifier = Modifier

                    .padding(9.dp),
                shape = RoundedCornerShape(16.dp), // Định dạng góc bo tròn của nút
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFffad99), // Màu nền của nút
                    contentColor = Color.Black // Màu chữ của nút
                )
            ) {
                Text(
                    text = "Đăng kí",
                    style = TextStyle(
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold
                    )

                )
            }

            Text(
                text = "Bạn đã có tài khoản? Đi đến đăng nhập!",
                style = TextStyle(
                    color = Color.Blue,
                    fontSize = 15.sp,
                    fontStyle = FontStyle.Italic
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .paddingFromBaseline(25.dp, 9.dp)

            )

            Button(
                onClick = {
                    // Điều hướng tới màn hình đăng kí
                    navController.navigate("login")
                },
                shape = RoundedCornerShape(10.dp), // Định dạng góc bo tròn của nút
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2952a3), // Màu nền của nút
                    contentColor = Color.White // Màu chữ của nút
                )
            ) {
                Text(text = "Đăng nhập ngay!")
            }



            DisposableEffect(Unit) {
                onDispose {
                    username.value = ""
                    password.value = ""
                    email.value = ""
                    phone.value = ""

                }
            }
            signupResult?.let { result ->
                result.fold(
                    onSuccess = { token ->
                        println("Đăng kí thành công")
                        navController.navigate("login")
                    },
                    onFailure = { exception ->
                        showError = true
                        errorMessage = exception.message ?: "Đăng kí thất bại"
                        Log.e("SignupForm", "Đăng kí thất bại: ${exception.message}")
                    }
                )

            }
        }

    }
}


@Preview(showBackground = true)
@Composable
fun SignUpPreview() {
    val context = LocalContext.current
    STTCTheme {
        SignUpForm(navController = rememberNavController(), AccountViewModel(context))
    }
}
package com.example.sttc.view

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
import androidx.compose.material.icons.filled.Lock
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.example.sttc.navagation.Screens
import com.example.sttc.ui.theme.STTCTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginForm(navController: NavController) {
    val username = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    Box(modifier =  Modifier.padding(0.dp, 0.dp, 0.dp, 1.dp)){
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {

            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(250.dp)
                    .clickable {},

                )

            Text(
                text = "Pet Shop đảm bảo 100% về chất lượng và độ an toàn!\n___",
                style = TextStyle(
                    fontFamily = FontFamily.Cursive, // Chọn font chữ
                    fontSize = 15.sp, // Kích thước chữ
                    color = Color.Black, // Màu chữ
                    fontStyle = FontStyle.Italic // Kiểu chữ
                ),
                textAlign = TextAlign.Center, // Căn giữa đoạn văn bản
                modifier = Modifier.paddingFromBaseline(0.dp, 20.dp),

                )

            Text(text = "Bạn đã có tài khoản?",
                style = TextStyle(
                    fontFamily = FontFamily.Serif,
                    fontSize = 33.sp,
                    color = Color(0xFFE96B56),
                    fontWeight = FontWeight.Bold
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.paddingFromBaseline(5.dp, 23.dp))

            TextField(
                colors = TextFieldDefaults.textFieldColors(
                    disabledTextColor = Color.Gray,
                    containerColor = Color(0xffffd6cc),
                    focusedIndicatorColor = Color.Green,
                    unfocusedIndicatorColor = Color(0xffe62e00),
                    disabledIndicatorColor = Color.Gray,
                    cursorColor = Color.Blue,
                    errorCursorColor = Color.Red
                ),
                value = username.value,
                onValueChange = { username.value = it },
                placeholder = { Text("Tên đăng nhập") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                leadingIcon = {
                    Icon(imageVector = Icons.Filled.Person, contentDescription = "AcountIcon" )}
            )

            TextField(
                modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 0.dp),
                colors = TextFieldDefaults.textFieldColors(
                    disabledTextColor = Color.Gray,
                    containerColor = Color(0xffffebe6),
                    focusedIndicatorColor = Color.Green,
                    unfocusedIndicatorColor = Color(0xffe62e00),
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
                    Icon(imageVector = Icons.Default.Lock, contentDescription = "PassIcon" )}
            )

            Button(onClick = {
                navController.navigate("homemenu")
                val name = username.value
                val pass = password.value
//                if (userViewModel.login(name, pass)) {
//                    println("Đăng nhập thành công")
//                    navController.navigate("home")
//                } else {
//                    println("Đăng nhập thất bại")
//                }
            },
                modifier = Modifier

                    .padding(9.dp),
                shape = RoundedCornerShape(16.dp), // Định dạng góc bo tròn của nút
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFB8E1FF), // Màu nền của nút
                    contentColor = Color.Black // Màu chữ của nút
                )
            ) {
                Text(
                    text = "Đăng nhập",
                    style = TextStyle(
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold
                    )

                )
            }

            Text(text = "Chưa có tài khoản? Nhanh tay đăng kí ngay!",
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
                    navController.navigate("signup")
                    // Điều hướng tới màn hình đăng kí
//                    navController.navigate("signup")
                },
                shape = RoundedCornerShape(10.dp), // Định dạng góc bo tròn của nút
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2952a3), // Màu nền của nút
                    contentColor = Color.White // Màu chữ của nút
                )
            ) {
                Text(text = "Đăng kí ngay!")
            }



            DisposableEffect(Unit) {
                onDispose {
                    username.value = ""
                    password.value = ""
                }
            }
        }

    }
}



@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    STTCTheme {
        val navController = rememberNavController()
        LoginForm(navController)
    }
}
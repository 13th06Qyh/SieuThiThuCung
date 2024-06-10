package com.example.sttc.view.Bill

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sttc.ui.theme.STTCTheme

@Composable
fun ComeBackScreen(
    openHomeMenu: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFffcccc)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Bạn đã mua hàng thành công! Cảm ơn bạn đã sử dụng dịch vụ của PetShop! \n" +
                    "~~ Love you <0.0> ~~",
            style = TextStyle(
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            ),
            modifier = Modifier
                .padding(40.dp, 0.dp)
                .fillMaxWidth()
        )

        Button(
            onClick = {
                openHomeMenu()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFcc0000), // Màu nền của nút
                contentColor = Color.Black, // Màu chữ của nút
            ),

            border = BorderStroke(1.dp, Color(0xFF018B0F)),
        ) {
            Text(
                "Quay trở về trang chủ", color = Color.White,
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                ),
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun ComeBack() {
    STTCTheme {
        ComeBackScreen( openHomeMenu = {})
    }
}
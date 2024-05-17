package com.example.sttc.view
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.sttc.R
import com.example.sttc.ui.theme.STTCTheme
import com.example.sttc.view.SuggestToday


@Composable
fun InforBillHistoryShipScreen(navController: NavController) {
    val scrollState = rememberScrollState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFf2f2f2))
            .verticalScroll(scrollState)

    ) {
        Column (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
//            TopIconInforBill()
            TitleInforBill()
            BillSuccess()
            ContentInforBill()
            SuccessPay()
            PayBill()
            LocationReceive()
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                HorizontalDivider(
                    modifier = Modifier
                        .weight(1f)
                        .padding(0.dp, 10.dp),  // Take up half the space
                    thickness = 1.2.dp,
                    color = Color.Gray
                )
                Text(
                    text = "Có thể bạn quan tâm",
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontStyle = FontStyle.Italic,
                        color = Color.Black,
                    ),
                    modifier = Modifier.padding(10.dp, 0.dp, 10.dp, 10.dp)
                )
                HorizontalDivider(
                    modifier = Modifier
                        .weight(1f)
                        .padding(0.dp, 10.dp),  // Take up half the space
                    thickness = 1.2.dp,
                    color = Color.Gray
                )
            }
            SuggestToday()
        }
    }

}

@Composable
fun BillSuccess(){
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Color(0xFF0a2929)
            )
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Color(0xFFccffff)
                )
                .padding(5.dp, 20.dp, 0.dp, 20.dp)
            ,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Column (
                modifier = Modifier
                    .width(270.dp)
            ){
                Text(
                    text = "Đơn hàng đã hoàn thành",
                    style = TextStyle(
                        fontSize = 17.sp,
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF239090),
                    ),
                    modifier = Modifier
                        .padding(10.dp, 10.dp, 0.dp, 0.dp)
                )

                Text(
                    text = "Cảm ơn bạn đã tin tưởng mua sản phẩm của Pet Shop!",
                    style = TextStyle(
                        fontSize = 17.sp,
                        fontStyle = FontStyle.Italic,
                        color = Color(0xFF239090),
                    ),
                    modifier = Modifier
                        .padding(10.dp, 10.dp, 0.dp, 10.dp)
                )
            }

            Image(
                painter = painterResource(id = R.drawable.receiversuccess),
                contentDescription = "Receive",
                modifier = Modifier
                    .size(90.dp)
                    .padding(0.dp, 0.dp, 15.dp, 0.dp)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp, 25.dp, 10.dp, 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Mã đơn hàng",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                )
            )
            Text(
                text = "123456",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                ),
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Thời gian đặt hàng",
                style = TextStyle(
                    fontSize = 18.sp,
                    color = Color.White,
                ),
                modifier = Modifier
                    .padding(10.dp, 10.dp)
            )
            Text(
                text = "11-4-2024 01:23",
                style = TextStyle(
                    fontSize = 18.sp,
                    color = Color.White,
                ),
                modifier = Modifier
                    .padding(10.dp, 10.dp)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Trạng thái",
                style = TextStyle(
                    fontSize = 18.sp,
                    color = Color.White,
                ),
                modifier = Modifier
                    .padding(10.dp, 10.dp)
            )
            Text(
                text = "Đã giao hàng",
                style = TextStyle(
                    fontSize = 18.sp,
                    color = Color.White,
                ),
                modifier = Modifier
                    .padding(10.dp)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Thời gian nhận hàng",
                style = TextStyle(
                    fontSize = 18.sp,
                    color = Color.White,
                ),
                modifier = Modifier
                    .padding(10.dp, 10.dp)
            )
            Text(
                text = "21-04-2024 11:27",
                style = TextStyle(
                    fontSize = 18.sp,
                    color = Color.White,
                ),
                modifier = Modifier
                    .padding(10.dp)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Thời gian thanh toán",
                style = TextStyle(
                    fontSize = 18.sp,
                    color = Color.White,
                ),
                modifier = Modifier
                    .padding(10.dp, 10.dp)
            )
            Text(
                text = "21-04-2024 11:27",
                style = TextStyle(
                    fontSize = 18.sp,
                    color = Color.White,
                ),
                modifier = Modifier
                    .padding(10.dp)
            )
        }
    }
}

@Composable
fun SuccessPay(){
    Column {
        HorizontalDivider(thickness = 1.2.dp, color = Color(0xFFcccccc))
        Row (
            modifier = Modifier
                .fillMaxWidth()
//                        .border(1.dp, color = Color(0xFFcc2900))
                .background(
                    Color.White
                ),
            horizontalArrangement = Arrangement.End

        ) {
            Button(
                onClick = { /* Do something! */ },
                shape = RoundedCornerShape(2.dp), // Định dạng góc bo tròn của nút
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFcc2900), // Màu nền của nút
                    contentColor = Color.White // Màu chữ của nút
                ),
                modifier = Modifier.fillMaxWidth()
                    .padding(7.dp, 5.dp)
            ) {
                Text(text = "Mua lại",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    ),
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InforBillHistoryScreenPreview() {
    STTCTheme {
        InforBillHistoryShipScreen(rememberNavController())
//        MyApp()
//        SignUpForm(navController = rememberNavController(), authController = AuthController())
    }
}
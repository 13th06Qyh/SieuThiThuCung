package com.example.sttc.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.sttc.R
import com.example.sttc.ui.theme.STTCTheme
import com.example.sttc.view.BillProduct
import com.example.sttc.view.ItemAccount
import com.example.sttc.view.Product
import com.example.sttc.view.SuggestToday
import com.example.sttc.view.formatNumber

@Composable
fun InforBillShipScreen(
    back : () -> Unit
) {
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
            TitleInforBill(back)
            Bill()
            ContentInforBill()
            PayBill()
            LocationReceive()
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                HorizontalDivider(
                    modifier = Modifier.weight(1f)
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
                    modifier = Modifier.weight(1f)
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
fun TitleInforBill(back: () -> Unit) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
//            .height(50.dp)
            .background(
                Brush.radialGradient(
                    colors = listOf(
                        Color(0xFFFFFFFF),
                        Color(0xFFFFE4E4),
                        Color(0xFFF6F2F2),
                    ),
                    radius = 600f
                )
            )
            .padding(0.dp, 10.dp)
            .border(1.dp, color = Color(0xFFff6666))
    ){
        Icon(
            Icons.Default.ArrowBack, contentDescription = "Back",
            modifier = Modifier
                .size(50.dp)
                .padding(10.dp, 0.dp)
                .clickable { back },
            tint = Color(0xFFcc2900)
        )
        Text(
            text = "Thông tin đơn hàng",
            style = TextStyle(
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = Color.Black
            ),
            modifier = Modifier
                .padding(10.dp, 10.dp)
        )
    }
}

@Composable
fun Bill(){
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 0.dp, 0.dp, 0.dp)
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
                .padding(5.dp, 20.dp)
            ,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Column {
                Text(
                    text = "Đơn hàng đang được giao",
                    style = TextStyle(
                        fontSize = 17.sp,
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF239090),
                    ),
                    modifier = Modifier
                        .padding(10.dp, 10.dp, 10.dp, 0.dp)
                        .width(280.dp)
                )

                Text(
                    text = "Ngày nhận hàng dự kiến từ 20-04-2024 đến 21-04-2024. Xin hãy vui lòng chờ đợi và chú ý điện thoại nhé!",
                    style = TextStyle(
                        fontSize = 17.sp,
                        fontStyle = FontStyle.Italic,
                        color = Color(0xFF239090),
                    ),
                    modifier = Modifier
                        .padding(10.dp, 10.dp)
                        .width(280.dp)
                )
            }

            Image(
                painter = painterResource(id = R.drawable.received),
                contentDescription = "Receive",
                modifier = Modifier
                    .size(80.dp)
                    .padding(10.dp, 10.dp)
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
                text = "Đang giao hàng",
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
fun ContentInforBill() {
    val items = listOf(
        BillProduct(
            Product(
                R.drawable.rs1,
                "Tag A",
                "Product A",
                10000
            ),
            com.example.sttc.view.Bill(1)
        ),
    )

    Column(
        modifier = Modifier
            .padding(0.dp, 10.dp, 0.dp, 0.dp)
            .border(1.dp, color = Color(0xFFFFFFFF))
            .fillMaxWidth()
            .background(
                Color.White
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = "Công ty TNHH QuacQUac",
                style = TextStyle(
                    fontSize = 15.sp,
                    fontStyle = FontStyle.Italic,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF000000),
                ),
                modifier = Modifier
                    .padding(10.dp, 5.dp)
            )
        }
        HorizontalDivider(thickness = 1.2.dp, color = Color(0xFFcccccc))
        Row(
            modifier = Modifier
                .padding(5.dp, 0.dp)
                .clickable { /* Do something! */ }
            ,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            Image(
                painter = painterResource(id = items[0].product.imageResId),
                contentDescription = "Image",
                modifier = Modifier
                    .size(100.dp)
                    .padding(5.dp, 5.dp)
                    .border(0.1.dp, color = Color.Black)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(5.dp, 10.dp),
            ) {
                Text(text = items[0].product.productName,
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                )
                Spacer(modifier = Modifier.height(4.dp)) // Thêm khoảng cách ở đây
                Text(text = items[0].product.tagName,
                    style = TextStyle(
                        fontSize = 13.sp,
                        fontStyle = FontStyle.Italic,
                        color = Color.Black,
                    )
                )
                Text("x" + items[0].bill.soluongmua.toString(),
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = Color.Black,
                        textAlign = TextAlign.End
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    formatNumber(items[0].product.productPrice) + "đ",
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFcc2900),
                        textAlign = TextAlign.End
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        HorizontalDivider(thickness = 1.2.dp, color = Color(0xFFcccccc))

        Row (
            modifier = Modifier
                .fillMaxWidth()
//                        .border(1.dp, color = Color(0xFF006600))
            ,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(
                text = "Thành tiền",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.End,
                    color = Color.Black,
                ),
                modifier = Modifier
                    .padding(5.dp, 10.dp)
            )
            Text(
                text = formatNumber(items[0].product.productPrice * items[0].bill.soluongmua) + "đ",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.End,
                    color = Color.Black,
                ),
                modifier = Modifier
                    .padding(5.dp, 10.dp)
            )

        }

        HorizontalDivider(thickness = 1.2.dp, color = Color(0xFFcccccc))

        Row (
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ){
            Icon(
                painter = painterResource(id = R.drawable.ship),
                contentDescription = "Money",
                tint = Color(0xFF006600),
                modifier = Modifier
                    .size(40.dp)
                    .padding(10.dp, 5.dp)
            )

            Text(text = "Thanh toán " + formatNumber(items[0].product.productPrice * items[0].bill.soluongmua) + "đ khi nhận hàng.",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontStyle = FontStyle.Italic,
                    textAlign = TextAlign.Start,
                    color = Color(0xFF006600),
                ),
                modifier = Modifier
                    .padding(0.dp, 11.dp)
            )
        }
    }
}


@Composable
fun PayBill(){
    Column (
        modifier = Modifier
            .fillMaxWidth()
//            .border(1.dp, color = Color(0xFFFFFFFF))
            .padding(0.dp, 10.dp, 0.dp, 0.dp)
            .background(
                Color.White
            )
    ){
        Row (
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ){
            Icon(
                painter = painterResource(id = R.drawable.money),
                contentDescription = "Money",
                tint = Color(0xFFcc2900),
                modifier = Modifier
                    .size(28.dp)
                    .padding(10.dp, 9.dp, 0.dp, 0.dp)
            )
            Text(
                text = "Phương thức thanh toán",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                ),
                modifier = Modifier
                    .padding(10.dp, 10.dp)
                )
        }

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(40.dp, 0.dp),
            horizontalArrangement = Arrangement.Start
        ){
            Text(text = "Thanh toán khi nhận hàng",
                style = TextStyle(
                    fontSize = 17.sp,
                    fontStyle = FontStyle.Italic,
                    color = Color.Black,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp, 10.dp)
            )
        }
    }
}

@Composable
fun LocationReceive(){
    val itemAccount = listOf(
        ItemAccount(
            "QuyhPham",
            "quynhpn.22it@vku.udn.vn",
            1234567890,
            "123456",
            "154 Trần Đại Nghĩa, Ngũ Hành Sơn, Đà Nẵng"
        )
    )
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 10.dp, 0.dp, 10.dp)
            .border(1.dp, color = Color(0xFFFFFFFF))
            .background(
                Color.White
            )

    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ){
            Icon(
                Icons.Filled.LocationOn,
                contentDescription = "Location",
                tint = Color(0xFFcc2900),
                modifier = Modifier
                    .size(35.dp)
                    .padding(0.dp, 9.dp, 0.dp, 0.dp)
            )
            Text(
                text = "Địa chỉ nhận hàng",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                ),
                modifier = Modifier
                    .padding(0.dp, 10.dp)
            )
        }

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(40.dp, 0.dp),
            horizontalArrangement = Arrangement.Start
        ){
            Column {
                    Text(
                        text = itemAccount[0].name,
                        style = TextStyle(
                            fontSize = 17.sp,
                            color = Color.Black,
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp, 10.dp)
                    )
                    Text(
                        text = "(+84)" + itemAccount[0].phone.toString(),
                        style = TextStyle(
                            fontSize = 17.sp,
                            color = Color.Black,
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp, 0.dp)
                    )
                    Text(
                        text = itemAccount[0].address,
                        style = TextStyle(
                            fontSize = 17.sp,
                            color = Color.Black,
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp, 10.dp)
                    )

            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun InforBillShipScreenPreview() {
    STTCTheme {
        InforBillShipScreen(back = {})
    }
}
package com.example.sttc.view

import android.content.Context
import android.util.Log
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import com.example.sttc.model.billDetail
import com.example.sttc.ui.theme.STTCTheme
import com.example.sttc.view.System.BillProduct
import com.example.sttc.view.System.ItemAccount
import com.example.sttc.view.System.Product
import com.example.sttc.view.System.SuggestToday
import com.example.sttc.view.System.SuggestTodayopen
import com.example.sttc.view.System.formatCreatedAt
import com.example.sttc.view.System.formatEstimatedDeliveryDate
import com.example.sttc.view.System.formatEstimatedDeliveryDateO
import com.example.sttc.view.System.formatNumber
import com.example.sttc.view.System.formatUpdatedAt
import com.example.sttc.viewmodel.AccountViewModel
import com.example.sttc.viewmodel.BillViewModel
import com.example.sttc.viewmodel.CartViewModel
import com.example.sttc.viewmodel.ProductViewModel

@Composable
fun InforBillShipScreen(
    back: () -> Unit,
    openDetailProducts: (id: Int) -> Unit,
    productViewModel: ProductViewModel,
    context: Context,
    billViewModel: BillViewModel,
    billId: Int,
    openCart: () -> Unit,
    cartViewModel: CartViewModel,
    accountViewModel: AccountViewModel,
    id: Int,

    ) {
    var billDetail by remember { mutableStateOf (emptyList<billDetail>()) }
    LaunchedEffect(Unit) {
        billViewModel.fetchBillDetail(billId)
        billViewModel.billDetail.collect {
            billDetail = it
        }
    }
    Log.d("BillId", "$billId")
    Log.d("listBillDetail", "$billDetail")
    val totalAmount = billDetail.sumBy { it.buyprice * it.soluong }
    val scrollState = rememberScrollState()
    val selectedOption = remember { mutableStateOf("") }
    val selectedAnimal = 0
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFf2f2f2))
            .verticalScroll(scrollState)

    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
//            TopIconInforBill()
            TitleInforBill(back)
            if (billDetail.isNotEmpty()) {
                Bill(item = billDetail[0])
            }
            billDetail.forEach { item ->
                Column(
                    modifier = Modifier
                        .padding(0.dp, 10.dp, 0.dp, 0.dp)
                        .border(1.dp, color = Color(0xFFFFFFFF))
                        .fillMaxWidth()
                        .background(Color.White),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Text(
                            text = item.proname,
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
                            .clickable { /* Do something! */ },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        val imageUrl = item.image
                        val fileName =
                            imageUrl.substringAfterLast("/").substringBeforeLast(".")
                        val resourceId = context.resources.getIdentifier(
                            fileName,
                            "drawable",
                            context.packageName
                        )
                        val a = context.resources.getResourceName(resourceId)
                        val b = a.substringAfter('/')
                        if (b == fileName) {
                            Image(
                                painter = painterResource(id = resourceId),
                                contentDescription = "Image",
                                modifier = Modifier
                                    .size(120.dp)
                                    .padding(5.dp, 5.dp)
                                    .border(0.1.dp, color = Color.Black)
                            )
                        } else {
                            Image(
                                painter = painterResource(id = R.drawable.rs3),
                                contentDescription = "Image",
                                modifier = Modifier
                                    .size(100.dp)
                                    .padding(5.dp, 5.dp)
                                    .border(0.1.dp, color = Color.Black)
                            )
                        }

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp)
                                .padding(5.dp, 10.dp),
                        ) {
                            Text(
                                text = item.tensp,
                                style = TextStyle(
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black
                                )
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = item.tagname,
                                style = TextStyle(
                                    fontSize = 13.sp,
                                    fontStyle = FontStyle.Italic,
                                    color = Color.Black,
                                )
                            )
                            Text(
                                "x${item.soluong}",
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    color = Color.Black,
                                    textAlign = TextAlign.End
                                ),
                                modifier = Modifier.fillMaxWidth()
                            )
                            Text(
                                "${formatNumber(item.buyprice)}đ",
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

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
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
                            text = "${formatNumber(item.buyprice * item.soluong)}đ",
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

                    SuccessPay(
                        openCart,
                        cartViewModel,
                        productViewModel,
                        accountViewModel,
                        id
                    )

                    HorizontalDivider(thickness = 1.2.dp, color = Color(0xFFcccccc))
                }
            }
            Money(totalAmount)
            if (billDetail.isNotEmpty()) {
                PayBill(item = billDetail[0])
                LocationReceive(item = billDetail[0])
            }
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
            SuggestToday(
                openDetailProducts,
                productViewModel,
                context,
                selectedOption.value,
                selectedAnimal
            )
        }
    }

}

@Composable
fun TitleInforBill(back: () -> Unit) {
    Row(
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
    ) {
        Icon(
            Icons.Default.ArrowBack, contentDescription = "Back",
            modifier = Modifier
                .size(50.dp)
                .padding(10.dp, 0.dp)
                .clickable { back() },
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
fun Bill(
    item: billDetail
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 0.dp, 0.dp, 0.dp)
            .background(
                Color(0xFF0a2929)
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Color(0xFFccffff)
                )
                .padding(5.dp, 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
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

                val dukien = formatUpdatedAt(item.created_at)
                Text(
                    text = "Ngày nhận hàng dự kiến từ " + formatEstimatedDeliveryDate(dukien) + " đến " + formatEstimatedDeliveryDateO(dukien) + ". Xin hãy vui lòng chờ đợi và chú ý điện thoại nhé!",
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
                text = item.maBill.toString(),
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
                text = formatCreatedAt(item.created_at),
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
fun ContentInforBill(
    openCart: () -> Unit,
    cartViewModel: CartViewModel,
    productViewModel: ProductViewModel,
    accountViewModel: AccountViewModel,
    id: Int,
    billViewModel: BillViewModel,
    billId: Int,
    context: Context
) {
    var billDetail by remember { mutableStateOf<List<billDetail>>(emptyList()) }
    LaunchedEffect(Unit) {
        billViewModel.fetchBillDetail(billId)
        billViewModel.billDetail.collect {
            billDetail = it
        }
    }
    Log.e("BillId", billId.toString())
    Log.e("listBillDetail", billDetail.toString())

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
        for (item in billDetail) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = item.proname,
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
            Column() {

                Row(
                    modifier = Modifier
                        .padding(5.dp, 0.dp)
                        .clickable { /* Do something! */ },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    val imageUrl = item.image
                    val fileName =
                        imageUrl.substringAfterLast("/").substringBeforeLast(".")
                    val resourceId = context.resources.getIdentifier(
                        fileName,
                        "drawable",
                        context.packageName
                    )
                    val a = context.resources.getResourceName(resourceId)
                    val b = a.substringAfter('/')
                    if (b == fileName) {
                        Image(
                            painter = painterResource(id = resourceId),
                            contentDescription = "Image",
                            modifier = Modifier
                                .size(120.dp)
                                .padding(5.dp, 5.dp)
                                .border(0.1.dp, color = Color.Black)
                        )
                    } else {
                        Image(
                            painter = painterResource(id = R.drawable.rs3),
                            contentDescription = "Image",
                            modifier = Modifier
                                .size(100.dp)
                                .padding(5.dp, 5.dp)
                                .border(0.1.dp, color = Color.Black)
                        )
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                            .padding(5.dp, 10.dp),
                    ) {
                        Text(
                            text = item.tensp,
                            style = TextStyle(
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        )
                        Spacer(modifier = Modifier.height(4.dp)) // Thêm khoảng cách ở đây
                        Text(
                            text = item.tagname,
                            style = TextStyle(
                                fontSize = 13.sp,
                                fontStyle = FontStyle.Italic,
                                color = Color.Black,
                            )
                        )
                        Text(
                            "x" + item.soluong.toString(),
                            style = TextStyle(
                                fontSize = 14.sp,
                                color = Color.Black,
                                textAlign = TextAlign.End
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )
                        Text(
                            formatNumber(item.buyprice) + "đ",
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

                SuccessPay(
                    openCart,
                    cartViewModel,
                    productViewModel,
                    accountViewModel,
                    id
                )
            }

            HorizontalDivider(thickness = 1.2.dp, color = Color(0xFFcccccc))


            Row(
                modifier = Modifier
                    .fillMaxWidth()
//                        .border(1.dp, color = Color(0xFF006600))
                ,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Thành tiền",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.End,
                        color = Color.Black,
                    ),
                    modifier = Modifier
                        .padding(10.dp, 10.dp)
                )
                Text(
                    text = formatNumber(item.buyprice * item.soluong) + "đ",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.End,
                        color = Color.Black,
                    ),
                    modifier = Modifier
                        .padding(10.dp, 10.dp)
                )

            }
        }
    }
}

@Composable
fun Money(
    totalAmount: Int
) {
    Row(
        modifier = Modifier
            .background(Color(0xFFe6ffe6))
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Start
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ship),
            contentDescription = "Money",
            tint = Color(0xFF006600),
            modifier = Modifier
                .size(40.dp)
                .padding(10.dp, 5.dp)
        )

        Text(
            text = "Thanh toán ${formatNumber(totalAmount)}đ khi nhận hàng.",
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
@Composable
fun PayBill(
    item: billDetail
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
//            .border(1.dp, color = Color(0xFFFFFFFF))
            .padding(0.dp, 10.dp, 0.dp, 0.dp)
            .background(
                Color.White
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
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

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(40.dp, 0.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = item.pay,
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
fun LocationReceive(
    item: billDetail
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 10.dp, 0.dp, 10.dp)
            .border(1.dp, color = Color(0xFFFFFFFF))
            .background(
                Color.White
            )

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
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

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(40.dp, 0.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            Column {
                Text(
                    text = item.username,
                    style = TextStyle(
                        fontSize = 17.sp,
                        color = Color.Black,
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp, 10.dp)
                )
                Text(
                    text = "(+84)" + formatNumber(item.sdt),
                    style = TextStyle(
                        fontSize = 17.sp,
                        color = Color.Black,
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp, 0.dp)
                )
                Text(
                    text = item.diachi,
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
        InforBillShipScreen(
            back = {},
            openDetailProducts = {},
            ProductViewModel(),
            LocalContext.current,
            BillViewModel(LocalContext.current),
            0,
            openCart = {},
            CartViewModel(LocalContext.current),
            AccountViewModel(LocalContext.current),
            1
        )
//        MyApp()
//        SignUpForm(navController = rememberNavController(), authController = AuthController())
    }
}
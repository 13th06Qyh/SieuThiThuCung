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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.sttc.R
import com.example.sttc.model.ImageSP
import com.example.sttc.ui.theme.STTCTheme
import com.example.sttc.view.System.BillProduct
import com.example.sttc.view.System.Product
import com.example.sttc.view.System.formatNumber
import com.example.sttc.viewmodel.AccountViewModel
import com.example.sttc.viewmodel.BillViewModel
import com.example.sttc.viewmodel.ProductViewModel
// BillHistoryScreen : những đơn hàng đã mua- trạng thái đã giao  (lịc sử mua hàng )
@Composable
fun BillHistoryScreen(
    openDetailBillHistory: (billId : Int ) -> Unit,
    productViewModel: ProductViewModel,
    accountViewModel: AccountViewModel,
    billViewModel: BillViewModel,
    context: Context
) {
    val scrollState = rememberScrollState()
    Box(
        modifier = Modifier
            .fillMaxSize()
//            .verticalScroll(scrollState)

    ) {
        Column (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            TopIconBillHistory()
            TitleBillHistory()
            ContentBillHistory(
                openDetailBillHistory ,
                 billViewModel,
                accountViewModel ,
                productViewModel ,
                context
            )
        }
    }
}

@Composable
fun TopIconBillHistory() {
    val context = LocalContext.current

    Image(
        painter = painterResource(id = R.drawable.buyhistory),
        contentDescription = "TopIconHistorFAFFFAy",
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
            .background(
                Brush.radialGradient(
                    colors = listOf(
                        Color(0xFF9EFFA4),
                        Color(0xFFD5F1D7),
                        Color(0xFFFFFFFF),
                        Color(0xFFDCFFDA),
                        Color(0xFFFAFFFA),
                    ),
                    radius = 360f
                )
            )
            .border(1.dp, color = Color(0xFF006600))
    )
}

@Composable
fun TitleBillHistory() {
    Text(
        text = "Lịch Sử Mua Hàng",
        style = TextStyle(
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = Color(0xFFcc2900)
        ),
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
    )
}

@Composable
fun ContentBillHistory(
    openDetailBillHistory: (billId : Int) -> Unit ,
    billViewModel: BillViewModel,
    accountViewModel: AccountViewModel,
    productViewModel: ProductViewModel,
    context: Context,
) {
    val items by billViewModel.billShow.collectAsState(initial = emptyList())
    val imagesMap by productViewModel.images.collectAsState(initial = emptyMap())
    val productImages = remember { mutableStateOf<List<ImageSP>>(emptyList()) }


    val userId = accountViewModel.getUserIdFromSharedPreferences()
    Log.d("BillCancelScreen", "userId: $userId")
    billViewModel.fetchBillHistory(userId)

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
//            .border(1.dp, color = Color(0xFF006600))
            .background(Color(0xFFccffcc))
            .padding(5.dp, 0.dp)
    ) {
        items(items) { item ->
            Column(
                modifier = Modifier
                    .padding(0.dp, 0.dp, 0.dp, 10.dp)
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
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(text = "Công ty TNHH QuacQUac",
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

                    Text(text = "Mã đơn hàng: ",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontStyle = FontStyle.Italic,
                            textAlign = TextAlign.Start,
                            color = Color(0xFF006600),
                        ),
                        modifier = Modifier
                            .padding(10.dp, 5.dp)
                    )
                }

                HorizontalDivider(thickness = 1.2.dp, color = Color(0xFFcccccc))
                Row(
                    modifier = Modifier.clickable { openDetailBillHistory( item.maBill) }
                        ,
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ){
                    LaunchedEffect(key1 = item.maSP) {
                        productViewModel.fetchImages(item.maSP)
                    }

                    productImages.value = imagesMap[item.maSP].orEmpty()
                    if (productImages.value.isNotEmpty()) {
                        val image = productImages.value.first()
                        val imageUrl = image.image
                        val fileName = imageUrl.substringBeforeLast(".")
                        val resourceId = context.resources.getIdentifier(
                            fileName,
                            "drawable",
                            context.packageName
                        )
                        if (resourceId != 0) {
                            Image(
                                painter = painterResource(id = resourceId),
                                contentDescription = "Image",
                                modifier = Modifier
                                    .size(100.dp)
                                    .padding(5.dp, 5.dp)
                                    .border(0.1.dp, color = Color.Black)
                            )
                        } else {
                            Text(text = "Image not found")
                        }
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                            .padding(5.dp, 10.dp),
                    ) {
                        Text(text = item.tensp,
                            style = TextStyle(
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        )
                        Spacer(modifier = Modifier.height(4.dp)) // Thêm khoảng cách ở đây
                        Text(text = item.tagname,
                            style = TextStyle(
                                fontSize = 13.sp,
                                fontStyle = FontStyle.Italic,
                                color = Color.Black,
                            )
                        )
                        Text("x${item.soluong}",
                            style = TextStyle(
                                fontSize = 14.sp,
                                color = Color.Black,
                                textAlign = TextAlign.End
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )
                        Text(
                            "Giá: ${formatNumber(item.buyprice)}đ",
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black,
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
                    horizontalArrangement = Arrangement.End
                ){
                    Icon(
                        painter = painterResource(id = R.drawable.money),
                        contentDescription = "Money",
                        tint = Color(0xFFcc2900),
                        modifier = Modifier
                            .size(28.dp)
                            .padding(0.dp, 9.dp, 0.dp, 0.dp)
                    )
                    Text(
                        text = "Đã thanh toán: " + formatNumber(item.buyprice * item.soluong) + "đ",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.End,
                            color = Color(0xFFcc2900),
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

                    Text(text = "Giao hàng thành công",
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
                        shape = RoundedCornerShape(10.dp), // Định dạng góc bo tròn của nút
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFcc2900), // Màu nền của nút
                            contentColor = Color.White // Màu chữ của nút
                        ),
                        modifier = Modifier
                            .padding(5.dp, 5.dp)
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
    }
}




@Preview(showBackground = true)
@Composable
fun BillHistoryScreenPreview() {
    STTCTheme {
        val context = LocalContext.current
        BillHistoryScreen(
            openDetailBillHistory = {},
            ProductViewModel(),
            AccountViewModel(context),
            BillViewModel(),
            context
        )
    }
}
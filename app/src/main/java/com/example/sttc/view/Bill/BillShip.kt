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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.request.ImageRequest
import com.example.sttc.R
import com.example.sttc.model.ImageSP
import com.example.sttc.model.billShow
import com.example.sttc.ui.theme.STTCTheme
import com.example.sttc.view.System.BillProduct
import com.example.sttc.view.System.Product
import com.example.sttc.view.System.formatNumber
import com.example.sttc.viewmodel.AccountViewModel
import com.example.sttc.viewmodel.BillViewModel
import com.example.sttc.viewmodel.ProductViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

//BillShipScreen : nơi hiển thị đơn trong trạng thái đang giao
@Composable
fun BillShipScreen(
    openDetailBillShip: (Int) -> Unit,
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
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopIconBillShip()
            TitleBillShip()
            ContentBillShip(
                openDetailBillShip,
                billViewModel,
                accountViewModel,
                productViewModel,
                context
            )
        }
    }

}

@Composable
fun TopIconBillShip() {
    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .components {
            add(GifDecoder.Factory())
        }
        .build()

    Image(
        painter = rememberAsyncImagePainter(
            ImageRequest.Builder(context).data(data = R.drawable.shipper).apply(block = {
            }).build(), imageLoader = imageLoader
        ),
        contentDescription = "TopIconShipper",
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
            .border(1.dp, color = Color(0xFFcc00cc))
            .background(
                Brush.radialGradient(
                    colors = listOf(
                        Color(0xFFFF6767),
                        Color(0xFFFFF4F4),
                        Color(0xFFFFD2D2),
                        Color(0xFFFFE4E4),
                        Color(0xFFF6F2F2),
                    ),
                    radius = 400f
                )
            )
    )

//    Image(
//        painter = painterResource(id = R.drawable.shipper),
//        contentDescription = "TopIconShipper",
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(130.dp)
//            .background(
//                Brush.radialGradient(
//                    colors = listOf(
//                        Color(0xFFFF6767),
//                        Color(0xFFFFF4F4),
//                        Color(0xFFFFD2D2),
//                        Color(0xFFFFE4E4),
//                        Color(0xFFF6F2F2),
//                    ),
//                    radius = 400f
//                )
//            )
//            .border(1.dp, color = Color(0xFFcc00cc))
//    )
}

@Composable
fun TitleBillShip() {
    Text(
        text = "Đơn Hàng Đang Giao",
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
fun ContentBillShip(
    openDetailBillShip: (Int) -> Unit,
    billViewModel: BillViewModel,
    accountViewModel: AccountViewModel,
    productViewModel: ProductViewModel,
    context: Context,
) {


    // chỗ ni đây là ko cho chạy nhiều lần
    var items by remember { mutableStateOf(emptyList<billShow>()) }

    LaunchedEffect(Unit) {
        val userId = accountViewModel.userId
        billViewModel.fetchBillShip(userId)
        Log.d("BillCancelScreen", "userId: $userId")
        billViewModel.billShow.collect { value ->
            items = value
        }
    }
    val imagesMap by productViewModel.images.collectAsState(initial = emptyMap())
    val productImages = remember { mutableStateOf<List<ImageSP>>(emptyList()) }


    Log.e("huhuhuhh", "items: $items")
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFccffff))
            .padding(5.dp, 0.dp)
    ) {
        items(items) { billShow ->
            Column(
                modifier = Modifier
                    .padding(0.dp, 0.dp, 0.dp, 10.dp)
                    .border(1.dp, color = Color(0xFFFFFFFF))
                    .fillMaxWidth()
                    .background(Color.White),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Công ty TNHH QuacQUac",
                        style = TextStyle(
                            fontSize = 15.sp,
                            fontStyle = FontStyle.Italic,
                            textAlign = TextAlign.Start,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF000000)
                        ),
                        modifier = Modifier.padding(10.dp, 5.dp)
                    )

                    Text(
                        text = "Mã đơn hàng:",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontStyle = FontStyle.Italic,
                            textAlign = TextAlign.Start,
                            color = Color(0xFFcc2900)
                        ),
                        modifier = Modifier.padding(10.dp, 5.dp)
                    )
                }

                HorizontalDivider(thickness = 1.dp, color = Color.Gray)

                Row(
                    modifier = Modifier.clickable {
                        openDetailBillShip(billShow.maBill)
                    },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    LaunchedEffect(Unit) {
                        productViewModel.fetchImages(billShow.maSP)
                    }

                    productImages.value = imagesMap[billShow.maSP].orEmpty()
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
                                    .size(120.dp)
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
                            .height(120.dp)
                            .padding(5.dp, 16.dp)
                    ) {
                        Text(
                            text = billShow.tensp,
                            style = TextStyle(
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = billShow.tagname,
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontStyle = FontStyle.Italic,
                                color = Color.Black
                            )
                        )
                        Text(
                            "x${billShow.soluong}",
                            style = TextStyle(
                                fontSize = 18.sp,
                                color = Color.Black,
                                textAlign = TextAlign.End
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )
                        Text(
                            "Giá: ${formatNumber(billShow.buyprice)}đ",
                            style = TextStyle(
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black,
                                textAlign = TextAlign.End
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }

                HorizontalDivider(thickness = 1.dp, color = Color.Gray)

                Row(
                    modifier = Modifier.fillMaxWidth(),
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
                        text = "Dự kiến giao hàng trước ngày",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontStyle = FontStyle.Italic,
                            textAlign = TextAlign.Start,
                            color = Color(0xFF006600),
                        ),
                        modifier = Modifier.padding(0.dp, 11.dp)
                    )
                }

                HorizontalDivider(thickness = 1.dp, color = Color.Gray)

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.money),
                        contentDescription = "Money",
                        tint = Color(0xFFcc2900),
                        modifier = Modifier
                            .size(45.dp)
                            .padding(0.dp, 9.dp, 2.dp, 10.dp)
                    )
                    Text(
                        text = "Thành tiền: ${formatNumber(billShow.buyprice * billShow.soluong)}đ",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.End,
                            color = Color(0xFFcc2900),
                        ),
                        modifier = Modifier.padding(5.dp, 10.dp)
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun BillShipScreenPreview() {
    STTCTheme {
        val context = LocalContext.current
        BillShipScreen(
            openDetailBillShip = {},
            ProductViewModel(),
            AccountViewModel(context),
            BillViewModel(),
            context
        )
    }
}
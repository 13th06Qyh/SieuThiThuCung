package com.example.sttc.view

import android.content.Context
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import android.media.Image
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import com.example.sttc.model.billShow
import com.example.sttc.ui.theme.STTCTheme
import com.example.sttc.view.System.formatNumber
import com.example.sttc.viewmodel.AccountViewModel
import com.example.sttc.viewmodel.BillViewModel
import com.example.sttc.viewmodel.ProductViewModel

// BillCancelScreen : đơn hàng đã bị hủy
@Composable
fun BillCancelScreen(
    billModelView: BillViewModel,
    accountViewModel: AccountViewModel,
    productViewModel: ProductViewModel,
    context: Context,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopIconBillCancel()
            TitleBillCancel()
            ContentBillCancel(billModelView, accountViewModel, productViewModel, context)
        }
    }
}


@Composable
fun TopIconBillCancel() {
    val context = LocalContext.current

    Image(
        painter = painterResource(id = R.drawable.cancelproduct),
        contentDescription = "TopIconCancelProduct",
        modifier = Modifier
            .fillMaxWidth()
            .height(165.dp)
            .background(
                Brush.radialGradient(
                    colors = listOf(
                        Color(0xFF706D6D),
                        Color(0xFFF1E9E9),
                        Color(0xFFB6B2B2),
                        Color(0xFFAFACAC),
                        Color(0xFFF6F2F2),
                    ),
                    radius = 550f
                )
            )
            .border(1.dp, color = Color(0xFFcc00cc))
    )
}

@Composable
fun TitleBillCancel() {
    Text(
        text = "Đơn Hàng Bị Hủy",
        style = TextStyle(
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = Color.Black
        ),
        modifier = Modifier
            .fillMaxWidth()
//            .height(50.dp)
            .background(
                Brush.radialGradient(
                    colors = listOf(
                        Color(0xFFFFFFFF),
                        Color(0xFFAFACAC),
                        Color(0xFFF6F2F2),
                    ),
                    radius = 600f
                )
            )
            .padding(0.dp, 10.dp)
            .border(1.dp, color = Color.Black)
    )
}

@Composable
fun ContentBillCancel(
    billViewModel: BillViewModel,
    accountViewModel: AccountViewModel,
    productViewModel: ProductViewModel,
    context: Context,
) {
    val billCancel by billViewModel.billShow.collectAsState(initial = emptyList())
    val imagesMap by productViewModel.images.collectAsState(initial = emptyMap())

    val userId = accountViewModel.getUserIdFromSharedPreferences()
    Log.d("BillCancelScreen", "userId: $userId")
//    LaunchedEffect(userId) {
        billViewModel.fetchBillCancel(userId)
//    }
//    val billCancelList = listOf(
//
//    )
    Log.e("huhukhocne", "billCancel: $billCancel")
    if (billCancel.isNotEmpty()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFe6e6e6))
                .padding(5.dp, 0.dp)
        ) {
            items(billCancel) { item ->
                BillCancelItem(item, imagesMap, productViewModel, context)
                Log.e("itemBillCancelScreen", "item: $item")
            }
        }
    } else {
        Text("No data available")
    }
}

@Composable
fun BillCancelItem(
    item: billShow,
    imagesMap: Map<Int, List<ImageSP>>,
    productViewModel: ProductViewModel,
    context: Context
) {
    val productImages = remember { mutableStateOf<List<ImageSP>>(emptyList()) }

    LaunchedEffect(key1 = item.maSP) {
        productViewModel.fetchImages(item.maSP)
    }

    productImages.value = imagesMap[item.maSP].orEmpty()

    Column(
        modifier = Modifier
            .padding(0.dp, 0.dp, 0.dp, 10.dp)
            .border(1.dp, color = Color(0xFFFFFFFF))
            .fillMaxWidth()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = "Ngày huỷ đơn: ",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontStyle = FontStyle.Italic,
                    textAlign = TextAlign.Start,
                    color = Color.Gray,
                ),
                modifier = Modifier
                    .padding(10.dp, 5.dp)
            )
        }

        HorizontalDivider(thickness = 1.2.dp, color = Color(0xFFcccccc))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
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
                    "Giá: ${formatNumber(item.buyprice)}đ",
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
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
            horizontalArrangement = Arrangement.End
        ) {
            Icon(
                painter = painterResource(id = R.drawable.money),
                contentDescription = "Money",
                tint = Color(0xFFcc2900),
                modifier = Modifier
                    .size(28.dp)
                    .padding(0.dp, 9.dp, 0.dp, 0.dp)
            )
            Text(
                text = "Thành tiền: ${formatNumber(item.buyprice * item.soluong)}đ",
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

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White),
            horizontalArrangement = Arrangement.End
        ) {
            Button(
                onClick = { /* Do something! */ },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFcc2900),
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .padding(5.dp, 5.dp)
            ) {
                Text(
                    text = "Mua lại",
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
fun BillCancelScreenPreview() {
    val context = LocalContext.current
    STTCTheme {
        BillCancelScreen(
            billModelView = BillViewModel(),
            accountViewModel = AccountViewModel(context),
            productViewModel = ProductViewModel(),
            context = context
        )
    }
}
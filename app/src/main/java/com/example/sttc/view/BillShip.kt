package com.example.sttc.view

import androidx.compose.foundation.lazy.LazyColumn
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
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
import com.example.sttc.ui.theme.STTCTheme

class BillShip : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            STTCTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BillShipScreen()
                }
            }
        }
    }
}

@Composable
fun BillShipScreen() {
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
            TopIconBillShip()
            TitleBillShip()
            ContentBillShip()
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

//    Image(
//        painter = rememberAsyncImagePainter(
//            ImageRequest.Builder(context).data(data = R.drawable.shipper).apply(block = {
//            }).build(), imageLoader = imageLoader
//        ),
//        contentDescription = "TopIconShipper",
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(130.dp)
//            .border(1.dp, color = Color(0xFFcc00cc))
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
//    )

    Image(
        painter = painterResource(id = R.drawable.shipper),
        contentDescription = "TopIconShipper",
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
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
            .border(1.dp, color = Color(0xFFcc00cc))
    )
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
fun ContentBillShip() {
    val items = listOf(
        Item(R.drawable.rs1, "Tag A", "Product A", "10.000đ"),
        Item(R.drawable.rs2, "Tag B", "Product B", "102.000đ"),
        Item(R.drawable.rs3, "Tag C", "Product C", "2.345.000đ"),
        Item(R.drawable.rs1, "Tag D", "Product D", "30.000đ"),
        Item(R.drawable.rs2, "Tag E", "Product E", "8.000đ"),
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, color = Color(0xFFff6666))
            .background(Color(0xFFccffff))
            .padding(5.dp, 0.dp)
    ) {
        items(items) { item ->
            Column(
                modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 15.dp)
                    .fillMaxWidth()
                    .background(
                        Brush.radialGradient(
                            colors = listOf(
                                Color(0xFFFFFFFF),
                                Color(0xFFFFFFF8),
                                Color(0xFFffebe6),
                                Color(0xFFFFEBE4),
                            ),
                            radius = 600f
                        )
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.border(2.dp, color = Color(0xFFff6666))
                        .clickable { /*TODO*/ },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ){
                    Image(
                        painter = painterResource(id = item.imageResId),
                        contentDescription = "Image",
                        modifier = Modifier
                            .size(120.dp)
                            .padding(5.dp, 5.dp)
                            .border(0.1.dp, color = Color.Black)
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp)
                            .padding(5.dp, 16.dp),
                    ) {
                        Text(text = item.productName,
                            style = TextStyle(
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        )
                        Spacer(modifier = Modifier.height(4.dp)) // Thêm khoảng cách ở đây
                        Text(text = item.tagName,
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontStyle = FontStyle.Italic,
                                color = Color.Black,
                            )
                        )
                        Text("x1",
                            style = TextStyle(
                                fontSize = 18.sp,
                                color = Color.Black,
                                textAlign = TextAlign.End
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )
                        Text("Giá: " + item.productPrice,
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

                Row (
                    modifier = Modifier.fillMaxWidth()
                        .border(2.dp, color = Color(0xFFff6666)),
                    horizontalArrangement = Arrangement.End
                ){
                    Icon(
                        painter = painterResource(id = R.drawable.money),
                        contentDescription = "Money",
                        tint = Color(0xFFcc2900),
                        modifier = Modifier
                            .size(45.dp)
                            .padding(0.dp, 9.dp, 2.dp, 10.dp)
                    )
                    Text(
                        text = "Thành tiền: ",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.End,
                            color = Color.Black,
                        ),
                        modifier = Modifier
                            .padding(0.dp, 10.dp)
                    )

                }
            }
        }
    }
}

data class ItemBillShip (
    var name: String,
    var email: String,
    var phone: Int,
    var pass: String,
    var address: String

)


@Preview(showBackground = true)
@Composable
fun BillShipScreenPreview() {
    STTCTheme {
        BillShipScreen()
    }
}
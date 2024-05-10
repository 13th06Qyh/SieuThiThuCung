package com.example.sttc.view

import com.example.sttc.R
import com.example.sttc.ui.theme.STTCTheme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.request.ImageRequest
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@ExperimentalMaterial3Api
class ListProductsDog : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DetailDog()
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun DetailDog() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Slide_Gif()
        }
        Row {
            var selectedIndex by remember { mutableIntStateOf(0) }
            val options = listOf("Áo quần", "Đồ ăn", "Đồ chơi")
            SingleChoiceSegmentedButtonRow {
                options.forEachIndexed { index, label ->
                    SegmentedButton(
                        shape = SegmentedButtonDefaults.itemShape(
                            index = index,
                            count = options.size
                        ),
                        onClick = { selectedIndex = index },
                        selected = index == selectedIndex
                    ) {
                        Text(label)
                    }
                }
            }
        }
        Row {
            ListProducts()
        }


    }
}

@Composable
fun Sanpham() {
    val items = listOf(
        R.drawable.rs1,
        R.drawable.rs2,
        R.drawable.rs1,
        R.drawable.rs2,
        R.drawable.rs3,
    )
    val coroutineScope = rememberCoroutineScope()
    val state = rememberLazyListState()
    val currentPage = remember { mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(3000L)
            coroutineScope.launch {
                val nextPage = if (currentPage.value < items.size - 1) {
                    currentPage.value + 1
                } else {
                    0
                }
                state.animateScrollToItem(nextPage)
                currentPage.value = nextPage
            }
        }
    }

    LazyRow(state = state) {
        items(items) { item ->
            Box(
                modifier = Modifier
                    .size(170.dp, 200.dp)
                    .padding(0.1.dp)
                    .border(1.dp, color = Color(0xFFffe1c2))
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color(0xFFF6F5F2),
                                Color(0xFFF1C4A3),
                                Color(0xFFDF4F4B)
                            ),
                            startY = 470f,
                            endY = 0f
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = item),
                        contentDescription = null,
                        modifier = Modifier
                            .width(200.dp)
                            .height(155.dp)
                            .padding(8.dp)
                    )

                    HorizontalDivider(thickness = 2.dp, color = Color(0xFFffdab3))

                    val colors = listOf(Color.Red, Color.Transparent)
                    var colorIndex by remember { mutableStateOf(0) }
                    val animatedColor by animateColorAsState(targetValue = colors[colorIndex])

                    LaunchedEffect(key1 = true) {
                        while (true) {
                            delay(300) // delay in milliseconds you want between each blink
                            colorIndex = 1 - colorIndex
                        }
                    }

                    Text(
                        text = "100K", // Thay đổi thành tiêu đề thực tế của bạn
                        style = TextStyle(
                            fontSize = 19.sp,
                            fontFamily = FontFamily.Monospace,
                            fontStyle = FontStyle.Italic,
                            fontWeight = FontWeight.Bold,
                            color = animatedColor,
                            textAlign = TextAlign.Center
                        ),
                        modifier = Modifier.padding(0.dp, 10.dp)
                    )
                }
            }
        }
    }

    CustomPagerIndicator(
        pageCount = items.size,
        currentPage = currentPage.value,
    )


}

@Composable
fun ListProducts() {
    val listProducts = listOf(
        ProductsItems(1, R.drawable.rs1, "Tag A", "Product A", "10.000đ"),
        ProductsItems(2, R.drawable.rs2, "Tag B", "Product B", "102.000đ"),
        ProductsItems(3, R.drawable.rs3, "Tag C", "Product C", "2.345.000đ"),
        ProductsItems(4, R.drawable.rs1, "Tag D", "Product D", "30.000đ"),
    )
    val rows = listProducts.chunked(2)
    Column{
        rows.forEach { rowItems ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(2.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color(0xFFF6F2F2),
                                    Color(0xFFFFC1B6),
                                    Color(0xFFFF9999)
                                ),
                                startY = 720f,
                                endY = 0f
                            )
                        ),
                ) {
                    for (item in rowItems) {
                        Column(
                            modifier = Modifier
                                .padding(4.dp)
                                .width(200.dp)
                                .border(1.dp, color = Color(0xFFff4d4d))
                                .clickable(onClick = {
                                    // Xử lý sự kiện khi một mục được nhấp vào
                                }),
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(200.dp, 210.dp)
                                    .border(1.dp, color = Color(0xFFff4d4d)),
                                contentAlignment = Alignment.Center
                            ) {
                                // Thay thế bằng hình ảnh thực tế của bạn
                                Image(
                                    painter = painterResource(id = item.imageResId),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.fillMaxSize()
                                )
                            }
                            Text(
                                text = item.tagName, // Thay thế bằng tên thẻ thực tế của bạn
                                modifier = Modifier.padding(8.dp),
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    fontStyle = FontStyle.Italic,
                                    color = Color.Gray
                                ),
                            )
                            Text(
                                text = item.productName, // Thay thế bằng tên sản phẩm thực tế của bạn
                                modifier = Modifier.padding(horizontal = 8.dp),
                                style = TextStyle(
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black
                                ),
                            )
                            Text(
                                text = item.productPrice, // Thay thế bằng giá sản phẩm thực tế của bạn
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 8.dp, vertical = 9.dp),
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFFff4d4d),
                                    textAlign = TextAlign.End
                                ),
                            )
                        }
                    }
                }
            }
        }
    }
}
@Composable
fun Slide_Gif() {
//    val context = LocalContext.current
//    val imageLoader = ImageLoader.Builder(context)
//        .components {
//            add(GifDecoder.Factory())
//        }
//        .build()
//    Image(
//        painter = rememberAsyncImagePainter(
//            ImageRequest.Builder(context).data(data = R.drawable.gifcho).apply(block = {
//            }).build(), imageLoader = imageLoader
//        ),
//        contentDescription = null,
//    )
    Image(
        painter = painterResource(id = R.drawable.gifcho), // Thay đổi vị trí file theo tên file của bạn
        contentDescription = null,
        modifier = Modifier.fillMaxWidth()
    )
}

data class ProductsItems(
    val id: Int,
    val imageResId: Int,
    val tagName: String,
    val productName: String,
    val productPrice: String
)

@ExperimentalMaterial3Api
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DetailDogMainPreview() {
    STTCTheme {
        DetailDog()
    }
}

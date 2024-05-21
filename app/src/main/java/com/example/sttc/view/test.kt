package com.example.sttc.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.sttc.R
import com.example.sttc.ui.theme.STTCTheme
import com.example.sttc.view.Products.ZoomableImageDialog
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DetailProductTest : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            STTCTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DetailProductTestScreen()
                }
            }
        }
    }
}

@Composable
fun DetailProductTestScreen(
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
//            .background(Color(0xFFF6F2F2))
            .verticalScroll(scrollState)
    ) {
        TitleInforProductTest()
        SlideImageTest()
        NameAndPriceTest()
        InforProductTest()
        BuyProductTest()
        ContentProductTest()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            HorizontalDivider(
                modifier = Modifier
                    .weight(1f)
                    .padding(0.dp, 20.dp),  // Take up half the space
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
                modifier = Modifier.padding(10.dp)
            )
            HorizontalDivider(
                modifier = Modifier
                    .weight(1f)
                    .padding(0.dp, 20.dp),  // Take up half the space
                thickness = 1.2.dp,
                color = Color.Gray
            )
        }
//        SuggestToday()
    }

}

@Composable
fun TitleInforProductTest() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
//            .height(50.dp)
            .background(
                Brush.radialGradient(
                    colors = listOf(
                        Color(0xFFFFFFFF),
                        Color(0xFFF6F2F2),
                    ),
                    radius = 600f
                )
            )
            .padding(0.dp, 5.dp)
//            .border(1.dp, color = Color(0xFFff6666))
    ) {
        Icon(
            Icons.Default.ArrowBack, contentDescription = "Back",
            modifier = Modifier
                .size(50.dp)
                .padding(10.dp, 0.dp)
                .clickable { /*TODO*/ },
            tint = Color(0xFFcc2900)
        )
        Text(
            text = "Chi tiết sản phẩm",
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

@OptIn(ExperimentalPagerApi::class)
@Composable
fun SlideImageTest(modifier: Modifier = Modifier) {

    val images = listOf(
        R.drawable.a73e0f7487b8e5297182c5a711d20bf26,
        R.drawable.doancho2,
    )
    val pagerState = rememberPagerState(pageCount = images.size)
    LaunchedEffect(Unit) {
        while (true) {
            delay(2000)
            val nextPage = (pagerState.currentPage + 1) % pagerState.pageCount
            pagerState.scrollToPage(nextPage)
        }
    }
    val scope = rememberCoroutineScope()
    var showZoomDialog by remember { mutableStateOf(false) }
    Box(
        modifier = modifier
            .wrapContentSize()
            .background(Color(0xFFF6F2F2))
    ) {
        HorizontalPager(
            state = pagerState,
            modifier
                .wrapContentSize()

        ) { currentPage ->
            Card(
                modifier.fillMaxWidth().height(300.dp)
                    .wrapContentSize()
                    .clickable {
                        showZoomDialog = true
                    },
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Image(
                    painter = painterResource(id = images[currentPage]),
                    contentDescription = "",
//                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize().background(Color.White)
                        ,
                )
            }

        }
        IconButton(
            onClick = {
                val nextPage = pagerState.currentPage + 1
                if (nextPage < images.size) {
                    scope.launch {
                        pagerState.scrollToPage(nextPage)
                    }
                }
            },
            modifier
                .size(48.dp)
                .align(Alignment.CenterEnd)
                .clip(CircleShape),
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = Color.Transparent
            )
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "",
                modifier.fillMaxSize(),
                tint = Color.LightGray
            )
        }
        IconButton(
            onClick = {
                val prevPage = pagerState.currentPage - 1
                if (prevPage >= 0) {
                    scope.launch {
                        pagerState.scrollToPage(prevPage)
                    }
                }
            },
            modifier
                .size(48.dp)
                .align(Alignment.CenterStart)
                .clip(CircleShape),
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = Color.Transparent
            )
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                contentDescription = "",
                modifier.fillMaxSize(),
                tint = Color.LightGray
            )
        }
    }
//    if (showZoomDialog) {
//        ZoomableImageDialog(images = images, images.size) {
//            showZoomDialog = false
//        }
//    }
}

//@OptIn(ExperimentalPagerApi::class)
//@Composable
//fun ZoomableImageDialog(images: List<Int>, initialPage: Int, onDismiss: () -> Unit) {
//    val zoomPagerState = rememberPagerState(initialPage)
//    Dialog(onDismissRequest = onDismiss) {
//        Box(
//            contentAlignment = Alignment.Center,
//            modifier = Modifier
//                .fillMaxWidth().height(600.dp)
//                .background(Color.Black.copy(alpha = 0.5f))
//        ) {
//            HorizontalPager(
//                state = zoomPagerState,
//                modifier = Modifier.fillMaxSize()
//            ) { page ->
//                Image(
//                    painter = painterResource(id = images[page]),
//                    contentDescription = null,
//                    modifier = Modifier.fillMaxSize()
//                        .clickable(onClick = onDismiss),
//                    contentScale = ContentScale.Fit
//                )
//            }
//            IconButton(
//                onClick = onDismiss,
//                modifier = Modifier
//                    .align(Alignment.TopEnd)
//                    .padding(16.dp)
//                    .background(Color.Gray, CircleShape)
//                    .size(36.dp)
//            ) {
//                Icon(
//                    imageVector = Icons.Default.Close,
//                    contentDescription = "Close",
//                    tint = Color.White
//                )
//            }
//        }
//    }
//}
@Composable
fun NameAndPriceTest() {
    Column(
        modifier = Modifier.background(Color(0xFFF6F2F2))
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(35.dp)
                .background(Color.Red),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = "41.000đ",
                style = TextStyle(
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                ),
                modifier = Modifier.padding(end = 10.dp)
            )
        }

        Text(
            text = "Tên Sản Phẩm",
            style = TextStyle(
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                color = Color.Black
            ),
            modifier = Modifier
                .padding(10.dp)
                .background(Color(0xFFF6F2F2))
        )
    }
}

@Composable
fun InforProductTest() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF6F2F2))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp, 0.dp, 10.dp, 5.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Chất liệu",
                style = TextStyle(
                    fontSize = 18.sp,
                    color = Color.Black
                )
            )

            Text(
                text = "Dạng khô",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                    color = Color.Black
                )
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp, 0.dp, 10.dp, 5.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Kho hàng",
                style = TextStyle(
                    fontSize = 18.sp,
                    color = Color.Black
                )
            )

            Text(
                text = "1278",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                    color = Color.Black
                )
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp, 0.dp, 10.dp, 5.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Nguồn hàng",
                style = TextStyle(
                    fontSize = 18.sp,
                    color = Color.Black
                )
            )

            Text(
                text = "Công ty TNHH QuacQuac",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                    color = Color.Black
                )
            )
        }
    }
}

@Composable
fun BuyProductTest() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 1.dp, 0.dp, 2.dp)
            .background(Color(0xFFFFFFFF)),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(
            onClick = { /* Do something! */ },
            shape = RoundedCornerShape(2.dp), // Định dạng góc bo tròn của nút
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF0a2929), // Màu nền của nút
                contentColor = Color.White // Màu chữ của nút
            ),
            modifier = Modifier
                .width(230.dp)
                .padding(7.dp, 5.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Default.ShoppingCart, contentDescription = "Add to cart",
                    modifier = Modifier
                        .size(25.dp)
                        .padding(0.dp, 0.dp, 5.dp, 0.dp),
                    tint = Color.White
                )

                Text(
                    text = "Thêm vào giỏ hàng",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    ),
                )
            }
        }

        Button(
            onClick = { /* Do something! */ },
            shape = RoundedCornerShape(2.dp), // Định dạng góc bo tròn của nút
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFcc2900), // Màu nền của nút
                contentColor = Color.White // Màu chữ của nút
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(7.dp, 5.dp)
        ) {
            Text(
                text = "Mua ngay",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                ),
            )
        }
    }
}

@Composable
fun ContentProductTest() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFFFFFFF))
    ) {
        Text(
            text = "Mô Tả Sản Phẩm",
            style = TextStyle(
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal,
                letterSpacing = 0.5.sp
            ),
            modifier = Modifier.padding(10.dp, 10.dp, 0.dp, 3.dp)
        )

        HorizontalDivider(thickness = 1.dp, color = Color(0xFF999999))

        Text(
            text = stringResource(id = R.string.motasanpham),
            style = TextStyle(
                fontSize = 18.sp,
                color = Color(0xFF595959)
            ),
            modifier = Modifier.padding(15.dp, 10.dp, 10.dp, 30.dp)
        )

    }

}

@Preview(showBackground = true)
@Composable
fun DetailProductsTestPreview() {
    STTCTheme {
        DetailProductTestScreen()
    }
}
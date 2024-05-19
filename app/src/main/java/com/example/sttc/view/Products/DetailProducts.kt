package com.example.sttc.view.Products

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
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
import com.example.sttc.R
import com.example.sttc.ui.theme.STTCTheme
import com.example.sttc.view.System.SuggestToday
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun DetailProductsScreen(
    back : () -> Unit ,
    openCart : () -> Unit
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
//            .background(Color(0xFFF6F2F2))
            .verticalScroll(scrollState)
    ) {
        TitleInforProduct(back)
        SlideImage()
        NameAndPrice()
        InforProduct()
        BuyProduct(openCart)
        ContentProduct()
        Row(
            modifier = Modifier.fillMaxWidth()
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
        SuggestToday()
    }

}

@Composable
fun TitleInforProduct(back : () -> Unit){
    Row (
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
    ){
        Icon(
            Icons.Default.ArrowBack, contentDescription = "Back",
            modifier = Modifier
                .size(50.dp)
                .padding(10.dp, 0.dp)
                .clickable { back()},
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

@Composable
@OptIn(ExperimentalPagerApi::class)
fun SlideImage(modifier: Modifier = Modifier) {

    val images = listOf(
        R.drawable.doancho1,
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

    Box(modifier = modifier
        .wrapContentSize()
        .background(Color(0xFFF6F2F2))
        ) {
        HorizontalPager(
            state = pagerState,
            modifier
                .wrapContentSize()

        ) { currentPage ->
            Card(
                modifier
                    .wrapContentSize(),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Image(
                    painter = painterResource(id = images[currentPage]),
                    contentDescription = ""
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
                containerColor = Color(0xFFf2f2f2)
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
                containerColor = Color(0xFFf2f2f2)
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
}

@Composable
fun NameAndPrice(){
    Column(
        modifier = Modifier.background(Color(0xFFF6F2F2))
    ){

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
                    color = Color.White),
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
            modifier = Modifier.padding(10.dp)
                .background(Color(0xFFF6F2F2))
        )
    }
}
@Composable
fun InforProduct(){
    Column(
        modifier = Modifier.fillMaxWidth()
            .background(Color(0xFFF6F2F2))
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp, 0.dp, 10.dp, 5.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
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

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp, 0.dp, 10.dp, 5.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
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

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp, 0.dp, 10.dp, 5.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
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
fun BuyProduct( openCart : () -> Unit){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 1.dp, 0.dp, 2.dp)
            .background(Color(0xFFFFFFFF)),
        horizontalArrangement = Arrangement.SpaceEvenly
    ){
        Button(
            onClick = { openCart() },
            shape = RoundedCornerShape(2.dp), // Định dạng góc bo tròn của nút
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF0a2929), // Màu nền của nút
                contentColor = Color.White // Màu chữ của nút
            ),
            modifier = Modifier
                .width(230.dp)
                .padding(7.dp, 5.dp)
        ) {
            Row (
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    Icons.Default.ShoppingCart, contentDescription = "Add to cart",
                    modifier = Modifier
                        .size(25.dp)
                        .padding(0.dp, 0.dp, 5.dp, 0.dp),
                    tint = Color.White
                )

                Text(text = "Thêm vào giỏ hàng",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    ),
                )
            }
        }

        Button(
            onClick = { openCart() },
            shape = RoundedCornerShape(2.dp), // Định dạng góc bo tròn của nút
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFcc2900), // Màu nền của nút
                contentColor = Color.White // Màu chữ của nút
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(7.dp, 5.dp)
        ) {
            Text(text = "Mua ngay",
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
fun ContentProduct(){
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
                fontStyle = FontStyle.Normal ,
                letterSpacing = 0.5.sp
            ) ,
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
fun DetailProductsPreview() {
    STTCTheme {
        DetailProductsScreen(back = {} , openCart = {})
    }
}
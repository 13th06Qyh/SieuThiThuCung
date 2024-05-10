package com.example.sttc.view

import com.example.sttc.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sttc.ui.theme.STTCTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DetailProducts : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DetailProductsScreen()
        }
    }
}

@Composable
fun DetailProductsScreen() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        SlideImage()
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Mô Tả Sản Phẩm",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W400,
                    fontStyle = FontStyle.Normal ,
                    letterSpacing = 0.5.sp
                ) ,
                modifier = Modifier.padding(10.dp)

            )
            Text(
                text = stringResource(id = R.string.motasanpham),
                style = TextStyle(
                    fontSize = 16.sp,
                    color = Color(0xFFB4AEAE)
                ),
                modifier = Modifier.padding(10.dp)
            )

        }
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


    Box(modifier = modifier.wrapContentSize()) {
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
                containerColor = Color(0x52373737)
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
                containerColor = Color(0x52373737)
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
    Row(
        modifier.fillMaxWidth().padding(top = 10.dp , start = 5.dp ),
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            text = "41 000 VND",
            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.Red),
        )
    }

    Column(
        modifier
            .padding(top = 5.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start){
        Text(
            text = "Tên Sản Phẩm",
            style = TextStyle(
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                color = Color.Black
            )
        )
        HorizontalDivider(
            modifier = Modifier.padding(top = 10.dp),
            thickness = 10.dp,
            color = Color(0xFFe6e6e6)
        )
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DetailProductsPreview() {
    STTCTheme {
        DetailProductsScreen()
    }
}
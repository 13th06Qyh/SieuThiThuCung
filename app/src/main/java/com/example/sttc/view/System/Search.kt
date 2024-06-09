package com.example.sttc.view.System

import android.content.Context
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
import com.example.sttc.ui.theme.STTCTheme
import com.example.sttc.viewmodel.ProductViewModel
import com.example.sttc.viewmodel.SharedViewModel
import kotlinx.coroutines.delay

@Composable
fun SearchScreen(
    openDetailProducts: (id: Int) -> Unit,
    sharedViewModel: SharedViewModel,
    context: Context
) {
    val products by sharedViewModel.productDT.collectAsState(initial = emptyList())
    val keyword by sharedViewModel.keywordInfoFlow.collectAsState(null)

    val limitedProducts = products.take(10)
    val rows = limitedProducts.chunked(2)
    val searchHistory = listOf("Đời", "KookMin", "Royal Canin", "VMin", "Cameo")

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .background(Color(0xFFf5f5f5))
                .fillMaxWidth()
                .padding(start = 15.dp, top = 10.dp, end = 15.dp, bottom = 5.dp),
        ) {
            Icon(
                painter = painterResource(R.drawable.history),
                contentDescription = "SearchHistory"
            )

            Text(
                text = "Tìm kiếm gần đây",
                modifier = Modifier
                    .padding(start = 5.dp),
                style = TextStyle(
                    fontSize = 20.sp,
                    fontStyle = FontStyle.Italic,
                    color = Color.Black
                )
            )
        }

        HorizontalDivider(modifier = Modifier.height(1.dp), color = Color.Black)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFf9ffff))
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp, 0.dp),
                verticalArrangement = Arrangement.Top
            ) {
                items(searchHistory.size) { index ->
//                    LaunchedEffect(searchHistory[index]) {
//                        sharedViewModel.setSelectedKeyword(Keyword(searchHistory[index]))
//                    }
                    IconButton(
                        onClick = {
                            sharedViewModel.setSelectedKeyword(Keyword(searchHistory[index]))
                        },
                        modifier = Modifier
                            .background(Color.White)
                            .padding(10.dp, 10.dp)
                            .width(100.dp)
                            .border(1.dp, Color.Gray, RoundedCornerShape(19.dp))
                    ) {
                        Text(
                            text = searchHistory[index],
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        )
                    }
                }
            }
        }

        HorizontalDivider(modifier = Modifier.height(1.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp),
        ) {
            if (products.isEmpty() || keyword == null) {
                Column(
                    modifier = Modifier
//                .background(Color.White)
                        .padding(5.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Không tìm thấy sản phẩm nào!",
                        modifier = Modifier.padding(5.dp),
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Italic,
                            textAlign = TextAlign.Center,
                            color = Color.Black
                        )
                    )
                }
            } else {
                Column(
                    modifier = Modifier
                        .background(Color.White)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    rows.forEach { rowItems ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(2.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .background(Color.White)
                                    .fillMaxWidth(),
//                    horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                for (item in rowItems) {
                                    val number = item.sanpham.buyprice.toInt()
                                    Column(
                                        modifier = Modifier
                                            .background(Color(0xFFffebeb))
                                            .padding(4.dp)
                                            .width(200.dp)
                                            .border(1.dp, color = Color(0xFFff4d4d))
                                            .clickable { openDetailProducts(item.sanpham.maSP) },
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .size(200.dp, 210.dp)
                                                .border(1.dp, color = Color(0xFFff4d4d)),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            val imageUrl = item.image
                                            val fileName =
                                                imageUrl.substringAfterLast("/")
                                                    .substringBeforeLast(".")
                                            val fileExtension = imageUrl.substringAfterLast(".")
                                            val resourceId = context.resources.getIdentifier(
                                                fileName,
                                                "drawable",
                                                context.packageName
                                            )
                                            val a = context.resources.getResourceName(resourceId)
                                            val b = a.substringAfter('/')
//                                    Log.d("test", "FileName: $fileName")
//                                    Log.d("test", "FileExtension: $fileExtension")
//                                    Log.d("test", "ResourceId: $resourceId")
//                                    Log.d("test", "ResourceName1: $a")
//                                    Log.d("test", "ResourceName2: $b")
                                            if (b == fileName) {
                                                Image(
                                                    painter = painterResource(id = resourceId),
                                                    contentDescription = "Image",
                                                    contentScale = ContentScale.Crop,
                                                    modifier = Modifier.fillMaxSize()
                                                )
                                            } else {
                                                Image(
                                                    painter = painterResource(id = R.drawable.rs1),
                                                    contentDescription = "Image",
                                                    contentScale = ContentScale.Crop,
                                                    modifier = Modifier.fillMaxSize()
                                                )
                                            }

                                        }
                                        Text(
                                            text = item.tagname, // Thay thế bằng tên thẻ thực tế của bạn
                                            modifier = Modifier.padding(8.dp),
                                            style = TextStyle(
                                                fontSize = 12.sp,
                                                fontStyle = FontStyle.Italic,
                                                color = Color.Gray
                                            ),
                                        )

                                        val productName = item.sanpham.tensp
                                        val shortenedName = if (productName.length > 15) {
                                            "${productName.substring(0, 15)}..."
                                        } else {
                                            productName
                                        }
                                        Text(
                                            text = shortenedName, // Thay thế bằng tên sản phẩm thực tế của bạn
                                            modifier = Modifier
                                                .padding(horizontal = 8.dp),
                                            style = TextStyle(
                                                fontSize = 18.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = Color.Black
                                            ),
                                        )
                                        Text(

                                            text = formatNumber(number) + "đ", // Thay thế bằng giá sản phẩm thực tế của bạn
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

        }
    }
}

@Preview(showBackground = true)
@Composable
fun Search() {
    STTCTheme {
        SearchScreen(
            openDetailProducts = {},
            sharedViewModel = SharedViewModel(LocalContext.current),
            context = LocalContext.current
        )
    }
}
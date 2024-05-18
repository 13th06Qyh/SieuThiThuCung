package com.example.sttc.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sttc.R
import java.text.NumberFormat
import java.util.Locale

data class ItemsBaiViet(
    val id: Int,
    val tieude: String,
    val noidung: String,
    val imageblog: Int,
)
data class ItemsCmt(
    val id: Int,
    val nameUser: String,
    var cmt: String,
)
data class ItemsCart(
    val id: Int,
    val image: Int,
    val productName: String,
    val productPrice: Int,
    val quantity: Int,
)
data class Product(
    val imageResId: Int,
    val tagName: String,
    val productName: String,
    val productPrice: Int
)

data class Bill(
    var soluongmua: Int
)
data class BillProduct(
    val product: Product,
    val bill: Bill
)
data class ItemAccount (
    var name: String,
    var email: String,
    var phone: Int,
    var pass: String,
    var address: String

)
data class ItemsNotification(
    val id: Int,
    val content: String,
    val time: String
)
@Composable
fun formatNumber(number: Int): String {
    val format = NumberFormat.getNumberInstance(Locale.GERMAN)
    return format.format(number)
}
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CustomPagerIndicator(
    pageCount: Int,
    currentPage: Int,
    modifier: Modifier = Modifier,
    activeColor: Color = Color(0xFF000000),
    inactiveColor: Color = Color(0xFFE96B56),
    indicatorSize: Dp = 10.dp
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        for (i in 0 until pageCount) {
            Box(
                modifier = Modifier
                    .padding(4.dp, 13.dp)
                    .size(indicatorSize)
                    .background(
                        if (currentPage == i) activeColor else inactiveColor,
                        CircleShape
                    )
            )
        }
    }
}

@Composable
fun SuggestToday() {
    val items = listOf(
        BillProduct(Product(R.drawable.rs1, "Tag A", "Product A", 10000), Bill(1)),
        BillProduct(Product(R.drawable.rs2, "Tag B", "Product B", 102000), Bill(2)),
        BillProduct(Product(R.drawable.rs3, "Tag C", "Product C", 2345000), Bill(2)),
        BillProduct(Product(R.drawable.rs1, "Tag D", "Product D", 30000), Bill(2)),
        BillProduct(Product(R.drawable.rs2, "Tag E", "Product E", 8000), Bill(2)),

        )

    // Kết quả: "10,000"
    // Chia danh sách thành các nhóm có 3 mặt hàng
    val rows = items.chunked(2)

    Column(
//        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        rows.forEach { rowItems ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(2.dp)
            ) {
                Row (
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
//                    horizontalArrangement = Arrangement.SpaceEvenly
                ){
                    for (item in rowItems) {
                        Column (
                            modifier = Modifier
                                .padding(4.dp)
                                .width(200.dp)
                                .border(1.dp, color = Color(0xFFff4d4d))
                                .clickable(onClick = {
                                }),
                        ){
                            Box(
                                modifier = Modifier
                                    .size(200.dp, 210.dp)
                                    .border(1.dp, color = Color(0xFFff4d4d)),
                                contentAlignment = Alignment.Center
                            ) {
                                // Thay thế bằng hình ảnh thực tế của bạn
                                Image(
                                    painter = painterResource(id = item.product.imageResId),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.fillMaxSize()
                                )
                            }
                            Text(
                                text = item.product.tagName, // Thay thế bằng tên thẻ thực tế của bạn
                                modifier = Modifier.padding(8.dp),
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    fontStyle = FontStyle.Italic,
                                    color = Color.Gray
                                ),
                            )
                            Text(
                                text = item.product.productName, // Thay thế bằng tên sản phẩm thực tế của bạn
                                modifier = Modifier.padding(horizontal = 8.dp),
                                style = TextStyle(
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black
                                ),
                            )
                            Text(
                                text = formatNumber(item.product.productPrice).toString() + "đ", // Thay thế bằng giá sản phẩm thực tế của bạn
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
fun SuggestTodayopen(openDetailProducts :  () -> Unit) {
    val items = listOf(
        BillProduct(Product(R.drawable.rs1, "Tag A", "Product A", 10000), Bill(1)),
        BillProduct(Product(R.drawable.rs2, "Tag B", "Product B", 102000), Bill(2)),
        BillProduct(Product(R.drawable.rs3, "Tag C", "Product C", 2345000), Bill(2)),
        BillProduct(Product(R.drawable.rs1, "Tag D", "Product D", 30000), Bill(2)),
        BillProduct(Product(R.drawable.rs2, "Tag E", "Product E", 8000), Bill(2)),

        )

    // Kết quả: "10,000"
    // Chia danh sách thành các nhóm có 3 mặt hàng
    val rows = items.chunked(2)

    Column(
//        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        rows.forEach { rowItems ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(2.dp)
            ) {
                Row (
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
//                    horizontalArrangement = Arrangement.SpaceEvenly
                ){
                    for (item in rowItems) {
                        Column (
                            modifier = Modifier
                                .padding(4.dp)
                                .width(200.dp)
                                .border(1.dp, color = Color(0xFFff4d4d))
                                .clickable{openDetailProducts()},
                        ){
                            Box(
                                modifier = Modifier
                                    .size(200.dp, 210.dp)
                                    .border(1.dp, color = Color(0xFFff4d4d)),
                                contentAlignment = Alignment.Center
                            ) {
                                // Thay thế bằng hình ảnh thực tế của bạn
                                Image(
                                    painter = painterResource(id = item.product.imageResId),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.fillMaxSize()
                                )
                            }
                            Text(
                                text = item.product.tagName, // Thay thế bằng tên thẻ thực tế của bạn
                                modifier = Modifier.padding(8.dp),
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    fontStyle = FontStyle.Italic,
                                    color = Color.Gray
                                ),
                            )
                            Text(
                                text = item.product.productName, // Thay thế bằng tên sản phẩm thực tế của bạn
                                modifier = Modifier.padding(horizontal = 8.dp),
                                style = TextStyle(
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black
                                ),
                            )
                            Text(
                                text = formatNumber(item.product.productPrice).toString() + "đ", // Thay thế bằng giá sản phẩm thực tế của bạn
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


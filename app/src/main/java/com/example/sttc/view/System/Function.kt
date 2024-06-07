package com.example.sttc.view.System

import android.content.Context
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sttc.ui.theme.STTCTheme
import com.example.sttc.viewmodel.ProductViewModel
import kotlinx.coroutines.delay
import java.text.NumberFormat
import java.util.Locale

data class Bank(
    val name: String,
    val image: Int
)

data class BankData(
    val bankName: String,
    val accountNumber: String
)

data class OtherAddress(
    val address: String
)

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

data class ItemAccount(
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

@Composable
fun capitalizeWords(text: String): String {
    return text.split(" ").joinToString(" ") { word ->
        word.lowercase().replaceFirstChar { it.uppercase() }
    }
}

@Composable
fun Allow(
    back: () -> Unit,
    showSuccess: Boolean
) {
    var showDialogAllow by remember { mutableStateOf(showSuccess) }
    var okButton by remember { mutableStateOf(false) }
    if (showDialogAllow) {
        AlertDialog(
            containerColor = Color(0xFFccf5ff),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.size(400.dp, 150.dp),
            onDismissRequest = { showDialogAllow = false },
            title = {},
            text = {},
            confirmButton = {},
            dismissButton = {
                Column {
                    Text(
                        "Bạn đã tạo mã thành công \n ~~ * ~~",
                        style = TextStyle(
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = Color(0xFF000099)
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                    )
                    Button(
                        onClick = {
                            showDialogAllow = false
                            okButton = true
                            back()
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFccffdd), // Màu nền của nút
                            contentColor = Color.Black, // Màu chữ của nút
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        border = BorderStroke(1.dp, Color(0xFF00e64d)),
                    ) {
                        Text(
                            "OK",
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFFcc3300)
                            )
                        )
                    }
                }
            }
        )
    }
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
fun SuggestTodayopen(
    openDetailProducts: (id: Int) -> Unit,
    productViewModel: ProductViewModel,
    context: Context,
    selectedOption: String,
    selectedAnimal: Int
) {
    val products by productViewModel.products.collectAsState(initial = emptyList())
    val imagesMap by productViewModel.images.collectAsState(initial = emptyMap())
    val tag by productViewModel.tag.collectAsState(initial = emptyList())
    val tagMap = remember(tag) { tag.associateBy { it.maTag } }
    LaunchedEffect(key1 = Unit) {
        delay(10000)
        productViewModel.fetchProduct()
        delay(9000)
        productViewModel.fetchTag()
    }

    val filteredProducts = when (selectedOption) {
        "Áo quần" -> products.filter { it.idtype == 1 && it.idanimal == selectedAnimal }
        "Thức ăn" -> products.filter { it.idtype == 2 && it.idanimal == selectedAnimal }
        "Vật dụng" -> products.filter { it.idtype == 3 && it.idanimal == selectedAnimal }
        else -> products
    }

    // Kết quả: "10,000"
    // Chia danh sách thành các nhóm có 2 mặt hàng
    val rows = filteredProducts.chunked(2)

    Column(
//        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
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
//                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    for (item in rowItems) {
                        val number = item.buyprice.toInt()
                        Column(
                            modifier = Modifier
                                .padding(4.dp)
                                .width(200.dp)
                                .border(1.dp, color = Color(0xFFff4d4d))
                                .clickable { openDetailProducts(item.maSP) },
                        ) {
                            LaunchedEffect(key1 = item.maSP) {
                                delay(10000)
                                productViewModel.fetchImages(item.maSP)
                            }

                            Box(
                                modifier = Modifier
                                    .size(200.dp, 210.dp)
                                    .border(1.dp, color = Color(0xFFff4d4d)),
                                contentAlignment = Alignment.Center
                            ) {
                                // Thay thế bằng hình ảnh thực tế của bạn
                                val productImages = imagesMap[item.maSP].orEmpty()
                                if (productImages.isNotEmpty()) {
                                    val image = productImages.first()
                                    val imageUrl = image.image
                                    val fileName =
                                        imageUrl.substringAfterLast("/").substringBeforeLast(".")
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
                                        Text(text = "Image not found")
                                    }
                                }
                            }

                            val tags = tagMap[item.idtag]
                            if (tags != null) {
                                Text(
                                    text = tags.tagname, // Thay thế bằng tên thẻ thực tế của bạn
                                    modifier = Modifier.padding(8.dp),
                                    style = TextStyle(
                                        fontSize = 12.sp,
                                        fontStyle = FontStyle.Italic,
                                        color = Color.Gray
                                    ),
                                )
                            }
                            val productName = item.tensp
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

@Composable
fun SuggestToday(
    openDetailProducts: (id: Int) -> Unit,
    productViewModel: ProductViewModel,
    context: Context,
    selectedOption: String,
    selectedAnimal: Int
) {
    val products by productViewModel.products.collectAsState(initial = emptyList())
    val imagesMap by productViewModel.images.collectAsState(initial = emptyMap())
    val tag by productViewModel.tag.collectAsState(initial = emptyList())
    val tagMap = remember(tag) { tag.associateBy { it.maTag } }


    productViewModel.fetchProduct()
    productViewModel.fetchTag()


    val filteredProducts = when (selectedOption) {
        "Áo quần" -> products.filter { it.idtype == 1 && it.idanimal == selectedAnimal }
        "Thức ăn" -> products.filter { it.idtype == 2 && it.idanimal == selectedAnimal }
        "Vật dụng" -> products.filter { it.idtype == 3 && it.idanimal == selectedAnimal }
        else -> products
    }

    // Kết quả: "10,000"
    // Chia danh sách thành các nhóm có 2 mặt hàng
    val rows = filteredProducts.chunked(2)

    Column(
//        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
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
//                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    for (item in rowItems) {
                        val number = item.buyprice.toInt()
                        Column(
                            modifier = Modifier
                                .padding(4.dp)
                                .width(200.dp)
                                .border(1.dp, color = Color(0xFFff4d4d))
                                .clickable { openDetailProducts(item.maSP) },
                        ) {
                            LaunchedEffect(key1 = item.maSP) {
                                delay(10000)
                                productViewModel.fetchImages(item.maSP)
                            }

                            Box(
                                modifier = Modifier
                                    .size(200.dp, 210.dp)
                                    .border(1.dp, color = Color(0xFFff4d4d)),
                                contentAlignment = Alignment.Center
                            ) {
                                // Thay thế bằng hình ảnh thực tế của bạn
                                val productImages = imagesMap[item.maSP].orEmpty()
                                if (productImages.isNotEmpty()) {
                                    val image = productImages.first()
                                    val imageUrl = image.image
                                    val fileName =
                                        imageUrl.substringAfterLast("/").substringBeforeLast(".")
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
                                        Text(text = "Image not found")
                                    }
                                }
                            }

                            val tags = tagMap[item.idtag]
                            if (tags != null) {
                                Text(
                                    text = tags.tagname, // Thay thế bằng tên thẻ thực tế của bạn
                                    modifier = Modifier.padding(8.dp),
                                    style = TextStyle(
                                        fontSize = 12.sp,
                                        fontStyle = FontStyle.Italic,
                                        color = Color.Gray
                                    ),
                                )
                            }
                            val productName = item.tensp
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

@Preview(showBackground = true)
@Composable
fun TestAll() {
    STTCTheme {
        Allow(back = {}, true)
    }
}


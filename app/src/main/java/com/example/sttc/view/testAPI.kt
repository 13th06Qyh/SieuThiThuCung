package com.example.sttc.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.Row
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import coil.decode.ImageSource
import com.bumptech.glide.Glide
import com.example.sttc.R
import com.example.sttc.ui.theme.STTCTheme
import com.example.sttc.view.System.formatNumber
import com.example.sttc.viewmodel.ProductViewModel
import java.io.File

class testAPI : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val productViewModel: ProductViewModel by viewModels()
            STTCTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    test(
                        productViewModel = productViewModel,
                        context = LocalContext.current
                    )
//                   // MyApp(navController = navController)
//                    MyApp()
//                    LoginForm(navController, AuthController())
                }
            }
        }
    }
}
@Composable
fun test(
    productViewModel: ProductViewModel,
    context: Context
){
    val products by productViewModel.products.collectAsState(initial = emptyList())
    val imagesMap by productViewModel.images.collectAsState(initial = emptyMap())
    LaunchedEffect(key1 = Unit) {
        productViewModel.fetchProduct()
//        productViewModel.fetchImages()
    }

    // Kết quả: "10,000"
    // Chia danh sách thành các nhóm có 2 mặt hàng
    val rows = products.chunked(2)

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
//                                .clickable { openDetailProducts() },
                        ) {
                            LaunchedEffect(key1 = item.maSP) {
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
                                    Log.d("test", "FileName: $fileName")
                                    Log.d("test", "FileExtension: $fileExtension")
                                    Log.d("test", "ResourceId: $resourceId")
                                    Log.d("test", "ResourceName1: $a")
                                    Log.d("test", "ResourceName2: $b")
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
//                                Image(
//                                    painter = painterResource(id = productImages[0].image),
//                                    contentDescription = null,
//                                    contentScale = ContentScale.Crop,
//                                    modifier = Modifier.fillMaxSize()
//                                )
                                }
                            }
                            Text(
                                text = item.idtag.toString(), // Thay thế bằng tên thẻ thực tế của bạn
                                modifier = Modifier.padding(8.dp),
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    fontStyle = FontStyle.Italic,
                                    color = Color.Gray
                                ),
                            )
                            Text(
                                text = item.tensp, // Thay thế bằng tên sản phẩm thực tế của bạn
                                modifier = Modifier
                                    .padding(horizontal = 8.dp)
                                    .height(50.dp),
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
@Preview(showBackground = true)
fun DefaultPreview() {
    val context = LocalContext.current
    STTCTheme {
        test(ProductViewModel(), context)
    }
}

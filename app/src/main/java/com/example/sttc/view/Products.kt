//package com.example.sttc.view
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.BorderStroke
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.border
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.aspectRatio
//import androidx.compose.foundation.layout.fillMaxHeight
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Surface
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.geometry.Offset
//import androidx.compose.ui.graphics.Brush
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.Dp
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavController
//import androidx.navigation.compose.rememberNavController
//import coil.ImageLoader
//import coil.compose.rememberAsyncImagePainter
//import coil.decode.GifDecoder
//import coil.request.ImageRequest
//import com.example.sttc.R
//import com.example.sttc.ui.theme.STTCTheme
//
//
//class Products : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            val navController = rememberNavController()
//            STTCTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    ProductScreens(navController)
//                }
//            }
//        }
//    }
//}
//@Composable
//fun ProductScreens(navController: NavController) {
//    Box(
//        modifier = Modifier
//            .fillMaxWidth()
////            .verticalScroll(scrollState)
//
//    ) {
//        Column(
//            modifier = Modifier.fillMaxWidth()
//                .background(
//                    Brush.radialGradient(
//                        colors = listOf(
//                            Color(0xFFffffff),
//                            Color(0xFFffd6cc),
//                            Color(0xFFffffff),
//                            Color(0xFFffd6b3),
//                            Color(0xFFff8566)
//                        ),
//                        radius = 800f,
//                    )
//                ),
////            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            TopIconProducts()
//            RowItems(
//                R.drawable.icon_dog, R.drawable.icon_cat,
//                colors = listOf(Color(0xFFffff99), Color(0xFFccffcc)),
//                onItemClick = { }
//            )
//            RowItems(
//                R.drawable.icon_chim,
//                colors = listOf(Color(0xFFffcccc)),
//                onItemClick = { }
//            )
//            RowItems(
//                R.drawable.icon_ca, R.drawable.icon_hamster,
//                colors = listOf(Color(0xFFb3f0ff), Color(0xFFffd699)),
//                onItemClick = {}
//            )
//        }
//    }
//}
//
//@Composable
//fun TopIconProducts() {
//    val context = LocalContext.current
//    val imageLoader = ImageLoader.Builder(context)
//        .components {
//            add(GifDecoder.Factory())
//        }
//        .build()
//
//    Image(
//        painter = rememberAsyncImagePainter(
//            ImageRequest.Builder(context).data(data = R.drawable.productanimal).apply(block = {
//            }).build(), imageLoader = imageLoader
//        ),
//        contentDescription = "TopIconProductsAnimals",
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(145.dp)
//            .border(1.dp, color = Color(0xFFcc00cc))
//            .background(
//                Brush.radialGradient(
//                    colors = listOf(
//                        Color(0xFF9ED9FF),
//                        Color(0xFFD2F0FF),
//                        Color(0xFFE0EEFF),
//                        Color(0xFFDAEBFF),
//                        Color(0xFF9CD8FF),
//                    ),
//                    radius = 360f
//                )
//            )
//    )
//
////    Image(
////        painter = painterResource(id = R.drawable.productanimal),
////        contentDescription = "TopIconProductsAnimals",
////        modifier = Modifier
////            .fillMaxWidth()
////            .height(130.dp)
////            .background(
////                Brush.radialGradient(
////                    colors = listOf(
////                        Color(0xFF9ED9FF),
////                        Color(0xFFD2F0FF),
////                        Color(0xFFE0EEFF),
////                        Color(0xFFDAEBFF),
////                        Color(0xFF9CD8FF),
////                    ),
////                    radius = 360f
////                )
////            )
////            .border(1.dp, color = Color(0xFFcc00cc))
////    )
//}
//
//@Composable
//fun RowItems(vararg images: Int, colors: List<Color> = listOf(), onItemClick: (Int) -> Unit) {
//    Row(
//        modifier = Modifier.fillMaxWidth()
//            .padding(0.dp, 10.dp, 0.dp, 5.dp),
//        horizontalArrangement = Arrangement.SpaceAround,
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        images.forEachIndexed { index, image ->
//            val color = if (index < colors.size) colors[index] else Color.Transparent
//            BoxItem(image = image, color = color, onItemClick = { onItemClick(image) })
//        }
//    }
//}
//
//@Composable
//fun BoxItem(image: Int, color: Color, onItemClick: () -> Unit) {
//    Box(
//        modifier = Modifier.size(170.dp)
//            .padding(10.dp)
//            .background(color, shape = RoundedCornerShape(10.dp))
//            .border(2.dp, color = Color(0xFF000000), shape = RoundedCornerShape(10.dp))
//            .clickable(onClick = onItemClick),
//        contentAlignment = Alignment.Center
//    ) {
//        Image(
//            painter = painterResource(id = image),
//            contentDescription = null,
//            modifier = Modifier.size(120.dp)
//        )
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun ProductPreview() {
//    STTCTheme {
//        ProductScreens(rememberNavController())
//    }
//}
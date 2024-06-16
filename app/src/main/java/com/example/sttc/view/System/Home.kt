package com.example.sttc.view

import android.content.Context
import androidx.activity.viewModels
import com.example.sttc.view.System.CustomPagerIndicator
import com.example.sttc.view.System.SuggestTodayopen
import com.example.sttc.viewmodel.ProductViewModel
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sttc.R
import com.example.sttc.ui.theme.STTCTheme
import com.example.sttc.view.System.SuggestToday
import com.example.sttc.view.System.formatNumber
import com.example.sttc.viewmodel.BlogsViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    openListProducts: (String) -> Unit,
    openDetailBlogs: (id: Int) -> Unit,
    openDetailProducts: (id: Int) -> Unit,
    productViewModel: ProductViewModel,
    context: Context,
    openBlogs: () -> Unit,
    openProduct: () -> Unit
) {
    val scrollState = rememberScrollState()
    val selectedOption = remember { mutableStateOf("") }
    val selectedAnimal = 0
    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)

    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeroSection()
            Animal(openListProducts)

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.electric),
                    contentDescription = "Electric",
                    tint = Color(0xFF0033cc),
                    modifier = Modifier
                        .padding(0.dp, 12.dp, 3.dp, 0.dp)
                        .size(37.dp)
                )
                Text(
                    text = "BÀI VIẾT MỚI NHẤT",
                    modifier = Modifier.padding(0.dp, 12.dp, 0.dp, 0.dp),
                    style = TextStyle(
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF0033cc)
                    ),
                )

                Text(
                    text = "Xem thêm",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp, 30.dp, 0.dp, 0.dp)
                        .clickable { openBlogs() },
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontStyle = FontStyle.Italic,
                        color = Color(0xFF0033cc),
                        textAlign = TextAlign.End
                    ),
                )
            }
            RecentBlogsSection(openDetailBlogs, BlogsViewModel(), context)

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp, 0.dp, 8.dp, 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.hot),
                    contentDescription = "Hot",
                    tint = Color(0xFFF00000),
                    modifier = Modifier
                        .padding(0.dp, 12.dp, 3.dp, 0.dp)
                        .size(37.dp)
                )
                Text(
                    text = "BÁN CHẠY GẦN ĐÂY",
                    modifier = Modifier.padding(0.dp, 12.dp, 0.dp, 0.dp),
                    style = TextStyle(
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFcc3300)
                    ),
                )

                Text(
                    text = "Xem thêm",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp, 30.dp, 0.dp, 0.dp)
                        .clickable { openProduct() },
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontStyle = FontStyle.Italic,
                        color = Color(0xFFcc3300),
                        textAlign = TextAlign.End
                    ),
                )
            }
            RecentSalesSection(openDetailProducts, productViewModel, context)

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp, 0.dp, 8.dp, 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.light),
                    contentDescription = "Light",
                    tint = Color(0xFFF00000),
                    modifier = Modifier
                        .padding(0.dp, 12.dp, 3.dp, 0.dp)
                        .size(37.dp)
                )
                Text(
                    text = "GỢI Ý HÔM NAY",
                    modifier = Modifier.padding(0.dp, 12.dp, 0.dp, 0.dp),
                    style = TextStyle(
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFcc3300)
                    ),
                )

                Text(
                    text = "Xem thêm",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp, 30.dp, 0.dp, 0.dp)
                        .clickable { openProduct() },
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontStyle = FontStyle.Italic,
                        color = Color(0xFFcc3300),
                        textAlign = TextAlign.End
                    ),
                )
            }

            SuggestToday(
                openDetailProducts,
                productViewModel,
                context,
                selectedOption.value,
                selectedAnimal
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HeroSection() {
    val images = listOf(
        painterResource(id = R.drawable.cm4),
        painterResource(id = R.drawable.cm7),
        painterResource(id = R.drawable.cm12),
        painterResource(id = R.drawable.cm9),
        painterResource(id = R.drawable.cm11),
        painterResource(id = R.drawable.cm13),
    )

    val pagerState = rememberPagerState(pageCount = { images.size })
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(pagerState) {
        while (true) {
            delay(3000L)
            coroutineScope.launch {
                if (pagerState.currentPage < pagerState.pageCount - 1) {
                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                } else {
                    pagerState.animateScrollToPage(0)
                }
            }
        }
    }

    HorizontalPager(
        state = pagerState,
        modifier = Modifier
            .height(235.dp)
            .border(1.dp, color = Color(0xFF000000))
    ) { page ->
        Image(
            painter = images[page],
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .height(200.dp)
        )
    }
}

@Composable
fun Animal(openListProducts: (String) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, color = Color(0xFFff6666))
            .padding(5.dp, 0.dp)
            .background(
                Brush.radialGradient(
                    colors = listOf(
                        Color(0xFF74DEFF),
                        Color(0xFFCDF3FF),
                        Color(0xFFFFEAE0),
                    ),
                    radius = 500f
                )
            )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //hàng animal thứ nhất
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp, 10.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                IconButton(
                    onClick = { openListProducts("dog") },
                    modifier = Modifier
                        .size(70.dp)
                        .border(1.dp, color = Color(0xFFE96B56), shape = CircleShape)
                        .background(Color(0xFFffc299), shape = CircleShape)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.dog),
                        contentDescription = "Dog",
                        modifier = Modifier.size(46.dp)
                    )
                }

                IconButton(
                    onClick = { openListProducts("bird") },
                    modifier = Modifier
                        .size(70.dp)
                        .border(1.dp, color = Color(0xFF005ce6), shape = CircleShape)
                        .background(Color(0xFFb3ecff), shape = CircleShape)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.bird),
                        contentDescription = "Bird",
                        modifier = Modifier.size(46.dp)
                    )
                }

                IconButton(
                    onClick = { openListProducts("cat") },
                    modifier = Modifier
                        .size(70.dp)
                        .border(1.dp, color = Color(0xFFb3b300), shape = CircleShape)
                        .background(Color(0xFFffff1a), shape = CircleShape)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.cat),
                        contentDescription = "Cat",
                        modifier = Modifier.size(46.dp)
                    )
                }
            }

            //hàng animal thứ hai
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                IconButton(
                    onClick = { openListProducts("hamster") },
                    modifier = Modifier
                        .size(70.dp)
                        .border(1.dp, color = Color(0xFF00e600), shape = CircleShape)
                        .background(Color(0xFFccffcc), shape = CircleShape)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.hamster),
                        contentDescription = "Hamster",
                        modifier = Modifier.size(46.dp)
                    )
                }

                IconButton(
                    onClick = { openListProducts("fish") },
                    modifier = Modifier
                        .size(70.dp)
                        .border(1.dp, color = Color(0xFFb300b3), shape = CircleShape)
                        .background(Color(0xFFffccff), shape = CircleShape)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.fish),
                        contentDescription = "Fish",
                        modifier = Modifier.size(46.dp)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RecentBlogsSection(
    openDetailBlogs: (id: Int) -> Unit, blogViewModel: BlogsViewModel,
    context: Context
) {
    val items = listOf(
        R.drawable.cm4,
        R.drawable.cm7,
        R.drawable.cm12,
        R.drawable.cm9,
        R.drawable.cm11,
        R.drawable.cm13
    )
    val blogs by blogViewModel.blogs.collectAsState(initial = emptyList())
    val coroutineScope = rememberCoroutineScope()
    val state = rememberLazyListState()
    val currentPage = remember { mutableStateOf(0) }

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
        items(blogs.take(6).size) { index ->
            val blog = blogs[index]
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = items[index]),
                    contentDescription = null,
                    modifier = Modifier
                        .size(137.dp, 73.dp)
                        .padding(6.dp, 0.dp, 6.dp, 0.dp)
                        .border(1.dp, color = Color(0xFF4d4d4d))
                        .clickable { openDetailBlogs(blog.maBlog) }
                )
                Text(
                    text = if (blog.title.length > 7) blog.title.take(5) + ".." else blog.title, // Thay đổi thành tiêu đề thực tế của bạn
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily.Monospace,
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF000000),
                        textAlign = TextAlign.Center
                    ),

                    )
            }
        }
    }

    CustomPagerIndicator(
        pageCount = items.size,
        currentPage = currentPage.value,
    )
}

@Composable
fun RecentSalesSection(
    openDetailProducts: (id: Int) -> Unit,
    productViewModel: ProductViewModel,
    context: Context
) {
    val products by productViewModel.products.collectAsState(initial = emptyList())
    val imagesMap by productViewModel.images.collectAsState(initial = emptyMap())
    val coroutineScope = rememberCoroutineScope()
    val state = rememberLazyListState()
    val currentPage = remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        delay(10000)
        productViewModel.fetchProduct()
        while (true) {
            delay(3000L)
            coroutineScope.launch {
                val nextPage = if (currentPage.value < 4) {
                    (currentPage.value + 1) % 5
                } else {
                    0
                }
                state.animateScrollToItem(nextPage)
                currentPage.value = nextPage
            }
        }
    }

    LazyRow(state = state) {
        items(products.take(6)) { product ->
            Box(
                modifier = Modifier.clickable { openDetailProducts(product.maSP) }
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
                    modifier = Modifier.clickable { openDetailProducts(product.maSP) },
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    LaunchedEffect(key1 = product.maSP) {
                        delay(10000)
                        productViewModel.fetchImages(product.maSP)
                    }
                    val productImages = imagesMap[product.maSP].orEmpty()
                    if (productImages.isNotEmpty()) {
                        val image = productImages.first()
                        val imageUrl = image.image
                        val fileName =
                            imageUrl.substringAfterLast("/").substringBeforeLast(".")
                        val resourceId = context.resources.getIdentifier(
                            fileName,
                            "drawable",
                            context.packageName
                        )
                        val a = context.resources.getResourceName(resourceId)
                        val b = a.substringAfter('/')
//                                    Log.d("test", "FileName: $fileName")
//                                    Log.d("test", "ResourceId: $resourceId")
//                                    Log.d("test", "ResourceName1: $a")
//                                    Log.d("test", "ResourceName2: $b")
                        if (b == fileName) {
                            Image(
                                painter = painterResource(id = resourceId),
                                contentDescription = "Image",
                                modifier = Modifier
                                    .width(200.dp)
                                    .height(155.dp)
                                    .padding(8.dp)
                                    .clickable { openDetailProducts(product.maSP) }
                            )
                        } else {
                            Image(
                                painter = painterResource(id = R.drawable.rs2),
                                contentDescription = "Image",
                                modifier = Modifier
                                    .width(200.dp)
                                    .height(155.dp)
                                    .padding(8.dp)
                            )
                        }
                    } else {
                        Text(text = "Image not found")
                    }

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

                    val price = product.buyprice.toInt()
                    Text(
                        text = product.buyprice + "Đ",
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
        pageCount = 5,
        currentPage = currentPage.value,
    )


}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val context = LocalContext.current
    STTCTheme {
        HomeScreen(
            openListProducts = {},
            openDetailBlogs = {},
            openDetailProducts = {},
            productViewModel = ProductViewModel(),
            context,
            openBlogs = {},
            openProduct = {}
        )
    }
}
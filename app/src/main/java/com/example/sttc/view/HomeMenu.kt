package com.example.sttc.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.sttc.R
import com.example.sttc.ui.theme.STTCTheme


class HomeMenu : ComponentActivity() {
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
                    MenuScreen()
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen() {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    Icon(painter = painterResource(id = R.drawable.logo), contentDescription = "Logo", modifier = Modifier.size(45.dp), tint = Color(0xFF007acc)
                    )
                },
                modifier = Modifier.background(color = Color(0xFF000000)),
                actions = {
                    val searchQuery = remember { mutableStateOf("") }
                    var query by remember { mutableStateOf("") }
                    var active by remember { mutableStateOf(false) }
                    val searchHistory = listOf("Thức ăn cho chó", "Thạch rau câu", "Đồ hộp", "Cá", "Chuồng")

                    SearchBar(
                        modifier = Modifier.size(240.dp, 50.dp),
                        query = query,
                        onQueryChange = { query = it },
                        onSearch = { newQuery ->
                            println("Performing search on query: $newQuery")
                        },
                        active = active,
                        onActiveChange = { active = it },
                        placeholder = {
                            Text(text = "Tìm kiếm",
                                style = TextStyle(
                                    fontSize = 17.sp),
                                modifier = Modifier.size(240.dp, 100.dp)
                            )
                        },
                        leadingIcon = {
                            Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
                        },
                    ) {
                        searchHistory.takeLast(3).forEach { item ->
                            ListItem(
                                modifier = Modifier.clickable { query = item },
                                headlineContent = { Text(text = item) },
                                leadingContent = {
                                    Icon(
                                        painter = painterResource(R.drawable.history),
                                        contentDescription = "SearchHistory"
                                    )
                                }
                            )
                        }
                    }

                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Filled.ShoppingCart, contentDescription = "Cart", modifier = Modifier.size(33.dp), tint = Color(0xFFE96B56))
                    }

                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Filled.Notifications, contentDescription = "Notice", modifier = Modifier.size(30.dp), tint = Color(0xFFffc266))
                    }

                }
            )
        },
        bottomBar = {

            BottomAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .border(2.dp, color = Color(0xFFF1C4A3)),
                content = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(80.dp)
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(
                                        Color(0xFFDF4F4B),
                                        Color(0xFFF1C4A3),
                                        Color(0xFFF6F5F2)
                                    ),
                                    startY = 350f,
                                    endY = 0f
                                )
                            )
                    ) {
                        IconButton(onClick = {
                            /*TODO*/
                        },
                            modifier = Modifier
                                .weight(1f)
                                .height(80.dp)
                                .border(0.1.dp, Color(0xFFDF4F4B))) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Icon(Icons.Filled.Home, contentDescription = "Home", modifier = Modifier.size(45.dp), tint = Color(0xFF3399ff))
                                Spacer(modifier = Modifier.height(1.dp)) // Thêm khoảng cách ở đây
                                Text("Trang chủ",
                                    style = TextStyle(
                                        fontSize = 15.sp
                                    )
                                ) // Thêm label ở đây
                            }
                        }

                        IconButton(onClick = {
                            /*TODO*/
                        },
                            modifier = Modifier
                                .weight(1f)
                                .height(80.dp)
                                .border(0.1.dp, Color(0xFFDF4F4B))) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Icon(painter = painterResource(id = R.drawable.sanpham), contentDescription = "SanPham", modifier = Modifier.size(35.dp), tint = Color(0xFFF00000))
                                Spacer(modifier = Modifier.height(9.dp)) // Thêm khoảng cách ở đây
                                Text("Sản phẩm",
                                    style = TextStyle(
                                        fontSize = 15.sp
                                    )) // Thêm label ở đây
                            }

                        }

                        IconButton(onClick = {
                            /*TODO*/
                        },
                            modifier = Modifier
                                .weight(1f)
                                .height(80.dp)
                                .border(0.1.dp, Color(0xFFDF4F4B))) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Icon(painter = painterResource(id = R.drawable.blog), contentDescription = "Blog", modifier = Modifier.size(35.dp), tint = Color(0xFFbf00ff))
                                Spacer(modifier = Modifier.height(8.dp)) // Thêm khoảng cách ở đây
                                Text("Bài viết",
                                    style = TextStyle(
                                        fontSize = 16.sp
                                    )) // Thêm label ở đây
                            }

                        }

                        IconButton(onClick = {
                            /*TODO*/
                        },
                            modifier = Modifier
                                .weight(1f)
                                .height(80.dp)
                                .border(0.1.dp, Color(0xFFDF4F4B))) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Icon(Icons.Filled.AccountCircle, contentDescription = "Account", modifier = Modifier.size(40.dp), tint = Color(0xFF000000))
                                Spacer(modifier = Modifier.height(4.dp)) // Thêm khoảng cách ở đây
                                Text("Tài khoản",
                                    style = TextStyle(
                                        fontSize = 16.sp
                                    )) // Thêm label ở đây
                            }

                        }
                    }
                }

            )
        },

        content = {paddingValues ->
            Box(modifier = Modifier.fillMaxSize()
                .padding(paddingValues)
                .background(
                Brush.radialGradient(
                    colors = listOf(
                        Color(0xFFFF8BAC),
                        Color(0xFFF1A3C7),
                        Color(0xFFF6F5F2)),
                    radius = 1000f
                )
            )
                ,
                contentAlignment = Alignment.Center) {
                HomeScreen()
//                HeroSection()
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun MenuScreenPreview() {
    MenuScreen()
}
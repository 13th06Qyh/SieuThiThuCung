//package com.example.sttc.view
//
//
//import android.annotation.SuppressLint
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.fillMaxHeight
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Surface
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavController
//import androidx.navigation.compose.rememberNavController
//import com.example.sttc.R
//import com.example.sttc.ui.theme.STTCTheme
//import androidx.compose.material.*
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.AccountCircle
//import androidx.compose.material.icons.filled.Home
//import androidx.compose.material.icons.filled.Menu
//import androidx.compose.material.icons.filled.Person
//import androidx.compose.material.icons.filled.Search
//import androidx.compose.material.icons.filled.Settings
//import androidx.compose.material.icons.filled.ShoppingCart
//import androidx.compose.material3.BottomAppBar
//import androidx.compose.material3.Divider
//import androidx.compose.material3.DrawerState
//import androidx.compose.material3.DrawerValue
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.ModalNavigationDrawer
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.material3.TextField
//import androidx.compose.material3.TextFieldDefaults
//import androidx.compose.material3.TopAppBar
//import androidx.compose.material3.darkColorScheme
//import androidx.compose.material3.lightColorScheme
//import androidx.compose.material3.rememberDrawerState
//import androidx.compose.runtime.MutableState
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.rememberCoroutineScope
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.text.font.FontFamily
//import androidx.compose.ui.text.font.FontStyle
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import kotlinx.coroutines.launch
//
//
//class Home : ComponentActivity() {
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
//                    HomeScreen()
//                }
//            }
//        }
//    }
//}
//
//@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun HomeScreen() {
//    val drawerState = rememberDrawerState(DrawerValue.Closed)
//    val scope = rememberCoroutineScope()
//    val categories = listOf("Sản phẩm", "Bài viết", "Tin tức")
//    val isLightTheme = remember { mutableStateOf(true) }
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { },
//                actions = {
//                    val searchQuery = remember { mutableStateOf("") }
//                    TextField(
//                        value = searchQuery.value,
//                        onValueChange = { searchQuery.value = it },
//                        placeholder = { Text("Tìm kiếm",
//                            style = TextStyle(
//                                fontSize = 17.sp)) },
//                        colors = TextFieldDefaults.textFieldColors(
//                            disabledTextColor = Color.Gray,
//                            containerColor = Color(0xffffebe6),
//                            focusedIndicatorColor = Color.Green,
//                            unfocusedIndicatorColor = Color(0xffffffff),
//                            disabledIndicatorColor = Color.Gray,
//                            cursorColor = Color.Blue,
//                            errorCursorColor = Color.Red
//                        ),
//                        modifier = Modifier.size(32.dp, 50.dp),
//                        shape = RoundedCornerShape(10.dp),
//                        leadingIcon = {
//                            Icon(imageVector = Icons.Filled.Search, contentDescription = "Search" )}
//                    )
//                    IconButton(onClick = { /*TODO*/ }) {
//                        Icon(Icons.Filled.ShoppingCart, contentDescription = "Cart", modifier = Modifier.size(40.dp))
//                    }
//
//                    ToggleThemeIconButton(isLightTheme, onClick = { toggleTheme(isLightTheme) })
//                }
//            )
//        },
//        bottomBar = {
//
//            BottomAppBar(
//                content = {
//                    Row(
//                        verticalAlignment = Alignment.CenterVertically,
//                        horizontalArrangement = Arrangement.SpaceBetween,
//                        modifier = Modifier.fillMaxSize()
//                    ) {
//                        IconButton(onClick = {
//                            /*TODO*/
//                        },
//                            modifier = Modifier.size(68.dp)) {
//                            Icon(Icons.Filled.Home, contentDescription = "Home", modifier = Modifier.size(50.dp), tint = Color(0xFF000000))
//                        }
//
//                        IconButton(onClick = {
//                            /*TODO*/
//                        },
//                            modifier = Modifier.size(68.dp)) {
//                            Icon(painter = painterResource(id = R.drawable.sanpham), contentDescription = "SanPham", modifier = Modifier.size(40.dp), tint = Color(0xFF000000))
//                        }
//
//                        IconButton(onClick = {
//                            /*TODO*/
//                        },
//                            modifier = Modifier.size(68.dp)) {
//                            Icon(painter = painterResource(id = R.drawable.blog), contentDescription = "Blog", modifier = Modifier.size(40.dp), tint = Color(0xFF000000))
//                        }
//
//                        IconButton(onClick = {
//                            /*TODO*/
//                        },
//                            modifier = Modifier.size(68.dp)) {
//                            Icon(Icons.Filled.AccountCircle, contentDescription = "Account", modifier = Modifier.size(50.dp), tint = Color(0xFF000000))
//                        }
//
////                        IconButton(onClick = {
////                            scope.launch {
////                                if (drawerState.isOpen) {
////                                    drawerState.close()
////                                } else {
////                                    drawerState.open()
////                                }
////                            }
////                        },
////                            modifier = Modifier.size(68.dp)) {
////                            Icon(Icons.Filled.Menu, contentDescription = "Menu", modifier = Modifier.size(50.dp))
////                        }
//                    }
//                }
//
//            )
//        },
//
//        content = {
////            ModalNavigationDrawer(
////                drawerState = drawerState,
////                drawerContent = {
////                    // Sử dụng hàm InnerContent để hiển thị danh sách các danh mục
////                    InnerContent(categories, drawerState)
////                }
////            ) {
//////                InnerContent(categories, drawerState)
////            }
//        }
//    )
//}
//
////@Composable
////fun InnerContent(
////    categories: List<String>,
////    drawerState: DrawerState
////) {
////    val scope = rememberCoroutineScope()
////    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.Center) {
////        categories.forEach { category ->
////            Text(
////                text = category,
////                style = MaterialTheme.typography.bodySmall,
////                modifier = Modifier
////                    .padding(vertical = 8.dp)
////                    .clickable {
////                        // TODO: Handle category selection
////                        scope.launch { drawerState.close() }
////                    }
////            )
////        }
////    }
////}
//@Composable
//fun ToggleThemeIconButton(
//    isLightTheme: MutableState<Boolean>,
//    onClick: () -> Unit
//) {
//    val LightColorPalette = lightColorScheme(
//        primary = Color(0xFF1EB980),
//        secondary = Color(0xFFE1306C)
//    )
//
//    val DarkColorPalette = darkColorScheme(
//        primary = Color(0xFF1EB980),
//        secondary = Color(0xFFE1306C)
//    )
//    val settingsIcon = if (isLightTheme.value) Icons.Filled.Settings else Icons.Filled.Menu
//    val colors = if (isLightTheme.value) LightColorPalette else DarkColorPalette
//    MaterialTheme(colorScheme = colors) {
//        IconButton(onClick = onClick) {
//            Icon(settingsIcon, contentDescription = "Settings")
//        }
//    }
//}
//
//fun toggleTheme(isLightTheme: MutableState<Boolean>) {
//    isLightTheme.value = !isLightTheme.value
//}
//@Preview(showBackground = true)
//@Composable
//fun HomeScreenPreview() {
//    HomeScreen()
//}
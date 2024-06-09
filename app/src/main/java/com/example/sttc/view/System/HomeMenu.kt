package com.example.sttc.view.System

import CommentsViewModel
import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.sttc.R
import com.example.sttc.view.BillCancelScreen
import com.example.sttc.view.BillHistoryScreen
import com.example.sttc.view.BillShipScreen
import com.example.sttc.view.Blogs.BlogsScreens
import com.example.sttc.view.Blogs.DetailCommentScreen
import com.example.sttc.view.Blogs.ListBlogScreen
import com.example.sttc.view.Cart.CartScreen
import com.example.sttc.view.DetailBlogsScreen
import com.example.sttc.view.HomeScreen
import com.example.sttc.view.InforBillHistoryShipScreen
import com.example.sttc.view.InforBillShipScreen
import com.example.sttc.view.PaymentScreen
import com.example.sttc.view.Products.DetailProductsScreen
import com.example.sttc.view.Products.ListProductScreen
import com.example.sttc.view.Products.ProductScreens
import com.example.sttc.view.Users.AccountScreen
import com.example.sttc.viewmodel.AccountViewModel
import com.example.sttc.viewmodel.BillViewModel
import com.example.sttc.viewmodel.BlogsViewModel
import com.example.sttc.viewmodel.ProductViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeMenuScreen(
    accountViewModel: AccountViewModel,
    openLogin : () -> Unit,
    openLogout: () -> Unit
) { //
    val navController = rememberNavController()
    var selectedProductType by remember { mutableStateOf("") }
    var selectBlogType by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize(),
    )
    {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
        ) {
            var query by remember { mutableStateOf("") }
            var active by remember { mutableStateOf(false) }
            val searchHistory = listOf("Thức ăn cho chó", "Thạch rau câu", "Đồ hộp", "Cá", "Chuồng")
            Icon(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier.size(45.dp),
                tint = Color(0xFF007acc)
            )
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
                    Text(
                        text = "Tìm kiếm",
                        style = TextStyle(
                            fontSize = 17.sp
                        ),
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
            IconButton(onClick = { navController.navigate("cart") }) {
                Icon(
                    Icons.Filled.ShoppingCart,
                    contentDescription = "Cart",
                    modifier = Modifier.size(33.dp),
                    tint = Color(0xFFE96B56)
                )
            }
            IconButton(onClick = { navController.navigate("notification") }) {
                Icon(
                    Icons.Filled.Notifications,
                    contentDescription = "Notice",
                    modifier = Modifier.size(30.dp),
                    tint = Color(0xFFffc266)
                )
            }
        }
        Scaffold(
            topBar = {},
            bottomBar = {
                BottomBar(navController = navController)
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding),
            ) {
                NavHost(navController = navController, startDestination = "home") {
                    composable("home") {
                        HomeScreen(
                            openListProducts = { navController.navigate("listProducts") },
                            openDetailProducts = { id -> navController.navigate("detailProducts/$id") },
                            openDetailBlogs = { navController.navigate("DetailBlogs") },
                            productViewModel = ProductViewModel(),
                            context = LocalContext.current
                        )
                    }
                    composable("product") {
                        ProductScreens(
                            openListProducts = { productType ->
                                selectedProductType = productType
                                navController.navigate("listProducts")
                            }
                        )
                    }
                    composable("blogs") {
                        BlogsScreens(
                            openListBlogs = { blogType->
                                selectBlogType = blogType
                            navController.navigate("listBlogs") }
                        )
                    }
                    composable("account") {
                        val context = LocalContext.current
                        val token = accountViewModel.getTokenFromSharedPreferences()
                        Log.d("token", token.toString())
                        if (token == null) {
                            // Nếu không, điều hướng người dùng đến màn hình đăng nhập
                            LaunchedEffect(Unit) {
                                openLogin()
                            }
                        } else {
                            // Nếu có token, hiển thị màn hình tài khoản
                            AccountScreen(
                                openBillShip = { navController.navigate("billShip") },
                                openBillHistory = { navController.navigate("billHistory") },
                                openBillCancel = { navController.navigate("billCancel") },
                                openLogout = {openLogout()},
                                accountViewModel = AccountViewModel(context)
                            )
                        }

                    }
                    // ------------sanPham---------------
                    composable("listProducts") {
                        ListProductScreen(
                            openDetailProducts = { id -> navController.navigate("detailProducts/$id") },
                            productType = selectedProductType,
                            productViewModel = ProductViewModel(),
                            context = LocalContext.current
                        )
                    }

                    composable("detailProducts/{productId}") { backStackEntry ->
                        val productId =
                            backStackEntry.arguments?.getString("productId")?.toIntOrNull() ?: 0
                        DetailProductsScreen(
                            back = { navController.popBackStack() },
                            openCart = { navController.navigate("cart") },
                            openDetailProducts = { id -> navController.navigate("detailProducts/$id") },
                            productViewModel = ProductViewModel(),
                            context = LocalContext.current,
                            productId = productId
                        )
                    }

                    //---------------blogs------------
                    composable("listBlogs") {
                        ListBlogScreen(
                            blogsViewModel = BlogsViewModel() ,
                            blogType = selectBlogType,
                            openDetailBlogs = { id->navController.navigate("detailBlog/$id") },
                            openDetailCmt = { id-> navController.navigate("detailComments/$id") },
                            context = LocalContext.current
                        )
                    }
                    composable("detailBlog/{id}") {backStackEntry->
                        DetailBlogsScreen(
                            back = { navController.popBackStack() },
                            blogsViewModel = BlogsViewModel() ,
                            commentViewModel = CommentsViewModel(),
                            context = LocalContext.current ,
                            blogId = backStackEntry.arguments?.getString("id")?.toIntOrNull() ?: 0
                        )
                    }
                    composable("detailComments/{id}") {backStackEntry->
                        val context = LocalContext.current
                        DetailCommentScreen(
                            back = {navController.popBackStack() },
                            commentViewModel = CommentsViewModel(),
                            accountViewModel = AccountViewModel(context) ,
                            blogId = backStackEntry.arguments?.getString("id")?.toIntOrNull() ?: 0 ,
                        )
                    }
                    // ------------bill---------------
                    composable("billShip") {
                        val context = LocalContext.current
                        BillShipScreen(
                            openDetailBillShip = { id->navController.navigate("detailBillShip/$id") },
                            productViewModel = ProductViewModel(), //sau này thay bằng billviewmodel
                            accountViewModel = AccountViewModel(context),
                            billViewModel = BillViewModel(),
                            context
                        )
                    }
                    composable("detailBillShip/{id}") {backStackEntry->
                        InforBillShipScreen(
                            back = { navController.popBackStack() },
//                            openDetailProducts = { navController.navigate("detailProducts") },
                            productViewModel = ProductViewModel(),//cai nay la moi hien suggesttoday thoi, sau nay them cai billviewmodel nua de hien thi contentbill
                            context = LocalContext.current ,
                            billViewModel = BillViewModel() ,
                            billId = backStackEntry.arguments?.getString("id")?.toIntOrNull() ?: 0 ,
                        )
                    }
                    composable("billHistory") {
                        val context = LocalContext.current
                        BillHistoryScreen(
                            openDetailBillHistory = {id->navController.navigate("detailBillHistory/$id")},
                            productViewModel = ProductViewModel(),
                            accountViewModel = AccountViewModel(context),
                            billViewModel = BillViewModel(),
                            context
                        )
                    }
                    composable("detailBillHistory/{id}") {backStackEntry->
                        val context = LocalContext.current
                        InforBillHistoryShipScreen(
                            back = {navController.popBackStack() },
                            openDetailProducts = { navController.navigate("detailProducts") },
                            productViewModel = ProductViewModel(),//cai nay la moi hien suggesttoday thoi, sau nay them cai billviewmodel nua de hien thi contentbill
                            context = context ,
                            billViewModel = BillViewModel() ,
                            billId = backStackEntry.arguments?.getString("id")?.toIntOrNull() ?: 0 ,
                        )
                    }
                    composable("billCancel") {
                        val context = LocalContext.current
                        BillCancelScreen(
                            billModelView = BillViewModel(),
                            accountViewModel = AccountViewModel(context),
                            productViewModel = ProductViewModel(),
                            context = context

                            )
                    }
                    // ------------cart---------------
                    composable("cart") {
                        CartScreen(
                            back = { navController.popBackStack() }
                        )
                    }
                    composable("payments") {
                        val context = LocalContext.current
                        PaymentScreen(
                            back = { navController.popBackStack() },
                            openOTP = { navController.navigate("otp") },
                            openCard = { navController.navigate("card") },
                            accountViewModel = AccountViewModel(context)
                        )
                    }
                    // ------------otp---------------
                    composable("otp") {
                        val context = LocalContext.current
                        Secret(
                            back = { navController.popBackStack() },
                            openCard = { navController.navigate("card") },
                            accountViewModel = AccountViewModel(context)
                        )
                    }
                    // ------------card---------------
                    composable("card") {
                        val context = LocalContext.current
                        Card(
                            back = { navController.popBackStack() },
                            accountViewModel = AccountViewModel(context)
                        )
                    }
                    // ------------notification---------------
                    composable("notification") {
                        NotificationScreen(back = { navController.popBackStack() })
                    }

                }
            }
        }

    }
}



@Composable
fun BottomBar(navController: NavHostController) {

    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Product,
        BottomBarScreen.Blog,
        BottomBarScreen.Account
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState() // lấy ra màn hình hiện tại
    val currentDestination = navBackStackEntry?.destination //

    NavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .border(2.dp, color = Color(0xFFF1C4A3)),
    ) {
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
            screens.forEachIndexed { _, screen ->
                NavigationBarItem(
                    modifier = Modifier
                        .weight(1f)
                        .height(80.dp)
                        .border(0.1.dp, Color(0xFFDF4F4B)),
                    label = {
                        when (screen) {
                            BottomBarScreen.Home -> {
                                Text(
                                    text = screen.title,
                                    style = TextStyle(fontSize = 15.sp)
                                )
                            }

                            BottomBarScreen.Product -> {
                                Text(
                                    text = screen.title,
                                    style = TextStyle(fontSize = 15.sp)
                                )
                            }

                            BottomBarScreen.Blog -> {
                                Text(
                                    text = screen.title,
                                    style = TextStyle(fontSize = 16.sp)
                                )
                            }

                            BottomBarScreen.Account -> {
                                Text(
                                    text = screen.title,
                                    style = TextStyle(fontSize = 16.sp)
                                )
                            }
                        }
                    },
                    icon = {
                        when (screen) {
                            BottomBarScreen.Home -> {
                                screen.icon?.let {
                                    Icon(
                                        imageVector = it,
                                        contentDescription = screen.title,
                                        modifier = Modifier.size(45.dp),
                                        tint = Color(0xFF3399ff)
                                    )
                                }
                            }

                            BottomBarScreen.Product -> {
                                Icon(
                                    painter = painterResource(id = screen.image!!),
                                    contentDescription = screen.title,
                                    modifier = Modifier.size(35.dp),
                                    tint = Color(0xFFF00000)
                                )
                            }

                            BottomBarScreen.Blog -> {
                                Icon(
                                    painter = painterResource(id = screen.image!!),
                                    contentDescription = screen.title,
                                    modifier = Modifier.size(35.dp),
                                    tint = Color(0xFFbf00ff)
                                )
                            }

                            BottomBarScreen.Account -> {
                                screen.icon?.let {
                                    Icon(
                                        imageVector = it,
                                        contentDescription = screen.title,
                                        modifier = Modifier.size(40.dp),
                                        tint = Color(0xFF000000)
                                    )
                                }
                            }
                        }
                    },
                    selected = currentDestination?.hierarchy?.any {
                        it.route == screen.route
                    } == true,
                    onClick = {
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.findStartDestination().id)
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    }
}

enum class BottomBarScreen(
    val title: String,
    val icon: ImageVector? = null,
    val image: Int? = null,
    val route: String,

    ) {
    Home("Trang chủ", Icons.Filled.Home, null, "home"),
    Product("Sản phẩm", null, R.drawable.sanpham, "product"),
    Blog("Bài viết", null, R.drawable.blog, "blogs"),
    Account("Tài khoản", Icons.Filled.AccountCircle, null, "account"),

}


@Preview(showBackground = true)
@Composable
fun MenuScreenPreview() {
    HomeMenuScreen(accountViewModel = AccountViewModel(LocalContext.current), openLogin = {}, openLogout = {})
}

package com.example.sttc.view.System

import android.content.Context
import android.util.Log
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.sttc.R
import com.example.sttc.model.PayData
import com.example.sttc.model.User
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
import com.example.sttc.view.Users.LoginForm
import com.example.sttc.viewmodel.AccountViewModel
import com.example.sttc.viewmodel.CartViewModel
import com.example.sttc.viewmodel.ProductViewModel
import com.example.sttc.viewmodel.SharedViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeMenuScreen(
    accountViewModel: AccountViewModel,
    openLogin: () -> Unit,
    openLogout: () -> Unit,
    cartViewModel: CartViewModel,
    sharedViewModel: SharedViewModel
) { //
    val navController = rememberNavController()
    var selectedProductType by remember { mutableStateOf("") }
    val count by cartViewModel.count.collectAsState(0)
    val user by accountViewModel.userInfoFlow.collectAsState(null)
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

            IconButton(
                onClick = {
                    user?.let { user ->
                        if (user.id == 0) {
                            openLogin()
                        } else {
                            navController.navigate("cart")
                        }
                    }
                },
                modifier = Modifier.size(52.dp)
            ) {
                Box {
                    Icon(
                        Icons.Filled.ShoppingCart,
                        contentDescription = "Cart",
                        modifier = Modifier.size(33.dp),
                        tint = Color(0xFFE96B56)
                    )
                    if (count > 0) {
                        // Nếu số lượng sản phẩm trong giỏ hàng lớn hơn 0, hiển thị thông báo đếm trên biểu tượng
                        Text(
                            text = count.toString(),
                            color = Color.White,
                            fontSize = 15.sp,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .size(18.dp)
                                .align(Alignment.TopEnd)
                                .offset(2.dp, (-4).dp)
                                .background(Color.Red, shape = CircleShape)
                                .padding(0.dp)
                        )
                    }
                }
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
                val context = LocalContext.current
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
                        BlogsScreens(openListBlogs = { navController.navigate("listBlogs") })
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
                                openLogout = { openLogout() },
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
                            cartViewModel = CartViewModel(context),
                            accountViewModel = AccountViewModel(context),
                            context = LocalContext.current,
                            productId = productId
                        )
                    }

                    //---------------blogs------------
                    composable("listBlogs") {
                        ListBlogScreen(
                            openDetailBlogs = { navController.navigate("DetailBlogs") },
                            openDetailCmt = { navController.navigate("DetailComments") },
                        )
                    }
                    composable("DetailBlogs") {
                        DetailBlogsScreen(
                            back = { navController.popBackStack() },
                        )
                    }
                    composable("DetailComments") {
                        DetailCommentScreen(
                            back = { navController.popBackStack() },
                        )
                    }
                    // ------------bill---------------
                    composable("billShip") {
                        BillShipScreen(
                            openDetailBillShip = { navController.navigate("detailBillShip") },
                            productViewModel = ProductViewModel(), //sau này thay bằng billviewmodel
                            context = LocalContext.current
                        )
                    }
                    composable("detailBillShip") {
                        InforBillShipScreen(
                            back = { navController.popBackStack() },
                            openDetailProducts = { navController.navigate("detailProducts") },
                            productViewModel = ProductViewModel(),//cai nay la moi hien suggesttoday thoi, sau nay them cai billviewmodel nua de hien thi contentbill
                            context = LocalContext.current
                        )
                    }
                    composable("billHistory") {
                        BillHistoryScreen(
                            openDetailBillHistory = { navController.navigate("detailBillHistory") },
                            productViewModel = ProductViewModel(),
                            context = LocalContext.current
                        )
                    }
                    composable("detailBillHistory") {
                        InforBillHistoryShipScreen(
                            back = { navController.popBackStack() },
                            openDetailProducts = { navController.navigate("detailProducts") },
                            productViewModel = ProductViewModel(),//cai nay la moi hien suggesttoday thoi, sau nay them cai billviewmodel nua de hien thi contentbill
                            context = LocalContext.current
                        )
                    }
                    composable("billCancel") {
                        BillCancelScreen()
                    }
                    // ------------cart---------------
                    composable("cart") {
                        CartScreen(
                            back = { navController.popBackStack() },
                            openDetailProducts = { id -> navController.navigate("detailProducts/$id") },
                            openPayment = { selectedProducts ->
                                sharedViewModel.setSelectedProducts(selectedProducts)
                                navController.navigate("payments")
                            },
                            accountViewModel = AccountViewModel(context),
                            cartViewModel = CartViewModel(context),
                            context = context
                        )
                    }
                    // ------------payment---------------
                    composable("payments") {
                        val selectedProducts by sharedViewModel.selectedProducts.collectAsState(emptyList())

                        PaymentScreen(
                            back = { navController.popBackStack() },
                            context = context,
                            openOTP = { navController.navigate("otp") },
                            openCard = { navController.navigate("card") },
                            accountViewModel = AccountViewModel(context),
                            openAccount = { navController.navigate("account") },
                            selectedProducts = selectedProducts,
                            sharedViewModel = SharedViewModel(context)
                        )
                    }
                    // ------------otp---------------
                    composable("otp") {
                        Secret(
                            back = { navController.popBackStack() },
                            accountViewModel = AccountViewModel(context)
                        )
                    }
                    // ------------card---------------
                    composable("card") {
                        Card(
                            back = { navController.popBackStack() },
                            openCart = { navController.navigate("cart") },
                            sharedViewModel = SharedViewModel(context)
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
    HomeMenuScreen(
        accountViewModel = AccountViewModel(LocalContext.current),
        openLogin = {},
        openLogout = {},
        cartViewModel = CartViewModel(LocalContext.current),
        sharedViewModel = SharedViewModel(LocalContext.current)
    )
}

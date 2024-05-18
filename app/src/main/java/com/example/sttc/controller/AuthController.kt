//package com.example.sttc.controller
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.runtime.Composable
//import androidx.navigation.NavController
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.rememberNavController
//import com.example.sttc.model.User
//import com.example.sttc.view.AccountScreen
//import com.example.sttc.view.BillCancelScreen
//import com.example.sttc.view.BillHistoryScreen
//import com.example.sttc.view.BillShipScreen
//import com.example.sttc.view.BlogsScreens
//import com.example.sttc.view.CartScreen
//import com.example.sttc.view.DetailBlogsScreen
//import com.example.sttc.view.DetailCommentScreen
//import com.example.sttc.view.DetailProductsScreen
//import com.example.sttc.view.HomeScreen
//import com.example.sttc.view.InforBillHistoryShipScreen
//import com.example.sttc.view.InforBillShipScreen
//import com.example.sttc.view.ListBlogScreen
//import com.example.sttc.view.ListProductScreen
//import com.example.sttc.view.LoginForm
//import com.example.sttc.view.MenuScreen
//import com.example.sttc.view.PaymentScreen
//import com.example.sttc.view.ProductScreens
//import com.example.sttc.view.SignUpForm
//import com.example.sttc.viewmodel.ProductViewModel
//
//class AuthController(private val userService: User) {
//    fun login(username: String, password: String): Boolean {
//        return userService.login(username, password)
//    }
//
//    fun signup(username: String, password: String): Boolean {
//        return userService.signup(username, password)
//    }
//}
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun MyApp (
//    navController: NavController,
//    productViewModel: ProductViewModel
//) {
//    val navController = rememberNavController()
//    NavHost(navController = navController, startDestination = "menu") {
//        composable("login") { LoginForm(navController = navController) }
//        composable("signup") { SignUpForm(navController = navController) }
//        composable("menu") { MenuScreen(navController = navController) }
//        composable("home") { HomeScreen(
//            navController = navController,
//            productViewModel = productViewModel
//        ) }
//        composable("products") { ProductScreens(navController = navController) }
//        composable("blogs") { BlogsScreens(navController = navController) }
//        composable("account") { AccountScreen(navController = navController) }
//        composable("cart") { CartScreen(navController = navController) }
//        composable("listProduct") { ListProductScreen(navController = navController) }
//        composable("listBlog") { ListBlogScreen(navController = navController) }
//        composable("detailProduct") { DetailProductsScreen(navController = navController) }
//        composable("detailBlog") { DetailBlogsScreen(navController = navController) }
//        composable("detailComment") { DetailCommentScreen(navController = navController) }
////        composable("notificaion") { NotificaionScreen(navController = navController) }
//        composable("billCancel") { BillCancelScreen(navController = navController) }
//        composable("billShip") { BillShipScreen(navController = navController) }
//        composable("billHistory") { BillHistoryScreen(navController = navController) }
//        composable("inforBillHistory") { InforBillHistoryShipScreen(navController = navController) }
//        composable("inforBillShip") { InforBillShipScreen(navController = navController) }
//        composable("payMent") { PaymentScreen(navController = navController) }
//    }
//}
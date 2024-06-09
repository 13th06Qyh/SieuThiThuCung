package com.example.sttc.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sttc.view.Cart.CartScreen
import com.example.sttc.view.Products.DetailProductsScreen
import com.example.sttc.view.System.Card
import com.example.sttc.view.System.HomeMenuScreen
import com.example.sttc.view.System.NotificationScreen
import com.example.sttc.view.System.Secret
import com.example.sttc.view.Users.LoginForm
import com.example.sttc.view.Users.SignUpForm
import com.example.sttc.viewmodel.AccountViewModel
import com.example.sttc.viewmodel.BillViewModel
import com.example.sttc.viewmodel.CartViewModel
import com.example.sttc.viewmodel.ProductViewModel
import com.example.sttc.viewmodel.SharedViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App(accountViewModel = AccountViewModel(LocalContext.current), SharedViewModel(LocalContext.current))
        }
    }
}

@Composable
fun App(
    accountViewModel: AccountViewModel,
    sharedViewModel: SharedViewModel,
) {
    val navController = rememberNavController()
    val context = LocalContext.current
    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginForm(
                navController,
                accountViewModel = AccountViewModel(context),
            )
        }
        composable("signup") {
            SignUpForm(
                navController,
                accountViewModel = AccountViewModel(context),
            )
        }
        composable("homemenu") {
            HomeMenuScreen(
                accountViewModel = AccountViewModel(context),
                openLogin = {
                    navController.navigate("login") {
                        popUpTo("homemenu") { inclusive = true }
                    }
                },
                openLogout = {
                    accountViewModel.logout()
                    navController.navigate("login")
                },
                cartViewModel = CartViewModel(context),
                sharedViewModel = SharedViewModel(context),
                openCart = { navController.navigate("cart") },
                openNotification = { navController.navigate("notification") },
                openPayment = { navController.navigate("payments") },
                openDetailProducts = { id -> navController.navigate("detailProducts/$id") },

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
                productId = productId,
                openPayment = { selectedProducts ->
                    sharedViewModel.setSelectedProducts(selectedProducts)
                    navController.navigate("payments")
                }
            )
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
            val selectedProducts by sharedViewModel.selectedProducts.collectAsState(
                emptyList()
            )

            PaymentScreen(
                back = { navController.popBackStack() },
                context = context,
                openOTP = { navController.navigate("otp") },
                openCard = { navController.navigate("card") },
                accountViewModel = AccountViewModel(context),
                openAccount = { navController.navigate("account") },
                selectedProducts = selectedProducts,
                sharedViewModel = SharedViewModel(context),
                billViewModel = BillViewModel(context),
                cartViewModel = CartViewModel(context)
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
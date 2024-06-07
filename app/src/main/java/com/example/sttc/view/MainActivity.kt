package com.example.sttc.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sttc.view.System.HomeMenuScreen
import com.example.sttc.view.Users.LoginForm
import com.example.sttc.view.Users.SignUpForm
import com.example.sttc.viewmodel.AccountViewModel
import com.example.sttc.viewmodel.CartViewModel
import com.example.sttc.viewmodel.SharedViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App(accountViewModel = AccountViewModel(LocalContext.current))
        }
    }
}

@Composable
fun App(
    accountViewModel: AccountViewModel
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
                sharedViewModel = SharedViewModel(context)

            )
        }

    }
}
package com.example.sttc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sttc.view.HomeScreen
import com.example.sttc.view.LoginScreen
import com.example.sttc.view.SignUpScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}
@Composable
fun App(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login" ){
        composable("login") {
            LoginScreen (
                openHomeMenu = { navController.navigate("home") },
                openSignin = { navController.navigate("signup") }
            )
        }
        composable("signup") {
            SignUpScreen(
                openLogin = { navController.navigate("login") }
            )
        }
        composable("home") {
            HomeScreen(
                openListProducts = { navController.navigate("listProducts") } ,
                openDetailProducts = { navController.navigate("detailProducts") } ,
                openDetailBlogs = { navController.navigate("DetailBlogs") }
            )
        }

    }
}
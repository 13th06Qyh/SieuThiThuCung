package com.example.sttc.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sttc.view.System.HomeMenuScreen
import com.example.sttc.view.Users.LoginForm
import com.example.sttc.view.Users.SignUpForm

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
            LoginForm (navController)
        }
        composable("signup") {
            SignUpForm(navController)
        }
        composable("homemenu") {
            HomeMenuScreen()
        }

    }
}
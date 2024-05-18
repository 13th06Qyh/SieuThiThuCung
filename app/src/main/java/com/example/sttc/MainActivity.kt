package com.example.sttc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sttc.view.HomeMenuScreen
import com.example.sttc.view.LoginForm
import com.example.sttc.view.SignUpForm

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
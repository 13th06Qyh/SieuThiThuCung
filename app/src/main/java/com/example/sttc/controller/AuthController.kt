package com.example.sttc.controller
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sttc.model.User
import com.example.sttc.view.HomeScreen
import com.example.sttc.view.LoginForm
import com.example.sttc.view.MenuScreen
import com.example.sttc.view.SignUp
import com.example.sttc.view.SignUpForm

class AuthController(private val userService: User) {
    fun login(username: String, password: String): Boolean {
        return userService.login(username, password)
    }

    fun signup(username: String, password: String): Boolean {
        return userService.signup(username, password)
    }
}
@Composable
fun MyApp (navController: NavController){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginForm(navController = navController) }
        composable("signup") { SignUpForm(navController = navController) }
        composable("home") { MenuScreen() }}
}
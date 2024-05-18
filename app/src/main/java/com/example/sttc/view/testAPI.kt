package com.example.sttc.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.sttc.ui.theme.STTCTheme
import com.example.sttc.viewmodel.ProductViewModel

class testAPI : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
//            MyApp(navController = navController)
            val productViewModel: ProductViewModel by viewModels()
            STTCTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    test(
                        productViewModel = productViewModel
                    )
//                   // MyApp(navController = navController)
//                    MyApp()
//                    LoginForm(navController, AuthController())
                }
            }
        }
    }
}
@Composable
fun test(
    productViewModel: ProductViewModel
){
    val products by productViewModel.products.collectAsState(initial = emptyList())
    LaunchedEffect(key1 = Unit) {
        productViewModel.fetchProduct()
    }

    LazyColumn{
        items(products){product->
            Text(text = product.tensp)
        }
    }
}
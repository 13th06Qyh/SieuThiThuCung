package com.example.sttc.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sttc.R
import com.example.sttc.ui.theme.STTCTheme

class Account : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            STTCTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AccountScreen()
                }
            }
        }
    }
}

@Composable
fun AccountScreen() {
//    val scrollState = rememberScrollState()
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .verticalScroll(scrollState)
//
//    ) {
//        Column (
//            modifier = Modifier.fillMaxSize(),
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ){
//
//        }
//    }
    StatusBill()
}

@Composable
fun TopIcon() {
    Image(
        painter = painterResource(id = R.drawable.topiconaccount),
        contentDescription = "TopIconAccount",
        modifier = Modifier.fillMaxWidth()
            .height(225.dp)
    )
}

@Composable
fun StatusBill() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, color = Color(0xFFff6666))
            .padding(5.dp, 0.dp)
            .background(
                Brush.radialGradient(
                    colors = listOf(
                        Color(0xFF74DEFF),
                        Color(0xFFCDF3FF),
                        Color(0xFFFFEAE0),
                    ),
                    radius = 500f
                )
            )
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp, 10.dp)
                .height(160.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            IconButton(onClick = { /*TODO*/ },
                modifier = Modifier
                    .size(70.dp)
                    .border(1.dp, color = Color(0xFFE96B56), shape = CircleShape)
                    .background(Color(0xFFffc299), shape = CircleShape)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.cancel),
                    contentDescription = "Dog",
                    modifier = Modifier.size(46.dp))
            }

            IconButton(onClick = { /*TODO*/ },
                modifier = Modifier
                    .size(70.dp)
                    .border(1.dp, color = Color(0xFF005ce6), shape = CircleShape)
                    .background(Color(0xFFb3ecff), shape = CircleShape)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.history),
                    contentDescription = "Bird",
                    modifier = Modifier.size(46.dp))
            }

            IconButton(onClick = { /*TODO*/ },
                modifier = Modifier
                    .size(70.dp)
                    .border(1.dp, color = Color(0xFFb3b300), shape = CircleShape)
                    .background(Color(0xFFffff1a), shape = CircleShape)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ship),
                    contentDescription = "Cat",
                    modifier = Modifier.size(46.dp))
            }
        }
    }
}

@Composable
fun InfoAccount() {

}

@Preview(showBackground = true)
@Composable
fun AccountScreenPreview() {
    STTCTheme {
        AccountScreen()
    }
}
package com.example.sttc.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.sttc.R
import com.example.sttc.ui.theme.STTCTheme
import java.text.NumberFormat
import java.util.Locale

class Notification : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            STTCTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                }
            }
        }
    }
}
@Composable
fun NotificationScreen() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFffddcc)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_back),
                    contentDescription = "arrow back",
                    tint = Color.Black,
                    modifier = Modifier.size(37.dp)
                )
            }
            Text(
                text = "Thông báo",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
        val items = listOf(
            ItemsNotification(
                id = 1,
                content = "Thông báo đến từ Thư  ",
                time = "20/10/2021"
            ),
            ItemsNotification(
                id = 2,
                content = "Thông báo đến từ Quỳnh",
                time = "20/10/2021"
            ),
            ItemsNotification(
                id = 3,
                content = "Thông báo đến từ Huy",
                time = "20/10/2021"
            ),
            ItemsNotification(
                id = 4,
                content = "Thông báo đến từ Thư",
                time = "20/10/2021"
            ),
            ItemsNotification(
                id = 5,
                content = "Thông báo đến từ Quỳnh",
                time = "20/10/2021"
            ),
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            items(items = items, key = { it.id }) { task ->
                Surface(
                    color = Color.White,
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()

                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp)

                    ) {
                        Surface(
                            modifier = Modifier
                                .size(40.dp)
                                .border(
                                    BorderStroke(2.dp, Color.Black),
                                    shape = CircleShape
                                )
                                .clip(shape = CircleShape)
                        ) {

                            Image(
                                painter = painterResource(id = R.drawable.icon_notifications),
                                contentDescription = "thongbao",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(30.dp)
                                    .clip(CircleShape) ,
                                        colorFilter = ColorFilter.tint(Color.Black)
                            )
                        }
                        Column(
                            modifier = Modifier
                                .padding(start = 10.dp)
                                .fillMaxWidth()
                        ) {

                            Text(
                                text = task.content,
                                color = Color.Black
                            )
                            Text(
                                text = task.time,
                                color = Color.Black,
                            )

                        }
                        Spacer(modifier =Modifier.height(50.dp))

                    }
                    HorizontalDivider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Gray)
                    )
                }
            }
        }
    }
}

data class ItemsNotification(
    val id: Int,
    val content: String,
    val time: String
)


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NotificationPreview() {
    STTCTheme {
        NotificationScreen()
    }
}


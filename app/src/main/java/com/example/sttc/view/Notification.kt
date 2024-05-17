package com.example.sttc.view

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sttc.R
import com.example.sttc.ui.theme.STTCTheme

@Composable
fun NotificationScreen() {
    Column(
        modifier = Modifier.fillMaxSize()
            .background(Color(0xFFffe6cc))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFffd7b3)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween

        ) {
            IconButton(
                onClick = { /*TODO*/ },
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_back),
                    contentDescription = "arrow back",
                    tint = Color(0xFF994a00),
                    modifier = Modifier.size(35.dp)
                )
            }

            Row(
                modifier = Modifier
                    .padding(start = 60.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ){
                Icon(
                    Icons.Filled.Notifications,
                    contentDescription = "Notifications",
                    tint = Color(0xFFcc6300),
                    modifier = Modifier.size(33.dp)
                )
                Text(
                    text = "Thông báo",
                    style = TextStyle(
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF994a00)
                    ),
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 5.dp)
                )
            }

        }
        val items = listOf(
            ItemsNotification(
                id = 1,
                content = "Bạn có thông báo mới 0",
                time = "20/10/2021"
            ),
            ItemsNotification(
                id = 2,
                content = "Bạn có thông báo mới 1",
                time = "21/10/2021"
            ),
            ItemsNotification(
                id = 3,
                content = "Bạn có thông báo mới 2",
                time = "23/10/2021"
            ),
            ItemsNotification(
                id = 4,
                content = "Bạn có thông báo mới 3",
                time = "27/10/2021"
            ),
            ItemsNotification(
                id = 5,
                content = "Bạn có thông báo mới 4",
                time = "20/12/2021"
            ),
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color(0xFFfff2e6))
        ) {
            items(items = items, key = { it.id }) { task ->
                Surface(
                    color = Color.White,
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)

                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            modifier = Modifier
                                .size(27.dp)
                                .border(
                                    BorderStroke(1.dp, Color(0xFFff9933)),
                                    shape = CircleShape
                                )
                                .clip(shape = CircleShape)
                        ) {

                            Image(
                                Icons.Filled.Notifications,
                                contentDescription = "Notifications",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .clip(CircleShape) ,
                                        colorFilter = ColorFilter.tint(Color(0xFFff9933))
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
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    fontStyle = FontStyle.Italic
                                )
                            )

                        }
                        Spacer(modifier =Modifier.height(50.dp))

                    }

                }
                HorizontalDivider(thickness = 1.dp, color = Color(0xFFff9933))

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotificationPreview() {
    STTCTheme {
        NotificationScreen()
    }
}


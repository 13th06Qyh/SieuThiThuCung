package com.example.sttc.view.System

import android.util.Log
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
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
import com.example.sttc.viewmodel.AccountViewModel
import com.example.sttc.viewmodel.BillViewModel
import com.example.sttc.viewmodel.NotificationViewModel

@Composable
fun NotificationScreen(
    back: () -> Unit,
    notificationViewModel: NotificationViewModel,
    accountViewModel: AccountViewModel
) {
    val noticesResult by notificationViewModel.notice.collectAsState(emptyList())
    val userState by accountViewModel.userInfoFlow.collectAsState(null)
    val filteredNotices = noticesResult.filter { it.userid == userState?.id }
    
    Log.d("NotificationScreen", "noticesResult: $noticesResult")
    Log.d("NotificationScreen", "Filtered notices: $filteredNotices")
    Column(
        modifier = Modifier
            .fillMaxSize()
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
                onClick = { back() },
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
                Row(
                    modifier = Modifier.fillMaxWidth()
                        .padding(start = 5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(
                        text = "Thông báo",
                        style = TextStyle(
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF994a00)
                        ),
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start
                    )
                    IconButton(
                        onClick = {
                            userState?.let {
                                notificationViewModel.deleteNotice(it.id)
                            }
                        },
                        modifier = Modifier
                            .height(30.dp)
                            .border(1.dp, Color.Gray)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.trash3),
                            contentDescription = "Delete",
                            tint = Color.Red,
                            modifier = Modifier.size(25.dp)
                        )
                    }
                }
            }

        }

        if (filteredNotices.isNotEmpty()){
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(Color(0xFFfff2e6))
            ) {
                items(items = filteredNotices) { notice ->
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
                                        .clip(CircleShape),
                                    colorFilter = ColorFilter.tint(Color(0xFFff9933))
                                )
                            }
                            Column(
                                modifier = Modifier
                                    .padding(start = 10.dp)
                                    .fillMaxWidth()
                            ) {

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ){
                                    Text(
                                        text = notice.message,
                                        color = Color.Black
                                    )
                                    Text(
                                        text = formatTime(notice.date),
                                        color = Color.Black,
                                        style = TextStyle(
                                            fontSize = 12.sp,
                                            fontStyle = FontStyle.Italic
                                        ),
                                        modifier = Modifier.padding(top = 5.dp, end = 5.dp)
                                    )
                                }

                                Text(
                                    text = formatDate(notice.date),
                                    color = Color.Black,
                                    style = TextStyle(
                                        fontSize = 12.sp,
                                        fontStyle = FontStyle.Italic
                                    )
                                )

                            }
                            Spacer(modifier = Modifier.height(50.dp))

                        }

                    }
                    HorizontalDivider(thickness = 1.dp, color = Color(0xFFff9933))

                }
            }
        }else{
            val items = listOf(
                ItemsNotification(
                    id = 1,
                    content = "Chúc bạn một ngày tốt lành",
                    time = "15/06/2024"
                ),
                ItemsNotification(
                    id = 2,
                    content = "Bạn đã thay đổi thông tin cá nhân",
                    time = "15/06/2024"
                ),
                ItemsNotification(
                    id = 3,
                    content = "Thêm vào giỏ hàng thành công",
                    time = "14/06/2024"
                ),
                ItemsNotification(
                    id = 4,
                    content = "Thêm vào giỏ hàng thành công",
                    time = "14/06/2024"
                ),
                ItemsNotification(
                    id = 5,
                    content = "Thêm vào giỏ hàng thành công",
                    time = "14/06/2024"
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

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ){
                                    Text(
                                        text = task.content,
                                        color = Color.Black
                                    )
                                    Text(
                                        text = "13:27",
                                        color = Color.Black,
                                        style = TextStyle(
                                            fontSize = 12.sp,
                                            fontStyle = FontStyle.Italic
                                        ),
                                        modifier = Modifier.padding(top = 5.dp, end = 5.dp)
                                    )
                                }
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
}

@Preview(showBackground = true)
@Composable
fun NotificationPreview() {
    STTCTheme {
        NotificationScreen(
            back = {},
            notificationViewModel = NotificationViewModel(LocalContext.current),
            accountViewModel = AccountViewModel(LocalContext.current)
        )
    }
}


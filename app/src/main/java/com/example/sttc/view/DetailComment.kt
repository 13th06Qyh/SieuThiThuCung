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
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sttc.R
import com.example.sttc.ui.theme.STTCTheme

class DetailComment : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DetailCommentScreen()
        }
    }
}
@Composable
fun DetailCommentScreen() {
    val itemsCmt = listOf(
        ItemsCmt(1, "Nguyen Anh Thu", "Nội dung bài viết 1"),
        ItemsCmt(2, "Pham Nhu Quynh", "Nội dung bài viết 2"),
        ItemsCmt(3, "Nguyen Van A", "Nội dung bài viết 3"),
        ItemsCmt(4, "Pham Nhu Quynh", "Nội dung bài viết 4"),
        ItemsCmt(5, "Pham Nhu Quynh", "Nội dung bài viết 5"),
    )
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
                .height(75.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically

        ) {
            Text(
                text = "Bình Luận Chi Tiết",
                style = TextStyle(
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                ),
                fontWeight = FontWeight.Bold
            )
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
        ) {
            items(items = itemsCmt, key = { it.id }) { task ->
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
                                painter = painterResource(R.drawable.user),
                                contentDescription = "avatar",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(30.dp)
                                    .clip(CircleShape)
                            )
                        }
                        Column(
                            modifier = Modifier
                                .padding(start = 4.dp)
                                .fillMaxWidth()
                                .background(Color(0xFFd9d9d9))
                                .border(
                                    width = 2.dp,
                                    color = Color.Black,
                                    shape = RoundedCornerShape(8.dp)
                                )
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .background(Color(0xFFf2f2f2))
                                    .padding(horizontal = 8.dp, vertical = 1.dp)
                            ) {
                                Text(
                                    text = task.nameUser,
                                    style = TextStyle(fontSize = 18.sp),
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.weight(1f)
                                )
                                IconButton(onClick = { /*TODO*/ }) {
                                    Icon(
                                        Icons.Default.Delete,
                                        contentDescription = "",
                                        tint = Color.Red
                                    )
                                }
                                IconButton(onClick = { /*TODO*/ }) {
                                    Icon(
                                        Icons.Default.Create,
                                        contentDescription = "",
                                        tint = Color.Black
                                    )
                                }
                            }
                            Text(
                                text = task.cmt,
                                fontSize = 14.sp,
                                modifier = Modifier.padding(8.dp)
                            )
                        }

                    }
                }
                Spacer(modifier = Modifier.height(30.dp))
            }
        }
    }
}


data class ItemsCmt(
    val id: Int,
    val nameUser: String,
    val cmt: String,
)

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DetailCommentPreview() {
    STTCTheme {
        DetailCommentScreen()
    }
}


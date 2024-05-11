package com.example.sttc.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sttc.R
import com.example.sttc.ui.theme.STTCTheme

class Blog : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BaiVietScreen()
        }
    }
}

@Composable
fun BaiVietScreen() {

    val itemsBV = listOf(
        ItemsBaiViet(1, "Một số đặc điểm nổi bậc của loài mèo Ba Tư", "Nội dung bài viết 1"),
        ItemsBaiViet(2, "Bài viết 2", "Nội dung bài viết 2"),
        ItemsBaiViet(3, "Bài viết 3", "Nội dung bài viết 3"),
        ItemsBaiViet(4, "Bài viết 4", "Nội dung bài viết 4"),
        ItemsBaiViet(5, "Bài viết 5", "Nội dung bài viết 5"),
        ItemsBaiViet(6, "Bài viết 6", "Nội dung bài viết 6")
    )

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {
        items(items = itemsBV, key = { it.id }) { task ->
            Column {
                Surface(
                    color = Color.White,
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = 1.dp,
                            color = Color(0xFFe6e6e6),
                            shape = RoundedCornerShape(1.dp)
                        )
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Avatar()
                        Column(
                            modifier = Modifier
                                .background(Color(0xFFfff2e6))
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = task.tieude,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = task.noidung,
                                fontSize = 14.sp
                            )
                        }

                    }
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),

            ) {
                Button(
                    modifier = Modifier
                        .weight(1f) ,
                    shape = RectangleShape,
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                    )
                ) {
                    Icon(
                        Icons.Default.ThumbUp,
                        contentDescription = "",
                        tint = Color.Blue
                    )
                    Spacer(modifier = Modifier.width(7.dp))
                    Text(text = "Thích", color = Color.Gray)
                }
                Button(
                    shape = RectangleShape,
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                    )
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.cmt),
                        contentDescription = "",
                        tint = Color(0xFF8A8686),
                    )
                    Spacer(modifier = Modifier.width(7.dp))
                    Text(text = "Bình Luận", color = Color.Gray)
                }
                Button(
                    shape = RectangleShape,
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                    )
                ) {
                    Icon(
                        Icons.Default.Menu,
                        contentDescription = "",
                        tint = Color.Red
                    )
                    Spacer(modifier = Modifier.width(7.dp))
                    Text(text = "Chi Tiết", color = Color.Gray)
                }
            }

        }
    }
}

@Composable
fun Avatar() {
    Row(
        verticalAlignment = Alignment.CenterVertically // Center items vertically
    ) {
        Surface(
            modifier = Modifier
                .size(40.dp)
                .border(BorderStroke(2.dp, Color.Black), shape = CircleShape)
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
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = "ADMIN",
            style = TextStyle(fontSize = 18.sp),
            fontWeight = FontWeight.Bold
        )
    }
}

data class ItemsBaiViet(
    val id: Int,
    val tieude: String,
    val noidung: String
)


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BaiVietPreview() {
    STTCTheme {
        BaiVietScreen()
    }
}
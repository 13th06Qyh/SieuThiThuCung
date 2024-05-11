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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sttc.R
import com.example.sttc.ui.theme.STTCTheme

class DetailBlog : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DetailBlogScreen()
        }
    }
}

@Composable
fun DetailBlogScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        NavagationTop()
        Blogs()
        HorizontalDivider( color = Color(0xFFf2f2f2), thickness = 50.dp)
        titleCmt()
        showCmt()

    }

}

@Composable
fun NavagationTop() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFffddcc)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(
            onClick = { /*TODO*/ },
        ) {
            Icon(
                painter = painterResource(id = R.drawable.arrow_back),
                contentDescription = "arrow back",
                tint = Color.Black,
                modifier = Modifier.size(37.dp)
            )
        }
        Text(
            text = "Chi Tiết Bài Viết ",
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun Blogs() {

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
            modifier = Modifier.fillMaxWidth()
        ) {
            Avatar()
            Column(
                modifier = Modifier
                    .background(Color(0xFFf0f0f5))
                    .fillMaxWidth(),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painterResource(id = R.drawable.blogmeo1),
                        contentDescription = null,
                    )
                }

                Text(
                    text = "Một số đặc điểm nổi bậc của loài mèo Ba Tư",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Nội dung bài viết 1",
                    fontSize = 14.sp
                )
            }

        }
    }

}

@Composable
fun titleCmt(){
    Row(

    ){
        Icon(
            painter = painterResource(id = R.drawable.cmt),
            contentDescription = "",
            tint = Color(0xFF8A8686),
        )
        Text(
            text = "Bình luận",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp ,
            modifier = Modifier.padding(start = 10.dp)
        )
    }


}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun showCmt(){
    //dialog
    var openDialogDelete by remember { mutableStateOf(false) }
    var openDialogBuild by remember { mutableStateOf(false) }

    val itemsCmt = listOf(
        ItemsCmt(1, "Nguyen Anh Thu", "Nội dung bài viết 1"),
        ItemsCmt(2, "Pham Nhu Quynh", "Nội dung bài viết 2"),
        ItemsCmt(3, "Nguyen Van A", "Nội dung bài viết 3"),
        ItemsCmt(4, "Pham Nhu Quynh", "Nội dung bài viết 4"),
        ItemsCmt(5, "Pham Nhu Quynh", "Nội dung bài viết 5"),
        ItemsCmt(6, "Nguyen Anh Thu", "Nội dung bài viết 5"),
    )
    var selectedTaskCmt by remember { mutableStateOf("") }
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
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
                            .padding(3.dp)

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
                                    .height(30.dp)
                            ) {
                                Text(
                                    text = task.nameUser,
                                    style = TextStyle(fontSize = 18.sp),
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.weight(1f)
                                )
                                IconButton(onClick = { openDialogDelete = true }) {
                                    Icon(
                                        Icons.Default.Delete,
                                        contentDescription = "",
                                        tint = Color.Red
                                    )
                                }
                                IconButton(onClick = {
                                    openDialogBuild = true
                                    selectedTaskCmt = task.cmt
                                }) {
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
                if (openDialogDelete) {
                    AlertDialog(
                        onDismissRequest = { openDialogDelete = false },
                        title = {
                            Text(
                                "Bạn có chắc chắn muốn xóa bình luận này không?",
                                style = TextStyle(
                                    textAlign = TextAlign.Center,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 23.sp,
                                ),
                                modifier = Modifier.fillMaxWidth()
                            )
                        },
                        confirmButton = {
                            TextButton(
                                onClick = {
                                    openDialogDelete = false
                                }
                            ) {
                                Text("Xác nhận")
                            }
                        },
                        dismissButton = {
                            TextButton(
                                onClick = {
                                    openDialogDelete = false
                                }
                            ) {
                                Text("Hủy")
                            }
                        }
                    )
                }
                if (openDialogBuild) {
                    AlertDialog(
                        onDismissRequest = { openDialogBuild = false },
                        title = {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            ) {
                                Text(
                                    "Chỉnh sửa bình luận",
                                    style = TextStyle(
                                        textAlign = TextAlign.Center,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 23.sp,
                                        color = Color(0xFF6200EE) // Màu sắc của tiêu đề
                                    )
                                )

                                OutlinedTextField(
                                    value = selectedTaskCmt,
                                    onValueChange = { newText -> selectedTaskCmt = newText },
                                    modifier = Modifier.padding(top = 16.dp), // Thêm padding cho TextField
                                    colors = TextFieldDefaults.outlinedTextFieldColors(
                                        focusedBorderColor = Color(0xFF6200EE), // Màu viền khi TextField được focus
                                        unfocusedBorderColor = Color.Gray // Màu viền khi TextField không được focus
                                    )
                                )
                            }
                        },
                        confirmButton = {
                            TextButton(
                                onClick = {
                                    // Thực hiện hành động xác nhận
                                    // Ví dụ: chỉnh sửa bình luận
                                    openDialogBuild = false
                                },
                                colors = ButtonDefaults.textButtonColors(
                                    Color(0xFF6200EE), // Màu nền của nút
                                    Color.White // Màu chữ của nút
                                )
                            ) {
                                Text("Xác nhận")
                            }
                        },
                        dismissButton = {
                            TextButton(
                                onClick = {
                                    openDialogBuild = false
                                },
                                colors = ButtonDefaults.textButtonColors(
                                    Color.Gray, // Màu nền của nút
                                    Color.White // Màu chữ của nút
                                )
                            ) {
                                Text("Hủy")
                            }
                        }
                    )

                }
            }
        }
    }

}
@Preview(showBackground = true)
@Composable
fun DetailBlogPreview() {
    STTCTheme {
        DetailBlogScreen()
    }
}

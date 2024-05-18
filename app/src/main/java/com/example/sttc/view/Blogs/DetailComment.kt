package com.example.sttc.view.Blogs

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.sttc.R
import com.example.sttc.ui.theme.STTCTheme
import com.example.sttc.view.ItemsCmt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailCommentScreen(back: () -> Unit) {
    //dialog
    var openDialogDelete by remember { mutableStateOf(false) }
    var openDialogBuild by remember { mutableStateOf(false) }

    val itemsCmt = listOf(
        ItemsCmt(1, "Nguyen Anh Thu", "Nội dung binh luan 1"),
        ItemsCmt(2, "Pham Nhu Quynh", "Nội dung binh luan 2"),
        ItemsCmt(3, "Nguyen Van A", "Nội dung binh luan 3"),
        ItemsCmt(4, "Pham Nhu Quynh", "Nội dung binh luan 4"),
        ItemsCmt(5, "Pham Nhu Quynh", "Nội dung binh luan 5"),
        ItemsCmt(6, "Nguyen Anh Thu", "Nội dung binh luan 5"),
    )
    var selectedTaskCmt by remember { mutableStateOf("") }
    var newCmt by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFf2f2f2)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween

        ) {
            IconButton(
                onClick = { back() },
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_back),
                    contentDescription = "arrow back",
                    tint = Color.Black,
                    modifier = Modifier.size(35.dp)
                )
            }
            Text(
                text = "Bình Luận",
                style = TextStyle(
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                ),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
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
                            .padding(5.dp)
                    ) {

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color(0xFFd9d9d9))
                                .border(
                                    width = 2.dp,
                                    color = Color.Black,
                                    shape = RoundedCornerShape(8.dp)
                                )
                        ) {

                            Column(
                                modifier = Modifier
                                    .padding(start = 4.dp)
                                    .fillMaxWidth()
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(Color(0xFFd9d9d9))
                                        .padding(horizontal = 8.dp, vertical = 1.dp)
                                ) {
                                    Surface(
                                        modifier = Modifier
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
                                    Text(
                                        text = task.nameUser,
                                        style = TextStyle(fontSize = 18.sp),
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier
                                            .weight(1f)
                                            .padding(start = 5.dp)
                                    )

                                    Text(text = "1 giờ trước", style = TextStyle(fontSize = 14.sp))

                                    Row(
                                        modifier = Modifier,
                                        horizontalArrangement = Arrangement.End,
                                    ) {
                                        IconButton(onClick = { openDialogDelete = true }) {
                                            Icon(
                                                Icons.Default.Delete,
                                                contentDescription = "DeleteCmt",
                                                tint = Color.Gray,
                                                modifier = Modifier.padding(end = 5.dp)
                                            )
                                        }
                                        IconButton(onClick = {
                                            openDialogBuild = true
                                            selectedTaskCmt = task.cmt
                                        }) {
                                            Icon(
                                                Icons.Default.Create,
                                                contentDescription = "EditCmt",
                                                tint = Color.Gray
                                            )
                                        }
                                    }
                                }
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(Color(0xFFf1f4fe))
                                        .padding(horizontal = 8.dp, vertical = 1.dp)
                                ) {
                                    Text(
                                        text = task.cmt,
                                        style = TextStyle(fontSize = 18.sp),
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier
                                            .weight(1f)
                                            .padding(top = 10.dp, bottom = 25.dp)
                                    )
                                }
                            }

                        }
                    }
                    if (openDialogDelete) {
                        AlertDialog(
                            containerColor = Color(0xFFfffff5),
                            onDismissRequest = { openDialogDelete = false },
                            title = {
                                Text(
                                    "Bạn chắc chắn muốn xóa bình luận?",
                                    style = TextStyle(
                                        textAlign = TextAlign.Center,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 16.sp,
                                    ),
                                    modifier = Modifier.fillMaxWidth()
                                )
                            },
                            confirmButton = {
                                TextButton(
                                    onClick = {
                                        openDialogDelete = false
                                    },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color(0xFFFFA483), // Màu nền của nút
                                        contentColor = Color.Black, // Màu chữ của nút
                                    ),

                                    border = BorderStroke(1.dp, Color(0xFF8B2701)),
                                ) {
                                    Text(
                                        "Xác nhận",
                                        style = TextStyle(
                                            fontWeight = FontWeight.Bold
                                        )
                                    )
                                }
                            },
                            dismissButton = {
                                TextButton(
                                    onClick = {
                                        openDialogDelete = false
                                    },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color(0xFFA2FFAB), // Màu nền của nút
                                        contentColor = Color.Black, // Màu chữ của nút
                                    ),

                                    border = BorderStroke(1.dp, Color(0xFF018B0F)),
                                ) {
                                    Text(
                                        "Hủy",
                                        style = TextStyle(
                                            fontWeight = FontWeight.Bold
                                        )
                                    )
                                }
                            }
                        )
                    }
                    if (openDialogBuild) {
                        AlertDialog(
                            containerColor = Color(0xFFcce6ff),
                            onDismissRequest = { openDialogBuild = false },
                            title = {
                                Text(
                                    "Chỉnh sửa bình luận",
                                    style = TextStyle(
                                        textAlign = TextAlign.Center,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 23.sp,
                                    ),
                                    modifier = Modifier.fillMaxWidth()
                                )
                            },
                            text = {
                                TextField(
                                    value = newCmt,
                                    onValueChange = { newCmt = it },
                                    placeholder = { Text("Nhập bình luận mới vào đây") },
                                    colors = TextFieldDefaults.textFieldColors(
                                        containerColor = Color(0xffe6ffff),
                                        unfocusedIndicatorColor = Color(0xff0000ff),
                                    ),
                                    modifier = Modifier.height(100.dp)
                                )
                            },
                            confirmButton = {
                                Button(
                                    onClick = {
                                        itemsCmt[0].cmt = newCmt
                                        openDialogBuild = false
                                    },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color(0xFFccffff), // Màu nền của nút
                                        contentColor = Color.Black, // Màu chữ của nút
                                    ),

                                    border = BorderStroke(1.dp, Color(0xFF0000ff)),
                                ) {
                                    Text(
                                        "Lưu",
                                        style = TextStyle(
                                            fontWeight = FontWeight.Bold
                                        )
                                    )
                                }
                            },
                            dismissButton = {
                                Button(
                                    onClick = { openDialogBuild = false },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color(0xFFFFA483), // Màu nền của nút
                                        contentColor = Color.Black, // Màu chữ của nút
                                    ),

                                    border = BorderStroke(1.dp, Color(0xFF8B2701)),
                                ) {
                                    Text(
                                        "Hủy",
                                        style = TextStyle(
                                            fontWeight = FontWeight.Bold
                                        )
                                    )
                                }
                            }
                        )

                    }
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            val comment = remember { mutableStateOf("") }
            OutlinedTextField(
                value = comment.value,
                onValueChange = { comment.value = it },
                placeholder = {
                    Text(
                        "Nhập bình luận của bạn tại đây",
                        style = TextStyle(
                            fontSize = 18.sp,
                        ),
                        modifier = Modifier
                            .padding(10.dp, 0.dp, 10.dp, 0.dp)
                    )
                },
                shape = RoundedCornerShape(25.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
                    .border(
                        3.dp,
                        Color(0xFF4d4dff),
                        RoundedCornerShape(25.dp)
                    ), // Thêm dòng này
                singleLine = false,
                trailingIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.icon_send),
                            contentDescription = "send",
                            tint = Color.Blue,
                            modifier = Modifier.size(37.dp)
                        )
                    }
                },
            )
        }

    }
}


@Preview(showBackground = true)
@Composable
fun DetailCommentPreview() {
    STTCTheme {
        DetailCommentScreen(back = {})
    }
}

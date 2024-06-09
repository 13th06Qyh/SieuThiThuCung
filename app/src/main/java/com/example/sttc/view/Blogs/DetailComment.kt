package com.example.sttc.view.Blogs

import CommentsViewModel
import android.util.Log
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sttc.R
import com.example.sttc.model.Comments
import com.example.sttc.ui.theme.STTCTheme
import com.example.sttc.view.System.ItemsCmt
import com.example.sttc.view.System.formatUpdatedAt
import com.example.sttc.viewmodel.AccountViewModel
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailCommentScreen(
    back: () -> Unit,
    commentViewModel: CommentsViewModel,
    accountViewModel: AccountViewModel,
    blogId: Int,
    openLogin: () -> Unit,
) {
    commentViewModel.fetchComments(blogId)

    val cmt by commentViewModel.comments.collectAsState()
    Log.e("ShowCmt", "Comments: $cmt")

    //dialog
    var openDialogDelete by remember { mutableStateOf(false) }
    var openDialogBuild by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    var showDialogError by remember { mutableStateOf(false) }
    var okButtonPressed by remember { mutableStateOf(false) }
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
                if (cmt.isEmpty()) {
                    item {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Text(
                                "Chưa có bình luận nào",
                                modifier = Modifier.padding(16.dp),
                                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                            )
                        }
                    }
                }else{
                    items(items = cmt) { task ->
                        Surface(
                            color = Color.White,
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp)
                        ) {//line 143
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
                                            text = task.username,
                                            style = TextStyle(fontSize = 18.sp),
                                            fontWeight = FontWeight.Bold,
                                            modifier = Modifier
                                                .weight(1f)
                                                .padding(start = 5.dp)
                                        )
                                        val date = task.updated_at
                                        Log.e("Update_at", "Update_at: $date")
                                        Text(text = formatUpdatedAt(task.updated_at), style = TextStyle(fontSize = 14.sp)) //line 195

                                        if (accountViewModel.getUserIdFromSharedPreferences() == task.iduser) {
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
                                                    selectedTaskCmt = task.noidungbl
                                                }) {
                                                    Icon(
                                                        Icons.Default.Create,
                                                        contentDescription = "EditCmt",
                                                        tint = Color.Gray
                                                    )
                                                }
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
                                            text = task.noidungbl,
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
                                            cmt[0].noidungbl = newCmt
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
                    IconButton(onClick = {
                        val commentText = comment.value
                        val userId = accountViewModel.getUserIdFromSharedPreferences()
                        Log.e("User ID", userId.toString())

                        userId?.let {
                            val newComment = Comments(
                                maBL = 0,
                                iduser = userId,
                                idblog = blogId,
                                noidungbl = commentText,
                                created_at = "",
                                updated_at = ""
                            )
                            Log.e("newComment", newComment.toString() )

                            commentViewModel.createCmt(newComment, blogId)
                            comment.value = ""
                        } ?: run {
                            errorMessage = "Vui lòng đăng nhập để thêm bình luận"
                            showDialogError = true
                            Log.e("User ID", "User ID is null")
                        }
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.icon_send),
                            contentDescription = "send",
                            tint = Color.Blue,
                            modifier = Modifier.size(37.dp)
                        )
                    }
                },
            )

            if (showDialogError) {
                AlertDialog(
                    containerColor = Color(0xFFccf5ff),
                    shape = RoundedCornerShape(8.dp),
                    onDismissRequest = { showDialogError = false },
                    title = {},
                    text = {
                        Text(
                            errorMessage,
                            style = TextStyle(
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                color = Color.Red
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    },
                    confirmButton = {
                        Button(
                            onClick = {
                                showDialogError = false
                                okButtonPressed = true
                                if (errorMessage == "Vui lòng đăng nhập để thêm bình luận") {
                                    openLogin()
                                }
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFccffdd), // Màu nền của nút
                                contentColor = Color.Black, // Màu chữ của nút
                            ),
                            border = BorderStroke(1.dp, Color(0xFF00e64d)),
                        ) {
                            Text(
                                "OK",
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFFcc3300)
                                )
                            )
                        }

                    },
                    dismissButton = {
                        Button(
                            onClick = {
                                showDialogError = false
                                okButtonPressed = true
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.LightGray, // Màu nền của nút
                                contentColor = Color.Black, // Màu chữ của nút
                            ),
                            border = BorderStroke(1.dp, Color.Black),
                        ) {
                            Text(
                                "Hủy",
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black
                                )
                            )
                        }
                    }

                )
            }
        }

    }
}


@Preview(showBackground = true)
@Composable
fun DetailCommentPreview() {
    val context = LocalContext.current
    STTCTheme {
        DetailCommentScreen(
            back = {},
            commentViewModel = CommentsViewModel(),
            accountViewModel = AccountViewModel(context),
            blogId = 0,
            openLogin = {}
        )
    }
}

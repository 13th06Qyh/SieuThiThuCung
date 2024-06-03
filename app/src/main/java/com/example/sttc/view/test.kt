package com.example.sttc.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.request.ImageRequest
import com.example.sttc.R
import com.example.sttc.ui.theme.STTCTheme
import com.example.sttc.view.System.ItemAccount

@Composable
fun TestAccountScreen(
    openBillShip: () -> Unit,
    openBillHistory: () -> Unit,
    openBillCancel: () -> Unit,
) {
    val scrollState = rememberScrollState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)

    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TestTopIcon()
            TestStatusBill(openBillShip, openBillHistory, openBillCancel)
            TestInfoAccount()
        }
    }

}

@Composable
fun TestTopIcon() {
    var openDialogLogout by remember { mutableStateOf(false) }
    var expanded by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .components {
            add(GifDecoder.Factory())
        }
        .build()

    Box(
    ) {

        Image(
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(context).data(data = R.drawable.topiconaccount)
                    .apply(block = {
                    }).build(), imageLoader = imageLoader
            ),
            contentDescription = "TopIconAccount",
            modifier = Modifier
                .fillMaxWidth()
                .height(225.dp)
                .border(1.dp, color = Color(0xFFff6666))
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            Color(0xFFFF6794),
                            Color(0xFFF4FFFF),
                            Color(0xFFFFDCD2),
                            Color(0xFFFFE4F9),
                            Color(0xFFF6F5F2),
                        ),
                        radius = 750f
                    )
                )
        )

        IconButton(
            onClick = { expanded = true },
            modifier = Modifier
                .padding(4.dp, 5.dp)
                .align(Alignment.TopEnd)
        ) {
            Icon(
                Icons.Default.Settings,
                contentDescription = "Logout",
                modifier = Modifier
                    .size(37.dp)

            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                DropdownMenuItem(
                    text = {
                        Text(
                            "Đăng xuất",
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFFcc2900),
                            )
                        )
                    },
                    onClick = {
                        expanded = false
                        openDialogLogout = true
                    },
                    modifier = Modifier.background(Color(0xFFffffff))
                )

            }
        }


    }

    if (openDialogLogout) {
        AlertDialog(
            containerColor = Color(0xFFfffff5),
            onDismissRequest = { openDialogLogout = false },
            title = {
                Text(
                    "Bạn chắc chắn muốn đăng xuất?",
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
                        openDialogLogout = false
//                        navController.navigate("login")
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
                        openDialogLogout = false
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

//    Image(
//        painter = painterResource(id = R.drawable.topiconaccount),
//        contentDescription = "TopIconAccount",
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(200.dp)
//            .background(
//                Brush.radialGradient(
//                    colors = listOf(
//                        Color(0xFFFF6794),
//                        Color(0xFFF4FFFF),
//                        Color(0xFFFFDCD2),
//                        Color(0xFFFFE4F9),
//                        Color(0xFFF6F5F2),
//                    ),
//                    radius = 750f
//                )
//            )
//            .border(1.dp, color = Color(0xFFcc00cc))
//    )
}

@Composable
fun TestStatusBill(
    openBillShip: () -> Unit,
    openBillHistory: () -> Unit,
    openBillCancel: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, color = Color(0xFFff6666))
            .padding(5.dp, 0.dp)
            .background(
                Brush.radialGradient(
                    colors = listOf(
                        Color(0xFFFFFFFF),
                        Color(0xFFFFFFF8),
                        Color(0xFFffebe6),
                        Color(0xFFFFEBE4),
                    ),
                    radius = 600f
                )
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            IconButton(
                onClick = { openBillShip() },
                modifier = Modifier
                    .size(135.dp, 130.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        painter = painterResource(id = R.drawable.ship),
                        contentDescription = "DangGiao",
                        modifier = Modifier.size(46.dp),
                        tint = Color(0xFFcc2900)
                    )
                    Spacer(modifier = Modifier.height(4.dp)) // Thêm khoảng cách ở đây
                    Text(
                        "Đang giao",
                        style = TextStyle(
                            fontSize = 16.sp,
                            color = Color(0xFFcc2900),

                            )
                    )
                }

            }

            IconButton(
                onClick = { openBillHistory() },
                modifier = Modifier
                    .size(135.dp, 160.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        painter = painterResource(id = R.drawable.history),
                        contentDescription = "LichSuMua",
                        modifier = Modifier.size(46.dp),
                        tint = Color(0xFFcc2900)
                    )
                    Spacer(modifier = Modifier.height(4.dp)) // Thêm khoảng cách ở đây
                    Text(
                        "Lịch sử mua",
                        style = TextStyle(
                            fontSize = 16.sp,
                            color = Color(0xFFcc2900)
                        )
                    )
                }

            }

            IconButton(
                onClick = { openBillCancel() },
                modifier = Modifier
                    .size(135.dp, 160.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        painter = painterResource(id = R.drawable.cancel),
                        contentDescription = "DaHuy",
                        modifier = Modifier.size(46.dp),
                        tint = Color(0xFFcc2900)
                    )
                    Spacer(modifier = Modifier.height(4.dp)) // Thêm khoảng cách ở đây
                    Text(
                        "Đơn đã hủy",
                        style = TextStyle(
                            fontSize = 16.sp,
                            color = Color(0xFFcc2900)
                        )
                    )
                }

            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TestInfoAccount() {
    var showDialogName by remember { mutableStateOf(false) }
    var showDialogPass by remember { mutableStateOf(false) }
    var showDialogEmail by remember { mutableStateOf(false) }
    var showDialogPhone by remember { mutableStateOf(false) }
    var showDialogAddress by remember { mutableStateOf(false) }

    var currentPassword by remember { mutableStateOf("") }
    var repeatedNewPassword by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }

    var newName by remember { mutableStateOf("") }
    var newEmail by remember { mutableStateOf("") }
    var newPhone by remember { mutableStateOf("") }
    var newAddress by remember { mutableStateOf("") }

    val itemAccount = listOf(
        ItemAccount(
            "QuyhPham",
            "quynhpn.22it@vku.udn.vn",
            1234567890,
            "123456",
            "154 Trần Đại Nghĩa, Ngũ Hành Sơn, Đà Nẵng"
        )
    )

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, color = Color.Red)
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xFFB2B0B0),
                            Color(0xFFCFCFCF),
                            Color(0xFFFFFFFF),
                            Color(0xFFCFCFCF),
                            Color(0xFFB2B0B0),
                        ),
                        startX = 860.0f,
                        endX = 860.0f
                    )
                ),
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.infor),
                contentDescription = "Info",
                modifier = Modifier.padding(8.dp, 11.dp)
            )
            Text(
                text = "Thông Tin Tài Khoản",
                modifier = Modifier.padding(0.dp, 10.dp),
                style = TextStyle(
                    fontSize = 22.sp,
                    color = Color(0xFF000000),
                    fontWeight = FontWeight.Bold
                )
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .border(0.1.dp, color = Color(0xFF000000))
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xFFB2B0B0),
                            Color(0xFFCFCFCF),
                            Color(0xFFFFFFFF),
                            Color(0xFFFFFFFF),
                            Color(0xFFFFEDFB),
                        ),
                        startX = 0.0f,
                        endX = 860.0f
                    )
                ),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                Icons.Default.Person,
                contentDescription = "Person",
                modifier = Modifier.size(18.dp),
            )
            Text(
                text = "Tên tài khoản:",
                modifier = Modifier.padding(5.dp, 0.dp, 9.dp, 0.dp),
                style = TextStyle(
                    fontSize = 17.sp,
                    color = Color(0xFF000000),
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start
                ),
            )
//            VerticalDivider(thickness = 1.dp, color = Color(0xFF000000))
            Text(
                text = itemAccount[0].name,
                modifier = Modifier
                    .padding(0.dp, 0.dp, 10.dp, 0.dp)
                    .width(200.dp),
                style = TextStyle(
                    fontSize = 18.sp,
                    color = Color(0xFF000000),
                    fontStyle = FontStyle.Italic,
                    textAlign = TextAlign.Start
                ),
            )

            IconButton(onClick = { showDialogName = true }) {
                Icon(
                    Icons.Default.Create,
                    contentDescription = "EditNameAccount",
                    tint = Color.Gray,
                    modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 0.dp),

                    )
            }

            if (showDialogName) {
                AlertDialog(
                    containerColor = Color(0xFFFFD5BE),
                    onDismissRequest = { showDialogName = false },
                    title = {
                        Text(
                            "Sửa Tên Tài Khoản",
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
                            value = newName,
                            onValueChange = { newName = it },
                            placeholder = { Text("Nhập tên mới vào đây") },
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = Color(0xffffebe6),
                                unfocusedIndicatorColor = Color(0xffe62e00),
                            ),
                        )
                    },
                    confirmButton = {
                        Button(
                            onClick = {
                                itemAccount[0].name = newName
                                showDialogName = false
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFA2FFAB), // Màu nền của nút
                                contentColor = Color.Black, // Màu chữ của nút
                            ),

                            border = BorderStroke(1.dp, Color(0xFF018B0F)),
                        ) {
                            Text(
                                "Sửa",
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }
                    },
                    dismissButton = {
                        Button(
                            onClick = { showDialogName = false },
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

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .border(0.1.dp, color = Color(0xFF000000))
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xFFB2B0B0),
                            Color(0xFFCFCFCF),
                            Color(0xFFFFFFFF),
                            Color(0xFFFFFFFF),
                            Color(0xFFFFEDFB),
                        ),
                        startX = 0.0f,
                        endX = 860.0f
                    )
                ),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                Icons.Default.Lock,
                contentDescription = "Lock",
                modifier = Modifier.size(18.dp),
            )
            Text(
                text = "Mật khẩu:",
                modifier = Modifier
                    .padding(5.dp, 0.dp, 15.dp, 1.dp)
                    .width(100.dp),
                style = TextStyle(
                    fontSize = 17.sp,
                    color = Color(0xFF000000),
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start
                ),
            )
//            VerticalDivider(thickness = 1.dp, color = Color(0xFF000000))
            Text(
                text = "********",
                modifier = Modifier
                    .padding(0.dp, 0.dp, 10.dp, 0.dp)
                    .width(200.dp),
                style = TextStyle(
                    fontSize = 18.sp,
                    color = Color(0xFF000000),
                    fontStyle = FontStyle.Italic,
                    textAlign = TextAlign.Start
                ),
            )

            IconButton(onClick = { showDialogPass = true }) {
                Icon(
                    Icons.Default.Create,
                    contentDescription = "EditPassAccount",
                    tint = Color.Gray,
                    modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 0.dp),

                    )
            }

            if (showDialogPass) {
                AlertDialog(
                    containerColor = Color(0xFF99D9FF),
                    onDismissRequest = { showDialogPass = false },
                    title = {
                        Text(
                            "Đổi Mật Khẩu",
                            style = TextStyle(
                                fontSize = 23.sp,
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Bold,
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )
                    },
                    text = {
                        Column {
                            TextField(
                                value = currentPassword,
                                onValueChange = { currentPassword = it },
                                label = {
                                    Text(
                                        "*Mật khẩu hiện tại:",
                                        style = TextStyle(
                                            fontSize = 14.sp,
                                            fontWeight = FontWeight.Bold,
                                        ),
                                        modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 8.dp)
                                    )
                                },
                                placeholder = { Text("-- Nhập mật khẩu hiện tại --") },
                                colors = TextFieldDefaults.textFieldColors(
                                    containerColor = Color(0xffD7F0FF),
                                    unfocusedIndicatorColor = Color(0xff0002D4),
                                ),
                                modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 8.dp)
                            )

                            TextField(
                                value = newPassword,
                                onValueChange = { newPassword = it },
                                label = {
                                    Text(
                                        "*Mật khẩu mới:",
                                        style = TextStyle(
                                            fontSize = 14.sp,
                                            fontWeight = FontWeight.Bold,
                                        ),
                                        modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 8.dp)
                                    )
                                },
                                placeholder = { Text("-- Nhập mật khẩu mới --") },
                                colors = TextFieldDefaults.textFieldColors(
                                    containerColor = Color(0xffD7F0FF),
                                    unfocusedIndicatorColor = Color(0xff0002D4),
                                ),
                                modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 8.dp)
                            )

                            TextField(
                                value = repeatedNewPassword,
                                onValueChange = { repeatedNewPassword = it },
                                label = {
                                    Text(
                                        "*Nhập lại:",
                                        style = TextStyle(
                                            fontSize = 14.sp,
                                            fontWeight = FontWeight.Bold,
                                        ),
                                        modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 8.dp)
                                    )
                                },
                                placeholder = { Text("-- Nhập lại mật khẩu mới --") },
                                colors = TextFieldDefaults.textFieldColors(
                                    containerColor = Color(0xffD7F0FF),
                                    unfocusedIndicatorColor = Color(0xff0002D4),
                                ),
                            )
                        }
                    },
                    confirmButton = {
                        Button(
                            onClick = {
                                itemAccount[0].pass = newPassword
                                showDialogPass = false
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFA2FFAB), // Màu nền của nút
                                contentColor = Color.Black, // Màu chữ của nút
                            ),

                            border = BorderStroke(1.dp, Color(0xFF018B0F)),
                        ) {
                            Text(
                                "Đổi mật khẩu",
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }
                    },
                    dismissButton = {
                        Button(
                            onClick = { showDialogPass = false },
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

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(67.dp)
                .border(0.2.dp, color = Color(0xFF000000))
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xFFB2B0B0),
                            Color(0xFFCFCFCF),
                            Color(0xFFFFFFFF),
                            Color(0xFFFFFFFF),
                            Color(0xFFFFEDFB),
                        ),
                        startX = 0.0f,
                        endX = 860.0f
                    )
                ),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                Icons.Default.Email,
                contentDescription = "Email",
                modifier = Modifier.size(18.dp),
            )
            Text(
                text = "Email:",
                modifier = Modifier
                    .padding(5.dp, 0.dp, 15.dp, 1.dp)
                    .width(100.dp),
                style = TextStyle(
                    fontSize = 17.sp,
                    color = Color(0xFF000000),
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start
                ),
            )
//            VerticalDivider(thickness = 1.dp, color = Color(0xFF000000))
            Text(
                text = itemAccount[0].email,
                modifier = Modifier
                    .padding(0.dp, 0.dp, 10.dp, 0.dp)
                    .width(200.dp),
                style = TextStyle(
                    fontSize = 18.sp,
                    color = Color(0xFF000000),
                    fontStyle = FontStyle.Italic,
                    textAlign = TextAlign.Center
                ),
            )

            IconButton(onClick = { showDialogEmail = true }) {
                Icon(
                    Icons.Default.Create,
                    contentDescription = "EditEmailAccount",
                    tint = Color.Gray,
                    modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 0.dp),

                    )

            }

            if (showDialogEmail) {
                AlertDialog(
                    containerColor = Color(0xFFBBFFB0),
                    onDismissRequest = { showDialogEmail = false },
                    title = {
                        Text(
                            "Sửa Email",
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
                            value = newEmail,
                            onValueChange = { newEmail = it },
                            placeholder = { Text("Nhập email mới vào đây") },
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = Color(0xffD2FFCB),
                                unfocusedIndicatorColor = Color(0xff17A400),
                            ),
                        )
                    },
                    confirmButton = {
                        Button(
                            onClick = {
                                itemAccount[0].email = newEmail
                                showDialogEmail = false
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF8DD5FF), // Màu nền của nút
                                contentColor = Color.Black, // Màu chữ của nút
                            ),

                            border = BorderStroke(1.dp, Color(0xFF018B0F)),
                        ) {
                            Text(
                                "Sửa",
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }
                    },
                    dismissButton = {
                        Button(
                            onClick = { showDialogEmail = false },
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

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .border(0.1.dp, color = Color(0xFF000000))
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xFFB2B0B0),
                            Color(0xFFCFCFCF),
                            Color(0xFFFFFFFF),
                            Color(0xFFFFFFFF),
                            Color(0xFFFFEDFB),
                        ),
                        startX = 0.0f,
                        endX = 860.0f
                    )
                ),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                Icons.Default.Phone,
                contentDescription = "Phone",
                modifier = Modifier.size(18.dp),
            )
            Text(
                text = "Số điện thoại:",
                modifier = Modifier
                    .padding(5.dp, 0.dp, 8.dp, 1.dp)
                    .width(110.dp),
                style = TextStyle(
                    fontSize = 17.sp,
                    color = Color(0xFF000000),
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start
                ),
            )
//            VerticalDivider(thickness = 1.dp, color = Color(0xFF000000))
            Text(
                text = "0" + itemAccount[0].phone.toString(),
                modifier = Modifier
                    .padding(0.dp, 0.dp, 10.dp, 0.dp)
                    .width(200.dp),
                style = TextStyle(
                    fontSize = 18.sp,
                    color = Color(0xFF000000),
                    fontStyle = FontStyle.Italic,
                    textAlign = TextAlign.Start
                ),
            )

            IconButton(onClick = { showDialogPhone = true }) {
                Icon(
                    Icons.Default.Create,
                    contentDescription = "EditPhoneAccount",
                    tint = Color.Gray,
                    modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 0.dp),

                    )
            }

            if (showDialogPhone) {
                AlertDialog(
                    containerColor = Color(0xFFFFF9AF),
                    onDismissRequest = { showDialogPhone = false },
                    title = {
                        Text(
                            "Đổi Số Điện Thoại",
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
                            value = newPhone,
                            onValueChange = { newPhone = it },
                            placeholder = { Text("Nhập sđt mới vào đây") },
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = Color(0xffFFFDDB),
                                unfocusedIndicatorColor = Color(0xffe62e00),
                            ),
                        )
                    },
                    confirmButton = {
                        Button(
                            onClick = {
                                itemAccount[0].phone = newPhone.toInt()
                                showDialogPhone = false
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFA2FFAB), // Màu nền của nút
                                contentColor = Color.Black, // Màu chữ của nút
                            ),

                            border = BorderStroke(1.dp, Color(0xFF018B0F)),
                        ) {
                            Text(
                                "Sửa",
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }
                    },
                    dismissButton = {
                        Button(
                            onClick = { showDialogPhone = false },
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

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .border(0.1.dp, color = Color(0xFF000000))
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xFFB2B0B0),
                            Color(0xFFCFCFCF),
                            Color(0xFFFFFFFF),
                            Color(0xFFFFFFFF),
                            Color(0xFFFFEDFB),
                        ),
                        startX = 0.0f,
                        endX = 860.0f
                    )
                ),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                Icons.Default.LocationOn,
                contentDescription = "LocationOn",
                modifier = Modifier.size(18.dp),
            )
            Text(
                text = "Địa chỉ:",
                modifier = Modifier
                    .padding(5.dp, 0.dp, 14.dp, 1.dp)
                    .width(100.dp),
                style = TextStyle(
                    fontSize = 17.sp,
                    color = Color(0xFF000000),
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start
                ),
            )
//            VerticalDivider(thickness = 1.dp, color = Color(0xFF000000))
            Text(
                text = itemAccount[0].address,
                modifier = Modifier
                    .padding(0.dp, 20.dp, 10.dp, 20.dp)
                    .width(200.dp),
                style = TextStyle(
                    fontSize = 18.sp,
                    color = Color(0xFF000000),
                    fontStyle = FontStyle.Italic,
                    textAlign = TextAlign.Start
                ),
            )

            IconButton(onClick = { showDialogAddress = true }) {
                Icon(
                    Icons.Default.Create,
                    contentDescription = "EditAddressAccount",
                    tint = Color.Gray,
                    modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 0.dp),
                )
            }

            if (showDialogAddress) {
                AlertDialog(
                    containerColor = Color(0xFFFFBEBE),
                    onDismissRequest = { showDialogAddress = false },
                    title = {
                        Text(
                            "Cập Nhật Địa Chỉ",
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
                            value = newAddress,
                            onValueChange = { newAddress = it },
                            placeholder = { Text("Nhập địa chỉ mới vào đây") },
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = Color(0xffffebe6),
                                unfocusedIndicatorColor = Color(0xffe62e00),
                            ),
                            modifier = Modifier.height(100.dp)
                        )
                    },
                    confirmButton = {
                        Button(
                            onClick = {
                                itemAccount[0].address = newAddress
                                showDialogAddress = false
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFA2FFAB), // Màu nền của nút
                                contentColor = Color.Black, // Màu chữ của nút
                            ),

                            border = BorderStroke(1.dp, Color(0xFF018B0F)),
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
                        Button(
                            onClick = { showDialogAddress = false },
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun aaa() {
    var showSuccessPass by remember { mutableStateOf(true) }
    AlertDialog(
        containerColor = Color(0xFF99D9FF),
        onDismissRequest = { },
        title = {
            Text(
                "Cập Nhật Địa Chỉ",
                style = TextStyle(
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 23.sp,
                ),
                modifier = Modifier.fillMaxWidth()
            )
        },
        text = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Đổi mật khẩu thành công ",
                    color = Color(0xFF009900),
                    modifier = Modifier.padding(0.dp, 5.dp, 0.dp, 0.dp),
                    style = TextStyle(
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center,
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.Bold
                    )
                )
                Icon(
                    painter = painterResource(id = R.drawable.passok),
                    tint = Color(0xFF009900),
                    contentDescription = "PassOk"
                )
            }
        },
        confirmButton = {

            Button(
                onClick = {
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFA2FFAB), // Màu nền của nút
                    contentColor = Color.Black, // Màu chữ của nút
                ),

                border = BorderStroke(1.dp, Color(0xFF018B0F)),
            ) {
                Text(
                    "Xác nhận",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold
                    )
                )
            }
            if (showSuccessPass) {
                Button(
                    onClick = { },
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
            }

        },
        dismissButton = {

            Button(
                onClick = { },
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

@Preview(showBackground = true)
@Composable
fun TestAccountScreenPreview() {
    STTCTheme {
        aaa()
    }
}


//package com.example.sttc.viewmodel
//
//import android.content.Context
//import android.util.Log
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.asFlow
//import com.example.sttc.model.ErrorResponse
//import com.example.sttc.model.ErrorSignupResponse
//import com.example.sttc.model.SignupResponse
//import com.example.sttc.model.LoginRequest
//import com.example.sttc.model.LoginResponse
//import com.example.sttc.model.SignupRequest
//import com.example.sttc.model.UpdateAddressRequest
//import com.example.sttc.model.UpdateAddressResponse
//import com.example.sttc.model.UpdateMailRequest
//import com.example.sttc.model.UpdateMailResponse
//import com.example.sttc.model.UpdateNameRequest
//import com.example.sttc.model.UpdateNameResponse
//import com.example.sttc.model.UpdatePassRequest
//import com.example.sttc.model.UpdatePassResponse
//import com.example.sttc.model.UpdatePhoneRequest
//import com.example.sttc.model.UpdatePhoneResponse
//import com.example.sttc.model.User
//import com.example.sttc.service.ApiService.apiService
//import com.google.gson.Gson
//import com.google.gson.JsonSyntaxException
//import com.google.gson.stream.JsonReader
//import org.json.JSONException
//import org.json.JSONObject
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//import java.io.StringReader
//
//class AccountViewModel(context: Context) : ViewModel() {
//    private val _userInfoFlow = MutableLiveData<User?>(null)
//    val userInfoFlow = _userInfoFlow.asFlow()
//
//    private val _request = MutableLiveData<String>(null)
//    val request = _request.asFlow()
//
//    private val _loginResult = MutableLiveData<Result<String>>()
//    val loginResult = _loginResult.asFlow()
//
//    private val _signupResult = MutableLiveData<Result<String>>()
//    val signupResult = _signupResult.asFlow()
//
//    private val context = context
//
//    private fun readUserInfoFromSharedPreferences() {
//        val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
//        val userJson = sharedPreferences.getString("user", null)
//        val user = getUserInfoFromJson(userJson)
//        _userInfoFlow.value = user
//    }
//
//    // Function to update user info
//    fun updateUserInfo(newUser: User) {
//        val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
//        val editor = sharedPreferences.edit()
//        editor.putString("user", Gson().toJson(newUser))
//        editor.apply()
//        _userInfoFlow.value = newUser
//    }
//
//    private fun getUserInfoFromJson(userJson: String?): User? {
//        return if (userJson != null) Gson().fromJson(userJson, User::class.java) else null
//    }
//
//    init {
//        readUserInfoFromSharedPreferences()
//    }
//    fun login(username: String, password: String) {
//        val loginRequest = LoginRequest(username, password)
//        apiService.login(loginRequest).enqueue(object : Callback<LoginResponse> {
//            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
//                if (response.isSuccessful) {
//                    response.body()?.let {loginResponse ->
//                        val token = loginResponse.token
//                        val user = loginResponse.user
//                        // Save token and user info to SharedPreferences
//                        saveUserInfoToSharedPreferences(token, user)
//                        updateUserInfo(user)
//                        _loginResult.value = Result.success(token)
//                    } ?: run {
//                        _loginResult.value = Result.failure(Exception("No token found"))
//                    }
//                } else {
//                    val errorBody = response.errorBody()?.string()
//                    Log.e("API Error", "Error body: $errorBody")
//                    val errorResponse = try {
//                        if (!errorBody.isNullOrEmpty()) {
//                            Gson().fromJson(errorBody, ErrorResponse::class.java)
//                        } else {
//                            ErrorResponse("Login failed", "Unknown error")
//                        }
//                    } catch (e: JsonSyntaxException) {
//                        ErrorResponse("Login failed", "Malformed error response")
//                    }
//                    _loginResult.value = Result.failure(Exception(errorResponse.error))
//                }
//
//            }
//
//            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
//                _loginResult.value = Result.failure(t)
//            }
//        })
//    }
//
//    fun updateName(username: String, id: Int) {
//        val token = getTokenFromSharedPreferences()
//        if (token == null) {
//            _loginResult.value = Result.failure(Exception("No token found"))
//            return
//        }
//        val updateNameRequest = UpdateNameRequest(username, id)
//        apiService.updateName("Bearer $token", id, updateNameRequest).enqueue(object : Callback<UpdateNameResponse> {
//            override fun onResponse(call: Call<UpdateNameResponse>, response: Response<UpdateNameResponse>) {
//                if (response.isSuccessful) {
//                    response.body()?.let { updatedResponse ->
//                        val user = updatedResponse.user
//                        saveUserInfoToSharedPreferences(token, user)
//                        updateUserInfo(user)
//                        _loginResult.value = Result.success(token)
//                    } ?: run {
//                        _loginResult.value = Result.failure(Exception("No user found"))
//                    }
//                } else {
//                    handleErrorResponse(response, "updateName")
//                }
//            }
//
//            override fun onFailure(call: Call<UpdateNameResponse>, t: Throwable) {
//                _loginResult.value = Result.failure(t)
//            }
//        })
//    }
//
//    fun updateMail(email: String, id: Int) {
//        val token = getTokenFromSharedPreferences()
//        if (token == null) {
//            _loginResult.value = Result.failure(Exception("No token found"))
//            return
//        }
//        val updateMailRequest = UpdateMailRequest(email, id)
//        apiService.updateMail("Bearer $token", id, updateMailRequest).enqueue(object : Callback<UpdateMailResponse> {
//            override fun onResponse(call: Call<UpdateMailResponse>, response: Response<UpdateMailResponse>) {
//                if (response.isSuccessful) {
//                    response.body()?.let { updatedResponse ->
//                        val user = updatedResponse.user
//                        saveUserInfoToSharedPreferences(token, user)
//                        updateUserInfo(user)
//                        _loginResult.value = Result.success(token)
//                    } ?: run {
//                        _loginResult.value = Result.failure(Exception("No user found"))
//                    }
//                } else {
//                    handleErrorResponse(response, "updateMail")
//                }
//            }
//
//            override fun onFailure(call: Call<UpdateMailResponse>, t: Throwable) {
//                _loginResult.value = Result.failure(t)
//            }
//        })
//    }
//
//    fun updatePhone(sdt: Int, id: Int) {
//        val token = getTokenFromSharedPreferences()
//        if (token == null) {
//            _loginResult.value = Result.failure(Exception("No token found"))
//            return
//        }
//        val updatePhoneRequest = UpdatePhoneRequest(sdt, id)
//        apiService.updatePhone("Bearer $token", id, updatePhoneRequest).enqueue(object : Callback<UpdatePhoneResponse> {
//            override fun onResponse(call: Call<UpdatePhoneResponse>, response: Response<UpdatePhoneResponse>) {
//                if (response.isSuccessful) {
//                    response.body()?.let { updatedResponse ->
//                        val user = updatedResponse.user
//                        saveUserInfoToSharedPreferences(token, user)
//                        updateUserInfo(user)
//                        _loginResult.value = Result.success(token)
//                    } ?: run {
//                        _loginResult.value = Result.failure(Exception("No user found"))
//                    }
//                } else {
//                    handleErrorResponse(response, "updatePhone")
//                }
//            }
//
//            override fun onFailure(call: Call<UpdatePhoneResponse>, t: Throwable, ) {
//                _loginResult.value = Result.failure(t)
//            }
//        })
//    }
//
//    fun updateAddress(diachi: String, id: Int) {
//        val token = getTokenFromSharedPreferences()
//        if (token == null) {
//            _loginResult.value = Result.failure(Exception("No token found"))
//            return
//        }
//        val updateAddressRequest = UpdateAddressRequest(diachi, id)
//        apiService.updateAddress("Bearer $token", id, updateAddressRequest).enqueue(object : Callback<UpdateAddressResponse> {
//            override fun onResponse(call: Call<UpdateAddressResponse>, response: Response<UpdateAddressResponse>) {
//                if (response.isSuccessful) {
//                    response.body()?.let { updatedResponse ->
//                        val user = updatedResponse.user
//                        saveUserInfoToSharedPreferences(token, user)
//                        updateUserInfo(user)
//                        _loginResult.value = Result.success(token)
//                    } ?: run {
//                        _loginResult.value = Result.failure(Exception("No user found"))
//                    }
//                } else {
//                    handleErrorResponse(response, "updateAddress")
//                }
//            }
//
//            override fun onFailure(call: Call<UpdateAddressResponse>, t: Throwable) {
//                _loginResult.value = Result.failure(t)
//            }
//        })
//    }
//
//    fun changePass(currentpassword: String, newpassword: String, confirmpassword: String, id: Int) {
//        val token = getTokenFromSharedPreferences()
//        if (token == null) {
//            _loginResult.value = Result.failure(Exception("No token found"))
//            return
//        }
//        val updatePassRequest = UpdatePassRequest(currentpassword, newpassword, confirmpassword, id)
//        apiService.changePass("Bearer $token", id, updatePassRequest).enqueue(object : Callback<UpdatePassResponse> {
//            override fun onResponse(call: Call<UpdatePassResponse>, response: Response<UpdatePassResponse>) {
//                if (response.isSuccessful) {
//                    response.body()?.let { updatedResponse ->
//                        val user = updatedResponse.user
//                        saveUserInfoToSharedPreferences(token, user)
//                        updateUserInfo(user)
//                        _loginResult.value = Result.success(token)
//                        Log.d("token", updatedResponse.message)
//                    } ?: run {
//                        _loginResult.value = Result.failure(Exception("No user found"))
//                    }
//                } else {
//                    val errorBody = response.errorBody()?.string()
//                    Log.e("API ChangePass Error", "Error body: $errorBody")
//
//                    // Parse JSON error body
//                    val errorMessage = try {
//                        val jsonObject = JSONObject(errorBody)
//                        // Directly fetch the error message using the "error" key
//                        jsonObject.getString("error")
//                    } catch (e: JSONException) {
//                        "Unknown error occurred"
//                    }
//                    Log.e("API ChangePass Error", "Parsed error message: $errorMessage")
//                    _loginResult.value = Result.failure(Exception(errorMessage))
//                }
//            }
//
//            override fun onFailure(call: Call<UpdatePassResponse>, t: Throwable) {
//                _loginResult.value = Result.failure(t)
//            }
//        })
//    }
//
//    private fun saveUserInfoToSharedPreferences(token: String, user: User) {
//        val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
//        with(sharedPreferences.edit()) {
//            putString("token", token)
//            putString("user", Gson().toJson(user)) // Convert user object to JSON string
//            apply()
//        }
//    }
//
//    fun getTokenFromSharedPreferences(): String? {
//        val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
//        return sharedPreferences.getString("token", null)
//    }
//
//    fun logout() {
//        // Xử lý việc đăng xuất ở đây, chẳng hạn là xóa token và thông tin user từ SharedPreferences
//        val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
//        with(sharedPreferences.edit()) {
//            remove("token")
//            remove("user")
//            apply()
//        }
//    }
//
//    fun signup(username: String, email: String, sdt: Int, password: String) {
//        val signupRequest = SignupRequest(username, email, sdt, password)
//        apiService.signup(signupRequest).enqueue(object : Callback<SignupResponse> {
//            override fun onResponse(call: Call<SignupResponse>, response: Response<SignupResponse>) {
//                if (response.isSuccessful) {
//                    response.body()?.let {
//                        _signupResult.value = Result.success(it.token)
//                    } ?: run {
//                        _signupResult.value = Result.failure(Exception("No token found"))
//                    }
//                } else {
//                    val errorBody = response.errorBody()?.string()
//                    Log.e("API Signup Error", "Error body: $errorBody")
//
//                    // Parse JSON error body
//                    val errorMessage = try {
//                        val jsonObject = JSONObject(errorBody)
//                        val errorsObject = jsonObject.getJSONObject("errors")
//
//                        val errorMessages = mutableListOf<String>()
//
//                        // Iterate through all fields in the errors object
//                        errorsObject.keys().forEach { key ->
//                            val errorArray = errorsObject.getJSONArray(key)
//                            for (i in 0 until errorArray.length()) {
//                                errorMessages.add(errorArray.getString(i))
//                            }
//                        }
//
//                        // Join all error messages into a single string
//                        errorMessages.joinToString(separator = "\n")
//
//                    } catch (e: JSONException) {
//                        "Unknown error occurred"
//                    }
//                    Log.e("API SignUP Error", "Parsed error message: $errorMessage")
//
//                    _signupResult.value = Result.failure(Exception(errorMessage))
//                }
//            }
//
//
//            override fun onFailure(call: Call<SignupResponse>, t: Throwable) {
//                _signupResult.value = Result.failure(t)
//            }
//        })
//    }
//
//    fun handleErrorResponse(response: Response<*>, requestType: String) {
//        _request.value = requestType
//        val errorBody = response.errorBody()?.string()
//        Log.e("API Update Error", "Error body: $errorBody")
//
//        // Parse JSON error body
//        val errorMessage = try {
//            val jsonObject = JSONObject(errorBody)
//            val errorsObject = jsonObject.getJSONObject("errors")
//
//            val errorMessages = mutableListOf<String>()
//
//            // Iterate through all fields in the errors object
//            errorsObject.keys().forEach { key ->
//                val errorArray = errorsObject.getJSONArray(key)
//                for (i in 0 until errorArray.length()) {
//                    errorMessages.add(errorArray.getString(i))
//                }
//            }
//
//            // Join all error messages into a single string
//            errorMessages.joinToString(separator = "\n")
//
//        } catch (e: JSONException) {
//            "Unknown error occurred"
//        }
//        Log.e("API Update Error", "Parsed error message: $errorMessage")
//        _loginResult.value = Result.failure(Exception(errorMessage))
//    }
//
//}
//



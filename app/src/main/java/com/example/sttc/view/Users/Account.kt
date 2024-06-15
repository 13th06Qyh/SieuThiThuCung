package com.example.sttc.view.Users

import android.util.Log
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
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import com.example.sttc.view.System.Allow
import com.example.sttc.view.System.getAddress
import com.example.sttc.viewmodel.AccountViewModel
import com.example.sttc.viewmodel.NotificationViewModel

@Composable
fun AccountScreen(
    openBillShip: () -> Unit,
    openBillHistory: () -> Unit,
    openBillCancel: () -> Unit,
    openLogout: () -> Unit,
    accountViewModel: AccountViewModel,
    notificationViewModel: NotificationViewModel
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
            TopIcon(openLogout)
            StatusBill(openBillShip, openBillHistory, openBillCancel)
            InfoAccount(accountViewModel, notificationViewModel)
        }
    }

}

@Composable
fun TopIcon(openLogout: () -> Unit) {
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
                        openLogout()
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
fun StatusBill(
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
fun InfoAccount(accountViewModel: AccountViewModel, notificationViewModel: NotificationViewModel) {
    val loginResult by accountViewModel.update.collectAsState(null)
    val requestType by accountViewModel.request.collectAsState(initial = "")

    var showErrorName by remember { mutableStateOf(false) }
    var errorMessageName by remember { mutableStateOf("") }

    var showErrorEmail by remember { mutableStateOf(false) }
    var errorMessageEmail by remember { mutableStateOf("") }

    var showErrorPhone by remember { mutableStateOf(false) }
    var errorMessagePhone by remember { mutableStateOf("") }

    var showErrorPass by remember { mutableStateOf(false) }
    var errorMessagePass by remember { mutableStateOf("") }
    var showSuccessPass by remember { mutableStateOf(false) }

    var showDialogName by remember { mutableStateOf(false) }
    var showDialogPass by remember { mutableStateOf(false) }
    var showDialogEmail by remember { mutableStateOf(false) }
    var showDialogPhone by remember { mutableStateOf(false) }

    var currentPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    var newName by remember { mutableStateOf("") }
    var newEmail by remember { mutableStateOf("") }
    var newPhone by remember { mutableStateOf("") }
    val userState by accountViewModel.userInfoFlow.collectAsState(initial = null)

    userState?.let { user ->
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
                    text = user.username,
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
                        onDismissRequest = {
                            showErrorName = false
                            showDialogName = false
                        },
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
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                TextField(
                                    value = newName,
                                    onValueChange = { newName = it },
                                    placeholder = { Text("Nhập tên mới vào đây") },
                                    colors = TextFieldDefaults.textFieldColors(
                                        containerColor = Color(0xffffebe6),
                                        unfocusedIndicatorColor = Color(0xffe62e00),
                                    ),
                                )

                                if (showErrorName) {
                                    Text(
                                        text = errorMessageName,
                                        color = Color.Red,
                                        modifier = Modifier.padding(16.dp),
                                        style = TextStyle(
                                            fontSize = 16.sp,
                                            textAlign = TextAlign.Center,
                                            fontStyle = FontStyle.Italic
                                        )
                                    )
                                }
                            }

                        },

                        confirmButton = {
                            Button(
                                onClick = {
                                    val id = user.id
                                    if (newName.isNotEmpty()) {
                                        accountViewModel.updateName(newName, id)

//                                        showDialogName = false
//                                        showErrorName = false


                                    } else {
                                        showErrorName = true
//                                        showDialogName = true
                                        errorMessageName = "Vui lòng nhập tên mới!"

                                    }

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
                                onClick = {
                                    showErrorName = false
                                    showDialogName = false
                                },
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

                IconButton(onClick = {
                    showDialogPass = true
                    showSuccessPass = false
                    showErrorPass = false
                }) {
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
                        onDismissRequest = {
                            showDialogPass = false
                            showErrorPass = false
                            showSuccessPass = false
                        },
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
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
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
                                    value = confirmPassword,
                                    onValueChange = { confirmPassword = it },
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

                                if (showErrorPass) {
                                    Text(
                                        text = errorMessagePass,
                                        color = Color.Red,
                                        modifier = Modifier.padding(16.dp),
                                        style = TextStyle(
                                            fontSize = 16.sp,
                                            textAlign = TextAlign.Center,
                                            fontStyle = FontStyle.Italic
                                        )
                                    )
                                }

                                if (showSuccessPass) {
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
                                }
                            }
                        },
                        confirmButton = {
                            Button(
                                onClick = {
                                    val id = user.id
                                    when {
                                        currentPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty() -> {
                                            showErrorPass = true
                                            errorMessagePass = "Vui lòng nhập đầy đủ các mục!"
                                        }

                                        newPassword.length < 8 -> {
                                            showErrorPass = true
                                            errorMessagePass =
                                                "Mật khẩu mới phải có ít nhất 8 ký tự!"
                                        }

                                        confirmPassword.length < 8 -> {
                                            showErrorPass = true
                                            errorMessagePass = "Xác nhận mật khẩu không khớp!"
                                        }

                                        else -> {
                                            accountViewModel.changePass(
                                                currentPassword,
                                                newPassword,
                                                confirmPassword,
                                                id
                                            )
                                        }
                                    }

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
                            if (showSuccessPass) {
                                Button(
                                    onClick = {
                                        showSuccessPass = false
                                        showDialogPass = false
                                        showErrorPass = false
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
                            } else {
                                Button(
                                    onClick = {
                                        showDialogPass = false
                                        showErrorPass = false
                                        showSuccessPass = false
                                    },
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
                    text = user.email,
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
                        onDismissRequest = {
                            showDialogEmail = false
                            showErrorEmail = false
                        },
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
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                TextField(
                                    value = newEmail,
                                    onValueChange = { newEmail = it },
                                    placeholder = { Text("Nhập email mới vào đây") },
                                    colors = TextFieldDefaults.textFieldColors(
                                        containerColor = Color(0xffD2FFCB),
                                        unfocusedIndicatorColor = Color(0xff17A400),
                                    ),
                                )

                                if (showErrorEmail) {
                                    Text(
                                        text = errorMessageEmail,
                                        color = Color.Red,
                                        modifier = Modifier.padding(16.dp),
                                        style = TextStyle(
                                            fontSize = 16.sp,
                                            textAlign = TextAlign.Center,
                                            fontStyle = FontStyle.Italic
                                        )
                                    )
                                }
                            }
                        },
                        confirmButton = {
                            Button(
                                onClick = {
                                    val id = user.id
                                    if (newEmail.isNotEmpty()) {
                                        accountViewModel.updateMail(newEmail, id)

//                                        showDialogEmail = false
//                                        showErrorEmail = false


                                    } else {
                                        showErrorEmail = true
//                                        showDialogEmail = true
                                        errorMessageEmail = "Vui lòng nhập email mới!"

                                    }

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
                                onClick = {
                                    showDialogEmail = false
                                    showErrorEmail = false
                                },
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
                    text = "0" + user.sdt.toString(),
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
                        onDismissRequest = {
                            showDialogPhone = false
                            showErrorPhone = false
                        },
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
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                TextField(
                                    value = newPhone,
                                    onValueChange = { newPhone = it },
                                    placeholder = { Text("Nhập sđt mới vào đây") },
                                    colors = TextFieldDefaults.textFieldColors(
                                        containerColor = Color(0xffFFFDDB),
                                        unfocusedIndicatorColor = Color(0xffe62e00),
                                    ),
                                )

                                if (showErrorPhone) {
                                    Text(
                                        text = errorMessagePhone,
                                        color = Color.Red,
                                        modifier = Modifier.padding(16.dp),
                                        style = TextStyle(
                                            fontSize = 16.sp,
                                            textAlign = TextAlign.Center,
                                            fontStyle = FontStyle.Italic
                                        )
                                    )
                                }
                            }
                        },
                        confirmButton = {
                            Button(
                                onClick = {
                                    val id = user.id
                                    if (newPhone.isNotEmpty()) {
                                        accountViewModel.updatePhone(newPhone.toInt(), id)

//                                        showDialogPhone = false
//                                        showErrorPhone = false


                                    } else {
                                        showErrorPhone = true
//                                        showDialogPhone = true
                                        errorMessagePhone = "Vui lòng nhập sdt mới!"

                                    }
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
                                onClick = {
                                    showDialogPhone = false
                                    showErrorPhone = false
                                },
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

            getAddress(user, accountViewModel)

            loginResult?.let { result ->
                result.fold(
                    onFailure = { exception ->
                        when (requestType) {
                            "updateName" -> {
                                user.username = user.username
                                showErrorName = true
                                showDialogName = true
                                errorMessageName =
                                    exception.message ?: "Cập nhật tên thất bại"
                                Log.e(
                                    "UpdateName",
                                    "Cập nhật tên thất bại: ${exception.message}"
                                )
                            }

                            "updateMail" -> {
                                // Xử lý lỗi từ yêu cầu cập nhật email
                                user.email = user.email
                                showErrorEmail = true
                                showDialogEmail = true
                                errorMessageEmail =
                                    exception.message ?: "Cập nhật email thất bại"
                                Log.e(
                                    "UpdateEmail",
                                    "Cập nhật email thất bại: ${exception.message}"
                                )
                            }

                            "updatePhone" -> {
                                // Xử lý lỗi từ yêu cầu cập nhật số điện thoại
                                user.sdt = user.sdt
                                showErrorPhone = true
                                showDialogPhone = true
                                errorMessagePhone =
                                    exception.message ?: "Cập nhật sdt thất bại"
                                Log.e(
                                    "UpdatePhone",
                                    "Cập nhật sdt thất bại: ${exception.message}"
                                )
                            }

                            "updateAddress" -> {
                                // Xử lý lỗi từ yêu cầu cập nhật địa chỉ
                                Log.e(
                                    "UpdateAddress",
                                    "Cập nhật địa chỉ thất bại: ${exception.message}"
                                )
                            }

                            else -> {
                                // Xử lý lỗi từ yêu cầu đổi mật khẩu
                                showErrorPass = true
                                showSuccessPass = false
                                showDialogPass = true
                                errorMessagePass =
                                    exception.message ?: "Đổi mật khẩu thất bại"
                                Log.e(
                                    "ChangePass",
                                    "Đổi mật khẩu thất bại: ${exception.message}"
                                )
                            }
                        }
                    },
                    onSuccess = { type ->
                        LaunchedEffect(type) {
                            notificationViewModel.updateNotice("Bạn đã thay đổi thông tin cá nhân")
                        }
                        val request = type.second
                        when (request) {
                            "updateName" -> {
                                println("Cập nhật tên thành công")
                                showErrorName = false
                                showDialogName = false
                                user.username = newName
                            }

                            "updateMail" -> {
                                println("Cập nhật mail thành công")
                                showErrorEmail = false
                                showDialogEmail = false
                                user.email = newEmail
                            }

                            "updatePhone" -> {
                                println("Cập nhật sdt thành công")
                                showErrorPhone = false
                                showDialogPhone = false
                                user.sdt = newPhone.toInt()
                            }

                            "updateAddress" -> {
                                println("Cập nhật địa chỉ thành công")
                            }

                            "changePass" -> {
                                println("Cập nhật pass thành công")
                                showErrorPass = false
                                showSuccessPass = true
                            }

                            else -> {
                                println("Không có yêu cầu nào")
                            }
                        }
                    },
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AccountScreenPreview() {
    val context = LocalContext.current
    STTCTheme {
        AccountScreen(
            openBillShip = {},
            openBillHistory = {},
            openBillCancel = {},
            openLogout = {},
            AccountViewModel(context),
            notificationViewModel = NotificationViewModel(context)
        )
    }
}
package com.example.sttc.view

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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

class Account : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            STTCTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AccountScreen()
                }
            }
        }
    }
}

@Composable
fun AccountScreen() {
    val scrollState = rememberScrollState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)

    ) {
        Column (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            TopIcon()
            StatusBill()
            InfoAccount()
        }
    }

}

@Composable
fun TopIcon() {
    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .components {
            add(GifDecoder.Factory())
        }
        .build()

    Image(
        painter = rememberAsyncImagePainter(
            ImageRequest.Builder(context).data(data = R.drawable.topiconaccount).apply(block = {
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
fun StatusBill() {
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
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            IconButton(onClick = {
                /*TODO*/
            },
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
                    Text("Đang giao",
                        style = TextStyle(
                            fontSize = 16.sp,
                            color = Color(0xFFcc2900),

                        )
                    )
                }

            }

            IconButton(onClick = {
                /*TODO*/
            },
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
                    Text("Lịch sử mua",
                        style = TextStyle(
                            fontSize = 16.sp,
                            color = Color(0xFFcc2900)
                        )
                    )
                }

            }

            IconButton(onClick = {
                /*TODO*/
            },
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
                    Text("Đơn đã hủy",
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

data class ItemAccount (
    var name: String,
    var email: String,
    var phone: Int,
    var pass: String,
    var address: String

)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoAccount() {
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
        Row (
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
        ){
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

        Row (
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
        ){
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
                        ) },
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
                            Text("Sửa",
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
                            Text("Hủy",
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }
                    }
                )
            }
        }

        Row (
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
        ){
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
                        ) },
                    text = {
                        Column {
                            TextField(
                                value = currentPassword,
                                onValueChange = { currentPassword = it },
                                label = { Text("*Mật khẩu hiện tại:",
                                    style = TextStyle(
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Bold,
                                    ),
                                    modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 8.dp)
                                ) },
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
                                label = { Text("*Mật khẩu mới:",
                                    style = TextStyle(
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Bold,
                                    ),
                                    modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 8.dp)
                                ) },
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
                                label = { Text("*Nhập lại:",
                                    style = TextStyle(
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Bold,
                                    ),
                                    modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 8.dp)
                                ) },
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
                            Text("Đổi mật khẩu",
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
                            Text("Hủy",
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }
                    }
                )
            }
        }

        Row (
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
        ){
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
                        ) },
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
                            Text("Sửa",
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
                            Text("Hủy",
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }
                    }
                )
            }
        }

        Row (
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
        ){
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
                        ) },
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
                            Text("Sửa",
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
                            Text("Hủy",
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }
                    }
                )
            }
        }

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .height(110.dp)
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
        ){
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
                    .padding(0.dp, 0.dp, 10.dp, 0.dp)
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
                    modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 0.dp),)
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
                        ) },
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
                            Text("Sửa",
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
                            Text("Hủy",
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

@Preview(showBackground = true)
@Composable
fun AccountScreenPreview() {
    STTCTheme {
        AccountScreen()
    }
}
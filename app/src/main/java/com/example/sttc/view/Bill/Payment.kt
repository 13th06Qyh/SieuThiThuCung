package com.example.sttc.view

import android.content.Context
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
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
import com.example.sttc.model.PayData
import com.example.sttc.ui.theme.STTCTheme
import com.example.sttc.view.System.PayBillChoose
import com.example.sttc.view.System.ShipChoose
import com.example.sttc.view.System.formatNumber
import com.example.sttc.view.System.getLocation
import com.example.sttc.viewmodel.AccountViewModel

@Composable
fun PaymentScreen(
    back: () -> Unit,
    context: Context,
    openOTP: () -> Unit,
    openCard: () -> Unit,
    accountViewModel: AccountViewModel,
    openAccount: () -> Unit,
    selectedProducts: List<PayData>
) {
    Log.d("PaymentScreen", selectedProducts.toString())
    val scrollState = rememberScrollState()
    val userState by accountViewModel.userInfoFlow.collectAsState(initial = null)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Color(0xFFcccccc)
            )
//            .verticalScroll(scrollState)

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFffcccc)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TitlePayment(back)
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                item {
                    LocationPayment(accountViewModel, openAccount)
                    ShipChoose()
                    PayBillChoose(openOTP, openCard, accountViewModel)

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp, 0.dp)
                    ) {
                        for (item in selectedProducts) {
                            Column(
                                modifier = Modifier
                                    .padding(0.dp, 5.dp, 0.dp, 0.dp)
                                    .border(1.dp, color = Color(0xFFFFFFFF))
                                    .fillMaxWidth()
                                    .background(
                                        Color.White
                                    ),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {

                                Row(
                                    modifier = Modifier
                                        .padding(5.dp, 8.dp)
                                        .clickable { /* Do something! */ },
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceEvenly
                                ) {
                                    val productImages = item.image
                                    val fileName = productImages.substringAfterLast("/").substringBeforeLast(".")
                                    val resourceId = context.resources.getIdentifier(
                                        fileName,
                                        "drawable",
                                        context.packageName
                                    )
                                    val a = context.resources.getResourceName(resourceId)
                                    val b = a.substringAfter('/')
                                    if (b == fileName) {
                                        Image(
                                            painter = painterResource(id = resourceId),
                                            contentDescription = "Image",
                                            modifier = Modifier
                                                .size(100.dp)
                                                .padding(5.dp, 5.dp)
                                                .border(0.5.dp, color = Color.Black)
                                        )
                                    }else{
                                        Text(text = "Image not found")
                                    }

                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(100.dp)
                                            .padding(5.dp, 10.dp),
                                    ) {
                                        Text(
                                            text = item.name,
                                            style = TextStyle(
                                                fontSize = 18.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = Color.Black
                                            )
                                        )
                                        Spacer(modifier = Modifier.height(4.dp)) // Thêm khoảng cách ở đây
                                        Text(
                                            text = item.tag,
                                            style = TextStyle(
                                                fontSize = 13.sp,
                                                fontStyle = FontStyle.Italic,
                                                color = Color.Black,
                                            )
                                        )
                                        Text(
                                            "x" + item.quantity,
                                            style = TextStyle(
                                                fontSize = 14.sp,
                                                color = Color.Black,
                                                textAlign = TextAlign.End
                                            ),
                                            modifier = Modifier.fillMaxWidth()
                                        )
                                        val totalone = item.quantity * item.oneprice
                                        Text(
                                            formatNumber(totalone) + "đ",
                                            style = TextStyle(
                                                fontSize = 15.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = Color(0xFFcc2900),
                                                textAlign = TextAlign.End
                                            ),
                                            modifier = Modifier.fillMaxWidth()
                                        )
                                    }
                                }

                            }
                        }
                    }

                }
            }
            HorizontalDivider(thickness = 1.dp, color = Color(0xFF000000))
            SuccessPayment(selectedProducts)
        }
    }
}

@Composable
fun TitlePayment(
    back: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Brush.radialGradient(
                    colors = listOf(
                        Color(0xFFFFFFFF),
                        Color(0xFFFFE4E4),
                        Color(0xFFF6F2F2),
                    ),
                    radius = 600f
                )
            )
            .padding(top = 5.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        Icon(
            Icons.Default.ArrowBack, contentDescription = "Back",
            modifier = Modifier
                .size(50.dp)
                .padding(10.dp, 0.dp)
                .clickable { back() },
            tint = Color.Black
        )

        Text(
            text = "Thanh toán",
            style = TextStyle(
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = Color(0xFFcc2900)
            ),
            modifier = Modifier
                .padding(80.dp, 10.dp)
        )
    }
}

@Composable
fun LocationPayment(
    accountViewModel: AccountViewModel,
    openAccount: () -> Unit
) {
    val users by accountViewModel.userInfoFlow.collectAsState(null)
    var showDialogAddress by remember { mutableStateOf(false) }

    users?.let { user ->
        val checkedStateDefautl = remember { mutableStateOf(user.diachi != "Chưa có địa chỉ") }
        val checkedStateOther = remember { mutableStateOf(user.diachi == "Chưa có địa chỉ") }
        getLocation(checkedStateOther, checkedStateDefautl)
        Column(
            modifier = Modifier.background(Color.White)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp, 5.dp, 10.dp, 0.dp)
                    .background(Color.White),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "Location Icon",
                    tint = Color.Red,
                    modifier = Modifier.size(30.dp)
                )
                Text(
                    text = "Địa chỉ mặc định",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    ),
                    modifier = Modifier.padding(10.dp, 10.dp)
                )

                if (user.diachi == "Chưa có địa chỉ") {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        IconButton(onClick = { showDialogAddress = true }) {
                            Icon(
                                Icons.Default.Add,
                                contentDescription = "EditAddressAccount",
                                tint = Color.Red,
                                modifier = Modifier
                                    .size(25.dp)
                                    .border(1.dp, color = Color.Black, shape = CircleShape)
                                    .padding(0.dp, 0.dp, 0.dp, 0.dp)
                            )
                        }
                        if (showDialogAddress) {
                            AlertDialog(
                                containerColor = Color(0xffffe6e6),
                                onDismissRequest = { showDialogAddress = false },
                                title = {
                                    Text(
                                        "Đặt địa chỉ mặc định",
                                        style = TextStyle(
                                            textAlign = TextAlign.Center,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 23.sp,
                                        ),
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                },
                                text = {
                                    Text(
                                        "Chuyển hướng đến trang tài khoản ngay bây giờ?",
                                        style = TextStyle(
                                            textAlign = TextAlign.Center,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 17.sp,
                                            color = Color(0xFF000099)
                                        ),
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(50.dp)
                                    )
                                },
                                confirmButton = {
                                    Button(
                                        onClick = {
                                            openAccount()
                                            showDialogAddress = false
                                        },
                                        border = BorderStroke(1.dp, Color.Blue),
                                    ) {
                                        Text("Chuyển hướng")
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
                } else {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 12.dp),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Checkbox(
                            checked = checkedStateDefautl.value,
                            onCheckedChange = {
                                checkedStateDefautl.value = it
                                if (it) checkedStateOther.value = false
                            },
                            modifier = Modifier
                                .size(20.dp)
                        )
                    }
                }

            }

            OutlinedTextField(
                value = user.diachi,
                onValueChange = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp, 5.dp, 20.dp, 15.dp)
                    .background(Color.White),
                singleLine = false
            )

        }
    }
}

@Composable
fun SuccessPayment(
    selectedProducts: List<PayData>
) {
    val total = selectedProducts.sumOf { it.oneprice * it.quantity }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFe6ffff))
//            .border(2.dp, color = Color(0xFFcc2900))
        ,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        Text(
            text = "Tổng cộng: ",
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            ),
        )
        Text(
            text = formatNumber(total) + "đ",
            style = TextStyle(
                fontSize = 16.sp,
                color = Color.Red,
                fontWeight = FontWeight.Bold,
            ),
            modifier = Modifier.padding(end = 10.dp)
        )

        Button(
            shape = RectangleShape,
            onClick = { /*TODO: Add your action here*/ },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red,
            ),
            modifier = Modifier.height(55.dp)
        ) {
            Text(
                text = "Thanh toán",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                ),
            ) // Change the color of the text
        }
    }

}


@Preview(showBackground = true)
@Composable
fun PaymentPreview() {
    STTCTheme {
        PaymentScreen(
            back = { },
            context = LocalContext.current,
            openOTP = { },
            openCard = { },
            accountViewModel = AccountViewModel(
                LocalContext.current
            ),
            openAccount = { },
            selectedProducts = listOf(
                PayData(
                    1,
                    "image",
                    "name",
                    10000,
                    "tag",
                    1
                )
            )
        )
    }
}
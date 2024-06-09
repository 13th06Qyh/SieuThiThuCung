package com.example.sttc.view.System

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sttc.R
import com.example.sttc.model.PayData
import com.example.sttc.ui.theme.STTCTheme
import com.example.sttc.viewmodel.AccountViewModel
import com.example.sttc.viewmodel.SharedViewModel

@Composable
fun PayBillChoose(
    openOTP: () -> Unit,
    openCard: () -> Unit,
    accountViewModel: AccountViewModel,
    sharedViewModel: SharedViewModel
) {
    val userState by accountViewModel.userInfoFlow.collectAsState(initial = null)

    val checkedStateFace = remember { mutableStateOf(true) }
    val checkedStateCard = remember { mutableStateOf(false) }

    val bankDataState by sharedViewModel.bankDataInfoFlow.collectAsState(null)
    Log.d("BankData", "BankData: $bankDataState")
    var pay by remember { mutableStateOf("Chưa có thẻ ngân hàng") }

    LaunchedEffect(checkedStateFace.value, checkedStateCard.value) {
        if (!checkedStateFace.value && !checkedStateCard.value) {
            checkedStateFace.value = true
        }
    }

    bankDataState?.let { bankData ->
        pay = bankData.bankName + " - " + bankData.accountNumber
    }

    userState?.let { user ->
        Log.d("PayBillChoose", "User: $user")
        Column(
            modifier = Modifier
                .fillMaxWidth()
//            .border(1.dp, color = Color(0xFFFFFFFF))
                .padding(0.dp, 5.dp, 0.dp, 0.dp)
                .background(
                    Color.White
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.money),
                    contentDescription = "Money",
                    tint = Color(0xFFcc2900),
                    modifier = Modifier
                        .size(28.dp)
                        .padding(10.dp, 9.dp, 0.dp, 0.dp)
                )
                Text(
                    text = "Phương thức thanh toán",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                    ),
                    modifier = Modifier
                        .padding(10.dp, 10.dp)
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(40.dp, 0.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Thanh toán khi nhận hàng",
                    style = TextStyle(
                        fontSize = 17.sp,
                        fontStyle = FontStyle.Italic,
                        color = Color.Black,
                    ),
                    modifier = Modifier
                        .padding(5.dp, 10.dp)
                )

                Checkbox(
                    checked = checkedStateFace.value,
                    onCheckedChange = {
                        checkedStateFace.value = it
                        if (it) checkedStateCard.value = false
                    },
                    modifier = Modifier
                        .size(20.dp) // Thay đổi kích thước của checkbox
                        .padding(23.dp, 0.dp, 15.dp, 0.dp)
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(40.dp, 0.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Thanh toán bằng thẻ ngân hàng",
                    style = TextStyle(
                        fontSize = 17.sp,
                        fontStyle = FontStyle.Italic,
                        color = Color.Black,
                    ),
                    modifier = Modifier
                        .padding(5.dp, 10.dp)
                )

                Checkbox(
                    checked = checkedStateCard.value,
                    onCheckedChange = {
                        if(bankDataState == null) {
                            Log.d("Checkbox", "Opening Card screen")
                            openCard()
                        }else {
                            checkedStateCard.value = it
                            if (it) checkedStateFace.value = false
                        }
                    },
                    modifier = Modifier
                        .size(20.dp) // Thay đổi kích thước của checkbox
                        .padding(23.dp, 10.dp, 15.dp, 0.dp)
                )

            }


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(40.dp, 0.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = pay,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                    ),
                    modifier = Modifier
                        .padding(7.dp, 10.dp)
                        .width(250.dp)
                )

                IconButton(
                    onClick = {
                        if (user.otp == "Nope") {
                            Log.d("Button", "Opening OTP screen")
                            openOTP()
                        } else {
                            Log.d("Button", "Opening Card screen")
                            openCard()
                        }
                    },
                    modifier = Modifier.padding(2.dp, 0.dp, 15.dp, 0.dp)
                ) {
                    Icon(
                        Icons.Default.Add,
                        contentDescription = "AddBank",
                        tint = Color.Red,
                        modifier = Modifier
                            .size(22.dp)
                            .border(1.dp, color = Color.Red, shape = CircleShape)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Secret(
    back: () -> Unit,
    accountViewModel: AccountViewModel
) {
    val secret = remember { mutableStateOf("") }
    val createResult by accountViewModel.create.collectAsState(null)
    var showError by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    var showSuccess by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
//            .border(1.dp, color = Color(0xFFFFFFFF))
            .padding(0.dp, 5.dp, 0.dp, 0.dp)
            .background(
                Color.White
            )
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
                text = "Quay lại trang trước",
                style = TextStyle(
                    fontSize = 23.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = Color(0xFFcc2900)
                ),
                modifier = Modifier
                    .padding(50.dp, 10.dp)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                Icons.Filled.Create,
                contentDescription = "Money",
                tint = Color(0xFFcc2900),
                modifier = Modifier
                    .size(38.dp)
                    .padding(10.dp, 9.dp, 0.dp, 0.dp)
            )
            Text(
                text = "Tạo mật khẩu giao dịch",
                style = TextStyle(
                    fontSize = 23.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                ),
                modifier = Modifier
                    .padding(8.dp, 9.dp, 0.dp, 0.dp)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                modifier = Modifier
                    .padding(10.dp, 10.dp)
                    .fillMaxWidth()
                    .border(1.dp, Color.Blue),
                colors = TextFieldDefaults.textFieldColors(
                    disabledTextColor = Color.Gray,
                    containerColor = Color(0xffffd6cc),
                    focusedIndicatorColor = Color.Green,
                    unfocusedIndicatorColor = Color(0xffe62e00),
                    disabledIndicatorColor = Color.Gray,
                    cursorColor = Color.Blue,
                    errorCursorColor = Color.Red
                ),
                value = secret.value,
                onValueChange = { newValue ->
                    if (newValue.length <= 6 && newValue.all { it.isDigit() }) {
                        secret.value = newValue
                    }
                },
                placeholder = {
                    Text(
                        "Tạo mật khẩu giao dịch ở đây",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontStyle = FontStyle.Italic,
                            color = Color.Gray
                        )
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                visualTransformation = PasswordVisualTransformation(),
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (showError) {
                Text(
                    text = errorMessage,
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

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {

                    val otp = secret.value
                    if (otp.isNotEmpty()) {
                        accountViewModel.createOTP(otp)
                    } else {
                        showError = true
                        errorMessage = "Vui lòng nhập mã!"

                    }
                },
                modifier = Modifier

                    .padding(0.dp),
                shape = RoundedCornerShape(16.dp), // Định dạng góc bo tròn của nút
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFB8E1FF), // Màu nền của nút
                    contentColor = Color.Black // Màu chữ của nút
                )
            ) {
                Text(
                    text = "Tạo",
                    style = TextStyle(
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold
                    )

                )
            }
        }

        if (showSuccess) {
            Allow(back, showSuccess)
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "**Lưu ý: ",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontStyle = FontStyle.Italic,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .padding(10.dp, 10.dp)
            )

        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "- Mật khẩu giao dịch phải là 6 số.",
                style = TextStyle(
                    fontSize = 17.sp,
                    fontStyle = FontStyle.Italic,
                    color = Color.Black,
                ),
                modifier = Modifier
                    .padding(15.dp, 0.dp)
            )

        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "- Mật khẩu giao dịch sẽ được sử dụng để xác nhận giao dịch của bạn.",
                style = TextStyle(
                    fontSize = 17.sp,
                    fontStyle = FontStyle.Italic,
                    color = Color.Black,
                ),
                modifier = Modifier
                    .padding(15.dp, 5.dp)
            )

        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "- Hãy chắc chắn rằng bạn luôn nhớ mật khẩu giao dịch này!",
                style = TextStyle(
                    fontSize = 17.sp,
                    fontStyle = FontStyle.Italic,
                    color = Color.Black,
                ),
                modifier = Modifier
                    .padding(15.dp, 0.dp)
            )

        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "- Vui lòng không chia sẻ mật khẩu giao dịch của bạn với người khác!",
                style = TextStyle(
                    fontSize = 17.sp,
                    fontStyle = FontStyle.Italic,
                    color = Color.Black,
                ),
                modifier = Modifier
                    .padding(15.dp, 5.dp)
            )

        }

        DisposableEffect(Unit) {
            onDispose {
                secret.value = ""
            }
        }

        createResult?.let { result ->
            result.fold(
                onSuccess = { token ->
                    showSuccess = true

                    Log.d("Secret", "Tạo otp thành công")
                },
                onFailure = { exception ->
                    showError = true
                    errorMessage = exception.message ?: "Tạo otp thất bại"
                    Log.e("Secret", "Tạo otp thất bại: ${exception.message}")
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Card(
    back: () -> Unit,
    openCart: () -> Unit,
    sharedViewModel: SharedViewModel
) {
    val stk = remember { mutableStateOf("") }
    var bankName by remember { mutableStateOf("") }

    val banks = listOf(
        Bank("Ngân hàng Vietinbank", R.drawable.vietinbank),
        Bank("Ngân hàng Sacombank", R.drawable.sacombank),
        Bank("Ngân hàng Agribank", R.drawable.agribank),
        Bank("Ngân hàng HD Bank", R.drawable.hdbank),
        Bank("Ngân hàng BIDV", R.drawable.bidv),
        Bank("Ngân hàng Techcombank", R.drawable.techcombank),
        Bank("Ngân hàng TP Bank", R.drawable.tpbank),
        Bank("Ngân hàng MB", R.drawable.mb),
        Bank("Ngân hàng VIB", R.drawable.vib),
        Bank("Ngân hàng MSB", R.drawable.msb),
        Bank("Ngân hàng OCB", R.drawable.ocb),
        Bank("Ngân hàng ACB", R.drawable.acb),
        Bank("Ngân hàng VP Bankk", R.drawable.vpbankk)
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 5.dp, 0.dp, 0.dp)
            .background(Color.White)
    ) {
        // Header section
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            Color(0xFFFFFFFF),
                            Color(0xFFFFE4E4),
                            Color(0xFFF6F2F2)
                        ), radius = 600f
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
                text = "Quay lại trang trước",
                style = TextStyle(
                    fontSize = 23.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFcc2900)
                ),
                modifier = Modifier.padding(50.dp, 10.dp)
            )
        }

        // Select bank section
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp, 9.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                Icons.Filled.Home,
                contentDescription = "Money",
                tint = Color(0xFFcc2900),
                modifier = Modifier
                    .size(35.dp)
                    .padding(10.dp, 9.dp, 0.dp, 0.dp)
            )
            Text(
                text = "Chọn ngân hàng",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                ),
                modifier = Modifier.padding(8.dp, 9.dp)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            TextField(
                value = bankName,
                onValueChange = {},
                textStyle = TextStyle(
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(horizontal = 12.dp),
                placeholder = {
                    Text(
                        text = "Ngân hàng bạn chọn sẽ hiển thị ở đây",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontStyle = FontStyle.Italic,
                            color = Color.Gray
                        )
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    disabledTextColor = Color.Gray,
                    containerColor = Color(0xffffd6cc),
                    focusedIndicatorColor = Color.Green,
                    unfocusedIndicatorColor = Color(0xffe62e00),
                    disabledIndicatorColor = Color.Gray,
                    cursorColor = Color.Blue,
                    errorCursorColor = Color.Red
                ),
            )
        }

        // Bank icons section
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp, 0.dp),
        ) {
            items(banks.size) { index ->
                IconButton(
                    onClick = {
                        bankName = banks[index].name
                    },
                    modifier = Modifier
                        .padding(10.dp, 10.dp)
                        .width(100.dp)
                        .border(1.dp, Color.Black)
                ) {
                    Image(
                        painterResource(id = banks[index].image),
                        contentDescription = "Bank Icon",
                        modifier = Modifier.size(100.dp)
                    )
                }
            }
        }

        // Account input field section
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp, 5.dp, 15.dp, 15.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = stk.value,
                onValueChange = { newValue ->
                    if (newValue.all { it.isDigit() }) {
                        stk.value = newValue
                    }
                },
                placeholder = {
                    Text(
                        text = "Nhập số tài khoản của bạn",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontStyle = FontStyle.Italic,
                            color = Color.Gray
                        )
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White),
                singleLine = false
            )
        }

        // Transaction button section
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp, 10.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button( // Tạo nút giao dịch
                onClick = {
                    sharedViewModel.setSelectedBankData(BankData(bankName, stk.value))
                    Log.d("Card", "BankName: $bankName - STK: ${stk.value}")
                    back()
                },
                shape = RoundedCornerShape(2.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFff5c33),
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(7.dp, 5.dp)
            ) {
                Text(
                    text = "Giao dịch",
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                )
            }
        }
    }
}


@Composable
fun ShipChoose() {
    val checkedStateReceive = remember { mutableStateOf(false) }
    val checkedStateShip = remember { mutableStateOf(true) }
    LaunchedEffect(checkedStateReceive.value, checkedStateShip.value) {
        if (!checkedStateReceive.value && !checkedStateShip.value) {
            checkedStateShip.value = true
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
//            .border(1.dp, color = Color(0xFFFFFFFF))
            .padding(0.dp, 5.dp, 0.dp, 0.dp)
            .background(
                Color.White
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ship),
                contentDescription = "Money",
                tint = Color(0xFFcc2900),
                modifier = Modifier
                    .size(28.dp)
                    .padding(10.dp, 9.dp, 0.dp, 0.dp)
            )
            Text(
                text = "Hình thức vận chuyển",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                ),
                modifier = Modifier
                    .padding(10.dp, 10.dp)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(40.dp, 0.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Giao hàng tận nơi",
                style = TextStyle(
                    fontSize = 17.sp,
                    fontStyle = FontStyle.Italic,
                    color = Color.Black,
                ),
                modifier = Modifier
                    .padding(5.dp, 10.dp)
            )


            Checkbox(
                checked = checkedStateShip.value,
                onCheckedChange = {
                    checkedStateShip.value = it
                    if (it) checkedStateReceive.value = false
                },
                modifier = Modifier
                    .size(20.dp) // Thay đổi kích thước của checkbox
                    .padding(23.dp, 0.dp, 15.dp, 0.dp)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(40.dp, 0.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Nhận trực tiếp tại cửa hàng",
                style = TextStyle(
                    fontSize = 17.sp,
                    fontStyle = FontStyle.Italic,
                    color = Color.Black,
                ),
                modifier = Modifier
                    .padding(5.dp, 10.dp)
            )

            Checkbox(
                checked = checkedStateReceive.value,
                onCheckedChange = {
                    checkedStateReceive.value = it
                    if (it) checkedStateShip.value = false
                },
                modifier = Modifier
                    .size(20.dp) // Thay đổi kích thước của checkbox
                    .padding(23.dp, 0.dp, 15.dp, 0.dp)
            )
        }
    }
}

@Composable
fun PinDigitField(digit: String, onDigitChange: (String) -> Unit) {
    OutlinedTextField(
        value = digit,
        onValueChange = onDigitChange,
        modifier = Modifier
            .size(39.dp),
        singleLine = true,
        maxLines = 1,
        textStyle = LocalTextStyle.current.copy(fontSize = 24.sp, textAlign = TextAlign.Center),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.NumberPassword
        ),
        visualTransformation = VisualTransformation.None,
    )
}

@Preview(showBackground = true)
@Composable
fun PayPreview() {
    STTCTheme {
//        Card(back = {}, openCart = {})
//        Card(back = {})
        PayBillChoose(
            openOTP = { /*TODO*/ },
            openCard = { /*TODO*/ },
            accountViewModel = AccountViewModel(
                LocalContext.current
            ),
            sharedViewModel = SharedViewModel(LocalContext.current)
        )
    }
}
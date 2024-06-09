package com.example.sttc.view

import android.content.Context
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.sttc.R
import com.example.sttc.ui.theme.STTCTheme
import com.example.sttc.view.System.SuggestToday
import com.example.sttc.view.System.SuggestTodayopen
import com.example.sttc.viewmodel.AccountViewModel
import com.example.sttc.viewmodel.CartViewModel
import com.example.sttc.viewmodel.ProductViewModel


@Composable
fun InforBillHistoryShipScreen(
    back: () -> Unit,
    openDetailProducts: (id: Int) -> Unit,
    productViewModel: ProductViewModel,
    context: Context,
    openCart: () -> Unit,
    cartViewModel: CartViewModel,
    accountViewModel: AccountViewModel,
    id: Int,
) {
    val scrollState = rememberScrollState()
    val selectedOption = remember { mutableStateOf("") }
    val selectedAnimal = 0
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFf2f2f2))
            .verticalScroll(scrollState)

    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
//            TopIconInforBill()
            TitleInforBill(back)
            BillSuccess()
            ContentInforBill(openCart, cartViewModel, productViewModel, accountViewModel, id)
            PayBill()
            LocationReceive()
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                HorizontalDivider(
                    modifier = Modifier
                        .weight(1f)
                        .padding(0.dp, 10.dp),  // Take up half the space
                    thickness = 1.2.dp,
                    color = Color.Gray
                )
                Text(
                    text = "Có thể bạn quan tâm",
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontStyle = FontStyle.Italic,
                        color = Color.Black,
                    ),
                    modifier = Modifier.padding(10.dp, 0.dp, 10.dp, 10.dp)
                )
                HorizontalDivider(
                    modifier = Modifier
                        .weight(1f)
                        .padding(0.dp, 10.dp),  // Take up half the space
                    thickness = 1.2.dp,
                    color = Color.Gray
                )
            }
            SuggestToday(
                openDetailProducts,
                productViewModel,
                context,
                selectedOption.value,
                selectedAnimal
            )
        }
    }

}

@Composable
fun BillSuccess() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Color(0xFF0a2929)
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Color(0xFFccffff)
                )
                .padding(5.dp, 20.dp, 0.dp, 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .width(270.dp)
            ) {
                Text(
                    text = "Đơn hàng đã hoàn thành",
                    style = TextStyle(
                        fontSize = 17.sp,
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF239090),
                    ),
                    modifier = Modifier
                        .padding(10.dp, 10.dp, 0.dp, 0.dp)
                )

                Text(
                    text = "Cảm ơn bạn đã tin tưởng mua sản phẩm của Pet Shop!",
                    style = TextStyle(
                        fontSize = 17.sp,
                        fontStyle = FontStyle.Italic,
                        color = Color(0xFF239090),
                    ),
                    modifier = Modifier
                        .padding(10.dp, 10.dp, 0.dp, 10.dp)
                )
            }

            Image(
                painter = painterResource(id = R.drawable.receiversuccess),
                contentDescription = "Receive",
                modifier = Modifier
                    .size(90.dp)
                    .padding(0.dp, 0.dp, 15.dp, 0.dp)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp, 25.dp, 10.dp, 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Mã đơn hàng",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                )
            )
            Text(
                text = "123456",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                ),
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Thời gian đặt hàng",
                style = TextStyle(
                    fontSize = 18.sp,
                    color = Color.White,
                ),
                modifier = Modifier
                    .padding(10.dp, 10.dp)
            )
            Text(
                text = "11-4-2024 01:23",
                style = TextStyle(
                    fontSize = 18.sp,
                    color = Color.White,
                ),
                modifier = Modifier
                    .padding(10.dp, 10.dp)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Trạng thái",
                style = TextStyle(
                    fontSize = 18.sp,
                    color = Color.White,
                ),
                modifier = Modifier
                    .padding(10.dp, 10.dp)
            )
            Text(
                text = "Đã giao hàng",
                style = TextStyle(
                    fontSize = 18.sp,
                    color = Color.White,
                ),
                modifier = Modifier
                    .padding(10.dp)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Thời gian nhận hàng",
                style = TextStyle(
                    fontSize = 18.sp,
                    color = Color.White,
                ),
                modifier = Modifier
                    .padding(10.dp, 10.dp)
            )
            Text(
                text = "21-04-2024 11:27",
                style = TextStyle(
                    fontSize = 18.sp,
                    color = Color.White,
                ),
                modifier = Modifier
                    .padding(10.dp)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Thời gian thanh toán",
                style = TextStyle(
                    fontSize = 18.sp,
                    color = Color.White,
                ),
                modifier = Modifier
                    .padding(10.dp, 10.dp)
            )
            Text(
                text = "21-04-2024 11:27",
                style = TextStyle(
                    fontSize = 18.sp,
                    color = Color.White,
                ),
                modifier = Modifier
                    .padding(10.dp)
            )
        }
    }
}

@Composable
fun SuccessPay(
    openCart: () -> Unit,
    cartViewModel: CartViewModel,
    productViewModel: ProductViewModel,
    accountViewModel: AccountViewModel,
    id: Int,
) {
    val products by productViewModel.products.collectAsState(initial = emptyList())
    val user by accountViewModel.userInfoFlow.collectAsState(null)
    val addCarts by cartViewModel.add.collectAsState(null)
    val product = products.find { it.maSP == id }
    var showErrorCart by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    var showDialogError by remember { mutableStateOf(false) }
    var okButtonPressed by remember { mutableStateOf(false) }

    HorizontalDivider(thickness = 1.2.dp, color = Color(0xFFcccccc))
    Row(
        modifier = Modifier
            .fillMaxWidth()
//                        .border(1.dp, color = Color(0xFFcc2900))
            .background(
                Color.White
            ),
        horizontalArrangement = Arrangement.End

    ) {
        Button(
            onClick = {
                okButtonPressed = false
                Log.d("AddToCartButton", "Button clicked")
                user?.let { user ->
                    Log.d("AddToCartButton", "User: $user")
                    if (user.id == 0) {
                        showDialogError = true
                        errorMessage = "Vui lòng đăng nhập để thêm vào giỏ hàng"
                        Log.d("AddToCartButton", "User not logged in")
                    } else {
                        if (product != null) {
                            val idsp = product.maSP
                            val iduser = user.id
                            Log.d(
                                "AddToCartButton",
                                "Adding to cart: Product ID: $idsp, User ID: $iduser"
                            )
                            cartViewModel.addCart(idsp, iduser)
                        } else {
                            Log.d("AddToCartButton", "Product is null")
                        }
                    }
                } ?: run {
                    Log.d("AddToCartButton", "User is null")
                }
            },
            shape = RoundedCornerShape(2.dp), // Định dạng góc bo tròn của nút
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFcc2900), // Màu nền của nút
                contentColor = Color.White // Màu chữ của nút
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(7.dp, 5.dp)
        ) {
            Text(
                text = "Mua lại",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                ),
            )
        }

        if(errorMessage == "Sản phẩm đã có trong giỏ hàng!"){
            showDialogError = false
            openCart()
        }

        if (showDialogError) {
            AlertDialog(
                containerColor = Color(0xFFccf5ff),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.size(400.dp, 150.dp),
                onDismissRequest = { showDialogError = false },
                title = {},
                text = {},
                confirmButton = {},
                dismissButton = {
                    Column {
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
                                .height(50.dp)
                        )
                        Button(
                            onClick = {
                                showDialogError = false
                                if(errorMessage == "Sản phẩm đã có trong giỏ hàng!"){
                                    openCart()
                                }
                                okButtonPressed = true
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFccffdd), // Màu nền của nút
                                contentColor = Color.Black, // Màu chữ của nút
                            ),
                            modifier = Modifier.fillMaxWidth(),
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
                }
            )
        }

        addCarts?.let { result ->
            result.fold(
                onSuccess = { token ->
                    println("Them vao gio thành công")
                },
                onFailure = { exception ->
                    showErrorCart = true
                    if (!okButtonPressed) {
                        showDialogError = true
                    }
                    errorMessage = exception.message ?: "AddCart thất bại"
                    Log.e("AddCart", "AddCart thất bại: ${exception.message}")
                }
            )

        }
    }

}

@Preview(showBackground = true)
@Composable
fun InforBillHistoryScreenPreview() {
    STTCTheme {
        InforBillHistoryShipScreen(
            back = {},
            openDetailProducts = {},
            ProductViewModel(),
            LocalContext.current,
            openCart = {},
            CartViewModel(LocalContext.current),
            AccountViewModel(LocalContext.current),
            1
        )
//        MyApp()
//        SignUpForm(navController = rememberNavController(), authController = AuthController())
    }
}
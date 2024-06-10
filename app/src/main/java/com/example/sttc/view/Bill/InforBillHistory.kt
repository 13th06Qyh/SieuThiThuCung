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
import androidx.compose.runtime.LaunchedEffect
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
import com.example.sttc.model.billDetail
import com.example.sttc.ui.theme.STTCTheme
import com.example.sttc.view.System.SuggestToday
import com.example.sttc.view.System.SuggestTodayopen
import com.example.sttc.view.System.formatCreatedAt
import com.example.sttc.view.System.formatNumber
import com.example.sttc.viewmodel.AccountViewModel
import com.example.sttc.viewmodel.BillViewModel
import com.example.sttc.viewmodel.CartViewModel
import com.example.sttc.viewmodel.ProductViewModel


@Composable
fun InforBillHistoryShipScreen(
    back: () -> Unit,
    openDetailProducts: (id: Int) -> Unit,
    productViewModel: ProductViewModel,
    context: Context,
    billViewModel: BillViewModel,
    billId: Int,
    openCart: () -> Unit,
    cartViewModel: CartViewModel,
    accountViewModel: AccountViewModel,
    id: Int,
) {
    var items by remember { mutableStateOf(emptyList<billDetail>()) }

    LaunchedEffect(Unit) {

        billViewModel.fetchBillDetail(billId)
        billViewModel.billDetail.collect { value ->
            items = value
        }
    }

    Log.e("billId", billId.toString())
    Log.e("billDetail", items.toString())
    val scrollState = rememberScrollState()
    val selectedOption = remember { mutableStateOf("") }
    val selectedAnimal = 0

    val totalAmount = items.sumBy { it.buyprice * it.soluong }
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
            if (items.isNotEmpty()) {
                BillSuccess(item = items[0])
            }
            items.forEach { item ->
                Column(
                    modifier = Modifier
                        .padding(0.dp, 10.dp, 0.dp, 0.dp)
                        .border(1.dp, color = Color(0xFFFFFFFF))
                        .fillMaxWidth()
                        .background(Color.White),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Text(
                            text = item.proname,
                            style = TextStyle(
                                fontSize = 15.sp,
                                fontStyle = FontStyle.Italic,
                                textAlign = TextAlign.Start,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF000000),
                            ),
                            modifier = Modifier
                                .padding(10.dp, 5.dp)
                        )
                    }
                    HorizontalDivider(thickness = 1.2.dp, color = Color(0xFFcccccc))
                    Row(
                        modifier = Modifier
                            .padding(5.dp, 0.dp)
                            .clickable { /* Do something! */ },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        val imageUrl = item.image
                        val fileName =
                            imageUrl.substringAfterLast("/").substringBeforeLast(".")
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
                                    .size(120.dp)
                                    .padding(5.dp, 5.dp)
                                    .border(0.1.dp, color = Color.Black)
                            )
                        } else {
                            Image(
                                painter = painterResource(id = R.drawable.rs3),
                                contentDescription = "Image",
                                modifier = Modifier
                                    .size(100.dp)
                                    .padding(5.dp, 5.dp)
                                    .border(0.1.dp, color = Color.Black)
                            )
                        }
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp)
                                .padding(5.dp, 10.dp),
                        ) {
                            Text(
                                text = item.tensp,
                                style = TextStyle(
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black
                                )
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = item.tagname,
                                style = TextStyle(
                                    fontSize = 13.sp,
                                    fontStyle = FontStyle.Italic,
                                    color = Color.Black,
                                )
                            )
                            Text(
                                "x${item.soluong}",
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    color = Color.Black,
                                    textAlign = TextAlign.End
                                ),
                                modifier = Modifier.fillMaxWidth()
                            )
                            Text(
                                "${formatNumber(item.buyprice)}đ",
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

                    SuccessPay(
                        openCart,
                        cartViewModel,
                        productViewModel,
                        accountViewModel,
                        id
                    )

                    HorizontalDivider(thickness = 1.2.dp, color = Color(0xFFcccccc))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Thành tiền",
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.End,
                                color = Color.Black,
                            ),
                            modifier = Modifier
                                .padding(5.dp, 10.dp)
                        )
                        Text(
                            text = "${formatNumber(item.buyprice * item.soluong)}đ",
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.End,
                                color = Color.Black,
                            ),
                            modifier = Modifier
                                .padding(5.dp, 10.dp)
                        )
                    }

                    HorizontalDivider(thickness = 1.2.dp, color = Color(0xFFcccccc))
                }
            }
            Money(totalAmount = totalAmount)
            if (items.isNotEmpty()) {
                PayBill(item = items[0])
                LocationReceive(item = items[0])
            }
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
fun BillSuccess(
    item: billDetail
) {
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
                text = item.maBill.toString(),
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
                text = formatCreatedAt(item.created_at),
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
                text = formatCreatedAt(item.updated_at),
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
                text = formatCreatedAt(item.updated_at),
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

        if (errorMessage == "Sản phẩm đã có trong giỏ hàng!") {
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
                                if (errorMessage == "Sản phẩm đã có trong giỏ hàng!") {
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

@Composable
fun ContentInforBillH(
    openCart: () -> Unit,
    cartViewModel: CartViewModel,
    productViewModel: ProductViewModel,
    accountViewModel: AccountViewModel,
    id: Int,
    billViewModel: BillViewModel,
    billId: Int,
    context: Context
) {
    var billDetail by remember { mutableStateOf<List<billDetail>>(emptyList()) }
    LaunchedEffect(Unit) {
        billViewModel.fetchBillDetail(billId)
        billViewModel.billDetail.collect {
            billDetail = it
        }
    }
    Log.e("BillId", billId.toString())
    Log.e("listBillDetail", billDetail.toString())

    Column(
        modifier = Modifier
            .padding(0.dp, 10.dp, 0.dp, 0.dp)
            .border(1.dp, color = Color(0xFFFFFFFF))
            .fillMaxWidth()
            .background(
                Color.White
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        for (item in billDetail) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = item.proname,
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontStyle = FontStyle.Italic,
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF000000),
                    ),
                    modifier = Modifier
                        .padding(10.dp, 5.dp)
                )
            }
            HorizontalDivider(thickness = 1.2.dp, color = Color(0xFFcccccc))
            Column() {

                Row(
                    modifier = Modifier
                        .padding(5.dp, 0.dp)
                        .clickable { /* Do something! */ },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    val imageUrl = item.image
                    val fileName =
                        imageUrl.substringAfterLast("/").substringBeforeLast(".")
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
                                .size(120.dp)
                                .padding(5.dp, 5.dp)
                                .border(0.1.dp, color = Color.Black)
                        )
                    } else {
                        Image(
                            painter = painterResource(id = R.drawable.rs3),
                            contentDescription = "Image",
                            modifier = Modifier
                                .size(100.dp)
                                .padding(5.dp, 5.dp)
                                .border(0.1.dp, color = Color.Black)
                        )
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                            .padding(5.dp, 10.dp),
                    ) {
                        Text(
                            text = item.tensp,
                            style = TextStyle(
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        )
                        Spacer(modifier = Modifier.height(4.dp)) // Thêm khoảng cách ở đây
                        Text(
                            text = item.tagname,
                            style = TextStyle(
                                fontSize = 13.sp,
                                fontStyle = FontStyle.Italic,
                                color = Color.Black,
                            )
                        )
                        Text(
                            "x" + item.soluong.toString(),
                            style = TextStyle(
                                fontSize = 14.sp,
                                color = Color.Black,
                                textAlign = TextAlign.End
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )
                        Text(
                            formatNumber(item.buyprice) + "đ",
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

//                SuccessPay(
//                    openCart,
//                    cartViewModel,
//                    productViewModel,
//                    accountViewModel,
//                    id
//                )
            }

            HorizontalDivider(thickness = 1.2.dp, color = Color(0xFFcccccc))


            Row(
                modifier = Modifier
                    .fillMaxWidth()
//                        .border(1.dp, color = Color(0xFF006600))
                ,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Thành tiền",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.End,
                        color = Color.Black,
                    ),
                    modifier = Modifier
                        .padding(10.dp, 10.dp)
                )
                Text(
                    text = formatNumber(item.buyprice * item.soluong) + "đ",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.End,
                        color = Color.Black,
                    ),
                    modifier = Modifier
                        .padding(10.dp, 10.dp)
                )

            }
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
            BillViewModel(LocalContext.current),
            0,
            openCart = {},
            CartViewModel(LocalContext.current),
            AccountViewModel(LocalContext.current),
            1
        )
//        MyApp()
//        SignUpForm(navController = rememberNavController(), authController = AuthController())
    }
}
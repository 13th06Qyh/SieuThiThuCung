//import androidx.compose.foundation.BorderStroke
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.AlertDialog
//import androidx.compose.material3.Button
//import androidx.compose.material3.ButtonDefaults
//import androidx.compose.material3.Text
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.example.sttc.view.System.PinDigitField
//
////package com.example.sttc.view.Cart
////
////import android.content.Context
////import android.util.Log
////import androidx.compose.foundation.BorderStroke
////import androidx.compose.foundation.Image
////import androidx.compose.foundation.background
////import androidx.compose.foundation.border
////import androidx.compose.foundation.clickable
////import androidx.compose.foundation.layout.Arrangement
////import androidx.compose.foundation.layout.Column
////import androidx.compose.foundation.layout.Row
////import androidx.compose.foundation.layout.Spacer
////import androidx.compose.foundation.layout.fillMaxSize
////import androidx.compose.foundation.layout.fillMaxWidth
////import androidx.compose.foundation.layout.height
////import androidx.compose.foundation.layout.padding
////import androidx.compose.foundation.layout.size
////import androidx.compose.foundation.layout.width
////import androidx.compose.foundation.lazy.LazyColumn
////import androidx.compose.foundation.lazy.items
////import androidx.compose.material.icons.Icons
////import androidx.compose.material.icons.filled.Add
////import androidx.compose.material.icons.filled.ArrowBack
////import androidx.compose.material.icons.filled.ShoppingCart
////import androidx.compose.material3.AlertDialog
////import androidx.compose.material3.Button
////import androidx.compose.material3.ButtonDefaults
////import androidx.compose.material3.Checkbox
////import androidx.compose.material3.CheckboxDefaults
////import androidx.compose.material3.HorizontalDivider
////import androidx.compose.material3.Icon
////import androidx.compose.material3.IconButton
////import androidx.compose.material3.Text
////import androidx.compose.material3.TextButton
////import androidx.compose.runtime.Composable
////import androidx.compose.runtime.LaunchedEffect
////import androidx.compose.runtime.collectAsState
////import androidx.compose.runtime.getValue
////import androidx.compose.runtime.mutableStateOf
////import androidx.compose.runtime.remember
////import androidx.compose.runtime.setValue
////import androidx.compose.ui.Alignment
////import androidx.compose.ui.Modifier
////import androidx.compose.ui.graphics.Brush
////import androidx.compose.ui.graphics.Color
////import androidx.compose.ui.graphics.RectangleShape
////import androidx.compose.ui.platform.LocalContext
////import androidx.compose.ui.res.painterResource
////import androidx.compose.ui.text.TextStyle
////import androidx.compose.ui.text.font.FontStyle
////import androidx.compose.ui.text.font.FontWeight
////import androidx.compose.ui.text.style.TextAlign
////import androidx.compose.ui.tooling.preview.Preview
////import androidx.compose.ui.unit.dp
////import androidx.compose.ui.unit.sp
////import com.example.sttc.R
////import com.example.sttc.ui.theme.STTCTheme
////import com.example.sttc.view.System.formatNumber
////import com.example.sttc.viewmodel.AccountViewModel
////import com.example.sttc.viewmodel.CartViewModel
////import com.example.sttc.viewmodel.ProductViewModel
////import kotlinx.coroutines.delay
////
////@Composable
////fun CartScreen(
////    back: () -> Unit,
////    openDetailProducts: (id: Int) -> Unit,
////    accountViewModel: AccountViewModel,
////    cartViewModel: CartViewModel,
////    context: Context
////) {
////    val users by accountViewModel.userInfoFlow.collectAsState(null)
////    val carts by cartViewModel.cart.collectAsState(emptyList())
////    val destroy by cartViewModel.delete.collectAsState(null)
////    var openDialogDeleteCart by remember { mutableStateOf(false) }
////
////    LaunchedEffect(users) {
////        users?.let { user ->
////            cartViewModel.fetchCart()
////            Log.d("CartScreenUU", "User ID: ${user.id}")
////        }
////    }
////
////    Log.d("CartScreen", "Carts: $carts")
////
////    Column(
////        modifier = Modifier.fillMaxSize(),
////        verticalArrangement = Arrangement.Center,
////        horizontalAlignment = Alignment.CenterHorizontally
////    ) {
////        Row(
////            modifier = Modifier
////                .fillMaxWidth()
////                .background(
////                    Brush.radialGradient(
////                        colors = listOf(
////                            Color(0xFFFFFFFF),
////                            Color(0xFFFFE4E4),
////                            Color(0xFFF6F2F2),
////                        ),
////                        radius = 600f
////                    )
////                )
////                .padding(top = 0.dp),
////            horizontalArrangement = Arrangement.Start
////        ) {
////            Icon(
////                Icons.Default.ArrowBack, contentDescription = "Back",
////                modifier = Modifier
////                    .size(50.dp)
////                    .padding(10.dp, 0.dp)
////                    .clickable { back() },
////                tint = Color.Black
////            )
////            Row(
////                modifier = Modifier
////                    .padding(70.dp, 0.dp, 0.dp, 0.dp),
////                horizontalArrangement = Arrangement.Center,
////                verticalAlignment = Alignment.CenterVertically
////            ) {
////                Icon(
////                    Icons.Default.ShoppingCart, contentDescription = "Add to cart",
////                    modifier = Modifier
////                        .size(35.dp)
////                        .padding(0.dp, 0.dp, 5.dp, 0.dp),
////                    tint = Color(0xFFcc2900)
////                )
////                Text(
////                    text = "Giỏ hàng",
////                    style = TextStyle(
////                        fontSize = 25.sp,
////                        fontWeight = FontWeight.Bold,
////                        textAlign = TextAlign.Center,
////                        color = Color(0xFFcc2900)
////                    ),
////                    modifier = Modifier
////                        .padding(5.dp, 10.dp)
////                )
////            }
////        }
////
////        LazyColumn(
////            modifier = Modifier
////                .weight(1f)
////                .fillMaxWidth()
////                .background(Color(0xFFffcccc))
////                .padding(5.dp, 5.dp)
////        ) {
////            items(carts) { cart ->
////                val product = cart.sanpham
////                Column(
////                    modifier = Modifier
////                        .padding(0.dp, 0.dp, 0.dp, 10.dp)
////                        .border(1.dp, color = Color(0xFFFFFFFF))
////                        .fillMaxWidth()
////                        .background(
////                            Color.White
////                        ),
////                    horizontalAlignment = Alignment.CenterHorizontally
////                ) {
////                    Row(
////                        modifier = Modifier
////                            .fillMaxWidth()
////                            .height(40.dp),
////                        verticalAlignment = Alignment.CenterVertically,
////                        horizontalArrangement = Arrangement.SpaceBetween
////                    ) {
////                        Text(
////                            text = cart.proname,
////                            style = TextStyle(
////                                fontSize = 15.sp,
////                                fontStyle = FontStyle.Italic,
////                                textAlign = TextAlign.Start,
////                                fontWeight = FontWeight.Bold,
////                                color = Color(0xFF000000),
////                            ),
////                            modifier = Modifier
////                                .padding(10.dp, 5.dp)
////                        )
////
////                        val checkedState = remember { mutableStateOf(false) }
////                        Checkbox(
////                            checked = checkedState.value,
////                            onCheckedChange = { checkedState.value = it },
////                            modifier = Modifier
////                                .size(20.dp) // Thay đổi kích thước của checkbox
////                                .padding(0.dp, 0.dp, 15.dp, 0.dp)
////                        )
////                    }
////
////                    HorizontalDivider(thickness = 1.dp, color = Color.Gray)
////
////                    Row(
////                        modifier = Modifier
////                            .clickable { openDetailProducts(product.maSP) },
////                        verticalAlignment = Alignment.CenterVertically,
////                        horizontalArrangement = Arrangement.SpaceEvenly
////                    ) {
////                        val productImages = cart.image
////                        val imageUrl = productImages
////                        val fileName =
////                            imageUrl.substringAfterLast("/").substringBeforeLast(".")
////                        val resourceId = context.resources.getIdentifier(
////                            fileName,
////                            "drawable",
////                            context.packageName
////                        )
////                        val a = context.resources.getResourceName(resourceId)
////                        val b = a.substringAfter('/')
//////                        Log.d("test", "FileName: $fileName")
//////                        Log.d("test", "ResourceId: $resourceId")
//////                        Log.d("test", "ResourceName1: $a")
//////                        Log.d("test", "ResourceName2: $b")
////                        if (b == fileName) {
////                            Image(
////                                painter = painterResource(id = resourceId),
////                                contentDescription = "Image",
////                                modifier = Modifier
////                                    .size(100.dp)
////                                    .padding(5.dp, 5.dp)
////                                    .border(0.1.dp, color = Color.Black)
////                            )
////                        } else {
////                            Text(text = "Image not found")
////                        }
////
////
////                        Column(
////                            modifier = Modifier
////                                .fillMaxWidth()
////                                .height(100.dp)
////                                .padding(5.dp, 16.dp),
////                        ) {
////                            Text(
////                                text = product.tensp,
////                                style = TextStyle(
////                                    fontSize = 22.sp,
////                                    fontWeight = FontWeight.Bold,
////                                    color = Color.Black
////                                )
////                            )
////                            Spacer(modifier = Modifier.height(4.dp)) // Thêm khoảng cách ở đây
////
////                            Text(
////                                text = cart.tagname,
////                                style = TextStyle(
////                                    fontSize = 16.sp,
////                                    fontStyle = FontStyle.Italic,
////                                    color = Color.Black,
////                                )
////                            )
////
////                            val price = product.buyprice.toInt()
////                            Text(
////                                "Giá: " + formatNumber(price) + "đ",
////                                style = TextStyle(
////                                    fontSize = 18.sp,
////                                    fontWeight = FontWeight.Bold,
////                                    color = Color.Red,
////                                    textAlign = TextAlign.End
////                                ),
////                                modifier = Modifier.fillMaxWidth()
////                            )
////                        }
////                    }
////
////                    HorizontalDivider(thickness = 1.dp, color = Color.Gray)
////                    HorizontalDivider(thickness = 10.dp, color = Color.White)
////
////                    Row(
////                        modifier = Modifier
////                            .fillMaxWidth()
////                            .height(50.dp)
////                            .padding(end = 10.dp),
////                        horizontalArrangement = Arrangement.Start,
////                        verticalAlignment = Alignment.CenterVertically
////                    ) {
////                        // Delete Icon Button
////                        IconButton(
////                            onClick = {
////                                val cartIdToDelete = cart.maCart// Lưu lại cartId khi nhấn nút
////                                Log.d(
////                                    "CartScreenD",
////                                    "Delete button clicked for cart ID: $cartIdToDelete"
////                                )
////                                cartViewModel.deleteCart(cart.maCart)
////                            },
////                            modifier = Modifier.size(40.dp)
////                        ) {
////                            Icon(
////                                painter = painterResource(id = R.drawable.trash3),
////                                contentDescription = "Delete",
////                                tint = Color.Red,
////                                modifier = Modifier.size(30.dp)
////                            )
////                        }
////
////                        // Quantity Control Row
////                        Row(
////                            modifier = Modifier
////                                .fillMaxWidth(),
////                            horizontalArrangement = Arrangement.End,
////                            verticalAlignment = Alignment.CenterVertically
////                        ) {
////                            // Remove Button
////                            IconButton(
////                                onClick = { /* Handle remove action */ },
////                                modifier = Modifier
////                                    .height(40.dp)
////                                    .border(1.dp, Color.Gray)
////                            ) {
////                                Icon(
////                                    painter = painterResource(id = R.drawable.remove),
////                                    contentDescription = "Remove",
////                                    tint = Color(0xFF4d4d4d)
////                                )
////                            }
////
////                            // Quantity Display
////                            Row(
////                                modifier = Modifier
////                                    .width(60.dp)
////                                    .height(45.dp)
////                                    .border(1.dp, Color.Gray),
////                                horizontalArrangement = Arrangement.Center,
////                                verticalAlignment = Alignment.CenterVertically
////                            ) {
////                                Text(
////                                    text = "1",
////                                    style = TextStyle(
////                                        fontSize = 20.sp,
////                                        color = Color.Black,
////                                        textAlign = TextAlign.Center
////                                    )
////                                )
////                            }
////
////                            // Add Button
////                            IconButton(
////                                onClick = { /* Handle add action */ },
////                                modifier = Modifier
////                                    .height(40.dp)
////                                    .border(1.dp, Color.Gray)
////                            ) {
////                                Icon(
////                                    imageVector = Icons.Default.Add,
////                                    contentDescription = "Add",
////                                    tint = Color(0xFF4d4d4d)
////                                )
////                            }
////                        }
////                    }
////                    HorizontalDivider(thickness = 10.dp, color = Color.White)
////                }
////
////
////
////                LaunchedEffect(destroy) {
////                    destroy?.let {
////                        if (it.isSuccess) {
////                            cartViewModel.fetchCart() // Cập nhật lại giỏ hàng sau khi xóa thành công
////                        } else {
////                            Log.e(
////                                "CartScreen",
////                                "Failed to delete cart: ${it.exceptionOrNull()?.message}"
////                            )
////                        }
////                    }
////                }
////
////            }
////
////        }
////
////        HorizontalDivider(thickness = 1.dp, color = Color(0xFFcc2900))
////
////        Row(
////            modifier = Modifier
////                .fillMaxWidth()
////                .background(Color(0xFFe6ffff))
//////                .border(2.dp, color = Color(0xFFcc2900))
////            ,
////            verticalAlignment = Alignment.CenterVertically,
////            horizontalArrangement = Arrangement.SpaceBetween
////        ) {
////            Row(
////                modifier = Modifier
////                    .width(95.dp)
////                    .padding(start = 10.dp)
////                    .clickable { /*TODO*/ },
////                verticalAlignment = Alignment.CenterVertically
////            ) {
////                Checkbox(
////                    checked = false,
////                    onCheckedChange = { /*TODO: Add your action here*/ },
////                    modifier = Modifier
////                        .size(20.dp),
////                    colors = CheckboxDefaults.colors(
////                        checkmarkColor = Color(0xFFcc2900),
////                        checkedColor = Color(0xFFcc2900),
////                        uncheckedColor = Color.Gray,
////                    )
////                )
////                Text(
////                    text = "Tất cả",
////                    modifier = Modifier.padding(start = 10.dp),
////                )
////            }
////            Row(
////                modifier = Modifier.fillMaxWidth(),
////                horizontalArrangement = Arrangement.End,
////                verticalAlignment = Alignment.CenterVertically
////            ) {
////                Column(
////                ) {
////                    Text(
////                        text = "Tổng cộng: ",
////                        style = TextStyle(
////                            fontSize = 14.sp,
////                            fontWeight = FontWeight.Bold,
////                            color = Color.Black
////                        ),
////                        modifier = Modifier.padding(bottom = 3.dp)
////                    )
////                    Text(
////                        text = formatNumber(1000000) + "đ ",
////                        color = Color.Red,
////                        fontWeight = FontWeight.Bold
////                    )
////                }
////                Button(
////                    shape = RectangleShape,
////                    onClick = { /*TODO: Add your action here*/ },
////                    colors = ButtonDefaults.buttonColors(
////                        containerColor = Color.Red,
////                    ),
////                    modifier = Modifier.height(63.dp)
////                ) {
////                    Text(
////                        text = "Mua hàng",
////                        style = TextStyle(
////                            fontSize = 18.sp,
////                            fontWeight = FontWeight.Bold,
////                            color = Color.White
////                        ),
////                    ) // Change the color of the text
////                }
////            }
////        }
////    }
////
////}
////
////
////@Preview(showBackground = true)
////@Composable
////fun CartPreview() {
////    STTCTheme {
////        CartScreen(
////            back = {},
////            openDetailProducts = {},
////            AccountViewModel(LocalContext.current),
////            CartViewModel(LocalContext.current),
////            LocalContext.current
////        )
////    }
////}
////
//
//
//
//
//
//
//
//
//
//// Dialog section
//if (showDialogSecret) {
//    AlertDialog(
//        modifier = Modifier.fillMaxWidth(),
//        onDismissRequest = { showDialogSecret = false },
//        title = {
//            Text(
//                text = "Nhập Mã Giao Dịch",
//                style = TextStyle(
//                    fontSize = 20.sp,
//                    fontWeight = FontWeight.Bold,
//                    textAlign = TextAlign.Center
//                ),
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(vertical = 10.dp)
//            )
//        },
//        text = {
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.spacedBy(8.dp),
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                for (i in 0 until 6) {
//                    PinDigitField(
//                        digit = if (pinCode.length > i) pinCode[i].toString() else "",
//                        onDigitChange = { newDigit ->
//                            if (newDigit.length <= 1 && newDigit.all { it.isDigit() }) {
//                                val newPinCode = StringBuilder(pinCode).apply {
//                                    if (newDigit.isEmpty() && pinCode.isNotEmpty()) {
//                                        deleteCharAt(lastIndex)
//                                    } else if (pinCode.length < 6) {
//                                        append(newDigit)
//                                    }
//                                }.toString()
//                                pinCode = newPinCode
//                            }
//                        }
//                    )
//                }
//            }
//        },
//        confirmButton = {
//            Button(
//                onClick = {
//                    // Confirm button logic here
//                    showDialogSecret = false
//                    openCart()
//
//                },
//                colors = ButtonDefaults.buttonColors(
//                    containerColor = Color(0xFFffcc99),
//                    contentColor = Color.Black
//                ),
//                border = BorderStroke(1.dp, Color.Red)
//            ) {
//                Text("OK", style = TextStyle(fontWeight = FontWeight.Bold))
//            }
//        },
//        dismissButton = {
//            Button(
//                onClick = {
//                    // Dismiss button logic here
//                    showDialogSecret = false
//                },
//                colors = ButtonDefaults.buttonColors(
//                    containerColor = Color(0xFFd9d9d9),
//                    contentColor = Color.Black
//                ),
//                border = BorderStroke(1.dp, Color.Black)
//            ) {
//                Text("Hủy", style = TextStyle(fontWeight = FontWeight.Bold))
//            }
//        }
//    )
//}
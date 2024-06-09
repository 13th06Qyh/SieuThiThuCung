package com.example.sttc.view.Cart

import android.content.Context
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
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
import com.example.sttc.view.System.formatNumber
import com.example.sttc.viewmodel.AccountViewModel
import com.example.sttc.viewmodel.CartViewModel
import com.example.sttc.viewmodel.ProductViewModel
import kotlinx.coroutines.delay

@Composable
fun CartScreen(
    back: () -> Unit,
    openDetailProducts: (id: Int) -> Unit,
    openPayment: (List<PayData>) -> Unit,
    accountViewModel: AccountViewModel,
    cartViewModel: CartViewModel,
    context: Context
) {
    val users by accountViewModel.userInfoFlow.collectAsState(null)
    val carts by cartViewModel.cart.collectAsState(emptyList())
    val destroy by cartViewModel.delete.collectAsState(null)
    val checkAll = remember { mutableStateOf(false) }
    val checkOnlyStates = remember { mutableStateListOf<MutableState<Boolean>>() }
    val selectedItemsPrice = remember { mutableStateOf(0) }
    val quantities = remember { mutableStateListOf<MutableState<Int>>() }
    val isAnySelected = remember { mutableStateOf(false) }
    var openDialogDeleteCart by remember { mutableStateOf(false) }

    fun calculateTotalPrice() {
        val totalPrice = carts
            .mapIndexed { index, cart ->
                if (checkOnlyStates[index].value) cart.sanpham.buyprice.toInt() * quantities[index].value
                else 0
            }
            .sum()
        selectedItemsPrice.value = totalPrice
    }

    fun updateIsAnySelected() {
        isAnySelected.value = checkOnlyStates.any { it.value }
    }

    LaunchedEffect(users) {
        users?.let { user ->
            cartViewModel.fetchCart()
            Log.d("CartScreenUU", "User ID: ${user.id}")
        }
    }

    Log.d("CartScreen", "Carts: $carts")

    LaunchedEffect(carts) {
        checkOnlyStates.clear()
        quantities.clear()
        carts.forEach { _ ->
            checkOnlyStates.add(mutableStateOf(false))
            quantities.add(mutableStateOf(1))
        }
        calculateTotalPrice()
        updateIsAnySelected()
    }
    LaunchedEffect(checkOnlyStates, quantities) {
        calculateTotalPrice()
        updateIsAnySelected()
    }
    LaunchedEffect(checkAll.value) {
        checkOnlyStates.forEach { it.value = checkAll.value }
        calculateTotalPrice()
        updateIsAnySelected()
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
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
                .padding(top = 0.dp),
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
            Row(
                modifier = Modifier
                    .padding(70.dp, 0.dp, 0.dp, 0.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Default.ShoppingCart, contentDescription = "Add to cart",
                    modifier = Modifier
                        .size(35.dp)
                        .padding(0.dp, 0.dp, 5.dp, 0.dp),
                    tint = Color(0xFFcc2900)
                )
                Text(
                    text = "Giỏ hàng",
                    style = TextStyle(
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        color = Color(0xFFcc2900)
                    ),
                    modifier = Modifier
                        .padding(5.dp, 10.dp)
                )
            }
        }

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(Color(0xFFffcccc))
                .padding(5.dp, 5.dp)
        ) {
            itemsIndexed(carts) { index, cart ->
                val product = cart.sanpham
                val checkOnly =
                    checkOnlyStates.getOrNull(index) ?: remember { mutableStateOf(false) }
                val quantityState = quantities.getOrNull(index) ?: remember { mutableStateOf(1) }
                var quantity by remember { mutableStateOf(quantityState.value) }

                LaunchedEffect(product.soluongkho) {
                    quantity =
                        if (quantity < 1) 1 else if (quantity > product.soluongkho) product.soluongkho else quantity
                }

                Column(
                    modifier = Modifier
                        .padding(0.dp, 0.dp, 0.dp, 10.dp)
                        .border(1.dp, color = Color(0xFFFFFFFF))
                        .fillMaxWidth()
                        .background(
                            Color.White
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = cart.proname,
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

                        Checkbox(
                            checked = checkOnly.value,
                            onCheckedChange = {
                                checkOnly.value = it
                                if (!it) checkAll.value = false
                                if (checkOnlyStates.all { state -> state.value }) checkAll.value =
                                    true
                                calculateTotalPrice()
                                updateIsAnySelected()
                            },
                            modifier = Modifier
                                .size(20.dp) // Thay đổi kích thước của checkbox
                                .padding(0.dp, 0.dp, 15.dp, 0.dp)
                        )
                    }

                    HorizontalDivider(thickness = 1.dp, color = Color.Gray)

                    Row(
                        modifier = Modifier
                            .clickable { openDetailProducts(product.maSP) },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        val productImages = cart.image
                        val imageUrl = productImages
                        val fileName =
                            imageUrl.substringAfterLast("/").substringBeforeLast(".")
                        val resourceId = context.resources.getIdentifier(
                            fileName,
                            "drawable",
                            context.packageName
                        )
                        val a = context.resources.getResourceName(resourceId)
                        val b = a.substringAfter('/')
//                        Log.d("test", "FileName: $fileName")
//                        Log.d("test", "ResourceId: $resourceId")
//                        Log.d("test", "ResourceName1: $a")
//                        Log.d("test", "ResourceName2: $b")
                        if (b == fileName) {
                            Image(
                                painter = painterResource(id = resourceId),
                                contentDescription = "Image",
                                modifier = Modifier
                                    .size(100.dp)
                                    .padding(5.dp, 5.dp)
                                    .border(0.1.dp, color = Color.Black)
                            )
                        } else {
                            Text(text = "Image not found")
                        }


                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp)
                                .padding(5.dp, 16.dp),
                        ) {
                            Text(
                                text = product.tensp,
                                style = TextStyle(
                                    fontSize = 22.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black
                                )
                            )
                            Spacer(modifier = Modifier.height(4.dp)) // Thêm khoảng cách ở đây

                            Text(
                                text = cart.tagname,
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    fontStyle = FontStyle.Italic,
                                    color = Color.Black,
                                )
                            )

                            val price = product.buyprice.toInt()
                            Text(
                                "Giá: " + formatNumber(price) + "đ",
                                style = TextStyle(
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Red,
                                    textAlign = TextAlign.End
                                ),
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }

                    HorizontalDivider(thickness = 1.dp, color = Color.Gray)
                    HorizontalDivider(thickness = 10.dp, color = Color.White)

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .padding(end = 10.dp),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Delete Icon Button
                        IconButton(
                            onClick = {
                                val cartIdToDelete = cart.maCart// Lưu lại cartId khi nhấn nút
                                Log.d(
                                    "CartScreenD",
                                    "Delete button clicked for cart ID: $cartIdToDelete"
                                )
                                cartViewModel.deleteCart(cart.maCart)
                            },
                            modifier = Modifier.size(40.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.trash3),
                                contentDescription = "Delete",
                                tint = Color.Red,
                                modifier = Modifier.size(30.dp)
                            )
                        }

                        // Quantity Control Row
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Remove Button
                            IconButton(
                                onClick = {
                                    if (quantity > 1) quantity -= 1
                                    quantityState.value = quantity
                                    calculateTotalPrice()
                                },
                                modifier = Modifier
                                    .height(40.dp)
                                    .border(1.dp, Color.Gray)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.remove),
                                    contentDescription = "Remove",
                                    tint = Color(0xFF4d4d4d)
                                )
                            }

                            // Quantity Display
                            Row(
                                modifier = Modifier
                                    .width(60.dp)
                                    .height(48.dp)
                                    .border(1.dp, Color.Gray),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                BasicTextField(
                                    value = quantity.toString(),
                                    onValueChange = {
                                        val newQuantity = it.toIntOrNull() ?: quantity
                                        quantity =
                                            if (newQuantity < 1) 1 else if (newQuantity > product.soluongkho.toInt()) product.soluongkho.toInt() else newQuantity
                                        quantityState.value = quantity
                                        calculateTotalPrice()
                                    },
                                    textStyle = TextStyle(
                                        fontSize = 20.sp,
                                        color = Color.Black,
                                        textAlign = TextAlign.Center
                                    )
                                )
                            }

                            // Add Button
                            IconButton(
                                onClick = {
                                    if (quantity < product.soluongkho.toInt()) quantity += 1
                                    quantityState.value = quantity
                                    calculateTotalPrice()
                                },
                                modifier = Modifier
                                    .height(40.dp)
                                    .border(1.dp, Color.Gray)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = "Add",
                                    tint = Color(0xFF4d4d4d)
                                )
                            }
                        }
                    }
                    HorizontalDivider(thickness = 10.dp, color = Color.White)
                }



                LaunchedEffect(destroy) {
                    destroy?.let {
                        if (it.isSuccess) {
                            cartViewModel.fetchCart() // Cập nhật lại giỏ hàng sau khi xóa thành công
                        } else {
                            Log.e(
                                "CartScreen",
                                "Failed to delete cart: ${it.exceptionOrNull()?.message}"
                            )
                        }
                    }
                }

            }

        }

        HorizontalDivider(thickness = 1.dp, color = Color(0xFFcc2900))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFe6ffff))
//                .border(2.dp, color = Color(0xFFcc2900))
            ,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier
                    .width(95.dp)
                    .padding(start = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = checkAll.value,
                    onCheckedChange = {
                        checkAll.value = it
                        checkOnlyStates.forEach { state -> state.value = it }
                        calculateTotalPrice()
                        updateIsAnySelected()
                    },
                    modifier = Modifier
                        .size(20.dp),
                    colors = CheckboxDefaults.colors(
                        checkmarkColor = Color.White,
                        checkedColor = Color(0xFFcc2900),
                        uncheckedColor = Color.Gray,
                    )
                )
                Text(
                    text = "Tất cả",
                    modifier = Modifier.padding(start = 10.dp),
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                ) {
                    Text(
                        text = "Tổng cộng: ",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        ),
                        modifier = Modifier.padding(bottom = 3.dp)
                    )
                    Text(
                        text = formatNumber(selectedItemsPrice.value) + "đ ",
                        color = Color.Red,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.End,
                    )
                }
                Button(
                    shape = RectangleShape,
                    onClick = {
                        updateIsAnySelected()
                        val selectedProducts = carts.mapIndexedNotNull { index, cart ->
                            val checkOnlyState = checkOnlyStates.getOrNull(index)
                            val quantityState = quantities.getOrNull(index)
                            if (checkOnlyState != null && checkOnlyState.value) {
                                val quantity = quantityState?.value ?: 1
                                PayData(
                                    idc = cart.maCart,
                                    id = cart.sanpham.maSP,
                                    image = cart.image,
                                    name = cart.sanpham.tensp,
                                    oneprice = cart.sanpham.buyprice.toInt(),
                                    tag = cart.tagname,
                                    quantity = quantity // Sử dụng quantity state được xác định
                                )
                            } else {
                                null
                            }
                        }
                        openPayment(selectedProducts)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red,
                    ),
                    enabled = isAnySelected.value,
                    modifier = Modifier.height(63.dp)
                ) {
                    Text(
                        text = "Mua hàng",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        ),
                    ) // Change the color of the text
                }
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun CartPreview() {
    STTCTheme {
        CartScreen(
            back = {},
            openDetailProducts = {},
            openPayment = {},
            AccountViewModel(LocalContext.current),
            CartViewModel(LocalContext.current),
            LocalContext.current
        )
    }
}


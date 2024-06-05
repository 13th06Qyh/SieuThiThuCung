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
    accountViewModel: AccountViewModel,
    cartViewModel: CartViewModel,
    context: Context
) {
    val user by accountViewModel.userInfoFlow.collectAsState(null)
    val carts by cartViewModel.cart.collectAsState(emptyList())
    val destroy by cartViewModel.delete.collectAsState(null)
    var openDialogDeleteCart by remember { mutableStateOf(false) }

    LaunchedEffect(user) {
        user?.let { user ->
            cartViewModel.fetchCart()
            Log.d("CartScreenUU", "User ID: ${user.id}")
        }
    }

    Log.d("CartScreen", "Carts: $carts")

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
            items(carts) { cart ->
                val product = cart.sanpham
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

                        val checkedState = remember { mutableStateOf(false) }
                        Checkbox(
                            checked = checkedState.value,
                            onCheckedChange = { checkedState.value = it },
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
                            .height(40.dp)
                            .padding(end = 5.dp),
                        horizontalArrangement = Arrangement.SpaceAround,
                    ) {

                        IconButton(onClick = { openDialogDeleteCart = true }) {
                            Icon(
                                painter = painterResource(id = R.drawable.trash3),
                                contentDescription = "Delete",
                                tint = Color.Red,
                                modifier = Modifier.size(25.dp)
                            )
                        }

                        Row(
                            modifier = Modifier
                                .padding(start = 145.dp)
                                .height(40.dp)
                                .border(1.dp, color = Color(0xFFd9d9d9)),
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.CenterVertically

                        ) {

                            IconButton(
                                onClick = { /*TODO*/ },
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.remove),
                                    contentDescription = "Remove",
                                    tint = Color(0xFF4d4d4d)
                                )
                            }


                            Row(
                                modifier = Modifier
                                    .width(60.dp)
                                    .height(40.dp)
                                    .border(1.dp, Color.Gray),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                val range = 1.toString()
                                Text(
                                    text = range,
                                    modifier = Modifier
                                        .width(60.dp),
                                    style = TextStyle(
                                        fontSize = 20.sp,
                                        color = Color.Black,
                                        textAlign = TextAlign.Center,
                                    )
                                )
                            }

                            IconButton(
                                onClick = { /*TODO*/ },
                            ) {
                                Icon(
                                    Icons.Default.Add,
                                    contentDescription = "Add",
                                    tint = Color(0xFF4d4d4d)
                                )
                            }
                        }
                    }
                    HorizontalDivider(thickness = 10.dp, color = Color.White)
                }

                if (openDialogDeleteCart) {
                    AlertDialog(
                        containerColor = Color(0xFFfffff5),
                        onDismissRequest = { openDialogDeleteCart = false },
                        title = {
                            Text(
                                "Bạn chắc chắn muốn xóa khỏi giỏ hàng?",
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
                                    cartViewModel.deleteCart(cart.maCart)
                                    openDialogDeleteCart = false
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
                                    openDialogDeleteCart = false
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

                LaunchedEffect(destroy) {
                    destroy?.let {
                        if (it.isSuccess) {
                            cartViewModel.fetchCart() // Cập nhật lại giỏ hàng sau khi xóa thành công
                        } else {
                            Log.e("CartScreen", "Failed to delete cart: ${it.exceptionOrNull()?.message}")
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
                    .padding(start = 10.dp)
                    .clickable { /*TODO*/ },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = false,
                    onCheckedChange = { /*TODO: Add your action here*/ },
                    modifier = Modifier
                        .size(20.dp),
                    colors = CheckboxDefaults.colors(
                        checkmarkColor = Color(0xFFcc2900),
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
                        text = formatNumber(1000000) + "đ ",
                        color = Color.Red,
                        fontWeight = FontWeight.Bold
                    )
                }
                Button(
                    shape = RectangleShape,
                    onClick = { /*TODO: Add your action here*/ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red,
                    ),
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
            AccountViewModel(LocalContext.current),
            CartViewModel(LocalContext.current),
            LocalContext.current
        )
    }
}


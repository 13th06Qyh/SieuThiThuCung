package com.example.sttc.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
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
import java.text.NumberFormat
import java.util.Locale

class Payment : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            STTCTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PaymentScreen(navController)
                }
            }
        }
    }
}

@Composable
fun PaymentScreen(navController: NavController) {
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
            TitlePayment()
            LocationPayment()
            val items = listOf(
                BillProduct(Product(R.drawable.rs1, "Tag A", "Product A", 10000), Bill(1)),
                BillProduct(Product(R.drawable.rs1, "Tag A", "Product A", 10000), Bill(1)),
                BillProduct(Product(R.drawable.rs1, "Tag A", "Product A", 10000), Bill(1)),
            )
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(5.dp, 0.dp)
            ) {
                items(items) { item ->


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
                            Image(
                                painter = painterResource(id = item.product.imageResId),
                                contentDescription = "Image",
                                modifier = Modifier
                                    .size(100.dp)
                                    .padding(5.dp, 5.dp)
                                    .border(0.5.dp, color = Color.Black)
                            )

                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(100.dp)
                                    .padding(5.dp, 10.dp),
                            ) {
                                Text(
                                    text = item.product.productName,
                                    style = TextStyle(
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.Black
                                    )
                                )
                                Spacer(modifier = Modifier.height(4.dp)) // Thêm khoảng cách ở đây
                                Text(
                                    text = item.product.tagName,
                                    style = TextStyle(
                                        fontSize = 13.sp,
                                        fontStyle = FontStyle.Italic,
                                        color = Color.Black,
                                    )
                                )
                                Text(
                                    "x" + item.bill.soluongmua.toString(),
                                    style = TextStyle(
                                        fontSize = 14.sp,
                                        color = Color.Black,
                                        textAlign = TextAlign.End
                                    ),
                                    modifier = Modifier.fillMaxWidth()
                                )
                                Text(
                                    formatNumber(item.product.productPrice) + "đ",
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
            PayBill()
            HorizontalDivider(thickness = 1.dp, color = Color(0xFF000000))
            SuccessPayment()
        }
    }
}

@Composable
fun TitlePayment() {
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
                .clickable { /*TODO*/ },
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
fun LocationPayment() {
    val location = remember { mutableStateOf("") }
    Column (
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
                text = "Địa chỉ giao hàng",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                ),
                modifier = Modifier.padding(10.dp, 10.dp)
            )
        }
        OutlinedTextField(
            value = location.value,
            onValueChange = { location.value = it },
            placeholder = {
                Text(
                    "Nhập địa chỉ chi tiết tại đây!",
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontStyle = FontStyle.Italic,
                        color = Color.Gray
                    )
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp, 5.dp, 20.dp, 15.dp)
                .background(Color.White),
            singleLine = false
        )
    }
}

@Composable
fun SuccessPayment() {
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
            text = formatNumber(1000000) + "đ",
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



@Preview(showBackground = true)
@Composable
fun PaymentPreview() {
    STTCTheme {
        PaymentScreen(rememberNavController())
    }
}
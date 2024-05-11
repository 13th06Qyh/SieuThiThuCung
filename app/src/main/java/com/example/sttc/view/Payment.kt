package com.example.sttc.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sttc.R
import com.example.sttc.ui.theme.STTCTheme
import java.text.NumberFormat
import java.util.Locale

class Payment : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PaymentScreen()
        }
    }
}

@Composable
fun PaymentScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFffddcc)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_back),
                    contentDescription = "arrow back",
                    tint = Color.Black,
                    modifier = Modifier.size(37.dp)
                )
            }
            Text(
                text = "Thanh toán",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = "Location Icon",
                tint = Color.Red
            )
            Text(text = "Địa chỉ giao hàng")
        }
        OutlinedTextField(
            value = "",
            onValueChange = { },
            label = { Text("Nhập địa chỉ chi tiết") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 5.dp, end = 5.dp)
                .height(100.dp),
            singleLine = false
        )
        Spacer(modifier = Modifier.height(10.dp))
        val itemsCart = listOf(
            ItemsCart(
                id = 1,
                image = R.drawable.rs1,
                productName = "Thức ăn ngon cho Quỳnh ",
                productPrice = 200000,
                quantity = 1
            ),
            ItemsCart(
                id = 2,
                image = R.drawable.rs1,
                productName = "Thức ăn ngon cho Quỳnh ",
                productPrice = 200000,
                quantity = 1
            ),

            )
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            items(items = itemsCart, key = { it.id }) { task ->
                Row(
                    modifier = Modifier.background(Color(0xFFf2f2f2)),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = task.image),
                        contentDescription = "Product Image",
                        modifier = Modifier.size(100.dp) // Thay đổi kích thước của ảnh
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                        Text(
                            text = task.productName,
                            fontSize = 20.sp
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            val format = NumberFormat.getCurrencyInstance(Locale("vi", "VN"))
                            Text(
                                text = format.format(task.productPrice),
                                modifier = Modifier.width(100.dp)
                            )
                            Text(
                                text = "x1"
                            )
                        }
                    }
                }
                HorizontalDivider(thickness = 15.dp, color = Color.White)
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFffddcc)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {

                Text(
                    text = "Tổng cộng: ",
                    fontSize = 13.sp
                )
                val format = NumberFormat.getCurrencyInstance(Locale("vi", "VN"))
                Text(
                    text = format.format(1070000),
                    modifier = Modifier.width(100.dp),
                    color = Color.Red,
                    fontWeight = FontWeight.Bold
                )
                Button(
                    shape = RectangleShape,
                    onClick = { /*TODO: Add your action here*/ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red,
                    )
                ) {
                    Text(
                        text = "Thanh toán",
                        color = Color.White
                    )
                }
            }
    }
}

data class ItemsPayment(
    val id: Int,
    val image: Int,
    val productName: String,
    val productPrice: Int,
    val quantity: Int,
)


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PaymentPreview() {
    STTCTheme {
        PaymentScreen()
    }
}
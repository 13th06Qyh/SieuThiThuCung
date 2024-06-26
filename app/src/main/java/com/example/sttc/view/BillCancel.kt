//package com.example.sttc.view
//
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.border
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.Button
//import androidx.compose.material3.ButtonDefaults
//import androidx.compose.material3.HorizontalDivider
//import androidx.compose.material3.Icon
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Surface
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Brush
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.text.font.FontStyle
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavController
//import androidx.navigation.compose.rememberNavController
//import com.example.sttc.R
//import com.example.sttc.controller.MyApp
//import com.example.sttc.ui.theme.STTCTheme
//
//class BillCancel : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            val navController = rememberNavController()
//            MyApp(navController = navController)
//            STTCTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    MyApp(navController = navController)
//                    BillCancelScreen(navController)
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun BillCancelScreen(navController: NavController) {
//    val scrollState = rememberScrollState()
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
////            .verticalScroll(scrollState)
//
//    ) {
//        Column (
//            modifier = Modifier.fillMaxSize(),
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ){
//            TopIconBillCancel()
//            TitleBillCancel()
//            ContentBillCancel()
//        }
//    }
//
//}
//
//@Composable
//fun TopIconBillCancel() {
//    val context = LocalContext.current
//
//    Image(
//        painter = painterResource(id = R.drawable.cancelproduct),
//        contentDescription = "TopIconCancelProduct",
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(165.dp)
//            .background(
//                Brush.radialGradient(
//                    colors = listOf(
//                        Color(0xFF706D6D),
//                        Color(0xFFF1E9E9),
//                        Color(0xFFB6B2B2),
//                        Color(0xFFAFACAC),
//                        Color(0xFFF6F2F2),
//                    ),
//                    radius = 550f
//                )
//            )
//            .border(1.dp, color = Color(0xFFcc00cc))
//    )
//}
//
//@Composable
//fun TitleBillCancel() {
//    Text(
//        text = "Đơn Hàng Bị Hủy",
//        style = TextStyle(
//            fontSize = 25.sp,
//            fontWeight = FontWeight.Bold,
//            textAlign = TextAlign.Center,
//            color = Color.Black
//        ),
//        modifier = Modifier
//            .fillMaxWidth()
////            .height(50.dp)
//            .background(
//                Brush.radialGradient(
//                    colors = listOf(
//                        Color(0xFFFFFFFF),
//                        Color(0xFFAFACAC),
//                        Color(0xFFF6F2F2),
//                    ),
//                    radius = 600f
//                )
//            )
//            .padding(0.dp, 10.dp)
//            .border(1.dp, color = Color.Black)
//    )
//}
//
//@Composable
//fun ContentBillCancel() {
//    val items = listOf(
//        BillProduct(Product(R.drawable.rs1, "Tag A", "Product A", 10000), Bill(1)),
//        BillProduct(Product(R.drawable.rs2, "Tag B", "Product B", 102000), Bill(2)),
//        BillProduct(Product(R.drawable.rs3, "Tag C", "Product C", 2345000), Bill(2)),
//        BillProduct(Product(R.drawable.rs1, "Tag D", "Product D", 30000), Bill(2)),
//        BillProduct(Product(R.drawable.rs2, "Tag E", "Product E", 8000), Bill(2)),
//
//        )
//
//    LazyColumn(
//        modifier = Modifier
//            .fillMaxWidth()
////            .border(1.dp, color = Color(0xFF006600))
//            .background(Color(0xFFe6e6e6))
//            .padding(5.dp, 0.dp)
//    ) {
//        items(items) { item ->
//            Column(
//                modifier = Modifier
//                    .padding(0.dp, 0.dp, 0.dp, 10.dp)
//                    .border(1.dp, color = Color(0xFFFFFFFF))
//                    .fillMaxWidth()
//                    .background(
//                        Color.White
//                    ),
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                Row (
//                    modifier = Modifier
//                        .fillMaxWidth(),
//                    horizontalArrangement = Arrangement.Start
//                ){
//                    Text(text = "Ngày huỷ đơn: ",
//                        style = TextStyle(
//                            fontSize = 14.sp,
//                            fontStyle = FontStyle.Italic,
//                            textAlign = TextAlign.Start,
//                            color = Color.Gray,
//                        ),
//                        modifier = Modifier
//                            .padding(10.dp, 5.dp)
//                    )
//                }
//
//                HorizontalDivider(thickness = 1.2.dp, color = Color(0xFFcccccc))
//                Row(
//                    modifier = Modifier
////                        .border(2.dp, color = Color(0xFF006600))
//                    ,
//                    verticalAlignment = Alignment.CenterVertically,
//                    horizontalArrangement = Arrangement.SpaceEvenly
//                ){
//                    Image(
//                        painter = painterResource(id = item.product.imageResId),
//                        contentDescription = "Image",
//                        modifier = Modifier
//                            .size(100.dp)
//                            .padding(5.dp, 5.dp)
//                            .border(0.1.dp, color = Color.Black)
//                    )
//
//                    Column(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .height(100.dp)
//                            .padding(5.dp, 10.dp),
//                    ) {
//                        Text(text = item.product.productName,
//                            style = TextStyle(
//                                fontSize = 18.sp,
//                                fontWeight = FontWeight.Bold,
//                                color = Color.Black
//                            )
//                        )
//                        Spacer(modifier = Modifier.height(4.dp)) // Thêm khoảng cách ở đây
//                        Text(text = item.product.tagName,
//                            style = TextStyle(
//                                fontSize = 13.sp,
//                                fontStyle = FontStyle.Italic,
//                                color = Color.Black,
//                            )
//                        )
//                        Text("x" + item.bill.soluongmua.toString(),
//                            style = TextStyle(
//                                fontSize = 14.sp,
//                                color = Color.Black,
//                                textAlign = TextAlign.End
//                            ),
//                            modifier = Modifier.fillMaxWidth()
//                        )
//                        Text("Giá: " + formatNumber(item.product.productPrice) + "đ",
//                            style = TextStyle(
//                                fontSize = 15.sp,
//                                fontWeight = FontWeight.Bold,
//                                color = Color.Black,
//                                textAlign = TextAlign.End
//                            ),
//                            modifier = Modifier.fillMaxWidth()
//                        )
//                    }
//                }
//
//                HorizontalDivider(thickness = 1.2.dp, color = Color(0xFFcccccc))
//
//                Row (
//                    modifier = Modifier
//                        .fillMaxWidth()
////                        .border(1.dp, color = Color(0xFF006600))
//                    ,
//                    horizontalArrangement = Arrangement.End
//                ){
//                    Icon(
//                        painter = painterResource(id = R.drawable.money),
//                        contentDescription = "Money",
//                        tint = Color(0xFFcc2900),
//                        modifier = Modifier
//                            .size(28.dp)
//                            .padding(0.dp, 9.dp, 0.dp, 0.dp)
//                    )
//                    Text(
//                        text = "Thành tiền: " + formatNumber(item.product.productPrice * item.bill.soluongmua) + "đ",
//                        style = TextStyle(
//                            fontSize = 16.sp,
//                            fontWeight = FontWeight.Bold,
//                            textAlign = TextAlign.End,
//                            color = Color(0xFFcc2900),
//                        ),
//                        modifier = Modifier
//                            .padding(5.dp, 10.dp)
//                    )
//
//                }
//
//                HorizontalDivider(thickness = 1.2.dp, color = Color(0xFFcccccc))
//
//                Row (
//                    modifier = Modifier
//                        .fillMaxWidth()
////                        .border(1.dp, color = Color(0xFFcc2900))
//                        .background(
//                            Color.White
//                        ),
//                    horizontalArrangement = Arrangement.End
//
//                ) {
//                    Button(
//                        onClick = { /* Do something! */ },
//                        shape = RoundedCornerShape(10.dp), // Định dạng góc bo tròn của nút
//                        colors = ButtonDefaults.buttonColors(
//                            containerColor = Color(0xFFcc2900), // Màu nền của nút
//                            contentColor = Color.White // Màu chữ của nút
//                        ),
//                        modifier = Modifier
//                            .padding(5.dp, 5.dp)
//                    ) {
//                        Text(text = "Mua lại",
//                            style = TextStyle(
//                                fontSize = 16.sp,
//                                fontWeight = FontWeight.Bold,
//                                color = Color.White
//                            ),
//                        )
//                    }
//                }
//            }
//        }
//    }
//}
//
//
//
//
//@Preview(showBackground = true)
//@Composable
//fun BillCancelScreenPreview() {
//    val navController = rememberNavController()
//    STTCTheme {
//        BillCancelScreen(navController)
//    }
//}
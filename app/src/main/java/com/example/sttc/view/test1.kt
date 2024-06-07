//@Composable
//fun Card(
//    back: () -> Unit,
//    accountViewModel: AccountViewModel
//) {
//    val stk = remember { mutableStateOf("") }
//    var showDialogSecret by remember { mutableStateOf(false) }
//    var pinCode by remember { mutableStateOf("") }
//
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
////            .border(1.dp, color = Color(0xFFFFFFFF))
//            .padding(0.dp, 5.dp, 0.dp, 0.dp)
//            .background(
//                Color.White
//            )
//    ) {
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .background(
//                    Brush.radialGradient(
//                        colors = listOf(
//                            Color(0xFFFFFFFF),
//                            Color(0xFFFFE4E4),
//                            Color(0xFFF6F2F2),
//                        ),
//                        radius = 600f
//                    )
//                )
//                .padding(top = 5.dp),
//            horizontalArrangement = Arrangement.Start
//        ) {
//            Icon(
//                Icons.Default.ArrowBack, contentDescription = "Back",
//                modifier = Modifier
//                    .size(50.dp)
//                    .padding(10.dp, 0.dp)
//                    .clickable { back() },
//                tint = Color.Black
//            )
//
//            Text(
//                text = "Quay lại trang trước",
//                style = TextStyle(
//                    fontSize = 25.sp,
//                    fontWeight = FontWeight.Bold,
//                    textAlign = TextAlign.Center,
//                    color = Color(0xFFcc2900)
//                ),
//                modifier = Modifier
//                    .padding(80.dp, 10.dp)
//            )
//        }
//        Row(
//            modifier = Modifier
//                .fillMaxWidth(),
//            horizontalArrangement = Arrangement.Start,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Icon(
//                Icons.Filled.Home,
//                contentDescription = "Money",
//                tint = Color(0xFFcc2900),
//                modifier = Modifier
//                    .size(35.dp)
//                    .padding(10.dp, 9.dp, 0.dp, 0.dp)
//            )
//            Text(
//                text = "Chọn ngân hàng",
//                style = TextStyle(
//                    fontSize = 20.sp,
//                    fontWeight = FontWeight.Bold,
//                    color = Color.Black,
//                ),
//                modifier = Modifier
//                    .padding(8.dp, 9.dp, 0.dp, 0.dp)
//            )
//        }
//
//        Row(
//            modifier = Modifier
//                .fillMaxWidth(),
//            horizontalArrangement = Arrangement.Start
//        ) {
//            TextField(
//                value = "",
//                onValueChange = {},
//                textStyle = TextStyle(
//                    color = Color.Black,
//                    fontSize = 18.sp,
//                    fontStyle = FontStyle.Normal,
//                    fontWeight = FontWeight.Bold
//                ),
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .background(Color.White)
//                    .padding(vertical = 12.dp, horizontal = 12.dp),
//                placeholder = {
//                    Text(
//                        text = "Ngân hàng bạn chọn sẽ hiển thị ở đây",
//                        style = TextStyle(
//                            fontSize = 18.sp,
//                            fontStyle = FontStyle.Italic,
//                            color = Color.Gray
//                        )
//                    )
//                },
//                colors = TextFieldDefaults.textFieldColors(
//                    disabledTextColor = Color.Gray,
//                    containerColor = Color(0xffffd6cc),
//                    focusedIndicatorColor = Color.Green,
//                    unfocusedIndicatorColor = Color(0xffe62e00),
//                    disabledIndicatorColor = Color.Gray,
//                    cursorColor = Color.Blue,
//                    errorCursorColor = Color.Red
//                ),
//            )
//        }
//
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(10.dp, 10.dp),
//            horizontalArrangement = Arrangement.SpaceAround,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            IconButton(
//                onClick = { },
//                modifier = Modifier
//                    .width(100.dp)
//                    .border(1.dp, Color.Black),
//            ) {
//                Image(
//                    painter = painterResource(id = R.drawable.vietinbank),
//                    contentDescription = "Notice",
//                    modifier = Modifier.size(100.dp),
//                )
//            }
//
//            IconButton(
//                onClick = { },
//                modifier = Modifier
//                    .width(100.dp)
//                    .border(1.dp, Color.Black),
//
//                ) {
//                Image(
//                    painter = painterResource(id = R.drawable.sacombank),
//                    contentDescription = "Notice",
//                    modifier = Modifier.size(100.dp),
//                )
//            }
//
//            IconButton(
//                onClick = { },
//                modifier = Modifier
//                    .width(100.dp)
//                    .border(1.dp, Color.Black),
//            ) {
//                Image(
//                    painter = painterResource(id = R.drawable.agribank),
//                    contentDescription = "Notice",
//                    modifier = Modifier.size(100.dp),
//                )
//            }
//        }
//
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(10.dp, 10.dp),
//            horizontalArrangement = Arrangement.SpaceAround,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            IconButton(
//                onClick = { },
//                modifier = Modifier
//                    .width(100.dp)
//                    .border(1.dp, Color.Black),
//            ) {
//                Image(
//                    painter = painterResource(id = R.drawable.hdbank),
//                    contentDescription = "Notice",
//                    modifier = Modifier.size(100.dp),
//                )
//            }
//
//            IconButton(
//                onClick = { },
//                modifier = Modifier
//                    .width(100.dp)
//                    .border(1.dp, Color.Black),
//
//                ) {
//                Image(
//                    painter = painterResource(id = R.drawable.bidv),
//                    contentDescription = "Notice",
//                    modifier = Modifier.size(100.dp),
//                )
//            }
//
//            IconButton(
//                onClick = { },
//                modifier = Modifier
//                    .width(100.dp)
//                    .border(1.dp, Color.Black),
//            ) {
//                Image(
//                    painter = painterResource(id = R.drawable.techcombank),
//                    contentDescription = "Notice",
//                    modifier = Modifier.size(100.dp),
//                )
//            }
//        }
//
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(10.dp, 10.dp),
//            horizontalArrangement = Arrangement.SpaceAround,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            IconButton(
//                onClick = { },
//                modifier = Modifier
//                    .width(100.dp)
//                    .border(1.dp, Color.Black),
//            ) {
//                Image(
//                    painter = painterResource(id = R.drawable.tpbank),
//                    contentDescription = "Notice",
//                    modifier = Modifier.size(100.dp),
//                )
//            }
//
//            IconButton(
//                onClick = { },
//                modifier = Modifier
//                    .width(100.dp)
//                    .border(1.dp, Color.Black),
//
//                ) {
//                Image(
//                    painter = painterResource(id = R.drawable.mb),
//                    contentDescription = "Notice",
//                    modifier = Modifier.size(100.dp),
//                )
//            }
//
//            IconButton(
//                onClick = { },
//                modifier = Modifier
//                    .width(100.dp)
//                    .border(1.dp, Color.Black),
//            ) {
//                Image(
//                    painter = painterResource(id = R.drawable.vib),
//                    contentDescription = "Notice",
//                    modifier = Modifier.size(100.dp),
//                )
//            }
//        }
//
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(10.dp, 10.dp),
//            horizontalArrangement = Arrangement.SpaceAround,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            IconButton(
//                onClick = { },
//                modifier = Modifier
//                    .width(100.dp)
//                    .border(1.dp, Color.Black),
//            ) {
//                Image(
//                    painter = painterResource(id = R.drawable.vietcombank),
//                    contentDescription = "Notice",
//                    modifier = Modifier.size(100.dp),
//                )
//            }
//
//            IconButton(
//                onClick = { },
//                modifier = Modifier
//                    .width(100.dp)
//                    .border(1.dp, Color.Black),
//
//                ) {
//                Image(
//                    painter = painterResource(id = R.drawable.msb),
//                    contentDescription = "Notice",
//                    modifier = Modifier.size(100.dp),
//                )
//            }
//
//            IconButton(
//                onClick = { },
//                modifier = Modifier
//                    .width(100.dp)
//                    .border(1.dp, Color.Black),
//            ) {
//                Image(
//                    painter = painterResource(id = R.drawable.ocb),
//                    contentDescription = "Notice",
//                    modifier = Modifier.size(100.dp),
//                )
//            }
//        }
//
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(10.dp, 10.dp),
//            horizontalArrangement = Arrangement.SpaceAround,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            IconButton(
//                onClick = { },
//                modifier = Modifier
//                    .width(100.dp)
//                    .border(1.dp, Color.Black),
//            ) {
//                Image(
//                    painter = painterResource(id = R.drawable.acb),
//                    contentDescription = "Notice",
//                    modifier = Modifier.size(100.dp),
//                )
//            }
//
//            IconButton(
//                onClick = { },
//                modifier = Modifier
//                    .width(100.dp)
//                    .border(1.dp, Color.Black),
//
//                ) {
//                Image(
//                    painter = painterResource(id = R.drawable.vpbankk),
//                    contentDescription = "Notice",
//                    modifier = Modifier.size(100.dp),
//                )
//            }
//
//            IconButton(
//                onClick = { }, modifier = Modifier
//                    .width(100.dp)
//            ) {} //Cái này để trống lam canh đều
//
//        }
//
//        Row(
//            modifier = Modifier
//                .fillMaxWidth(),
//            horizontalArrangement = Arrangement.Center,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            OutlinedTextField(
//                value = stk.value,
//                onValueChange = { stk.value = it },
//                placeholder = {
//                    Text(
//                        "Nhập số tài khoản của bạn",
//                        style = TextStyle(
//                            fontSize = 18.sp,
//                            fontStyle = FontStyle.Italic,
//                            color = Color.Gray
//                        )
//                    )
//                },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(15.dp, 5.dp, 15.dp, 15.dp)
//                    .background(Color.White),
//                singleLine = false
//            )
//        }
//
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(10.dp, 10.dp),
//            horizontalArrangement = Arrangement.Center,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Button(
//                onClick = { /* Do something! */ },
//                shape = RoundedCornerShape(2.dp), // Định dạng góc bo tròn của nút
//                colors = ButtonDefaults.buttonColors(
//                    containerColor = Color(0xFFff5c33), // Màu nền của nút
//                    contentColor = Color.White // Màu chữ của nút
//                ),
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(7.dp, 5.dp)
//            ) {
//                Text(
//                    text = "Giao dịch",
//                    style = TextStyle(
//                        fontSize = 18.sp,
//                        fontWeight = FontWeight.Bold,
//                        color = Color.White
//                    ),
//                )
//            }
//            if (showDialogSecret) {
//                AlertDialog(
//                    modifier = Modifier.fillMaxWidth(),
//                    onDismissRequest = { /*TODO*/ },
//                    title = {
//                        Text(
//                            text = "Nhập Mã Giao Dịch",
//                            style = TextStyle(
//                                fontSize = 20.sp,
//                                fontWeight = FontWeight.Bold,
//                                color = Color.Black,
//                                textAlign = TextAlign.Center
//                            ),
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(vertical = 10.dp)
//                        )
//                    },
//                    text = {
//                        Row(
//                            modifier = Modifier.fillMaxWidth(),
//                            horizontalArrangement = Arrangement.spacedBy(8.dp),
//                            verticalAlignment = Alignment.CenterVertically
//                        ) {
//                            for (i in 0 until 6) {
//                                PinDigitField(
//                                    digit = if (pinCode.length > i) pinCode[i].toString() else "",
//                                    onDigitChange = { newDigit ->
//                                        if (newDigit.length <= 1 && newDigit.all { it.isDigit() }) {
//                                            val newPinCode = StringBuilder(pinCode).apply {
//                                                if (newDigit.isEmpty() && pinCode.isNotEmpty()) {
//                                                    deleteCharAt(lastIndex)
//                                                } else if (pinCode.length < 6) {
//                                                    append(newDigit)
//                                                }
//                                            }.toString()
//                                            pinCode = newPinCode
//                                        }
//                                    }
//                                )
//                            }
//                        }
//                    },
//                    confirmButton = {
//                        Button(
//                            onClick = {
//
//                            },
//                            colors = ButtonDefaults.buttonColors(
//                                containerColor = Color(0xFFffcc99), // Màu nền của nút
//                                contentColor = Color.Black, // Màu chữ của nút
//                            ),
//
//                            border = BorderStroke(1.dp, Color.Red),
//                        ) {
//                            Text(
//                                "OK",
//                                style = TextStyle(
//                                    fontWeight = FontWeight.Bold
//                                )
//                            )
//                        }
//                    },
//                    dismissButton = {
//                        Button(
//                            onClick = {
//                            },
//                            colors = ButtonDefaults.buttonColors(
//                                containerColor = Color(0xFFd9d9d9), // Màu nền của nút
//                                contentColor = Color.Black, // Màu chữ của nút
//                            ),
//
//                            border = BorderStroke(1.dp, Color.Black),
//                        ) {
//                            Text(
//                                "Hủy",
//                                style = TextStyle(
//                                    fontWeight = FontWeight.Bold
//                                )
//                            )
//                        }
//                    }
//                )
//            }
//
//        }
//    }
//
//}
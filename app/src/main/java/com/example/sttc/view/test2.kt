//package com.example.sttc.view
//
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Search
//import androidx.compose.material3.Icon
//import androidx.compose.material3.Text
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.unit.sp
//
//TextField(
//value = searchQuery.value,
//onValueChange = { searchQuery.value = it },
//placeholder = { Text("Tìm kiếm",
//    style = TextStyle(
//        fontSize = 17.sp)
//) },
//colors = TextFieldDefaults.textFieldColors(
//disabledTextColor = Color.Gray,
//containerColor = Color(0xffffebe6),
//focusedIndicatorColor = Color.Green,
//unfocusedIndicatorColor = Color(0xffffffff),
//disabledIndicatorColor = Color.Gray,
//cursorColor = Color.Blue,
//errorCursorColor = Color.Red
//),
//modifier = Modifier.size(240.dp, 50.dp),
//shape = RoundedCornerShape(10.dp),
//leadingIcon = {
//    Icon(imageVector = Icons.Filled.Search, contentDescription = "Search" )
//}
//)
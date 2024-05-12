import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sttc.R
import com.example.sttc.ui.theme.STTCTheme
import com.example.sttc.view.Avatar
import com.example.sttc.view.ItemAccount
import com.example.sttc.view.ItemsBaiViet
import com.example.sttc.view.ItemsCmt


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Test3() {
    val itemCmt = listOf(
        ItemsCmt(
            1,
            "Quyh",
            "Hay qua"
        )
    )
    var selectedTaskCmt by remember { mutableStateOf("") }
    var openDialogDelete by remember { mutableStateOf(false) }
    var openDialogBuild by remember { mutableStateOf(false) }
    var newCmt by remember { mutableStateOf("") }
    AlertDialog(
        containerColor = Color(0xFFcce6ff),
        onDismissRequest = { openDialogBuild = false },
        title = {
            Text(
                "Chỉnh sửa bình luận",
                style = TextStyle(
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 23.sp,
                ),
                modifier = Modifier.fillMaxWidth()
            ) },
        text = {
            TextField(
                value = newCmt,
                onValueChange = { newCmt = it },
                placeholder = { Text("Nhập bình luận mới vào đây") },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color(0xffe6ffff),
                    unfocusedIndicatorColor = Color(0xff0000ff),
                ),
                modifier = Modifier.height(100.dp)
            )
        },
        confirmButton = {
            Button(
                onClick = {
                    itemCmt[0].cmt = newCmt
                    openDialogBuild = false
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFccffff), // Màu nền của nút
                    contentColor = Color.Black, // Màu chữ của nút
                ),

                border = BorderStroke(1.dp, Color(0xFF0000ff)),
            ) {
                Text("Lưu",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        },
        dismissButton = {
            Button(
                onClick = { openDialogBuild = false },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFFA483), // Màu nền của nút
                    contentColor = Color.Black, // Màu chữ của nút
                ),

                border = BorderStroke(1.dp, Color(0xFF8B2701)),
            ) {
                Text("Hủy",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    )

}

@Composable
fun title() {
    Row(
        verticalAlignment = Alignment.CenterVertically // Center items vertically
    ) {
        Surface(
            modifier = Modifier
                .size(40.dp)
                .border(BorderStroke(2.dp, Color.Black), shape = CircleShape)
                .clip(shape = CircleShape)
        ) {

            Image(
                painter = painterResource(R.drawable.user),
                contentDescription = "avatar",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(30.dp)
                    .clip(CircleShape)
            )
        }
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = "ADMIN",
            style = TextStyle(fontSize = 18.sp),
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun Content() {
    Row(
        modifier = Modifier.fillMaxWidth(),

        ) {
        Button(
            modifier = Modifier
                .weight(1f),
            shape = RectangleShape,
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
            )
        ) {
            Icon(
                Icons.Default.ThumbUp,
                contentDescription = "",
                tint = Color.Blue
            )
            Spacer(modifier = Modifier.width(7.dp))
            Text(text = "Thích", color = Color.Gray)
        }
        Button(
            shape = RectangleShape,
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
            )
        ) {
            Icon(
                painter = painterResource(id = R.drawable.cmt),
                contentDescription = "",
                tint = Color(0xFF8A8686),
            )
            Spacer(modifier = Modifier.width(7.dp))
            Text(text = "Bình Luận", color = Color.Gray)
        }
        Button(
            shape = RectangleShape,
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
            )
        ) {
            Icon(
                Icons.Default.Menu,
                contentDescription = "",
                tint = Color.Red
            )
            Spacer(modifier = Modifier.width(7.dp))
            Text(text = "Chi Tiết", color = Color.Gray)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun Preview() {
    STTCTheme {
        Test3()
    }
}

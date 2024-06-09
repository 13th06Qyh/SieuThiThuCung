import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.example.sttc.R
import com.example.sttc.ui.theme.STTCTheme
import com.example.sttc.view.System.PayBillChoose
import com.example.sttc.view.System.PinDigitField
import com.example.sttc.view.System.PinEntryField
import com.example.sttc.viewmodel.AccountViewModel

@Composable
fun PayBillChooseT(
    openOTP: () -> Unit,
    openCard: () -> Unit,
    accountViewModel: AccountViewModel
) {
    var showDialogError by remember { mutableStateOf(false) }
    var pinCode by remember { mutableStateOf("") }
    val userState by accountViewModel.userInfoFlow.collectAsState(initial = null)
    var okButtonPressed by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    AlertDialog(
        containerColor = Color(0xFFccf5ff),
        shape = RoundedCornerShape(8.dp),
        onDismissRequest = { showDialogError = false },
        title = {},
        text = {
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
            )
        },
        confirmButton = {
                Button(
                    onClick = {
                        showDialogError = false
                        okButtonPressed = true
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFccffdd), // Màu nền của nút
                        contentColor = Color.Black, // Màu chữ của nút
                    ),
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

        },
        dismissButton = {
                Button(
                    onClick = {
                        showDialogError = false
                        okButtonPressed = true
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.LightGray, // Màu nền của nút
                        contentColor = Color.Black, // Màu chữ của nút
                    ),
                    border = BorderStroke(1.dp, Color.Black),
                ) {
                    Text(
                        "Hủy",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    )
                }
            }

    )
}


@Preview(showBackground = true)
@Composable
fun PayPreviewT() {
    STTCTheme {
//        Card(back = {}, openCart = {})
//        Card(back = {})
        PayBillChooseT(
            openOTP = { /*TODO*/ },
            openCard = { /*TODO*/ },
            accountViewModel = AccountViewModel(
                LocalContext.current
            )
        )
    }
}
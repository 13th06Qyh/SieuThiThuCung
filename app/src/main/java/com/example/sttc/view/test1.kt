import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
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
import com.example.sttc.viewmodel.AccountViewModel

@Composable
fun PayBillChooseT(
    openOTP: () -> Unit,
    openCard: () -> Unit,
    accountViewModel: AccountViewModel
) {
    var showDialogSecret by remember { mutableStateOf(false) }
    var pinCode by remember { mutableStateOf("") }
    val userState by accountViewModel.userInfoFlow.collectAsState(initial = null)
    var showErrorOTP by remember { mutableStateOf(false) }
    var errorMessageOTP by remember { mutableStateOf("") }

    AlertDialog(
        modifier = Modifier.fillMaxWidth(),
        onDismissRequest = { showDialogSecret = false },
        title = {
            Text(
                text = "Nhập Mã Giao Dịch",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
            )
        },
        text = {
            Column{
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    for (i in 0 until 6) {
                        PinDigitField(
                            digit = if (pinCode.length > i) pinCode[i].toString() else "",
                            onDigitChange = { newDigit ->
                                if (newDigit.length <= 1 && newDigit.all { it.isDigit() }) {
                                    val newPinCode = StringBuilder(pinCode).apply {
                                        if (newDigit.isEmpty() && pinCode.isNotEmpty()) {
                                            deleteCharAt(lastIndex)
                                        } else if (pinCode.length < 6) {
                                            append(newDigit)
                                        }
                                    }.toString()
                                    pinCode = newPinCode

                                    showErrorOTP = true
                                    errorMessageOTP = "Mã OTP không đúng"
                                }
                            }
                        )
                    }
                }

                if(showErrorOTP)
                {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Mã OTP không đúng!",
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Red,
                                textAlign = TextAlign.Center
                            )
                        )
                    }
                }
            }

        },
        confirmButton = {
            Button(
                onClick = {
                    // Confirm button logic here
                    showDialogSecret = false
//                    openCart()

                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFffcc99),
                    contentColor = Color.Black
                ),
                border = BorderStroke(1.dp, Color.Red)
            ) {
                Text("OK", style = TextStyle(fontWeight = FontWeight.Bold))
            }
        },
        dismissButton = {
            Button(
                onClick = {
                    // Dismiss button logic here
                    showDialogSecret = false
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFd9d9d9),
                    contentColor = Color.Black
                ),
                border = BorderStroke(1.dp, Color.Black)
            ) {
                Text("Hủy", style = TextStyle(fontWeight = FontWeight.Bold))
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
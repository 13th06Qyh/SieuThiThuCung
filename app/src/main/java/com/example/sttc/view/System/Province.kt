package com.example.sttc.view.System

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sttc.model.User
import com.example.sttc.viewmodel.AccountViewModel

data class Province(val name: String, val cities: List<City>)
data class City(val name: String, val wards: List<Ward>)
data class Ward(val name: String, val teams: List<Team>)
data class Team(val name: String)

val teams = listOf(
    Team("TDP 1"), Team("TDP 2"), Team("TDP 3"),
    Team("TDP 4"), Team("TDP 5"), Team("TDP 6"),
    Team("TDP 7"), Team("TDP 8"), Team("TDP 9"),
    Team("TDP 10"), Team("Thôn La Hà Tây"), Team("Thôn Hòa Phong"), Team("Thôn Bình Hòa"),
    Team("Thôn Phú Mỹ"), Team("Thôn Thanh Đa")
)

val provinces = listOf(
    Province(
        name = "Quảng Bình",
        cities = listOf(
            City(
                name = "Thành phố Đồng Hới",
                wards = listOf(
                    Ward(name = "Phường Bắc Lý", teams = teams),
                    Ward(name = "Phường Nam Lý", teams = teams),
                    Ward(name = "Phường Đồng Phú", teams = teams),
                    Ward(name = "Xã Bảo Ninh", teams = teams),
                    Ward(name = "Xã Lộc Ninh", teams = teams)
                )
            ),
            City(
                name = "Thị xã Ba Đồn",
                wards = listOf(
                    Ward(name = "Phường Ba Đồn", teams = teams),
                    Ward(name = "Phường Quảng Long", teams = teams),
                    Ward(name = "Xã Quảng Trung", teams = teams),
                    Ward(name = "Xã Quảng Văn", teams = teams)
                )
            ),
            City(
                name = "Huyện Bố Trạch",
                wards = listOf(
                    Ward(name = "Thị trấn Hoàn Lão", teams = teams),
                    Ward(name = "Xã Xuân Trạch", teams = teams)
                )
            ),
            City(
                name = "Huyện Lệ Thủy",
                wards = listOf(
                    Ward(name = "Thị trấn Kiến Giang", teams = teams),
                    Ward(name = "Xã Hưng Thủy", teams = teams)
                )
            )
        )
    ),
    Province(
        name = "Quảng Nam",
        cities = listOf(
            City(
                name = "Thành phố Tam Kỳ",
                wards = listOf(
                    Ward(name = "Phường An Mỹ", teams = teams),
                    Ward(name = "Phường Tân Thạnh", teams = teams),
                    Ward(name = "Xã Tam Phú", teams = teams)
                )
            ),
            City(
                name = "Thành phố Hội An",
                wards = listOf(
                    Ward(name = "Phường Cẩm An", teams = teams),
                    Ward(name = "Phường Cẩm Châu", teams = teams),
                    Ward(name = "Xã Tân Hiệp", teams = teams),
                    Ward(name = "Xã Cẩm Hà", teams = teams)
                )
            ),
            City(
                name = "Thị xã Điện Bàn",
                wards = listOf(
                    Ward(name = "Phường Điện Ngọc", teams = teams),
                    Ward(name = "Phường Điện Nam Bắc", teams = teams),
                    Ward(name = "Phường Điện Nam Đông", teams = teams),
                    Ward(name = "Xã Điện Trung", teams = teams)
                )
            ),
            City(
                name = "Huyện Duy Xuyên",
                wards = listOf(
                    Ward(name = "Thị trấn Nam Phước", teams = teams),
                    Ward(name = "Xã Duy Thành", teams = teams)
                )
            ),
            City(
                name = "Huyện Đại Lộc",
                wards = listOf(
                    Ward(name = "Thị trấn Ái Nghĩa", teams = teams),
                    Ward(name = "Xã Đại Thạnh", teams = teams)
                )
            )
        )
    ),
    Province(
        name = "Thanh Hóa",
        cities = listOf(
            City(
                name = "Thành phố Thanh Hóa",
                wards = listOf(
                    Ward(name = "Phường Điện Biên", teams = teams),
                    Ward(name = "Xã Thiệu Vân", teams = teams)
                )
            ),
            City(
                name = "Thành phố Sầm Sơn",
                wards = listOf(
                    Ward(name = "Phường Quảng Châu", teams = teams),
                    Ward(name = "Phường Quảng Tiến", teams = teams),
                    Ward(name = "Xã Quảng Đại", teams = teams)
                )
            ),
            City(
                name = "Thị xã Nghi Sơn",
                wards = listOf(
                    Ward(name = "Phường Bình Minh", teams = teams),
                    Ward(name = "Xã Thanh Thủy", teams = teams)
                )
            ),
            City(
                name = "Huyện Bá Thước",
                wards = listOf(
                    Ward(name = "Thị trấn Cành Nàng", teams = teams),
                    Ward(name = "Xã Văn Nho", teams = teams)
                )
            )
        )
    ),
    Province(
        name = "Hà Nội",
        cities = listOf(
            City(
                name = "Quận Ba Đình",
                wards = listOf(
                    Ward(name = "Phường Trúc Bạch", teams = teams),
                    Ward(name = "Phường Liễu Giai", teams = teams)
                )
            ),
            City(
                name = "Quận Đống Đa",
                wards = listOf(
                    Ward(name = "Phường Láng Hạ", teams = teams),
                    Ward(name = "Phường Ô Chợ Dừa", teams = teams),
                    Ward(name = "Phường Kim Liên", teams = teams),
                    Ward(name = "Phường Quốc Tử Giám", teams = teams)
                )
            ),
            City(
                name = "Thị xã Sơn Tây",
                wards = listOf(
                    Ward(name = "Phường Lê Lợi", teams = teams),
                    Ward(name = "Xã Sơn Đông", teams = teams)
                )
            )
        )
    ),
    Province(
        name = "TP.Hồ Chí Minh",
        cities = listOf(
            City(
                name = "Thành phố Thủ Đức",
                wards = listOf(
                    Ward(name = "Phường Bình Chiểu", teams = teams),
                    Ward(name = "Phường Tân Phú", teams = teams),
                    Ward(name = "Phường Thủ Thiêm", teams = teams),
                    Ward(name = "Phường An Khánh", teams = teams),
                    Ward(name = "Phường Hiệp Bình Chánh", teams = teams)
                )
            ),
            City(
                name = "Quận 1",
                wards = listOf(
                    Ward(name = "Phường Bến Nghé", teams = teams),
                    Ward(name = "Phường Cầu Ông Lãnh", teams = teams),
                    Ward(name = "Phường Nguyễn Cư Trinh", teams = teams),
                    Ward(name = "Phường Phạm Ngũ Lão", teams = teams)
                )
            ),
            City(
                name = "Quận 12",
                wards = listOf(
                    Ward(name = "Phường An Phú Đông", teams = teams),
                    Ward(name = "Phường Thạnh Xuân", teams = teams),
                    Ward(name = "Phường Thới An ", teams = teams),
                    Ward(name = "Phường Tân Chánh Hiệp", teams = teams)
                )
            ),
            City(
                name = "Quận Tân Bình",
                wards = listOf(
                    Ward(name = "Phường 1", teams = teams),
                    Ward(name = "Phường 2", teams = teams),
                    Ward(name = "Phường 3", teams = teams),
                    Ward(name = "Phường 4", teams = teams),
                    Ward(name = "Phường 5", teams = teams)
                )
            ),
            City(
                name = "Quận Gò Vấp",
                wards = listOf(
                    Ward(name = "Phường 1", teams = teams),
                    Ward(name = "Phường 2", teams = teams),
                    Ward(name = "Phường 3", teams = teams),
                    Ward(name = "Phường 4", teams = teams)
                )
            ),
            City(
                name = "Huyện Bình Chánh",
                wards = listOf(
                    Ward(name = "Thị trấn Tân Túc", teams = teams),
                    Ward(name = "Xã An Phú Tây", teams = teams),
                    Ward(name = "Xã Bình Chánh", teams = teams),
                    Ward(name = "Xã Vĩnh Lộc B", teams = teams)
                )
            )
        )
    ),
    Province(
        name = "Đà Nẵng",
        cities = listOf(
            City(
                name = "Quận Thanh Khê",
                wards = listOf(
                    Ward(name = "Phường An Khê", teams = teams),
                    Ward(name = "Phường Chính Gián", teams = teams),
                    Ward(name = "Phường Hòa Khê", teams = teams),
                    Ward(name = "Phường Tam Thuận", teams = teams)
                )
            ),
            City(
                name = "Quận Liên Chiểu",
                wards = listOf(
                    Ward(name = "Phường Hòa Khánh Nam", teams = teams),
                    Ward(name = "Phường Hòa Khánh Bắc", teams = teams),
                    Ward(name = "Phường Hòa Minh", teams = teams)
                )
            ),
            City(
                name = "Quận Hải Châu",
                wards = listOf(
                    Ward(name = "Phường Thuận Phước", teams = teams),
                    Ward(name = "Phường Hải Châu I", teams = teams),
                    Ward(name = "Phường Hải Châu II", teams = teams)
                )
            ),
            City(
                name = "Quận Ngũ Hành Sơn",
                wards = listOf(
                    Ward(name = "Phường Hòa Quý", teams = teams),
                    Ward(name = "Phường Khuê Mỹ", teams = teams),
                    Ward(name = "Phường Hòa Hải", teams = teams)
                )
            )
        )
    ),
    Province(
        name = "Hà Tĩnh",
        cities = listOf(
            City(
                name = "Thành phố Hà Tĩnh",
                wards = listOf(
                    Ward(name = "Phường Trần Phú", teams = teams),
                    Ward(name = "Phường Văn Yên", teams = teams)
                )
            ),
            City(
                name = "Thị xã Hồng Lĩnh",
                wards = listOf(
                    Ward(name = "Phường Trung Lương", teams = teams),
                    Ward(name = "Xã Thuận Lộc", teams = teams)
                )
            )
        )
    ),
    Province(
        name = "Thừa Thiên Huế",
        cities = listOf(
            City(
                name = "Thành phố Huế",
                wards = listOf(
                    Ward(name = "Phường Hương Vinh", teams = teams),
                    Ward(name = "Phường Kim Long", teams = teams),
                    Ward(name = "Phường Phú Hậu", teams = teams),
                    Ward(name = "Phường Phú Nhuận", teams = teams),
                    Ward(name = "Xã Phú Mậu", teams = teams)
                )
            ),
            City(
                name = "Thị xã Hương Thủy",
                wards = listOf(
                    Ward(name = "Phường Phú Bài", teams = teams),
                    Ward(name = "Xã Thủy Thanh", teams = teams)
                )
            ),
            City(
                name = "Thị xã Hương Trà",
                wards = listOf(
                    Ward(name = "Phường Hương Vân", teams = teams),
                    Ward(name = "Xã Bình Thành", teams = teams)
                )
            )
        )
    ),
    Province(
        name = "Bà Rịa - Vũng Tàu",
        cities = listOf(
            City(
                name = "Thành phố Bà Rịa",
                wards = listOf(
                    Ward(name = "Phường Phước Hưng", teams = teams),
                    Ward(name = "Phường Phước Nguyên", teams = teams)
                )
            ),
            City(
                name = "Thành phố Vũng Tàu",
                wards = listOf(
                    Ward(name = "Phường 1", teams = teams),
                    Ward(name = "Phường 2", teams = teams),
                    Ward(name = "Phường 3", teams = teams)
                )
            )
        )
    ),

    Province(
        name = "Cần Thơ",
        cities = listOf(
            City(
                name = "Quận Bình Thủy",
                wards = listOf(
                    Ward(name = "Phường An Thới", teams = teams),
                    Ward(name = "Phường Long Hòa", teams = teams)
                )
            ),
            City(
                name = "Quận Thốt Nốt",
                wards = listOf(
                    Ward(name = "Phường Tân Hưng", teams = teams),
                    Ward(name = "Phường Thuận An", teams = teams)
                )
            ),
            City(
                name = "Huyện Phong Điền",
                wards = listOf(
                    Ward(name = "Thị trấn Phong Điền", teams = teams),
                    Ward(name = "Xã Giai Xuân", teams = teams)
                )
            )
        )
    ),
    Province(
        name = "Quảng Trị",
        cities = listOf(
            City(
                name = "Thành phố Đông Hà",
                wards = listOf(
                    Ward(name = "Phường Đông Giang", teams = teams),
                    Ward(name = "Phường Đông Lễ", teams = teams)
                )
            ),
            City(
                name = "Thị xã Quảng Trị",
                wards = listOf(
                    Ward(name = "Phường An Đôn", teams = teams),
                    Ward(name = "Xã Hải Lệ", teams = teams)
                )
            ),
            City(
                name = "Huyện Cam Lộ",
                wards = listOf(
                    Ward(name = "Thị trấn Cam Lộ", teams = teams),
                    Ward(name = "Xã Cam Chính", teams = teams)
                )
            )
        )
    ),
    Province(
        name = "Nghệ An",
        cities = listOf(
            City(
                name = "Thành phố Vinh",
                wards = listOf(
                    Ward(name = "Phường Bến Thủy", teams = teams),
                    Ward(name = "Phường Cửa Nam", teams = teams),
                    Ward(name = "Phường Hồng Sơn", teams = teams),
                    Ward(name = "Phường Hưng Bình", teams = teams),
                    Ward(name = "Xã Nghi Phú", teams = teams)
                )
            ),
            City(
                name = "Thị xã Cửa Lò",
                wards = listOf(
                    Ward(name = "Phường Nghi Hải", teams = teams),
                    Ward(name = "Phường Nghi Hòa", teams = teams)
                )
            ),
            City(
                name = "Huyện Con Cuông",
                wards = listOf(
                    Ward(name = "Thị trấn Con Cuông", teams = teams),
                    Ward(name = "Xã: Bình Chuẩn", teams = teams)
                )
            )
        )
    ),

    Province(
        name = "Tuyên Quang",
        cities = listOf(
            City(
                name = "Thành phố Tuyên Quang",
                wards = listOf(
                    Ward(name = "Phường An Tường", teams = teams),
                    Ward(name = "Phường Đội Cấn", teams = teams)
                )
            ),
            City(
                name = "Huyện Hàm Yên",
                wards = listOf(
                    Ward(name = "Thị trấn Tân Yên", teams = teams),
                    Ward(name = "Xã: Bạch Xa", teams = teams)
                )
            ),
            City(
                name = "Huyện Chiêm Hóa",
                wards = listOf(
                    Ward(name = "Thị trấn Vĩnh Lộc", teams = teams),
                    Ward(name = "Xã: Bình Nhân", teams = teams)
                )
            )
        )
    ),
    Province(
        name = "Hải Phòng",
        cities = listOf(
            City(
                name = "Quận Hồng Bàng",
                wards = listOf(
                    Ward(name = "Phường Hạ Lý", teams = teams),
                    Ward(name = "Phường Hoàng Văn Thụ", teams = teams)
                )
            ),
            City(
                name = "Quận Ngô Quyền",
                wards = listOf(
                    Ward(name = "Phường Cầu Đất", teams = teams),
                    Ward(name = "Phường Cầu Tre", teams = teams)
                )
            ),
            City(
                name = "Huyện An Lão",
                wards = listOf(
                    Ward(name = "Thị trấn An Lão", teams = teams),
                    Ward(name = "Thị trấn Trường Sơn", teams = teams)
                )
            )
        )
    ),
    Province(
        name = "Phú Yên",
        cities = listOf(
            City(
                name = "Thành phố Tuy Hòa",
                wards = listOf(
                    Ward(name = "Phường 1", teams = teams),
                    Ward(name = "Phường 2", teams = teams),
                    Ward(name = "Phường 3", teams = teams),
                    Ward(name = "Phường 4", teams = teams),
                    Ward(name = "Phường 5", teams = teams),
                    Ward(name = "Phường Phú Đông", teams = teams),
                    Ward(name = "Xã Hòa Kiến", teams = teams),
                )
            ),
            City(
                name = "Thị xã Sông Cầu",
                wards = listOf(
                    Ward(name = "Phường Hòa Xuân Đài", teams = teams),
                    Ward(name = "Phường Hòa Xuân Phú", teams = teams),
                    Ward(name = "Xã Xuân Thịnh", teams = teams),
                    Ward(name = "Xã Xuân Phương", teams = teams),
                )
            ),
            City(
                name = "Thị xã Đông Hòa",
                wards = listOf(
                    Ward(name = "Phường Hòa Hiệp Trung", teams = teams),
                    Ward(name = "Phường Hòa Hiệp Bắc", teams = teams),
                    Ward(name = "Phường Hòa Hiệp Nam", teams = teams),
                    Ward(name = "Phường Hòa Xuân Tây", teams = teams)
                )
            )
        )
    ),
    Province(
        name = "Quảng Ngãi",
        cities = listOf(
            City(
                name = "Thành phố Quảng Ngãi",
                wards = listOf(
                    Ward(name = "Phường Chánh Lộ", teams = teams),
                    Ward(name = "Phường Lê Hồng Phong", teams = teams),
                    Ward(name = "Xã Nghĩa An", teams = teams),
                    Ward(name = "Xã Tịnh Thiện", teams = teams)
                )
            ),
            City(
                name = "Thị xã Đức Phổ",
                wards = listOf(
                    Ward(name = "Phường Nguyễn Nghiêm", teams = teams),
                    Ward(name = "Phường Phổ Hòa", teams = teams),
                    Ward(name = "Xã Phổ An", teams = teams),
                    Ward(name = "Xã Phổ Châu", teams = teams)
                )
            ),
            City(
                name = "Huyện Bình Sơn",
                wards = listOf(
                    Ward(name = "Thị trấn Châu Ổ", teams = teams),
                    Ward(name = "Xã Bình Chánh", teams = teams)
                )
            )
        )
    ),
    Province(
        name = "Phú Thọ",
        cities = listOf(
            City(
                name = "Thành phố Việt Trì",
                wards = listOf(
                    Ward(name = "Phường Bạch Hạc", teams = teams),
                    Ward(name = "Phường Bến Gót", teams = teams),
                    Ward(name = "Xã Trưng Vương", teams = teams),
                    Ward(name = "Xã Thụy Vân", teams = teams)
                )
            ),
            City(
                name = "Thị xã Phú Thọ",
                wards = listOf(
                    Ward(name = "Phường Âu Cơ", teams = teams),
                    Ward(name = "Phường Hùng Vương", teams = teams),
                    Ward(name = "Xã Phú Hộ", teams = teams),
                    Ward(name = "Xã Thanh Minh", teams = teams)
                )
            ),
            City(
                name = "Huyện Cẩm Khê",
                wards = listOf(
                    Ward(name = "Thị trấn Cẩm Khê", teams = teams),
                    Ward(name = "Xã Yên Tập", teams = teams)
                )
            )
        )
    )


)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun getAddress(
    user: User,
    accountViewModel: AccountViewModel
) {
    var newAddress by remember { mutableStateOf("") }
    var showErrorAddress by remember { mutableStateOf(false) }
    var showDialogAddress by remember { mutableStateOf(false) }

    var showProvinceDropdown by remember { mutableStateOf(false) }
    var showCityDropdown by remember { mutableStateOf(false) }
    var showWardDropdown by remember { mutableStateOf(false) }
    var showTeamDropdown by remember { mutableStateOf(false) }

    var selectedProvince by remember { mutableStateOf<Province?>(null) }
    var selectedCity by remember { mutableStateOf<City?>(null) }
    var selectedWard by remember { mutableStateOf<Ward?>(null) }
    var selectedTeam by remember { mutableStateOf<Team?>(null) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(0.2.dp, color = Color(0xFF000000))
            .background(
                Brush.horizontalGradient(
                    colors = listOf(
                        Color(0xFFB2B0B0),
                        Color(0xFFCFCFCF),
                        Color(0xFFFFFFFF),
                        Color(0xFFFFFFFF),
                        Color(0xFFFFEDFB),
                    ),
                    startX = 0.0f,
                    endX = 860.0f
                )
            ),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            Icons.Default.LocationOn,
            contentDescription = "LocationOn",
            modifier = Modifier.size(18.dp),
        )
        Text(
            text = "Địa chỉ:",
            modifier = Modifier
                .padding(5.dp, 0.dp, 14.dp, 1.dp)
                .width(100.dp),
            style = TextStyle(
                fontSize = 17.sp,
                color = Color(0xFF000000),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start
            ),
        )
//            VerticalDivider(thickness = 1.dp, color = Color(0xFF000000))
        Text(
            text = user.diachi,
            modifier = Modifier
                .padding(0.dp, 20.dp, 10.dp, 20.dp)
                .width(200.dp),
            style = TextStyle(
                fontSize = 18.sp,
                color = Color(0xFF000000),
                fontStyle = FontStyle.Italic,
                textAlign = TextAlign.Start
            ),
        )

        IconButton(onClick = { showDialogAddress = true }) {
            Icon(
                Icons.Default.Create,
                contentDescription = "EditAddressAccount",
                tint = Color.Gray,
                modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 0.dp),
            )
        }
        if (showDialogAddress) {
            AlertDialog(
                containerColor = Color(0xFFFFBEBE),
                onDismissRequest = {
                    showErrorAddress = false
                    showDialogAddress = false
                },
                title = {
                    Text(
                        "Cập Nhật Địa Chỉ",
                        style = TextStyle(
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            fontSize = 23.sp,
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                text = {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(1.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Box {
                                Row(
                                    modifier = Modifier
                                        .size(132.dp, 45.dp)
                                        .border(1.dp, Color.White, RoundedCornerShape(10.dp))
                                        .background(Color(0xFF99D9FF), RoundedCornerShape(10.dp)),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceEvenly
                                ) {
                                    Text(
                                        text = selectedProvince?.name ?: "Tỉnh/Thành Phố",
                                        modifier = Modifier
                                            .padding(start = 5.dp, top = 5.dp, bottom = 5.dp)
                                            .clickable { showProvinceDropdown = true })
                                    Icon(
                                        Icons.Default.KeyboardArrowDown,
                                        contentDescription = "Dropdown icon"
                                    )
                                }
                                DropdownMenu(
                                    expanded = showProvinceDropdown,
                                    onDismissRequest = { showProvinceDropdown = false },
                                    modifier = Modifier.fillMaxWidth()

                                ) {
                                    provinces.forEach { province ->
                                        DropdownMenuItem(
                                            onClick = {
                                                selectedProvince = province
                                                selectedCity = null
                                                selectedWard = null
                                                selectedTeam = null
                                                showProvinceDropdown = false
                                            },
                                            text = {
                                                Text(text = province.name)
                                            }
                                        )
                                    }
                                }
                            }
                            Box {
                                Row(
                                    modifier = Modifier
                                        .size(132.dp, 45.dp)
                                        .border(1.dp, Color.White, RoundedCornerShape(10.dp))
                                        .background(Color(0xFF99D9FF), RoundedCornerShape(10.dp)),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        text = selectedCity?.name ?: "Quận/Huyện",
                                        modifier = Modifier
                                            .padding(start = 5.dp, top = 5.dp, bottom = 5.dp)
                                            .clickable {
                                                if (selectedProvince != null) {
                                                    showCityDropdown = true
                                                }
                                            }
                                    )
                                    Icon(
                                        Icons.Default.KeyboardArrowDown,
                                        contentDescription = "Dropdown icon"
                                    )
                                }
                                DropdownMenu(
                                    expanded = showCityDropdown,
                                    onDismissRequest = { showCityDropdown = false },
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    selectedProvince?.cities?.forEach { city ->
                                        DropdownMenuItem(
                                            onClick = {
                                                selectedCity = city
                                                selectedWard = null
                                                selectedTeam = null
                                                showCityDropdown = false
                                            },
                                            text = {
                                                Text(text = city.name)
                                            }
                                        )
                                    }
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Box {
                                Row(
                                    modifier = Modifier
                                        .size(132.dp, 45.dp)
                                        .border(1.dp, Color.White, RoundedCornerShape(10.dp))
                                        .background(Color(0xFF99D9FF), RoundedCornerShape(10.dp)),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        text = selectedWard?.name ?: "Xã/Phường",
                                        modifier = Modifier
                                            .padding(start = 5.dp, top = 5.dp, bottom = 5.dp)
                                            .clickable {
                                                if (selectedCity != null) {
                                                    showWardDropdown = true
                                                }
                                            }
                                    )
                                    Icon(
                                        Icons.Default.KeyboardArrowDown,
                                        contentDescription = "Dropdown icon"
                                    )
                                }
                                DropdownMenu(
                                    expanded = showWardDropdown,
                                    onDismissRequest = { showWardDropdown = false },
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    selectedCity?.wards?.forEach { ward ->
                                        DropdownMenuItem(
                                            onClick = {
                                                selectedWard = ward
                                                selectedTeam = null
                                                showWardDropdown = false
                                            },
                                            text = {
                                                Text(text = ward.name)
                                            }
                                        )
                                    }
                                }
                            }
                            Box {
                                Row(
                                    modifier = Modifier
                                        .size(132.dp, 45.dp)
                                        .border(1.dp, Color.White, RoundedCornerShape(10.dp))
                                        .background(Color(0xFF99D9FF), RoundedCornerShape(10.dp)),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        text = selectedTeam?.name ?: "Thôn/Tổ",
                                        modifier = Modifier
                                            .padding(start = 5.dp, top = 5.dp, bottom = 5.dp)
                                            .clickable {
                                                if (selectedWard != null) {
                                                    showTeamDropdown = true
                                                }
                                            }
                                    )
                                    Icon(
                                        Icons.Default.KeyboardArrowDown,
                                        contentDescription = "Dropdown icon"
                                    )
                                }
                                DropdownMenu(
                                    expanded = showTeamDropdown,
                                    onDismissRequest = { showTeamDropdown = false },
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    selectedWard?.teams?.forEach { team ->
                                        DropdownMenuItem(
                                            onClick = {
                                                selectedTeam = team
                                                showTeamDropdown = false
                                            },
                                            text = {
                                                Text(text = team.name)
                                            }
                                        )
                                    }
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        BasicTextField(
                            value = listOfNotNull(
                                selectedTeam?.name,
                                selectedWard?.name,
                                selectedCity?.name,
                                selectedProvince?.name
                            ).joinToString(", "),
                            onValueChange = {},
                            textStyle = TextStyle(
                                color = Color.Black
                            ),
                            modifier = Modifier
                                .height(80.dp)
                                .fillMaxWidth()
                                .border(1.dp, Color.Black, RoundedCornerShape(10.dp))
                                .background(Color.White, RoundedCornerShape(10.dp))
                                .padding(vertical = 12.dp, horizontal = 12.dp)
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        TextField(
                            value = newAddress,
                            onValueChange = { newAddress = it },
                            placeholder = { Text("Nhập tên đường, số nhà, số ngõ nếu có hoặc đặc điểm chi tiết.") },
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = Color(0xffffebe6),
                                unfocusedIndicatorColor = Color(0xffe62e00),
                            )
                        )

                        if (showErrorAddress) {
                            Text(
                                text = "Vui lòng chọn đầy đủ địa chỉ và nhập địa chỉ chi tiết.",
                                color = Color.Red,
                                modifier = Modifier.padding(16.dp),
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    textAlign = TextAlign.Center,
                                    fontStyle = FontStyle.Italic
                                )
                            )
                        }
                    }
                },
                confirmButton = {
                    Button(
                        onClick = {
                            val id = user.id
                            val location = newAddress + ", " + listOfNotNull(
                                selectedTeam?.name,
                                selectedWard?.name,
                                selectedCity?.name,
                                selectedProvince?.name
                            ).joinToString(", ")
                            if (newAddress.isNotEmpty() && selectedProvince != null && selectedCity != null && selectedWard != null && selectedTeam != null) {
                                accountViewModel.updateAddress(location.toString(), id)
                                user.diachi = location.toString()
                                showDialogAddress = false
                                showErrorAddress = false


                            } else {
                                showErrorAddress = true
//                                showDialogAddress = true

                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFA2FFAB), // Màu nền của nút
                            contentColor = Color.Black, // Màu chữ của nút
                        ),

                        border = BorderStroke(1.dp, Color(0xFF018B0F)),
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
                    Button(
                        onClick = {
                            showErrorAddress = false
                            showDialogAddress = false
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFFA483), // Màu nền của nút
                            contentColor = Color.Black, // Màu chữ của nút
                        ),

                        border = BorderStroke(1.dp, Color(0xFF8B2701)),
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
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun getLocation() {
    var newAddress by remember { mutableStateOf("") }
    var showErrorAddress by remember { mutableStateOf(false) }

    var showProvinceDropdown by remember { mutableStateOf(false) }
    var showCityDropdown by remember { mutableStateOf(false) }
    var showWardDropdown by remember { mutableStateOf(false) }
    var showTeamDropdown by remember { mutableStateOf(false) }

    var selectedProvince by remember { mutableStateOf<Province?>(null) }
    var selectedCity by remember { mutableStateOf<City?>(null) }
    var selectedWard by remember { mutableStateOf<Ward?>(null) }
    var selectedTeam by remember { mutableStateOf<Team?>(null) }

    Column(
        modifier = Modifier.fillMaxWidth().padding(5.dp).background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp, 5.dp, 10.dp, 0.dp)
                .background(Color.White),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.AddCircle,
                contentDescription = "Location Icon",
                tint = Color.Red,
                modifier = Modifier.size(30.dp)
            )
            Text(
                text = "Dùng địa chỉ khác",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                ),
                modifier = Modifier.padding(10.dp, 10.dp)
            )
            val checkedStateOther = remember { mutableStateOf(false) }
            Checkbox(
                checked = checkedStateOther.value,
                onCheckedChange = { checkedStateOther.value = it },
                modifier = Modifier
                    .size(20.dp) // Thay đổi kích thước của checkbox
                    .padding(130.dp, 0.dp, 15.dp, 0.dp)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Box {
                Row(
                    modifier = Modifier
                        .size(132.dp, 45.dp)
                        .border(1.dp, Color.White, RoundedCornerShape(10.dp))
                        .background(Color(0xFFffcccc), RoundedCornerShape(10.dp)),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(
                        text = selectedProvince?.name ?: "Tỉnh/Thành Phố",
                        modifier = Modifier
                            .padding(start = 5.dp, top = 5.dp, bottom = 5.dp)
                            .clickable { showProvinceDropdown = true })
                    Icon(
                        Icons.Default.KeyboardArrowDown,
                        contentDescription = "Dropdown icon"
                    )
                }
                DropdownMenu(
                    expanded = showProvinceDropdown,
                    onDismissRequest = { showProvinceDropdown = false },
                    modifier = Modifier.fillMaxWidth()

                ) {
                    provinces.forEach { province ->
                        DropdownMenuItem(
                            onClick = {
                                selectedProvince = province
                                selectedCity = null
                                selectedWard = null
                                selectedTeam = null
                                showProvinceDropdown = false
                            },
                            text = {
                                Text(text = province.name)
                            }
                        )
                    }
                }
            }
            Box {
                Row(
                    modifier = Modifier
                        .size(132.dp, 45.dp)
                        .border(1.dp, Color.White, RoundedCornerShape(10.dp))
                        .background(Color(0xFFffcccc), RoundedCornerShape(10.dp)),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = selectedCity?.name ?: "Quận/Huyện",
                        modifier = Modifier
                            .padding(start = 5.dp, top = 5.dp, bottom = 5.dp)
                            .clickable {
                                if (selectedProvince != null) {
                                    showCityDropdown = true
                                }
                            }
                    )
                    Icon(
                        Icons.Default.KeyboardArrowDown,
                        contentDescription = "Dropdown icon"
                    )
                }
                DropdownMenu(
                    expanded = showCityDropdown,
                    onDismissRequest = { showCityDropdown = false },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    selectedProvince?.cities?.forEach { city ->
                        DropdownMenuItem(
                            onClick = {
                                selectedCity = city
                                selectedWard = null
                                selectedTeam = null
                                showCityDropdown = false
                            },
                            text = {
                                Text(text = city.name)
                            }
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Box {
                Row(
                    modifier = Modifier
                        .size(132.dp, 45.dp)
                        .border(1.dp, Color.White, RoundedCornerShape(10.dp))
                        .background(Color(0xFFffcccc), RoundedCornerShape(10.dp)),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = selectedWard?.name ?: "Xã/Phường",
                        modifier = Modifier
                            .padding(start = 5.dp, top = 5.dp, bottom = 5.dp)
                            .clickable {
                                if (selectedCity != null) {
                                    showWardDropdown = true
                                }
                            }
                    )
                    Icon(
                        Icons.Default.KeyboardArrowDown,
                        contentDescription = "Dropdown icon"
                    )
                }
                DropdownMenu(
                    expanded = showWardDropdown,
                    onDismissRequest = { showWardDropdown = false },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    selectedCity?.wards?.forEach { ward ->
                        DropdownMenuItem(
                            onClick = {
                                selectedWard = ward
                                selectedTeam = null
                                showWardDropdown = false
                            },
                            text = {
                                Text(text = ward.name)
                            }
                        )
                    }
                }
            }
            Box {
                Row(
                    modifier = Modifier
                        .size(132.dp, 45.dp)
                        .border(1.dp, Color.White, RoundedCornerShape(10.dp))
                        .background(Color(0xFFffcccc), RoundedCornerShape(10.dp)),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = selectedTeam?.name ?: "Thôn/Tổ",
                        modifier = Modifier
                            .padding(start = 5.dp, top = 5.dp, bottom = 5.dp)
                            .clickable {
                                if (selectedWard != null) {
                                    showTeamDropdown = true
                                }
                            }
                    )
                    Icon(
                        Icons.Default.KeyboardArrowDown,
                        contentDescription = "Dropdown icon"
                    )
                }
                DropdownMenu(
                    expanded = showTeamDropdown,
                    onDismissRequest = { showTeamDropdown = false },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    selectedWard?.teams?.forEach { team ->
                        DropdownMenuItem(
                            onClick = {
                                selectedTeam = team
                                showTeamDropdown = false
                            },
                            text = {
                                Text(text = team.name)
                            }
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        BasicTextField(
            value = listOfNotNull(
                selectedTeam?.name,
                selectedWard?.name,
                selectedCity?.name,
                selectedProvince?.name
            ).joinToString(", "),
            onValueChange = {},
            textStyle = TextStyle(
                color = Color.Black
            ),
            modifier = Modifier.padding(start = 5.dp, end = 5.dp)
                .height(70.dp)
                .fillMaxWidth()
                .border(1.dp, Color.Black, RoundedCornerShape(10.dp))
                .background(Color.White, RoundedCornerShape(10.dp))
                .padding(vertical = 12.dp, horizontal = 12.dp)

        )

        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = newAddress,
            onValueChange = { newAddress = it },
            placeholder = { Text("Nhập tên đường, số nhà, số ngõ nếu có hoặc đặc điểm chi tiết tại đây.") },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(0xffffebe6),
                unfocusedIndicatorColor = Color(0xffe62e00),
            )
        )

        if (showErrorAddress) {
            Text(
                text = "Vui lòng chọn đầy đủ địa chỉ và nhập địa chỉ chi tiết.",
                color = Color.Red,
                modifier = Modifier.padding(16.dp),
                style = TextStyle(
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    fontStyle = FontStyle.Italic
                )
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ProPreview() {
    getLocation()
}




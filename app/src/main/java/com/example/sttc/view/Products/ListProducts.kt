package com.example.sttc.view.Products

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.request.ImageRequest
import com.example.sttc.R
import com.example.sttc.ui.theme.STTCTheme
import com.example.sttc.view.System.SuggestTodayopen
import com.example.sttc.viewmodel.ProductViewModel

@OptIn(ExperimentalFoundationApi::class)
@ExperimentalMaterial3Api
@Composable
fun ListProductScreen(
    openDetailProducts: (id:Int) -> Unit,
    productType: String,
    productViewModel: ProductViewModel,
    context: Context
) {
    var selectedOption by remember { mutableStateOf("Áo quần") }
    val gifResource = when (productType) {
        "dog" -> R.drawable.gifcho
        "cat" -> R.drawable.gifmeo
        "bird" -> R.drawable.gifchim
        "fish" -> R.drawable.gifca
        "hamster" -> R.drawable.gifchuot
        else -> R.drawable.gifcho // Default gif if no match
    }

    val selectedAnimal = when (productType) {
        "dog" -> 1
        "cat" -> 2
        "bird" -> 3
        "fish" -> 5
        "hamster" -> 4
        else -> 1
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Slide_Gif(gifResource)
        }
        stickyHeader {

            val options = listOf("Áo quần", "Thức ăn", "Vật dụng")
            Row {

            }
            SingleChoiceSegmentedButtonRow {
                options.forEachIndexed { index, label ->
                    SegmentedButton(
                        shape = SegmentedButtonDefaults.itemShape(
                            index = index,
                            count = options.size
                        ),
                        onClick = { selectedOption = label },
                        selected = label == selectedOption,
                        modifier = Modifier
                            .size(100.dp, 56.dp)
                            .padding(0.dp, 1.dp, 0.dp, 5.dp),
                        colors = SegmentedButtonDefaults.colors(
                            activeContainerColor = Color(0xFFffad33),
                            inactiveContainerColor = Color(0xFF990000)
                        ),
                        border = BorderStroke(2.dp, Color(0xFFcc2900))
                    ) {
                        Text(
                            label,
                            style = TextStyle(
                            fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFFFFFFFF)
                            )
                        )
                    }
                }
            }
        }
        item {
            HorizontalDivider(thickness = 2.dp, color = Color(0xFFffdab3), modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 5.dp))
            SuggestTodayopen(openDetailProducts, productViewModel, context, selectedOption, selectedAnimal)
        }
    }
}

@Composable
fun Slide_Gif(gifResource: Int) {

    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .components {
            add(GifDecoder.Factory())
        }
        .build()
    Image(
        painter = rememberAsyncImagePainter(
            ImageRequest.Builder(context).data(data = gifResource).apply(block = {
            }).build(), imageLoader = imageLoader
        ),
        contentDescription = "gif",
            modifier = Modifier.fillMaxWidth()
            .height(220.dp)
    )
//    Image(
//        painter = painterResource(id = R.drawable.gifcho), // Thay đổi vị trí file theo tên file của bạn
//        contentDescription = "gifcho",
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(220.dp)
//    )
}
@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun DetailDogMainPreview() {
    var selectedProductType by remember { mutableStateOf("") }
    STTCTheme {
        ListProductScreen(openDetailProducts = {}, selectedProductType, ProductViewModel(), LocalContext.current)
    }
}

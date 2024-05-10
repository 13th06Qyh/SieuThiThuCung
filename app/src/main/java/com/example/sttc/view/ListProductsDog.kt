package com.example.sttc.view

import com.example.sttc.R
import com.example.sttc.ui.theme.STTCTheme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.request.ImageRequest

@ExperimentalMaterial3Api
class ListProductsDog: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DetailDog()
        }
    }
}
@ExperimentalMaterial3Api
@Composable
fun DetailDog() {
    Column (
        modifier = Modifier.fillMaxSize() ,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Row(
            modifier = Modifier.fillMaxWidth() ,
        ){
            Slide_Gif()
        }
        Row{
            var selectedIndex by remember { mutableIntStateOf(0) }
            val options = listOf("Áo quần", "Đồ ăn", "Đồ chơi")
            SingleChoiceSegmentedButtonRow {
                options.forEachIndexed { index, label ->
                    SegmentedButton(
                        shape = SegmentedButtonDefaults.itemShape(index = index, count = options.size),
                        onClick = { selectedIndex = index },
                        selected = index == selectedIndex
                    ) {
                        Text(label)
                    }
                }
            }
        }
        Row{
            ListProducts()
        }


    }
}
@Composable
fun ListProducts(){
    val listProducts = listOf(
        ProductsItems(1, "Do thu nhatjhu yuih ygyu ", R.drawable.icon_dog , "Quan ao" , 100000) ,
        ProductsItems(2, "Do thu hai", R.drawable.icon_cat  , "Quan ao" , 100000) ,
        ProductsItems(3, "Do thu ba", R.drawable.icon_hamster  , "Quan ao" , 100000)
    )
    LazyVerticalGrid(columns = GridCells.Fixed(2) ,  modifier = Modifier.fillMaxSize()) {
        items(items = listProducts , key = {it.id}){
            BoxItem_Sp(size = 300.dp, image = it.image, tensp = it.name, phanloai = it.phanloai , giatien = it.giatien, onItemClick = {})
        }
    }

}
@Composable
fun BoxItem_Sp(size:Dp, image: Int, tensp: String, phanloai : String , giatien : Int  ,  onItemClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(size)
            .clip(RoundedCornerShape(8.dp))
            .border(
                BorderStroke(1.dp, Color.Black),
                shape = RoundedCornerShape(8.dp)
            )
            .clickable(onClick = onItemClick)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = null,
                modifier = Modifier
                    .size(300.dp , 200.dp)
//                    .aspectRatio(1f)
                    .padding(8.dp)
            )
            Text(
                text = phanloai,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth()
                    .padding(top = 4.dp) ,

                style = TextStyle(fontSize = 16.sp)
            )
            Text(
                text = tensp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
                    .padding(top = 4.dp) ,

                style = TextStyle(fontSize = 16.sp)
            )
            Text(
                text = "$giatien VND",
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth()
                    .padding(top = 4.dp) ,

                style = TextStyle(fontSize = 16.sp)
            )
        }
    }
}


@Composable
fun Slide_Gif() {
    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .components {
            add(GifDecoder.Factory())
        }
        .build()
    Image(
        painter = rememberAsyncImagePainter(
            ImageRequest.Builder(context).data(data = R.drawable.gifcho).apply(block = {
            }).build(), imageLoader = imageLoader
        ),
        contentDescription = null,
    )
//    Image(
//        painter = painterResource(id = R.drawable.gifcho), // Thay đổi vị trí file theo tên file của bạn
//        contentDescription = null,
//        modifier = Modifier.fillMaxWidth()
//    )
}
data class ProductsItems(
    val id: Int,
    val name: String,
    val image: Int ,
    val phanloai : String,
    val giatien : Int
)
@ExperimentalMaterial3Api
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DetailDogMainPreview() {
    STTCTheme {
        DetailDog()
    }
}

package com.example.sttc.view
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.sttc.R
import com.example.sttc.ui.theme.STTCTheme


class Products : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            STTCTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ProductScreens()
                }
            }
        }
    }
}
@Composable
fun ProductScreens() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.size(300.dp, 400.dp),
            verticalArrangement = Arrangement.SpaceAround,
        ) {
            RowItems(R.drawable.icon_dog, R.drawable.icon_cat, onItemClick = { })
            RowItems(R.drawable.icon_ca, R.drawable.icon_hamster, onItemClick = {})
            RowItems(R.drawable.icon_chim, onItemClick = { })
        }
    }
}
@Composable
fun RowItems(vararg images: Int, onItemClick: (Int) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        images.forEach { image ->
            BoxItem(size = 100.dp, image = image, onItemClick = { onItemClick(image) })
        }
    }
}

@Composable
fun BoxItem(size: Dp = 100.dp, image: Int, onItemClick: () -> Unit) {
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
        Image(
            painter = painterResource(id = image),
            contentDescription = null,
            modifier = Modifier.aspectRatio(1f)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProductPreview() {
    STTCTheme {
        ProductScreens()
    }
}
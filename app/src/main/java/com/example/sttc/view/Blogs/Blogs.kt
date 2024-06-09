package com.example.sttc.view.Blogs

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.request.ImageRequest
import com.example.sttc.R
import com.example.sttc.ui.theme.STTCTheme

@Composable
fun BlogsScreens(
    openListBlogs: (String) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            Color(0xFFFFFFFF),
                            Color(0xFFE0EEFF),
                            Color(0xFFFFFFFF),
                            Color(0xFF9CD8FF),
                            Color(0xFF9ED9FF),
                        ),
                        radius = 800f,
                    )
                ),
//            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopIconBlogs()
            BlogsRowItems(
                R.drawable.icon_dog, R.drawable.icon_cat,
                colors = listOf(Color(0xFFffff99), Color(0xFFccffcc)),
                onItemClick = { imageRes ->
                    val blogType = when (imageRes) {
                        R.drawable.icon_dog -> "dog"
                        R.drawable.icon_cat-> "cat"
                        else -> ""
                    }
                    openListBlogs(blogType)
                }
            )
            BlogsRowItems(
                R.drawable.icon_chim,
                colors = listOf(Color(0xFFffcccc)),
                onItemClick = {
                    openListBlogs("bird")
                }
            )
            BlogsRowItems(
                R.drawable.icon_ca, R.drawable.icon_hamster,
                colors = listOf(Color(0xFFb3f0ff), Color(0xFFffd699)),
                onItemClick = { imageRes ->
                    val blogType = when (imageRes) {
                        R.drawable.icon_ca -> "fish"
                        R.drawable.icon_hamster-> "hasmter"
                        else -> ""
                    }
                    openListBlogs(blogType)
                }
            )
        }
    }
}

@Composable
fun TopIconBlogs() {
    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .components {
            add(GifDecoder.Factory())
        }
        .build()

    Image(
        painter = rememberAsyncImagePainter(
            ImageRequest.Builder(context).data(data = R.drawable.blogs).apply(block = {
            }).build(), imageLoader = imageLoader
        ),
        contentDescription = "TopIconBlogsAnimals",
        modifier = Modifier
            .fillMaxWidth()
            .height(145.dp)
            .border(1.dp, color = Color(0xFFcc00cc))
            .background(
                Brush.radialGradient(
                    colors = listOf(
                        Color(0xFFFFE3DC),
                        Color(0xFFFFE3DC),
                        Color(0xFFFFEBEB),
                        Color(0xFFFFE8D4),
                        Color(0xFFFFBDAC),
                    ),
                    radius = 350f
                )
            )
    )

//    Image(
//        painter = painterResource(id = R.drawable.blogs),
//        contentDescription = "TopIconBlogsAnimals",
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(130.dp)
//            .background(
//                Brush.radialGradient(
//                    colors = listOf(
//                        Color(0xFFFFE3DC),
//                        Color(0xFFFFE3DC),
//                        Color(0xFFFFEBEB),
//                        Color(0xFFFFE8D4),
//                        Color(0xFFFFBDAC),
//                    ),
//                    radius = 350f
//                )
//            )
//            .border(1.dp, color = Color(0xFFcc00cc))
//    )
}

@Composable
fun BlogsRowItems(vararg images: Int, colors: List<Color> = listOf(), onItemClick: (Int) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 10.dp, 0.dp, 5.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        images.forEachIndexed { index, image ->
            val color = if (index < colors.size) colors[index] else Color.Transparent
            BlogsBoxItem(image = image, color = color, onItemClick = { onItemClick(image) })
        }
    }
}

@Composable
fun BlogsBoxItem(image: Int, color: Color, onItemClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(170.dp)
            .padding(10.dp)
            .background(color, shape = CircleShape)
            .border(2.dp, color = Color(0xFF000000), shape = CircleShape)
            .clickable(onClick = onItemClick),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = null,
            modifier = Modifier.size(120.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BlogsScreensPreview() {
    STTCTheme {
        BlogsScreens(openListBlogs = {})
    }
}
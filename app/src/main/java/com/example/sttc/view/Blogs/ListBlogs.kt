package com.example.sttc.view.Blogs

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sttc.R
import com.example.sttc.model.Blogs
import com.example.sttc.model.extractFileName
import com.example.sttc.ui.theme.STTCTheme
import com.example.sttc.viewmodel.BlogsViewModel
import com.example.sttc.viewmodel.ProductViewModel
import androidx.compose.runtime.livedata.observeAsState

@Composable
fun ListBlogScreen(
    blogsViewModel: BlogsViewModel= hiltViewModel() ,
    openDetailCmt: () -> Unit,
    openDetailBlogs: () -> Unit,
) {
    val blogs by blogsViewModel.blogs.observeAsState(arrayListOf())
    val isLoading by blogsViewModel.isLoading.observeAsState(false)

    val context = LocalContext.current

    var itemBlogs = blogs.size
    if (isLoading) itemBlogs++
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF000000)),
    ) {
        items(count = itemBlogs) { index ->
            var auxIndex = index
            if (isLoading) {
                if (auxIndex == 0)
                    return@items LoadingBlogs()
                auxIndex--
            }
            val blog = blogs[auxIndex]
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Surface(
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(BorderStroke(2.dp, Color(0xFF000000)))
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.horizontalGradient(
                                    colors = listOf(
                                        Color(0xFF9CD8FF),
                                        Color(0xFFFFFFFF),
                                        Color(0xFFDAEBFF),
                                        Color(0xFFFFFFFF),
                                    ),
                                    startX = 1000f,
                                    endX = 70f
                                )
                            )
                    ) {
                        Avatar()
                        Column(
                            modifier = Modifier
                                .padding(0.dp, 5.dp, 0.dp, 0.dp)
                                .background(Color(0xFFffffff))
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = blog.title,
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp,
                                    fontStyle = FontStyle.Italic
                                ),
                                modifier = Modifier.padding(
                                    top = 12.dp,
                                    bottom = 4.dp,
                                    start = 10.dp,
                                    end = 10.dp
                                )
                            )
                            Spacer(modifier = Modifier.height(4.dp))

                            // Load the image
                            val imageUrl = blog.imageBlogs.firstOrNull()?.image ?: ""
                            val imageName = imageUrl.extractFileName()
                            val imageResId = context.resources.getIdentifier(imageName, "drawable", context.packageName)

                            Image(
                                painter = if (imageResId != 0) painterResource(id = imageResId) else painterResource(id = R.drawable.logo),
                                contentDescription = "blog image",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .padding(start = 5.dp, end = 5.dp)
                                    .fillMaxWidth()
                                    .height(200.dp)
                                    .clip(shape = RoundedCornerShape(4.dp))
                            )
                        }
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White),
            ) {
                Button(
                    modifier = Modifier.weight(1f),
                    shape = RectangleShape,
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White)
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
                    onClick = { openDetailCmt() },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.cmt),
                        contentDescription = "",
                        tint = Color(0xFF8A8686)
                    )
                    Spacer(modifier = Modifier.width(7.dp))
                    Text(text = "Bình Luận", color = Color.Gray)
                }
                Button(
                    shape = RectangleShape,
                    onClick = { openDetailBlogs() },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White)
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
            HorizontalDivider(thickness = 7.dp, color = Color(0xFF99d6ff))
        }
    }
}
@Composable
fun LoadingBlogs() {

}

@Composable
fun Avatar() {
    Row(
        modifier = Modifier
            .padding(start = 5.dp, top = 5.dp),
        verticalAlignment = Alignment.CenterVertically // Center items vertically
    ) {
        Surface(
            modifier = Modifier
                .size(40.dp)
        ) {

            Icon(
                painter = painterResource(R.drawable.logo),
                contentDescription = "avatar",
                modifier = Modifier
                    .size(30.dp),
                tint = Color(0xFF990000)
            )
        }
        Column(
            modifier = Modifier.padding(10.dp, 10.dp, 10.dp, 5.dp)
        ) {
            Text(
                text = "Pet Shop",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = Color(0xFF990000),
                modifier = Modifier.padding(bottom = 4.dp)
            )

            Text(
                text = "23:45 12-05-2024",
                style = TextStyle(
                    fontSize = 10.sp,
                    fontStyle = FontStyle.Italic
                ),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BaiVietPreview() {
    STTCTheme {
        ListBlogScreen(openDetailCmt = {}, openDetailBlogs = {} )
    }
}
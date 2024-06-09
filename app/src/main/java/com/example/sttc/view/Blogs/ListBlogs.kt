package com.example.sttc.view.Blogs

import android.content.Context
import android.util.Log
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
import androidx.compose.foundation.lazy.items
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.sttc.R
import com.example.sttc.ui.theme.STTCTheme
import com.example.sttc.view.System.ItemsBaiViet
import com.example.sttc.viewmodel.BlogsViewModel

@Composable
fun ListBlogScreen(
    blogsViewModel: BlogsViewModel,
    blogType: String,
    openDetailCmt: (id: Int) -> Unit,
    openDetailBlogs: (id: Int) -> Unit,
    context: Context
) {
    val blogs by blogsViewModel.blogs.collectAsState(emptyList())
    val imagesMap by blogsViewModel.imagesBlogs.collectAsState(initial = emptyMap())
    val selectedAnimal = when (blogType) {
        "dog" -> 1
        "cat" -> 2
        "bird" -> 3
        "fish" -> 5
        "hamster" -> 4
        else -> 1
    }
    val filteredBlogs = blogs.filter { it.idanimal == selectedAnimal }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF000000)),
    ) {
        items(items = filteredBlogs) { blog ->
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
//                            .background(Color(0xFFe6f5ff))
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
//                        HorizontalDivider(thickness = 1.dp, color = Color(0xFFE0E0E0))
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

                            LaunchedEffect(key1 = blog.maBlog) {
                                if (imagesMap[blog.maBlog].isNullOrEmpty()) {
                                    blogsViewModel.fetchImages(blog.maBlog)
                                }
                            }

                            val blogImages = imagesMap[blog.maBlog].orEmpty()
//                            Log.e("anh imageMap", "$blogImages")
                            if (blogImages.isNotEmpty()) {
                                val image = blogImages.first()
                                val imageUrl = image.image
                                val fileName = imageUrl.substringBeforeLast(".")
                                val resourceId = context.resources.getIdentifier(
                                    fileName,
                                    "drawable",
                                    context.packageName
                                )
                                if (resourceId != 0) {
                                    Image(
                                        painter = painterResource(id = resourceId),
                                        contentDescription = "blog image",
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .padding(start = 5.dp, end = 5.dp)
                                            .fillMaxWidth()
                                            .height(200.dp)
                                            .clip(shape = RoundedCornerShape(4.dp))
                                    )
                                } else {
                                    Image(
                                        painter = painterResource(id = R.drawable.bg1),
                                        contentDescription = "blog image",
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .padding(start = 5.dp, end = 5.dp)
                                            .fillMaxWidth()
                                            .height(200.dp)
                                            .clip(shape = RoundedCornerShape(4.dp))
                                    )
                                    Log.e("ListBlogScreen", "Image not found: $imageUrl")
                                }
                            }
                        }

                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White),

                ) {
                var isClicked by remember { mutableStateOf(false) }
                Button(
                    modifier = Modifier
                        .weight(1f),
                    shape = RectangleShape,
                    onClick = { isClicked = !isClicked },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isClicked) Color.Blue else Color.Gray,
                    )
                ) {
                    Icon(
                        Icons.Default.ThumbUp,
                        contentDescription = "",
                        tint = if (isClicked) Color.Gray else Color.Blue
                    )
                    Spacer(modifier = Modifier.width(7.dp))
                    Text(text = "Thích", color = if (isClicked) Color.Blue else Color.Gray)
                }
                Button(
                    shape = RectangleShape,
                    onClick = {
                        Log.e(
                            "OpenDetailCmt",
                            "openDetailCmt: ${blog.maBlog}"
                        ) // Ghi log khi click
                        openDetailCmt(blog.maBlog)
                    },
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
                    onClick = { openDetailBlogs(blog.maBlog) },
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
            HorizontalDivider(thickness = 7.dp, color = Color(0xFF99d6ff))

        }
    }
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
        val selectedBlogType by remember { mutableStateOf("") }
        ListBlogScreen(
            blogsViewModel = BlogsViewModel(),
            blogType = selectedBlogType,
            openDetailCmt = {},
            openDetailBlogs = {},
            context = LocalContext.current
        )
    }
}
package com.example.sttc.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
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
import coil.compose.rememberImagePainter
import com.example.sttc.model.Blogs
import com.example.sttc.model.extractFileName
import com.example.sttc.ui.theme.STTCTheme
import com.example.sttc.view.Blogs.Avatar
import com.example.sttc.viewmodel.BlogsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TestApiBlogs : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            STTCTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    MyApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp(
    blogsViewModel: BlogsViewModel = hiltViewModel(),
) {
    val blogs by blogsViewModel.blogs.observeAsState(arrayListOf())
    val isLoading by blogsViewModel.isLoading.observeAsState(false)
    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        var itemCount = blogs.size
        if (isLoading) itemCount++

        items(count = itemCount) { index ->
            var auxIndex = index
            if (isLoading) {
                if (auxIndex == 0) {
                    CircularProgressIndicator(modifier = Modifier.fillMaxWidth())
                    return@items
                }
                auxIndex--
            }
            val blog = blogs[auxIndex]
            BlogItem(blog, context)
        }
    }
}

@Composable
fun BlogItem(blog: Blogs, context: android.content.Context) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Surface(
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .border(BorderStroke(2.dp, Color.Black))
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
                                Color(0xFFFFFFFF)
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
                        .background(Color.White)
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
                        painter = if (imageResId != 0) painterResource(id = imageResId)
                        else rememberImagePainter(data = "https://your_base_url/$imageUrl"),
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
}
@Preview(showBackground = true)
@Composable
fun TestApiBlogsPreview() {
    STTCTheme {
        MyApp()
    }
}

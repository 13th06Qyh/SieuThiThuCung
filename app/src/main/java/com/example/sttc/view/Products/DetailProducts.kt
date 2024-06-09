package com.example.sttc.view.Products

import android.content.Context
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.sttc.R
import com.example.sttc.model.ImageSP
import com.example.sttc.model.Now
import com.example.sttc.model.PayData
import com.example.sttc.model.User
import com.example.sttc.ui.theme.STTCTheme
import com.example.sttc.view.System.PinDigitField
import com.example.sttc.view.System.SuggestToday
import com.example.sttc.view.System.SuggestTodayopen
import com.example.sttc.view.System.capitalizeWords
import com.example.sttc.view.System.formatNumber
import com.example.sttc.viewmodel.AccountViewModel
import com.example.sttc.viewmodel.CartViewModel
import com.example.sttc.viewmodel.ProductViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun DetailProductsScreen(
    back: () -> Unit,
    openCart: () -> Unit,
    openDetailProducts: (id: Int) -> Unit,
    productViewModel: ProductViewModel,
    cartViewModel: CartViewModel,
    accountViewModel: AccountViewModel,
    context: Context,
    productId: Int,
    openPayment: (List<PayData>) -> Unit
) {
    val scrollState = rememberScrollState()
    val selectedOption = remember { mutableStateOf("") }
    val selectedAnimal = 0
    Column(
        modifier = Modifier
            .fillMaxSize()
//            .background(Color(0xFFF6F2F2))
            .verticalScroll(scrollState)
    ) {
        TitleInforProduct(back)
        SlideImage(Modifier, productViewModel, context, productId)
        NameAndPrice(productViewModel, productId)
        InforProduct(productViewModel, productId)
        BuyProduct(
            openCart,
            cartViewModel,
            productViewModel,
            accountViewModel,
            productId,
            openPayment
        )
        ContentProduct(productViewModel, productId)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            HorizontalDivider(
                modifier = Modifier
                    .weight(1f)
                    .padding(0.dp, 20.dp),  // Take up half the space
                thickness = 1.2.dp,
                color = Color.Gray
            )
            Text(
                text = "Có thể bạn quan tâm",
                style = TextStyle(
                    fontSize = 15.sp,
                    fontStyle = FontStyle.Italic,
                    color = Color.Black,
                ),
                modifier = Modifier.padding(10.dp)
            )
            HorizontalDivider(
                modifier = Modifier
                    .weight(1f)
                    .padding(0.dp, 20.dp),  // Take up half the space
                thickness = 1.2.dp,
                color = Color.Gray
            )
        }
        SuggestToday(
            openDetailProducts,
            productViewModel,
            context,
            selectedOption.value,
            selectedAnimal
        )
    }

}

@Composable
fun TitleInforProduct(back: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
//            .height(50.dp)
            .background(
                Brush.radialGradient(
                    colors = listOf(
                        Color(0xFFFFFFFF),
                        Color(0xFFF6F2F2),
                    ),
                    radius = 600f
                )
            )
            .padding(0.dp, 5.dp)
//            .border(1.dp, color = Color(0xFFff6666))
    ) {
        Icon(
            Icons.Default.ArrowBack, contentDescription = "Back",
            modifier = Modifier
                .size(50.dp)
                .padding(10.dp, 0.dp)
                .clickable { back() },
            tint = Color(0xFFcc2900)
        )
        Text(
            text = "Chi tiết sản phẩm",
            style = TextStyle(
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = Color.Black
            ),
            modifier = Modifier
                .padding(10.dp, 10.dp)
        )
    }
}

@Composable
@OptIn(ExperimentalPagerApi::class)
fun SlideImage(
    modifier: Modifier = Modifier,
    productViewModel: ProductViewModel,
    context: Context,
    id: Int
) {
    val products by productViewModel.products.collectAsState(initial = emptyList())
    val imagesMap by productViewModel.images.collectAsState(initial = emptyMap())
    LaunchedEffect(key1 = id) {
        delay(1000)
        productViewModel.fetchProductById(id)
        productViewModel.fetchImages(id)
    }
    Log.d("test", "SlideImage: $id")
    val product = products.find { it.maSP == id }
    val productImages = if (product != null) {
        imagesMap[product.maSP].orEmpty()
    } else {
        emptyList() // Trả về danh sách hình ảnh rỗng nếu không tìm thấy sản phẩm hoặc không có hình ảnh
    }
    if (productImages.isNotEmpty()) {
        val pagerState = rememberPagerState(pageCount = productImages.size)
        LaunchedEffect(Unit) {
            while (true) {
                delay(2000)
                val nextPage = (pagerState.currentPage + 1) % pagerState.pageCount
                pagerState.scrollToPage(nextPage)
            }
        }
        val scope = rememberCoroutineScope()
        var showZoomDialog by remember { mutableStateOf(false) }
        Box(
            modifier = modifier
                .wrapContentSize()
                .background(Color(0xFFF6F2F2))
        ) {
            HorizontalPager(
                state = pagerState,
                modifier
                    .wrapContentSize()

            ) { currentPage ->
                Card(
                    modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .wrapContentSize()
                        .clickable {
                            showZoomDialog = true
                        },
                    elevation = CardDefaults.cardElevation(8.dp)
                ) {
                    val image = productImages[currentPage]
                    val imageUrl = image.image
                    val fileName =
                        imageUrl.substringAfterLast("/").substringBeforeLast(".")
                    val fileExtension = imageUrl.substringAfterLast(".")
                    val resourceId = context.resources.getIdentifier(
                        fileName,
                        "drawable",
                        context.packageName
                    )
                    val a = context.resources.getResourceName(resourceId)
                    val b = a.substringAfter('/')
                    Log.d("test", "FileName: $fileName")
                    Log.d("test", "FileExtension: $fileExtension")
                    Log.d("test", "ResourceId: $resourceId")
                    Log.d("test", "ResourceName1: $a")
                    Log.d("test", "ResourceName2: $b")
                    if (b == fileName) {
                        Image(
                            painter = painterResource(id = resourceId),
                            contentDescription = "Image",
//                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.White)

                        )
                    } else {
                        Image(
                            painter = painterResource(id = R.drawable.rs1),
                            contentDescription = "Image",
//                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.White)

                        )
                    }
                }
            }
            IconButton(
                onClick = {
                    val nextPage = pagerState.currentPage + 1
                    if (nextPage < productImages.size) {
                        scope.launch {
                            pagerState.scrollToPage(nextPage)
                        }
                    }
                },
                modifier
                    .size(48.dp)
                    .align(Alignment.CenterEnd)
                    .clip(CircleShape),
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = Color.Transparent
                )
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = "",
                    modifier.fillMaxSize(),
                    tint = Color.LightGray
                )
            }
            IconButton(
                onClick = {
                    val prevPage = pagerState.currentPage - 1
                    if (prevPage >= 0) {
                        scope.launch {
                            pagerState.scrollToPage(prevPage)
                        }
                    }
                },
                modifier
                    .size(48.dp)
                    .align(Alignment.CenterStart)
                    .clip(CircleShape),
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = Color.Transparent
                )
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = "",
                    modifier.fillMaxSize(),
                    tint = Color.LightGray
                )
            }
        }
        if (showZoomDialog) {
            ZoomableImageDialog(images = productImages, productImages.size) {
                showZoomDialog = false
            }
        }
    } else {
        Image(
            painter = painterResource(id = R.drawable.rs1),
            contentDescription = "Image",
//                            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)

        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ZoomableImageDialog(images: List<ImageSP>, initialPage: Int, onDismiss: () -> Unit) {
    val zoomPagerState = rememberPagerState(initialPage)
    Dialog(onDismissRequest = onDismiss) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(600.dp)
                .background(Color.Black.copy(alpha = 0.5f))
        ) {
            HorizontalPager(
                state = zoomPagerState,
                modifier = Modifier.fillMaxSize()
            ) { page ->
                val image = images[page]
                val imageUrl = image.image
                val fileName =
                    imageUrl.substringAfterLast("/").substringBeforeLast(".")
                val fileExtension = imageUrl.substringAfterLast(".")
                val resourceId = LocalContext.current.resources.getIdentifier(
                    fileName,
                    "drawable",
                    LocalContext.current.packageName
                )
                val a = LocalContext.current.resources.getResourceName(resourceId)
                val b = a.substringAfter('/')
                if (b == fileName) {
                    Image(
                        painter = painterResource(id = resourceId),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable(onClick = onDismiss),
                        contentScale = ContentScale.Fit
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.rs1),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable(onClick = onDismiss),
                        contentScale = ContentScale.Fit
                    )
                }
            }
            IconButton(
                onClick = onDismiss,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(16.dp)
                    .background(Color.Gray, CircleShape)
                    .size(36.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close",
                    tint = Color.White
                )
            }
        }
    }
}

@Composable
fun NameAndPrice(
    productViewModel: ProductViewModel,
    id: Int
) {
    Column(
        modifier = Modifier.background(Color(0xFFF6F2F2))
    ) {
        val products by productViewModel.products.collectAsState(initial = emptyList())
        val product = products.find { it.maSP == id }
        LaunchedEffect(key1 = id) {
            delay(5000)
            productViewModel.fetchProductById(id)
        }

        if (product != null) {
            val price = product.buyprice.toInt()
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(35.dp)
                    .background(Color.Red),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {

                Text(
                    text = formatNumber(price) + "đ",
                    style = TextStyle(
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    ),
                    modifier = Modifier.padding(end = 10.dp)
                )
            }

            Text(
                text = capitalizeWords(product.tensp),
                style = TextStyle(
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                    color = Color.Black
                ),
                modifier = Modifier
                    .padding(10.dp)
                    .background(Color(0xFFF6F2F2))
            )
        }
    }
}

@Composable
fun InforProduct(
    productViewModel: ProductViewModel,
    id: Int
) {
    val products by productViewModel.products.collectAsState(initial = emptyList())
    val tag by productViewModel.tag.collectAsState(initial = emptyList())
    val tagMap = remember(tag) { tag.associateBy { it.maTag } }
    val provide by productViewModel.provide.collectAsState(initial = emptyList())
    val provideMap = remember(provide) { provide.associateBy { it.maNCC } }
    val product = products.find { it.maSP == id }
    LaunchedEffect(key1 = id) {
        delay(10000)
        productViewModel.fetchProductById(id)
        delay(7000)
        productViewModel.fetchTag()
        delay(5000)
        productViewModel.fetchProvide()
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF6F2F2))
    ) {
        if (product != null) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp, 0.dp, 10.dp, 5.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Chất liệu",
                    style = TextStyle(
                        fontSize = 18.sp,
                        color = Color.Black
                    )
                )

                Log.d("test", "InforTag: ${product.idtag}")
                val tags = tagMap[product.idtag]
                if (tags != null) {
                    Text(
                        text = tags.tagname,
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Italic,
                            color = Color.Black
                        )
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp, 0.dp, 10.dp, 5.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Kho hàng",
                    style = TextStyle(
                        fontSize = 18.sp,
                        color = Color.Black
                    )
                )

                Text(
                    text = product.soluongkho.toString(),
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic,
                        color = Color.Black
                    )
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp, 0.dp, 10.dp, 5.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Nguồn hàng",
                    style = TextStyle(
                        fontSize = 18.sp,
                        color = Color.Black
                    )
                )

                Log.d("test", "InforProvide: ${product.idNCC}")
                val provides = provideMap[product.idNCC]
                if (provides != null) {
                    Text(
                        text = provides.proname,
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Italic,
                            color = Color.Black
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun BuyProduct(
    openCart: () -> Unit,
    cartViewModel: CartViewModel,
    productViewModel: ProductViewModel,
    accountViewModel: AccountViewModel,
    id: Int,
    openPayment: (List<PayData>) -> Unit
) {
    val products by productViewModel.products.collectAsState(initial = emptyList())
    val nows by cartViewModel.now.collectAsState(emptyList())
    val user by accountViewModel.userInfoFlow.collectAsState(null)
    val addCarts by cartViewModel.add.collectAsState(null)
    val addNow by cartViewModel.addN.collectAsState(null)
    val product = products.find { it.maSP == id }
    var showErrorCart by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    var showDialogError by remember { mutableStateOf(false) }
    var okButtonPressed by remember { mutableStateOf(false) }
    var okButtonPressedN by remember { mutableStateOf(false) }
    var showNow by remember { mutableStateOf(false) }
    var showDialogNow by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 1.dp, 0.dp, 2.dp)
            .background(Color(0xFFFFFFFF)),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(
            onClick = {
                okButtonPressed = false
                Log.d("AddToCartButton", "Button clicked")
                user?.let { user ->
                    Log.d("AddToCartButton", "User: $user")
                    if (user.id == 0) {
                        showDialogError = true
                        errorMessage = "Vui lòng đăng nhập để thêm vào giỏ hàng"
                        Log.d("AddToCartButton", "User not logged in")
                    } else {
                        if (product != null) {
                            val idsp = product.maSP
                            val iduser = user.id
                            Log.d(
                                "AddToCartButton",
                                "Adding to cart: Product ID: $idsp, User ID: $iduser"
                            )
                            cartViewModel.addCart(idsp, iduser)
                        } else {
                            Log.d("AddToCartButton", "Product is null")
                        }
                    }
                } ?: run {
                    Log.d("AddToCartButton", "User is null")
                }
            },
            shape = RoundedCornerShape(2.dp), // Định dạng góc bo tròn của nút
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF0a2929), // Màu nền của nút
                contentColor = Color.White // Màu chữ của nút
            ),
            modifier = Modifier
                .width(230.dp)
                .padding(7.dp, 5.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Default.ShoppingCart, contentDescription = "Add to cart",
                    modifier = Modifier
                        .size(25.dp)
                        .padding(0.dp, 0.dp, 5.dp, 0.dp),
                    tint = Color.White
                )

                Text(
                    text = "Thêm vào giỏ hàng",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    ),
                )
            }
        }

        if (showDialogError) {
            AlertDialog(
                containerColor = Color(0xFFccf5ff),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.size(400.dp, 150.dp),
                onDismissRequest = { showDialogError = false },
                title = {},
                text = {},
                confirmButton = {},
                dismissButton = {
                    Column {
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
                                .height(50.dp)
                        )
                        Button(
                            onClick = {
                                showDialogError = false
                                okButtonPressed = true
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFccffdd), // Màu nền của nút
                                contentColor = Color.Black, // Màu chữ của nút
                            ),
                            modifier = Modifier.fillMaxWidth(),
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
                    }
                }
            )
        }

        Button(
            onClick = {
                user?.let { user ->
                    okButtonPressedN = false
                    Log.d("AddTonowButton", "User: $user")
                    if (user.id == 0) {
                        showDialogError = true
                        errorMessage = "Vui lòng đăng nhập để thêm vào giỏ hàng"
                        Log.d("AddTonowButton", "User not logged in")
                    } else {
                        if (product != null) {
                            val idsp = product.maSP
                            val iduser = user.id
                            Log.d(
                                "AddTonowButton",
                                "Adding to now: Product ID: $idsp, User ID: $iduser"
                            )
                            cartViewModel.addNow(idsp, iduser)
                        } else {
                            Log.d("AddTonowButton", "Product is null")
                        }
                    }
                }
            },
            shape = RoundedCornerShape(2.dp), // Định dạng góc bo tròn của nút
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFcc2900), // Màu nền của nút
                contentColor = Color.White // Màu chữ của nút
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(7.dp, 5.dp)
        ) {
            Text(
                text = "Mua ngay",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                ),
            )
        }

        if (showDialogNow) {
            AlertDialog(
                containerColor = Color(0xFFccf5ff),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.size(400.dp, 150.dp),
                onDismissRequest = { showDialogNow = false },
                title = {},
                text = {},
                confirmButton = {},
                dismissButton = {
                    Column {
                        Text(
                            "Chuyển sang trang thanh toán?",
                            style = TextStyle(
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                color = Color.Red
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                        )
                        Button(
                            onClick = {
                                showDialogNow = false
                                okButtonPressedN = true
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFccffdd), // Màu nền của nút
                                contentColor = Color.Black, // Màu chữ của nút
                            ),
                            modifier = Modifier.fillMaxWidth(),
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
                    }
                }
            )
        }

        addCarts?.let { result ->
            result.fold(
                onSuccess = { token ->
                    println("Them vao gio thành công")
                },
                onFailure = { exception ->
                    showErrorCart = true
                    if (!okButtonPressed) {
                        showDialogError = true
                    }
                    errorMessage = exception.message ?: "AddCart thất bại"
                    Log.e("AddCart", "AddCart thất bại: ${exception.message}")
                }
            )

        }

        addNow?.let { result ->
            result.fold(
                onSuccess = { token ->
                    println("Them vao now thành công")
                    showNow = true
                    if (!okButtonPressedN) {
                        showDialogNow = true

                    } else {

                        val selectedProducts = nows.mapIndexed { index, now ->
                            PayData(
                                idc = now.maNow,
                                id = now.idsp,
                                image = now.image,
                                name = now.tensp,
                                oneprice = now.buyprice.toInt(),
                                tag = now.tagname,
                                quantity = 1 // Sử dụng quantity state được xác định
                            )
                        }
                        Log.d("nowPayments", "Selected products: $nows")
                        Log.d("SelectNowPayments", "Selected products: $selectedProducts")
                        if (selectedProducts.isNotEmpty()) {
                            openPayment(selectedProducts)
                        } else {
                            showDialogNow = false
                        }

                    }
                },
                onFailure = { exception ->
                    val selectedProducts = nows.mapIndexed { index, now ->
                        PayData(
                            idc = now.maNow,
                            id = now.idsp,
                            image = now.image,
                            name = now.tensp,
                            oneprice = now.buyprice.toInt(),
                            tag = now.tagname,
                            quantity = 1 // Sử dụng quantity state được xác định
                        )
                    }
                    Log.d("SelectNowPayments", "Selected products: $selectedProducts")
                    if (selectedProducts.isNotEmpty())
                        openPayment(selectedProducts)

                    errorMessage = exception.message ?: "AddNow thất bại"
                    Log.e("AddNow", "AddCart thất bại: ${exception.message}")
                }
            )

        }
    }
}

@Composable
fun ContentProduct(
    productViewModel: ProductViewModel,
    id: Int
) {
    val products by productViewModel.products.collectAsState(initial = emptyList())
    val product = products.find { it.maSP == id }
    LaunchedEffect(key1 = id) {
        delay(5000)
        productViewModel.fetchProductById(id)
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFFFFFFF))
    ) {
        Text(
            text = "Mô Tả Sản Phẩm",
            style = TextStyle(
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal,
                letterSpacing = 0.5.sp
            ),
            modifier = Modifier.padding(10.dp, 10.dp, 0.dp, 3.dp)
        )

        HorizontalDivider(thickness = 1.dp, color = Color(0xFF999999))

        if (product != null) {
            Text(
                text = product.mota,
                style = TextStyle(
                    fontSize = 18.sp,
                    color = Color(0xFF595959)
                ),
                modifier = Modifier.padding(15.dp, 10.dp, 10.dp, 30.dp)
            )
        }

    }

}

@Preview(showBackground = true)
@Composable
fun DetailProductsPreview() {
    STTCTheme {
        DetailProductsScreen(
            back = {},
            openCart = {},
            openDetailProducts = {},
            ProductViewModel(),
            CartViewModel(LocalContext.current),
            AccountViewModel(LocalContext.current),
            LocalContext.current,
            0,
            openPayment = {}
        )
    }
}


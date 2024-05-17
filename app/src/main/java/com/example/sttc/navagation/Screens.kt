package com.example.sttc.navagation
import androidx.annotation.StringRes
import com.example.sttc.R

sealed class Screens(val route: String, @StringRes val resourceId: Int) {
    data object LoginRoute : Screens("login", R.string.login)
    data object SignupRoute : Screens("signup", R.string.signup)
    data object HomeMenuRoute : Screens("home menu", R.string.home)

    // ------------san pham---------------
    data object ListProductsRoute : Screens("listProducts", R.string.products)

    // detail products
    data object DetailProductsRoute : Screens("detailProducts", R.string.account)

    // ---------------blogs------------
    data object ListBlogsRoute : Screens("listBlogs", R.string.account)
    //detail blogs
    data object DetailBlogsRoute : Screens("detailBlogs", R.string.account)

    //---------------comment---------
    data object ListCommentsRoute : Screens("listComments", R.string.account)
    // ---------------account------------
    data object AccountRoute : Screens("account", R.string.account)

    // bill
    data object BillCancelRoute : Screens("billCancel", R.string.account)
    data object BillHistoryRoute : Screens("billHistory", R.string.account)
    data object BillShipRoute : Screens("billShip", R.string.account)
    data object InfoBillHistoryRoute : Screens("infoBillHistory", R.string.account)
    data object InfoBillShipRoute : Screens("infoBillShip", R.string.account)
    data object PaymentRoute : Screens("bill", R.string.account)
    //cart
    data object CartRoute : Screens("cart", R.string.account)



}

// SUGGETS : infor , detail products
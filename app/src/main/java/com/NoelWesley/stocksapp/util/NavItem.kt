package com.NoelWesley.stocksapp.util

sealed class NavItem(
    val route: String
){

    object SignUp: NavItem("signup")

    object Login: NavItem("login")

    object MainScreen: NavItem("main")

}

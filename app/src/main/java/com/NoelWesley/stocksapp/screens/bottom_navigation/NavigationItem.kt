package com.NoelWesley.stocksapp.screens.bottom_navigation

import com.NoelWesley.stocksapp.R

sealed class NavigationItem(
    val route:String, val title: String, val icon:Int
){

    object Home : NavigationItem("home", "Home", R.drawable.iconhome)

    object Search : NavigationItem("search", "Search", R.drawable.iconsearch)

    object Cart : NavigationItem("cart", "Cart", R.drawable.iconcart)

    object Account : NavigationItem("account", "Account", R.drawable.iconsperson)

}



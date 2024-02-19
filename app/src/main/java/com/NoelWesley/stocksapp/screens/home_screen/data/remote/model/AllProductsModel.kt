package com.NoelWesley.stocksapp.screens.home_screen.data.remote.model

data class AllProductsModel(
    val availableStock: Int,
    val createdTime: String,
    val description: String,
    val minOrder: Int,
    val price: Double,
    val productId: Long,
    val productName: String,
)
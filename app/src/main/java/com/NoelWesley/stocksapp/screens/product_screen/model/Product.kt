package com.NoelWesley.stocksapp.screens.product_screen.model

data class Product(
    val availableStock: Int,
    val category: Category,
    val description: String,
    val minOrder: Int,
    val price: Double,
    val productId: Long,
    val productName: String,
    val user: User
)
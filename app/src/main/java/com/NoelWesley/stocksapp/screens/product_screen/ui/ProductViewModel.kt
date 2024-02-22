package com.NoelWesley.stocksapp.screens.product_screen.ui

import androidx.lifecycle.ViewModel
import com.NoelWesley.stocksapp.screens.product_screen.model.Category
import com.NoelWesley.stocksapp.screens.product_screen.model.Product
import com.NoelWesley.stocksapp.screens.product_screen.model.User
import com.NoelWesley.stocksapp.screens.product_screen.repo.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(private val productRepository: ProductRepository) : ViewModel(){
    val productRepositoryInstance = productRepository
}
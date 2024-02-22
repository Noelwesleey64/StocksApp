package com.NoelWesley.stocksapp.screens.product_screen.repo

import com.NoelWesley.stocksapp.screens.home_screen.data.remote.model.ProductImageModel
import com.NoelWesley.stocksapp.screens.product_screen.data.remote.ProductService
import com.NoelWesley.stocksapp.screens.product_screen.model.Product
import javax.inject.Inject

// Define a class named ProductRepository.
// Annotate the constructor with @Inject to request dependency injection.
// Declare a private read-only property 'productService' of type ProductService.
class ProductRepository @Inject constructor(private val productService: ProductService ) {

    // Define a suspending function named 'getProduct' that takes a productId and returns a Product.
    suspend fun getProduct(productId: Long): Product{
        // Return the result of the 'getProduct' method from the injected productService.
        return productService.getProduct(productId)
    }

    //Method to return an object that contains image urls of a product
    suspend fun getProductImage(productId: Long): ProductImageModel {
        return productService.getProductImage(productId)
    }


}
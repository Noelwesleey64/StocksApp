package com.NoelWesley.stocksapp.screens.product_screen.data.remote

import com.NoelWesley.stocksapp.screens.home_screen.data.remote.model.ProductImageModel
import com.NoelWesley.stocksapp.screens.product_screen.model.Category
import com.NoelWesley.stocksapp.screens.product_screen.model.Product
import com.NoelWesley.stocksapp.screens.product_screen.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

// Define a class named ProductService.
// Annotate the constructor with @Inject to request dependency injection.
// Declare a private read-only property 'productApi' of type ProductApi.
class ProductService @Inject constructor(private val productApi: ProductApi) {


    // Define a suspending function named 'getProduct' that takes a productId and returns a Product.
    suspend fun getProduct(productId: Long): Product {

        // Create an instance of Category with default values
        val category = Category(0, "")

        // Create an instance of User with default values.
        val user = User("","", "")


        // Use 'withContext' to switch the coroutine context to IO for network operations.
        return withContext(Dispatchers.IO){
            // Call 'getProduct' on productApi passing in the productId, and store the result in 'product'.
            val product = productApi.getProduct(productId)

            // Return the body of the product response if it's not null; otherwise, return a default Product instance.
            product.body()?: Product(0, category, "", 0, 0.0, 0L, "" , user )
        }

    }

    // Define a suspending function named 'getProductImage' that takes a 'productId' of type Long and returns a 'ProductImageModel'
    suspend fun getProductImage(productId: Long): ProductImageModel {

        // 'withContext' is a coroutine builder that switches the context of the coroutine to the IO dispatcher, which is optimized for I/O operations
        return withContext(Dispatchers.IO){
            // Call 'getProductImage' from 'productApi' with the given 'productId', and store the result in the 'image' variable
            val image = productApi.getProductImage(productId)

            // Return the body of the 'image' if it's not null; otherwise, return a default 'ProductImageModel' with empty strings and 0 for the integer value
            image.body()?: ProductImageModel("", "", "", "", 0)
        }



    }
}
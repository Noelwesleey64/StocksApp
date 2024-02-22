package com.NoelWesley.stocksapp.screens.product_screen.data.remote

import com.NoelWesley.stocksapp.screens.home_screen.data.remote.model.ProductImageModel
import com.NoelWesley.stocksapp.screens.product_screen.model.Product
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

// Define an interface for the Retrofit API service.
interface ProductApi {
    // Annotate the method with @GET to declare an HTTP GET request type.
    // Specify the endpoint URL, with a path parameter 'productId'.
    @GET("product/getproduct/{productId}")
    // Define a suspending function 'getProduct' that can be called within a coroutine.
    // It takes a 'productId' as a parameter and returns a Retrofit Response object wrapping a Product.
    // Annotate the 'productId' parameter with @Path to bind it to the path parameter in the URL.
    suspend fun getProduct(@Path("productId") productId: Long): Response<Product>

    // Annotate the method with @GET to declare an HTTP GET request type.
    // Specify the endpoint URL, with a path parameter 'productId'.
    @GET("product/getProductImages/{productId}")
    // Define a suspending function 'getProductImage' that can be called within a coroutine.
    // It takes a 'productId' as a parameter and returns a Retrofit Response object wrapping a ProductImageModel.
    // Annotate the 'productId' parameter with @Path to bind it to the path parameter in the URL.
    suspend fun getProductImage(@Path("productId") productId: Long): Response<ProductImageModel>
}
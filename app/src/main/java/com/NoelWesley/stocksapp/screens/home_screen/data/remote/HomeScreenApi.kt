package com.NoelWesley.stocksapp.screens.home_screen.data.remote

import com.NoelWesley.stocksapp.screens.home_screen.data.remote.model.AllProductsModel
import com.NoelWesley.stocksapp.screens.home_screen.data.remote.model.ProductImageModel
import com.NoelWesley.stocksapp.screens.home_screen.data.remote.model.ProductsOnCategoryModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

//Interface class to handle of our Api calls
interface HomeScreenApi {


    //Get request method to return all the list of all products
    @GET("/product/getProducts")
    suspend fun getAllProducts(): Response<List<AllProductsModel>>

    @GET("product/getProductImages/{productId}")
    suspend fun getProductImage(@Path("productId") productId: Long): Response<ProductImageModel>

    //Get request to get products list that belong to a certain category
    @GET("product/getProductsOnCategory/{categoryId}")
    suspend fun getProductsBasedOnCategory(@Path("categoryId") categoryId : Long): Response<List<ProductsOnCategoryModel>>

}
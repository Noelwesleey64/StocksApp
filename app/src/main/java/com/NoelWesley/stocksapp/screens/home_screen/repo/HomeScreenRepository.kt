package com.NoelWesley.stocksapp.screens.home_screen.repo

import com.NoelWesley.stocksapp.screens.home_screen.data.remote.HomeScreenService
import com.NoelWesley.stocksapp.screens.home_screen.data.remote.model.AllProductsModel
import com.NoelWesley.stocksapp.screens.home_screen.data.remote.model.ProductImageModel
import com.NoelWesley.stocksapp.screens.home_screen.data.remote.model.ProductsOnCategoryModel
import javax.inject.Inject

//@Inject annotation is provided by the Hilt library, a dependency injection framework for Android1.
// It tells Hilt to look for a way to provide an instance of HomeScreenService when it needs to instantiate HomeScreenRepository
class HomeScreenRepository @Inject constructor(private val homeScreenService: HomeScreenService) {

    //This declares a suspending function named getAllProducts that returns a List of all Products.
    suspend fun getAllProducts(): List<AllProductsModel>{
        //Call the method in getAllProducts method in homeScreenService
        return homeScreenService.getAllProducts()
    }

    //Method to return an object that contains image urls of a product
    suspend fun getProductImage(productId: Long): ProductImageModel{
        return homeScreenService.getProductImage(productId)
    }

    suspend fun getProductsBasedOnCategory(categoryId: Long): List<ProductsOnCategoryModel>{

        return homeScreenService.getProductsBasedOnCategory(categoryId)
    }
}
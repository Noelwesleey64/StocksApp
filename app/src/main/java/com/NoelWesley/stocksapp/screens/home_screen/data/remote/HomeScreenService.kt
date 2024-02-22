package com.NoelWesley.stocksapp.screens.home_screen.data.remote

import com.NoelWesley.stocksapp.screens.home_screen.data.remote.model.AllProductsModel
import com.NoelWesley.stocksapp.screens.home_screen.data.remote.model.ProductImageModel
import com.NoelWesley.stocksapp.screens.home_screen.data.remote.model.ProductsOnCategoryModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

//Class to access the homescreenApi methods
class HomeScreenService @Inject constructor(private val homeScreenApi: HomeScreenApi){

    //This declares a suspending function named getAllProducts that returns a List of AllProductsModel
    suspend fun getAllProducts(): List<AllProductsModel>{

        //The withContext function is a suspending function that changes the coroutine context,
        // suspends until it completes, and returns the result
        //In summary, this function is a suspending function that is expected to perform some IO operations and return a list of all Products
        return withContext(Dispatchers.IO){
            //Dispatchers.IO is used for offloading blocking IO tasks to a shared pool of threads.
            //This line is making a network request to get all Products using the homescreenApi object.
            val products = homeScreenApi.getAllProducts()

            // This line is getting the body of the response.
            // If the body is null, it returns an empty list.
            products.body()?: emptyList()

            //In summary, this block of code is switching the coroutine context to Dispatchers.IO,
            // making a network request to get products,
            // and returning the body of the response or an empty list if the body is null
        }
    }


    suspend fun getProductsBasedOnCategory(categoryId: Long): List<ProductsOnCategoryModel>{
        //The withContext function is a suspending function that changes the coroutine context,
        // suspends until it completes, and returns the result
        //In summary, this function is a suspending function that is expected to perform some IO operations and return a list of all Products
        return withContext(Dispatchers.IO){
            //Dispatchers.IO is used for offloading blocking IO tasks to a shared pool of threads.
            //This line is making a network request to get all Products using the homescreenApi object.
            val products = homeScreenApi.getProductsBasedOnCategory(categoryId)

            // This line is getting the body of the response.
            // If the body is null, it returns an empty list.
            products.body()?: emptyList()

            //In summary, this block of code is switching the coroutine context to Dispatchers.IO,
            // making a network request to get products,
            // and returning the body of the response or an empty list if the body is null
        }
    }

    // Define a suspending function named 'getProductImage' that takes a 'productId' of type Long and returns a 'ProductImageModel'
    suspend fun getProductImage(productId: Long): ProductImageModel{

        // 'withContext' is a coroutine builder that switches the context of the coroutine to the IO dispatcher, which is optimized for I/O operations
        return withContext(Dispatchers.IO){
            // Call 'getProductImage' from 'homeScreenApi' with the given 'productId', and store the result in the 'image' variable
            val image = homeScreenApi.getProductImage(productId)

            // Return the body of the 'image' if it's not null; otherwise, return a default 'ProductImageModel' with empty strings and 0 for the integer value
            image.body()?: ProductImageModel("", "", "", "", 0)
        }



    }

}
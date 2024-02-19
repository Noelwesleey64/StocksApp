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

    suspend fun getProductImage(productId: Long): ProductImageModel{

        return withContext(Dispatchers.IO){
            val image = homeScreenApi.getProductImage(productId)

            image.body()?: ProductImageModel("", "", "", "", 0)
        }



    }

}
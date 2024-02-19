package com.NoelWesley.stocksapp.screens.home_screen.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.NoelWesley.stocksapp.screens.home_screen.data.remote.model.AllProductsModel
import com.NoelWesley.stocksapp.screens.home_screen.data.remote.model.ProductImageModel
import com.NoelWesley.stocksapp.screens.home_screen.data.remote.model.ProductsOnCategoryModel
import com.NoelWesley.stocksapp.screens.home_screen.repo.HomeScreenRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(private val homeScreenRepository: HomeScreenRepository) : ViewModel() {

    //The code declares a private property named _allProducts
    //It is of type MutableStateFlow<List<AllProductsModel>>
    //We set it's current state to an empty list of game items
    private val _allProducts = MutableStateFlow(emptyList<AllProductsModel>())

    //A StateFlow is a Kotlin Flow implementation that represents a stateful value as a flow of immutable updates.
    //The code uses the get() syntax to define a custom getter for the allProducts property.
    //The custom getter returns the value of another property named _allProducts
    val allProducts :StateFlow<List<AllProductsModel>> get() = _allProducts

    private val _electronicProducts = MutableStateFlow(emptyList<ProductsOnCategoryModel>())

    val electronicProducts :StateFlow<List<ProductsOnCategoryModel>> get() = _electronicProducts

    //Attach the injected homeScreenRepository object to our homeRespository object which is of type HomeScreenRepository
    //We do this so that we can access methods in homeScreenRepository in our HomeScreenUi
    val homeRepository = homeScreenRepository



    init {
        getAllProducts()


    }


    private fun getAllProducts() {
        //we create a coroutine within the scope of the view mode
        viewModelScope.launch {
            //we try to catch an exception incase an exception is thrown
            try{
                //get lists of allProducts
                val allProducts = homeScreenRepository.getAllProducts().shuffled()

                //update the value of allProducts property
                _allProducts.value = allProducts

                // The parameter name is omitted by using an underscore _,
                // which means that the parameter is not used in the block.
                //the block body is empty, which means that the exception is ignored and no action is taken.
            } catch (_: Exception){

            }
        }
    }

    fun getelectronicProducts(categoryId : Long) {
        //we create a coroutine within the scope of the view mode
        viewModelScope.launch {
            //we try to catch an exception incase an exception is thrown
            try{
                //get lists of allProducts
                val electronicProducts = homeScreenRepository.getProductsBasedOnCategory(categoryId)

                //update the value of allProducts property
                _electronicProducts.value = electronicProducts

                // The parameter name is omitted by using an underscore _,
                // which means that the parameter is not used in the block.
                //the block body is empty, which means that the exception is ignored and no action is taken.
            } catch (_: Exception){

            }
        }
    }


    private val _clothingProducts = MutableStateFlow(emptyList<ProductsOnCategoryModel>())

    val clothingProducts :StateFlow<List<ProductsOnCategoryModel>> get() = _clothingProducts
    fun getClothingShoesAccessories(categoryId : Long) {
        //we create a coroutine within the scope of the view mode
        viewModelScope.launch {
            //we try to catch an exception incase an exception is thrown
            try{
                //get lists of allProducts
                val clothingProducts = homeScreenRepository.getProductsBasedOnCategory(categoryId)

                //update the value of allProducts property
                _clothingProducts.value = clothingProducts

                // The parameter name is omitted by using an underscore _,
                // which means that the parameter is not used in the block.
                //the block body is empty, which means that the exception is ignored and no action is taken.
            } catch (_: Exception){

            }
        }
    }

    private val _jewelryProducts = MutableStateFlow(emptyList<ProductsOnCategoryModel>())

    val jewelryProducts :StateFlow<List<ProductsOnCategoryModel>> get() = _jewelryProducts
    fun getJewlryWatches(categoryId : Long) {
        //we create a coroutine within the scope of the view mode
        viewModelScope.launch {
            //we try to catch an exception incase an exception is thrown
            try{
                //get lists of allProducts
                val jewelryProducts = homeScreenRepository.getProductsBasedOnCategory(categoryId)

                //update the value of allProducts property
                _jewelryProducts.value = jewelryProducts

                // The parameter name is omitted by using an underscore _,
                // which means that the parameter is not used in the block.
                //the block body is empty, which means that the exception is ignored and no action is taken.
            } catch (_: Exception){

            }
        }
    }

    private val _healthProducts = MutableStateFlow(emptyList<ProductsOnCategoryModel>())

    val healthProducts :StateFlow<List<ProductsOnCategoryModel>> get() = _healthProducts
    fun gethealthBeautyProducts(categoryId : Long) {
        //we create a coroutine within the scope of the view mode
        viewModelScope.launch {
            //we try to catch an exception incase an exception is thrown
            try{
                //get lists of allProducts
                val healthProducts = homeScreenRepository.getProductsBasedOnCategory(categoryId)

                //update the value of allProducts property
                _healthProducts.value = healthProducts

                // The parameter name is omitted by using an underscore _,
                // which means that the parameter is not used in the block.
                //the block body is empty, which means that the exception is ignored and no action is taken.
            } catch (_: Exception){

            }
        }
    }









}
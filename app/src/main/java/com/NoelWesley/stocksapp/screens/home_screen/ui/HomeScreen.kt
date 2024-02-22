package com.NoelWesley.stocksapp.screens.home_screen.ui

import android.widget.Button
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.Surface
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.NoelWesley.stocksapp.R
import com.NoelWesley.stocksapp.screens.home_screen.data.remote.model.AllProductsModel
import com.NoelWesley.stocksapp.screens.home_screen.data.remote.model.ProductImageModel
import com.NoelWesley.stocksapp.screens.home_screen.data.remote.model.ProductsOnCategoryModel
import com.NoelWesley.stocksapp.util.Constants
import com.NoelWesley.stocksapp.util.NavItem
import com.NoelWesley.stocksapp.util.UsernamePreferenceDatastore

@Composable
fun HomeScreen(homeScreenViewModel: HomeScreenViewModel = hiltViewModel(), navController: NavController){

    // Declare a local variable named context and assign it the value of the current context
    // The context is an object that provides access to system services and resources
    // The LocalContext is a composition local that holds the context value
    val context = LocalContext.current
    // Declare a local variable named dataStore and assign it the value of a UsernamePreferenceDatastore object
    // The UsernamePreferenceDatastore is a custom class that handles storing and retrieving the username using DataStore
    val dataStore = UsernamePreferenceDatastore(context)
    // Declare a local variable named savedUsername and assign it the value of a Flow object
    // The dataStore.getUsername is a property that returns a Flow of the username from the DataStore
    // The collectAsState() function converts the Flow into a State object that can be used in composables
    val savedUsername = dataStore.getUsername.collectAsState(initial = "")

    val allproducts by homeScreenViewModel.allProducts.collectAsState()


    MainSection(navController =navController)


}

@Composable
fun TopCardswithInfo(){
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background),

        ){

        item {
            val image: Painter = painterResource(id = R.drawable.productimagesample)
            val image2: Painter = painterResource(id = R.drawable.beautyproduct)
            val image3: Painter = painterResource(id = R.drawable.grocery)
            TopCardsWithInfoCard(image = image, description = "Get Upto 50% off on Bags" )
            TopCardsWithInfoCard(image = image2, description = "Stock Up On Your Grocery With Prices Starting From Ksh 100" )
            TopCardsWithInfoCard(image = image3, description = "Buy Beauty Products With Prices Off By 10%" )


        }

    }

}

@Composable
fun CategoriesRow(){
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background),

        ){

        item {
            val imageClothing: Painter = painterResource(id = R.drawable.clothing)
            val imageElectronics: Painter = painterResource(id = R.drawable.electronics)
            val imageHealth: Painter = painterResource(id = R.drawable.health)
            val imageGrocery: Painter = painterResource(id = R.drawable.grocerycategory)
            val imageCereals: Painter = painterResource(id = R.drawable.cereal)
            val imageJewelry: Painter = painterResource(id = R.drawable.watch)
            val imageBooks: Painter = painterResource(id = R.drawable.book)


            CategoriesRowRoundBox(image = imageClothing, category = "Clothing Shoes & Accessories")
            CategoriesRowRoundBox(image = imageElectronics, category = "Electronics")
            CategoriesRowRoundBox(image = imageHealth, category = "Health & Beauty")
            CategoriesRowRoundBox(image = imageGrocery, category = "Groceries")
            CategoriesRowRoundBox(image = imageCereals, category = "Cereals")
            CategoriesRowRoundBox(image = imageJewelry, category = "Jewelry&Watches")
            CategoriesRowRoundBox(image = imageBooks, category = "Books, Movies & Music")


        }

    }

}

@Composable
fun TopCardsWithInfoCard(
    image: Painter,
    description: String

){
    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxWidth()
            .padding(top = 20.dp, start = 30.dp)
            ,


    ) {
        Box(
            modifier = Modifier
                .height(200.dp)
                .width(300.dp)
                .clip(shape = RoundedCornerShape(10.dp))
                ,


        ) {
            Image(

                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .drawWithCache {
                        onDrawWithContent {
                            drawContent()
                            drawRect(
                                Brush.verticalGradient(
                                    0.5f to Color.Black.copy(alpha = 0F),
                                    1F to Color.Black
                                )
                            )
                        }
                    },
                painter = image,
                contentScale = ContentScale.Crop,
                contentDescription = "The delasign logo",
            )

            Text(
                modifier = Modifier
                    .offset(6.dp, -15.dp)
                    .align(Alignment.BottomStart)
                    ,
                text = description,
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold


            )


        }

        }


    }

@Composable
fun CategoriesRowRoundBox(
    image: Painter,
    category: String
){
    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxWidth()
            .padding(top = 20.dp, start = 30.dp)
        ,


        ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(shape = RoundedCornerShape(120.dp))
                ,


                ) {
                Image(

                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    painter = image,
                    contentScale = ContentScale.Crop,
                    contentDescription = "The delasign logo",
                   )
               }



            Text(
                modifier = Modifier.width(120.dp),
                text = category,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground)


            }

        }


    }

@Composable
fun ProductOnCategorySectionTemplate(productsOnCategory: ProductsOnCategoryModel, homeScreenViewModel: HomeScreenViewModel = hiltViewModel(), navController: NavController){

    val productId = productsOnCategory.productId
          //Create a variable to hold the price of our product
    val price = productsOnCategory.price

    //Create a variable to hold the minimum Order of our product
    val minOrder = productsOnCategory.minOrder

    //Create a variable to hold the title of our product
    val title = productsOnCategory.productName

    //Create a mutableStateOf variable called imageProduct, which will save the state of out ProductImageModel that we will get from getProductImage method from homeScreenViewModel
    val imageProduct = remember { mutableStateOf(ProductImageModel("","","","",0)) }

    //Run the suspend function called getProductImage, which is in the homeScreenViewModel
    //The method will return the ProductImageModel based on the product id passed to it
    //The productId passed will be the productId of the current object in the lazy column
    //LaunchedEffect helps us run suspend functions inside a composable
    LaunchedEffect(Unit){
        //Store the state of the imageProduct
        imageProduct.value = homeScreenViewModel.homeRepository.getProductImage(productsOnCategory.productId)
    }


    Row(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxWidth()
            .padding(top = 20.dp, start = 30.dp, end = 20.dp)
            //navigate to the productScreen when clicked, and pass the query parameter productId value
            .clickable { navController.navigate("product/$productId") }
        ,


        ) {
        Box(
            modifier = Modifier
                .height(100.dp)
                .width(150.dp)
                .clip(shape = RoundedCornerShape(10.dp))
            ,


            ) {
            val image: Painter = painterResource(id = R.drawable.blankprofile)
            val baseUrl = Constants.BASE_URL;
            AsyncImage(
                modifier = Modifier
                    .fillMaxSize()
                    .fillMaxHeight(),
                //Concatenate the baseUrl with the endPoint url gotten from the imageProduct object which is an instance of ProductImageModel
                //Set the model of the url formed after Concatenate baseUrl and image1_url
                model = baseUrl.plus(imageProduct.value.image1_Url),
                error = image,
                contentScale = ContentScale.Crop,
                contentDescription = "The delasign logo")

        }

        Spacer(modifier = Modifier.size(20.dp))

        Column {
            Text(
                text = title,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onBackground)

            Spacer(modifier = Modifier.size(5.dp))

            Text(text = "Ksh. $price",
                color = MaterialTheme.colorScheme.primary,
                fontSize = 15.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.size(5.dp))

            Text(text = "Min. order: $minOrder units", fontSize = 10.sp)


        }


    }
}

@Composable
fun ProductSectionTemplate(allProductsModel: AllProductsModel, homeScreenViewModel: HomeScreenViewModel = hiltViewModel(), navController: NavController){

    val productId = allProductsModel.productId
    //Create a variable to hold the price of our product
    val price = allProductsModel.price

    //Create a variable to hold the minimum Order of our product
    val minOrder = allProductsModel.minOrder

    //Create a variable to hold the title of our product
    val title = allProductsModel.productName

    //Create a mutableStateOf variable called imageProduct, which will save the state of out ProductImageModel that we will get from getProductImage method from homeScreenViewModel
    val imageProduct = remember { mutableStateOf(ProductImageModel("","","","",0)) }

    //Run the suspend function called getProductImage, which is in the homeScreenViewModel
    //The method will return the ProductImageModel based on the product id passed to it
    //The productId passed will be the productId of the current object in the lazy column
    //LaunchedEffect helps us run suspend functions inside a composable
    LaunchedEffect(Unit){
        //Store the state of the imageProduct
        imageProduct.value = homeScreenViewModel.homeRepository.getProductImage(allProductsModel.productId)
    }


    Row(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxWidth()
            .padding(top = 20.dp, start = 30.dp, end = 20.dp)
            //navigate to the productScreen when clicked, and pass the query parameter productId value
            .clickable { navController.navigate("product/$productId") }
        ,


        ) {
        Box(
            modifier = Modifier
                .height(100.dp)
                .width(150.dp)
                .clip(shape = RoundedCornerShape(10.dp))
            ,


            ) {
            val image: Painter = painterResource(id = R.drawable.blankprofile)
            val baseUrl = Constants.BASE_URL;
            AsyncImage(
                modifier = Modifier
                    .fillMaxSize()
                    .fillMaxHeight(),
                //Concatenate the baseUrl with the endPoint url gotten from the imageProduct object which is an instance of ProductImageModel
                //Set the model of the url formed after Concatenate baseUrl and image1_url
                model = baseUrl.plus(imageProduct.value.image1_Url),
                error = image,
                contentScale = ContentScale.Crop,
                contentDescription = "The delasign logo")

        }
        
        Spacer(modifier = Modifier.size(20.dp))

        Column {
            Text(
                text = title,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onBackground)

            Spacer(modifier = Modifier.size(5.dp))

            Text(text = "Ksh. $price",
                color = MaterialTheme.colorScheme.primary,
                fontSize = 15.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.size(5.dp))

            Text(text = "Min. order: $minOrder units", fontSize = 10.sp)


        }


    }
}
@Composable
fun MainSection(homeScreenViewModel: HomeScreenViewModel = hiltViewModel(), navController: NavController){

    //The value of the homScreenViewModel variable is obtained by calling the viewModel function,
    // which is a composable function that provides a ViewModel instance scoped to the current navigation destination or the current composable hierarchy
    //val homeScreenViewModel = viewModel(modelClass = HomeScreenViewModel::class.java)

    //Create a variable called allProducts and store the latest value of AllProductsModel list gotten by:
    //homeScreenViewModel.allProducts
    //collectAsState(),  is a composable function that collects a StateFlow as a State object.
    //So, we store the latest value of the allProducts item list on the allProducts variable
    val allproducts by homeScreenViewModel.allProducts.collectAsState()

    //The the categoryId arguememnt of the method to 8 which sets the productsBasedOnCategoryList in viewmodel to electronics
    homeScreenViewModel.getelectronicProducts(8)

    //Read the state of the productsBasedOnCatergory parameter from the viewmodel which returns the list of electronics because we passed 8 to the method above
    //The 8 symbolize the categoryId of electronics
    val electronicsProducts by homeScreenViewModel.electronicProducts.collectAsState()

    //Trimming the electronics products list to 5
    val electronicsProductsTrimmed = electronicsProducts.take(5)

    //The the categoryId arguememnt of the method to 8 which sets the productsBasedOnCategoryList in viewmodel to electronics
    homeScreenViewModel.getClothingShoesAccessories(2)

    //Read the state of the productsBasedOnCatergory parameter from the viewmodel which returns the list of electronics because we passed 8 to the method above
    //The 8 symbolize the categoryId of electronics
    val clothingProducts by homeScreenViewModel.clothingProducts.collectAsState()

    //Trimming the electronics products list to 5
    val clothingProductsTrimmed = clothingProducts.take(5)

    //The the categoryId arguememnt of the method to 8 which sets the productsBasedOnCategoryList in viewmodel to electronics
    homeScreenViewModel.getJewlryWatches(6)

    //Read the state of the productsBasedOnCatergory parameter from the viewmodel which returns the list of electronics because we passed 8 to the method above
    //The 8 symbolize the categoryId of electronics
    val jewelryProducts by homeScreenViewModel.jewelryProducts.collectAsState()

    //Trimming the electronics products list to 5
    val jewelryProductsTrimmed = jewelryProducts.take(5)






    LazyColumn {
        item {
            TopCardswithInfo()
            Spacer(modifier = Modifier.size(40.dp))
            Text(
                modifier = Modifier.offset(50.dp),
                text = "Categories",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
            )
            Spacer(modifier = Modifier.size(5.dp))
            CategoriesRow()
            Spacer(modifier = Modifier.size(40.dp))

            Text(modifier= Modifier.offset(x = 50.dp),text = "Electronics", color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold, fontSize = 20.sp)


            Spacer(modifier = Modifier.size(10.dp),)


        }




        //Add a collection of items to the lazy column, the collection items are allProducts

        //The lambda expression takes a AllProductsModel object as parameter and returns composable function that displays the items ui

        //The lambda expression defines how to bind each product item to a view holder
        //The parameter allproduct is the current product item, and the type All ProductsModel is a data class that represents the properties of a product,
        items(electronicsProductsTrimmed){productsBasedOnCategory: ProductsOnCategoryModel ->

            ProductOnCategorySectionTemplate(productsOnCategory = productsBasedOnCategory, navController = navController)

        }

        item {
            Spacer(modifier = Modifier.size(15.dp))
            ViewAllButton()
            Spacer(modifier = Modifier.size(35.dp))
        }


        item {
            Text(modifier= Modifier.offset(x = 50.dp),text = "Clothing Shoes & Accessories", color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold, fontSize = 20.sp)

            Spacer(modifier = Modifier.size(10.dp),)
        }

        //Add a collection of items to the lazy column, the collection items are allProducts

        //The lambda expression takes a AllProductsModel object as parameter and returns composable function that displays the items ui

        //The lambda expression defines how to bind each product item to a view holder
        //The parameter allproduct is the current product item, and the type All ProductsModel is a data class that represents the properties of a product,
        items(clothingProductsTrimmed){productsBasedOnCategory: ProductsOnCategoryModel ->

            ProductOnCategorySectionTemplate(productsOnCategory = productsBasedOnCategory, navController = navController)

        }

        item {
            Spacer(modifier = Modifier.size(15.dp))
            ViewAllButton()
            Spacer(modifier = Modifier.size(35.dp))
        }


        item {
            Text(modifier= Modifier.offset(x = 50.dp),text = "Jewelry & Watches", color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold, fontSize = 20.sp)

            Spacer(modifier = Modifier.size(10.dp),)
        }

        //Add a collection of items to the lazy column, the collection items are allProducts

        //The lambda expression takes a AllProductsModel object as parameter and returns composable function that displays the items ui

        //The lambda expression defines how to bind each product item to a view holder
        //The parameter allproduct is the current product item, and the type All ProductsModel is a data class that represents the properties of a product,
        items(jewelryProductsTrimmed){productsBasedOnCategory: ProductsOnCategoryModel ->

            ProductOnCategorySectionTemplate(productsOnCategory = productsBasedOnCategory, navController = navController)

        }

        item {
            Spacer(modifier = Modifier.size(15.dp))
            ViewAllButton()
            Spacer(modifier = Modifier.size(35.dp))
        }


        item {
            Text(modifier= Modifier.offset(x = 50.dp),text = "AllProducts", color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold, fontSize = 20.sp)

            Spacer(modifier = Modifier.size(10.dp),)
        }


        //Add a collection of items to the lazy column, the collection items are allProducts

        //The lambda expression takes a AllProductsModel object as parameter and returns composable function that displays the items ui

        //The lambda expression defines how to bind each product item to a view holder
        //The parameter allproduct is the current product item, and the type All ProductsModel is a data class that represents the properties of a product,
        items(allproducts){allproduct: AllProductsModel ->

            ProductSectionTemplate(allProductsModel = allproduct, navController = navController)

        }
    }


}

@Composable
fun ViewAllButton(){

    Surface(modifier = Modifier
        .fillMaxSize()
        .padding(end = 20.dp, start = 20.dp)) {
        Box(){
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .border(
                    width = 2.dp,
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(5.dp)
                )
                .background(color = MaterialTheme.colorScheme.background),
                contentAlignment = Alignment.Center){
                Text(text = "View All", color = MaterialTheme.colorScheme.onBackground, textAlign = TextAlign.Center, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
        }
    }

          
  
}





@Preview(showBackground = true, widthDp = 392  , heightDp = 775)
@Composable
fun HomePreview(){





}

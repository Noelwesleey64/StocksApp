package com.NoelWesley.stocksapp.screens.product_screen.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.NoelWesley.stocksapp.R
import com.NoelWesley.stocksapp.screens.home_screen.data.remote.model.ProductImageModel
import com.NoelWesley.stocksapp.screens.product_screen.model.Category
import com.NoelWesley.stocksapp.screens.product_screen.model.Product
import com.NoelWesley.stocksapp.screens.product_screen.model.User
import com.NoelWesley.stocksapp.ui.theme.StocksAppTheme
import com.NoelWesley.stocksapp.util.Constants

@Preview(showBackground = true, widthDp = 392  , heightDp = 775)
@Composable
fun GreetingPreview() {
    StocksAppTheme {
            ProductSCreen(1)

    }
}

@Composable
fun ProductImageTemplate(imageUrl: String){

    Box()
    {


          Box(
              modifier = Modifier
                  .size(150.dp)
                  .clip(shape = RoundedCornerShape(10.dp))
                  .padding(start = 2.dp, end = 2.dp, top = 2.dp)
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
                  model = baseUrl.plus(imageUrl),
                  error = image,
                  contentScale = ContentScale.Crop,
                  contentDescription = "The delasign logo")

          }


    }
    
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductSCreen(productId: Int, productViewModel:ProductViewModel = hiltViewModel()){
    // Create an instance of Category with default values
    val category = Category(0, "")

    // Convert the 'productId' from Int to Long data type.
    val productIdLong = productId.toLong()

    // Create an instance of User with default values.
    val user = User("","", "")


    // Declare a 'product' state that remembers the state across recompositions.
// Initialize it with a default Product instance.
    val product = remember{ mutableStateOf( Product(0, category, "", 0, 0.0, 0L, "" , user )) }

    //Create a mutableStateOf variable called imageProduct, which will save the state of out ProductImageModel that we will get from getProductImage method from productViewModel
    val imageProduct = remember { mutableStateOf(ProductImageModel("","","","",0)) }

    // LaunchedEffect is a side effect that launches a coroutine when the composable enters the Composition.
   // 'Unit' is passed as a key, which means this effect will only run once.
    LaunchedEffect(Unit){

        // Inside the coroutine, assign a new value to 'product'.
        // Fetch the product details using the 'productViewModel' and 'productIdLong'.
        // The 'value' property of 'product' is updated with the new Product instance.
        product.value = productViewModel.productRepositoryInstance.getProduct(productIdLong)

        //Store the state of the imageProduct
        imageProduct.value = productViewModel.productRepositoryInstance.getProductImage(productIdLong)


    }


    LazyColumn(

        Modifier
            .fillMaxHeight()
            .padding(start = 10.dp, end = 10.dp)){
       item {
           Spacer(modifier = Modifier.size(40.dp))
           LazyRow(modifier = Modifier
               .fillMaxWidth()
               .padding(end = 10.dp, start = 10.dp)) {
               item {
                   val image1url = imageProduct.value.image1_Url
                   ProductImageTemplate(image1url)
                   Spacer(modifier = Modifier.size(5.dp))

                   val image2url = imageProduct.value.image2_Url
                   ProductImageTemplate(image2url)
                   Spacer(modifier = Modifier.size(5.dp))

                   val image3url = imageProduct.value.image3_Url
                   ProductImageTemplate(image3url)
               }

           }
           
           Spacer(modifier = Modifier.size(10.dp))
           
           Text(text = product.value.productName,
               Modifier.fillMaxWidth(),
               textAlign = TextAlign.Start,
               fontSize = 20.sp,
               fontWeight = FontWeight.Bold,
               color = MaterialTheme.colorScheme.onBackground)

           Spacer(modifier = Modifier.size(25.dp))

           Box(modifier = Modifier
               .fillMaxWidth()
               .padding(start = 10.dp, end = 10.dp)
               .clip(shape = RoundedCornerShape(5.dp))
               .border(
                   border = BorderStroke(1.dp, Color.Gray),
                   shape = RoundedCornerShape(5.dp)
               ),
               ){
               val image: Painter = painterResource(id = R.drawable.watchone)
               Row(modifier = Modifier.padding(5.dp)) {
                   Card(
                       modifier = Modifier
                           .size(20.dp)
                       ,
                       shape = CircleShape


                       ) {
                       Image(painter = image,
                           contentDescription = "Crono Watch", contentScale = ContentScale.Crop,
                           modifier = Modifier
                               .fillMaxWidth()
                               .fillMaxHeight())

                   }
                   Spacer(modifier = Modifier.size(10.dp))
                   Text(text = product.value.user.userName,
                       textAlign = TextAlign.Start,
                       fontSize = 12.sp,
                       fontWeight = FontWeight.Light,
                       color = MaterialTheme.colorScheme.onBackground)

                   Spacer(modifier = Modifier.width(200.dp))

                   Text(text = "97% Positive",
                       textAlign = TextAlign.Start,
                       fontSize = 12.sp,
                       fontWeight = FontWeight.Medium,
                       color = MaterialTheme.colorScheme.onBackground)



               }

           }
           val productPrice = product.value.price.toString()
           Spacer(modifier = Modifier.size(25.dp))
           Text(text = "Ksh. $productPrice", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = MaterialTheme.colorScheme.onBackground)

           val productUnits = product.value.availableStock
           Spacer(modifier = Modifier.size(10.dp))
           Text(text = "Available Units: $productUnits units", fontSize = 14.sp)
           var units by remember { mutableStateOf("1") }
           Spacer(modifier = Modifier.size(40.dp))
           Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.height(70.dp)) {

               Text(text = "Units Amount :  ", fontSize = 15.sp, fontWeight = FontWeight.Bold, modifier = Modifier
                   .align(Alignment.CenterVertically)
                   .height(40.dp)

               )

               OutlinedTextField(

                   value = units,
                   onValueChange = {units = it},
                   keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                   modifier = Modifier
                       .padding(top = 20.dp)
                       .height(60.dp)
                       .width(70.dp)
                       .align(Alignment.CenterVertically)
                       .offset(y = -25.dp),
                   colors = TextFieldDefaults.textFieldColors(containerColor = Color.Transparent,
                       focusedLabelColor = MaterialTheme.colorScheme.primary,
                       unfocusedLabelColor = MaterialTheme.colorScheme.primary,
                       focusedIndicatorColor = MaterialTheme.colorScheme.primary))

           }
           
           Spacer(modifier = Modifier.size(40.dp))

          Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
              ElevatedButton(

                  onClick = {},
                  modifier = Modifier
                      .width(200.dp)
                      .height(50.dp),
                  shape = RoundedCornerShape(10.dp),
                  colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)

              ){
                  Text(text = "Add To Cart", color = MaterialTheme.colorScheme.onPrimary, fontWeight = FontWeight.ExtraBold, fontSize = 18.sp)
              }

          }

           Spacer(modifier = Modifier.size(40.dp))
           Text(text = "Description", fontSize = 20.sp, fontWeight = FontWeight.Bold)
           Spacer(modifier = Modifier.size(10.dp))
           
           Text(text = product.value.description, fontSize = 16.sp, textAlign = TextAlign.Start)
           

       }
       
       
       
      
   }
   

    
}



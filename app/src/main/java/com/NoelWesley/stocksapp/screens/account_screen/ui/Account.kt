package com.NoelWesley.stocksapp.screens.account_screen.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.NoelWesley.stocksapp.R
import com.NoelWesley.stocksapp.screens.login.model.response.LoginResponse
import com.NoelWesley.stocksapp.util.Constants.Companion.BASE_URL
import com.NoelWesley.stocksapp.util.UsernamePreferenceDatastore


@Composable
fun AccountScreen() {

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






    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        item {
            ImageSection(savedUsername)
            BottomSection()

        }


    }
}


@Composable
fun ImageSection(savedUsername: State<String?>, accountViewModel: AccountViewModel = hiltViewModel()){


       val baseUrl = BASE_URL;
       val username = savedUsername.value!!

       //var profile2 : State<String> = accountViewModel.profile.observeAsState("")


       //val profile : State<ByteArray> = accountViewModel.profile.observeAsState(byteArrayOf())
       Box(modifier = Modifier
           .fillMaxSize()
           .background(MaterialTheme.colorScheme.background)
       ) {
           Box(
               modifier = Modifier
                   .fillMaxWidth()
                   .wrapContentHeight()
                   .padding(bottom = 20.dp)
                   .background(MaterialTheme.colorScheme.surfaceVariant),
               contentAlignment = Alignment.Center
           ) {
               Column(modifier = Modifier
                   .padding(bottom = 20.dp, top = 20.dp),
                   horizontalAlignment = Alignment.CenterHorizontally,
                   verticalArrangement = Arrangement.Center


               ) {
                   Card(
                       modifier = Modifier.size(90.dp),
                       shape = RoundedCornerShape(50.dp)

                   ) {
                         val image: Painter = painterResource(id = R.drawable.blankprofile)
                       AsyncImage(
                           modifier = Modifier.fillMaxSize().fillMaxHeight(),
                           model = "$baseUrl/user/getProfile/$username",
                           error = image,
                           contentScale = ContentScale.Crop,
                           contentDescription = "The delasign logo",
                       )
                       

                   }

                   Text(
                       modifier = Modifier
                           .width(90.dp)
                           .offset(y = 6.dp)
                           .wrapContentWidth(),
                       text = "profile",
                       color = MaterialTheme.colorScheme.onBackground,
                       textAlign = TextAlign.Center,
                       fontSize = 14.sp,
                       fontWeight = FontWeight.Bold
                   )
                   
                   Text(
                       modifier = Modifier
                           .width(160.dp)
                           .offset(y = 6.dp)
                           ,
                       text = savedUsername.value!!,
                       fontWeight = FontWeight.Light,
                       color  = Color.Gray,
                       textAlign = TextAlign.Center,
                       fontSize = 12.sp,

                   )
               }

           }
       }




}

@Composable
fun BottomSection(){
    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Text(text = "My Account", color = Color.Gray, modifier = Modifier.width(120.dp), textAlign = TextAlign.Center, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.size(15.dp))
        navigationItem(itemName = "Watchlist")
        Spacer(modifier = Modifier.size(10.dp))
        navigationItem(itemName = "Orders")
        Spacer(modifier = Modifier.size(10.dp))
        navigationItem(itemName = "Transactions")
        Spacer(modifier = Modifier.size(10.dp))
        navigationItem(itemName = "Sell")

        Spacer(modifier = Modifier.size(15.dp))

        Text(text = "My Profile", color = Color.Gray, modifier = Modifier.width(120.dp), textAlign = TextAlign.Center, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.size(15.dp))
        navigationItem(itemName = "General")
        Spacer(modifier = Modifier.size(10.dp))
        navigationItem(itemName = "Profile")
        Spacer(modifier = Modifier.size(15.dp))

        Text(text = "Support", color = Color.Gray, modifier = Modifier.width(120.dp), textAlign = TextAlign.Center,fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.size(15.dp))
        navigationItem(itemName = "Notifications")
        Spacer(modifier = Modifier.size(10.dp))
        navigationItem(itemName = "FAQ")
        Spacer(modifier = Modifier.size(10.dp))
        navigationItem(itemName = "Privacy settings")
        Spacer(modifier = Modifier.size(10.dp))
        navigationItem(itemName = "Terms and conditions")



    }

}

@Composable
fun navigationItem(itemName: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),

        ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val rightArrowImage = Icons.Default.KeyboardArrowRight
            Text(
                text = itemName,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold
            )
            Icon(
                imageVector = rightArrowImage,
                contentDescription = "",
                tint = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}


@Preview(showBackground = true, widthDp = 392  , heightDp = 775)
@Composable
fun FuctionPreview(){
    AccountScreen()
}
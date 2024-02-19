package com.NoelWesley.stocksapp.screens.cart_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.NoelWesley.stocksapp.util.UsernamePreferenceDatastore

@Composable
fun CartScreen(){
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

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(MaterialTheme.colorScheme.background),
    ){
        Text(text = savedUsername.value!!,
            color = MaterialTheme.colorScheme.onPrimaryContainer)
    }

}


@Preview(showBackground = true, widthDp = 392  , heightDp = 775)
@Composable
fun CartPreview(){
    CartScreen()
}
package com.NoelWesley.stocksapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.NoelWesley.stocksapp.screens.bottom_navigation.MainScreen
import com.NoelWesley.stocksapp.screens.login.ui.LoginMain
import com.NoelWesley.stocksapp.ui.theme.StocksAppTheme
import com.NoelWesley.stocksapp.screens.registration.ui.RegistrationMain
import com.NoelWesley.stocksapp.util.NavItem
import com.NoelWesley.stocksapp.util.UsernamePreferenceDatastore
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {

            StocksAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(

                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    //creating nav controller to carry out navigation
                    val navController = rememberNavController()

                    val context = LocalContext.current

                    // Declare a local variable named dataStore and assign it the value of a UsernamePreferenceDatastore object
                    // The UsernamePreferenceDatastore is a custom class that handles storing and retrieving the username using DataStore
                    val dataStore = UsernamePreferenceDatastore(context)



                    //NavHost is a container that manages the navigation between different composables in a Jetpack Compose app
                    NavHost(
                        navController = navController,
                        startDestination = NavItem.Login.route  ){

                        //Define login Screen destination
                        composable(NavItem.Login.route){
                        LoginMain(navController)
                        }

                        //Define sign up screen destination
                        composable(NavItem.SignUp.route){
                          RegistrationMain(navController)
                        }

                        composable(NavItem.MainScreen.route){
                            MainScreen()
                        }


                    }

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    StocksAppTheme {
        Greeting("Android")
    }
}
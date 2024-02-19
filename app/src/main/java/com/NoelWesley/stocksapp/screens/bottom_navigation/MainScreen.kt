package com.NoelWesley.stocksapp.screens.bottom_navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.NoelWesley.stocksapp.screens.account_screen.ui.AccountScreen
import com.NoelWesley.stocksapp.screens.cart_screen.CartScreen
import com.NoelWesley.stocksapp.screens.home_screen.ui.HomeScreen
import com.NoelWesley.stocksapp.screens.search_screen.SearchScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {

    //Creating a NavController object with it's state stored
    val navController = rememberNavController()



    Scaffold(
        //bottomBar parameter allows you to specify a composable function that creates the app bar across the bottom of the screen
        //In this case BottomNavigationBar is a composable for creating bottom navigation
        bottomBar = { BottomNavigationBar(navController = navController) },

        content = {padding ->
            Box(modifier = Modifier.padding(padding)){
                Navigation(navController = navController)
            }
        },
        containerColor = MaterialTheme.colorScheme.background

        )




}

    //The following code defines a composable function called Navigation, which takes a NavHostController as a parameter
//A NavHostController is an object that manages the navigation within a NavHost, which is a container for navigation destinations.
    @Composable
    fun Navigation(navController: NavHostController){
        //The code uses the NavHost composable function to create a NavHost with the given navController and startDestination.
        NavHost(navController = navController, startDestination = NavigationItem.Home.route ){

            //Adding destinations to the navHost based on the values of route in the SealedObjects
            composable(NavigationItem.Home.route){
                HomeScreen()
            }

            composable(NavigationItem.Search.route){
                SearchScreen()
            }

            composable(NavigationItem.Cart.route){
                CartScreen()
            }

            composable(NavigationItem.Account.route){
                AccountScreen()
            }
        }
    }

@Composable
fun BottomNavigationBar(navController: NavController){
    //Creating a list of NavigationItem Objects which store different information for our bottom navigation buttons
    val items = listOf(
        NavigationItem.Home,
        NavigationItem.Search,
        NavigationItem.Cart,
        NavigationItem.Account
    )

    BottomNavigation(
        //Parameter of Bottom Navigation to change backgroud color
        backgroundColor =  MaterialTheme.colorScheme.background,

        //Parameter to specify the color of icons and their label
        contentColor = MaterialTheme.colorScheme.onPrimary

    ) {
        //The currentBackStackEntryAsState function returns a State object that represents the current destination on the back stack of the NavController.
        //The back stack is a data structure that stores the history of navigation destinations that the user has visited.
        ////The current destination is the one that is currently visible on the screen.
        val navBackStackEntry by navController.currentBackStackEntryAsState()

        // This means that the currentRoute variable will hold the route of the current destination,
        // if the navBackStackEntry and the destination are not null, otherwise it will be null.
        val currentRoute = navBackStackEntry?.destination?.route
        
        items.forEach { item ->
            BottomNavigationItem(
                //Set the icon parameter of the Item to it's corresponding value in the sealed class
                icon = { Icon(painterResource(id = item.icon), contentDescription = item.title) },

                //Set the title parameter of the Item to it's corresponding value in the sealed class
                label = { Text(text = item.title) },

                //Set Color when item is selected
                selectedContentColor = MaterialTheme.colorScheme.onSecondaryContainer,

                //Set color when item is unselected
                unselectedContentColor = MaterialTheme.colorScheme.primary,

                //always show label
                alwaysShowLabel = true,

                //If selected set the current route to the route of the selected item
                selected = currentRoute == item.route,

                //Setting the onclick parameter when a navigationItem is clicked
                onClick = {
                    //If clicked, we navigate to the Screen attached to the route
                    navController.navigate(item.route){
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        //startDestinationRoute property of the graph is a String that holds the route of the start destination of the navigation graph
                        navController.graph.startDestinationRoute?.let {route ->
                            //It removes all the destinations from the back stack until it reaches the specified destination
                            popUpTo(route) {
                                //The codes set the saveState option to true,
                                // which means that the state of the popped destinations will be saved and restored if the user navigates back to them later
                                saveState = true
                            }

                            // Avoid multiple copies of the same destination when
                            // reselecting the same item
                            launchSingleTop = true
                            // Restore state when reselecting a previously selected item
                            restoreState = true

                        }
                    }
                }




            )



            
        }

    }

}

@Preview(showBackground = true, widthDp = 392  , heightDp = 775)
@Composable
fun MainScreenPreview(){


}



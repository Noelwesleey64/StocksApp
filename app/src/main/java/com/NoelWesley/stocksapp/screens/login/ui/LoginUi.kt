package com.NoelWesley.stocksapp.screens.login.ui

import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.NoelWesley.stocksapp.R
import com.NoelWesley.stocksapp.screens.bottom_navigation.NavigationItem
import com.NoelWesley.stocksapp.screens.login.data.remote.LoginApi
import com.NoelWesley.stocksapp.screens.login.model.response.LoginResponse
import com.NoelWesley.stocksapp.screens.registration.ui.ButtonSection
import com.NoelWesley.stocksapp.screens.registration.ui.RegistrationMain
import com.NoelWesley.stocksapp.screens.registration.ui.RegistrationViewModel
import com.NoelWesley.stocksapp.screens.registration.ui.logo
import com.NoelWesley.stocksapp.ui.theme.StocksAppTheme
import com.NoelWesley.stocksapp.util.NavItem
import com.NoelWesley.stocksapp.util.UsernamePreferenceDatastore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.launch

@Preview(showBackground = true, widthDp = 392  , heightDp = 775)
@Composable
fun GreetingPreview() {
    StocksAppTheme {
        Column {
           logoLogin()
            Spacer(modifier = Modifier.size(100.dp))
            //TextFields()

        }
    }
}

@Composable
fun LoginMain(navController: NavController){
    Column(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
        logoLogin()
        Spacer(modifier = Modifier.size(100.dp))
        TextFields(navController)

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFields(
    navController: NavController,
    loginViewModel: LoginViewModel = hiltViewModel(),


){

    //State mutableState variables
    var userNameEmail by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }
    var passwordLength by remember { mutableStateOf(0) }
    val isPasswordEmpty = remember { mutableStateOf(false) }
    val isUserNameOrEmailEmpty = remember { mutableStateOf(false) }
    val isUserNameEmpty = remember { mutableStateOf(false) }

    //// This line declares a variable named message of type State<LoginResponse>
    //// State is an observable type that holds a value and allows composables to read it
    //// LoginResponse is a custom data class that holds a string and a boolean value
    val message : State<LoginResponse> = loginViewModel.status.observeAsState(LoginResponse("", false))



    //Get the password length of the password the user types
    passwordLength = password.length




    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            text = message.value.message,
            color = if(message.value.status){
                Color.Green
            }else{
                Color.Red
            }
        )
        OutlinedTextField(

            value = userNameEmail,
            onValueChange = {userNameEmail = it},
            label ={ Text("Username or Email", color = MaterialTheme.colorScheme.onBackground) },
            keyboardOptions = KeyboardOptions.Default,
            placeholder = { Text(text = "Enter your username or email", color = MaterialTheme.colorScheme.onBackground) },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 20.dp)
                .height(60.dp),
            colors = TextFieldDefaults.textFieldColors(containerColor = Color.Transparent,
                focusedLabelColor = MaterialTheme.colorScheme.primary,
                unfocusedLabelColor = MaterialTheme.colorScheme.primary,
                focusedIndicatorColor = if(isUserNameEmpty.value){
                    Color.Red
                }else{
                    MaterialTheme.colorScheme.primary
                },
                unfocusedIndicatorColor =  if(isUserNameOrEmailEmpty.value){
                    Color.Red
                }else{
                    MaterialTheme.colorScheme.primary
                })



        )

        OutlinedTextField(

            value = password,
            onValueChange = {password = it},
            label ={ Text("Password", color = MaterialTheme.colorScheme.onBackground) },
            placeholder = { Text(text = "Type your password", color = MaterialTheme.colorScheme.onBackground) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation =  if(showPassword){
                VisualTransformation.None
            } else{
                PasswordVisualTransformation()
            },
            trailingIcon = {
                if(showPassword){
                    IconButton(onClick = {
                        showPassword = false
                    } , modifier = Modifier.size(20.dp)) {
                        Icon(painter = painterResource(id = R.drawable.visibility), contentDescription = "visible", tint = MaterialTheme.colorScheme.onBackground)
                    }
                }else{
                    IconButton(onClick = {
                        showPassword = true
                    } , modifier = Modifier.size(20.dp)) {
                        Icon(painter = painterResource(id = R.drawable.visibilityoff), contentDescription = "not visible", tint = MaterialTheme.colorScheme.onBackground)
                    }
                }
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 20.dp)
                .height(60.dp),
            colors = TextFieldDefaults.textFieldColors(containerColor = Color.Transparent,
                focusedLabelColor = MaterialTheme.colorScheme.primary,
                unfocusedLabelColor = MaterialTheme.colorScheme.primary,
                focusedIndicatorColor = if(isPasswordEmpty.value){
                    Color.Red
                }else{
                    MaterialTheme.colorScheme.primary
                },
                unfocusedIndicatorColor =  if(isPasswordEmpty.value){
                    Color.Red
                }else{
                    MaterialTheme.colorScheme.primary
                })

        )

        Text(
            text = if(passwordLength in 1..7){
                "Password should be a minimum of 8 characters"
            }else{
                ""
            },
            fontSize = 10.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            color = Color.Red
        )


        Spacer(modifier = Modifier.size(20.dp))

       ButtonSection(
           navController,
           usernameOrEmail = userNameEmail ,
           password = password,
           isPasswordempty = isPasswordEmpty,
           isuserNameOrEmailEmpty =  isUserNameOrEmailEmpty,
           //datastore = usernamePreferenceDatastore
       )


    }

}

@Composable
fun logoLogin() {
    Column() {
        Image(

            painter = painterResource(id = R.drawable.logo),
            contentDescription = "logo",
            modifier = Modifier
                .size(80.dp)
                .offset(40.dp, 60.dp)
        )

        Text("Sign In",
            modifier = Modifier
                .padding(top = 20.dp)
                .offset(40.dp, 80.dp),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary

        )
    }
}

@Composable
fun ButtonSection(
    navController: NavController,
    usernameOrEmail: String,
    password: String,
    isPasswordempty: MutableState<Boolean>,
    isuserNameOrEmailEmpty: MutableState<Boolean>,
    loginViewModel: LoginViewModel = hiltViewModel(),


 ){







    // Declare a local variable named context and assign it the value of the current context
    // The context is an object that provides access to system services and resources
    // The LocalContext is a composition local that holds the context value
    val context = LocalContext.current

    // Declare a local variable named scope and assign it the value of a coroutine scope
    // A coroutine scope is an object that defines the lifecycle and cancellation policy of coroutines
    // The rememberCoroutineScope() function creates a coroutine scope that is tied to the current composable
    val scope = rememberCoroutineScope()

    // Declare a local variable named dataStore and assign it the value of a UsernamePreferenceDatastore object
    // The UsernamePreferenceDatastore is a custom class that handles storing and retrieving the username using DataStore
    val dataStore = UsernamePreferenceDatastore(context)

    // Declare a local variable named savedUsername and assign it the value of a Flow object
    // The dataStore.getUsername is a property that returns a Flow of the username from the DataStore
    // The collectAsState() function converts the Flow into a State object that can be used in composables
    val savedUsername = dataStore.getUsername.collectAsState(initial = "")

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {

        val message : State<LoginResponse> = loginViewModel.status.observeAsState(LoginResponse("", false))




        ElevatedButton(

            onClick = {
                //if user clicks button, set the isuserNameOrEmailEmpty.value to either true or false in regards to
                //if Uername or email string is empty or not
                //if usernameOrEmail.isEmpty is Empty it will set it to true else it will be false

                isuserNameOrEmailEmpty.value = usernameOrEmail.isEmpty()


                //if user clicks button, set the isPasswordEmpty.value to either true or false in regards to
                //if password string is empty or not
                //if password.isEmpty() is Empty it will set it to true else it will be false
                isPasswordempty.value = password.isEmpty()




                if (isuserNameOrEmailEmpty.value == true){
                    Toast.makeText(context, "Please enter the email or username", Toast.LENGTH_SHORT).show()
                }else if(isPasswordempty.value == true){
                    Toast.makeText(context, "Please enter the password", Toast.LENGTH_SHORT).show()
                } else{

                    //If the button is clicked and all input validations are observed then call userLogin method in the LoginViewModel
                    loginViewModel.userLogin(usernameOrEmail,password)

                    // Use the saveUsername() function to store the username variable in the dataStore object
                    // The dataStore object is a StoreUsername object that handles storing and retrieving the username using DataStore
                    scope.launch {
                        dataStore.saveUsername(usernameOrEmail)
                    }






                }

            },
            modifier = Modifier
                .width(120.dp)
                .height(50.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)

        ) {
            //If the message property of the message object observed in the loginViewModel is not empty then run the if statement
            if (message.value.message != ""){
                //If the status property of the message object is true then run the statements below
                if (message.value.status){
                    navController.navigate(NavItem.MainScreen.route)
                    //Toast.makeText(context, "Login SuccessFull ${message.value.status}", Toast.LENGTH_SHORT).show()
                } else {
                   // Toast.makeText(context, "Login failed ${message.value.status}", Toast.LENGTH_SHORT).show()
                }

            }

            Text(text = "Sign In", fontWeight = FontWeight.Bold, fontSize = 18.sp)
        }

        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
            ) {

            Text(
                text = "Don't Have an Account?",
                modifier = Modifier
                    .padding(top = 30.dp)
                    .offset(10.dp, 0.dp),
                color = Color.Gray
            )



            Text(
                text = "Sign Up",
                modifier = Modifier
                    .padding(top = 30.dp)
                    .offset(10.dp, 0.dp)
                    .clickable(enabled = true) {
                        //Go to the signup page when clicked
                         navController.navigate(NavItem.SignUp.route)
                    },
                color = MaterialTheme.colorScheme.primary,

            )

        }



        }
    }




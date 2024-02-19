package com.NoelWesley.stocksapp.screens.registration.ui

import android.annotation.SuppressLint
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

import com.NoelWesley.stocksapp.R
import com.NoelWesley.stocksapp.ui.theme.StocksAppTheme
import com.NoelWesley.stocksapp.util.NavItem
import java.time.LocalDateTime


@Composable
fun RegistrationMain(
    navController: NavController,
    registrationViewModel: RegistrationViewModel = hiltViewModel(),

    ){

LazyColumn(modifier = Modifier
    .fillMaxSize()
    .background(MaterialTheme.colorScheme.background)
) {
  item {
      logo()
      Spacer(modifier = Modifier.size(100.dp))
      TextFields(registrationViewModel, navController)
      Spacer(modifier = Modifier.size(40.dp))

  }



}
}







//Logo and Sign up text section
@Composable
fun logo() {
    Column() {
        Image(

            painter = painterResource(id = R.drawable.logo),
            contentDescription = "logo",
            modifier = Modifier
                .size(80.dp)
                .offset(40.dp, 60.dp)
        )

        Text("Sign Up",
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

@SuppressLint("NewApi")
private fun getCurrentTime(): LocalDateTime {

    return LocalDateTime.now()
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFields(registrationViewModel: RegistrationViewModel, navController: NavController){

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var userName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password2 by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }
    var showPassword2 by remember { mutableStateOf(false) }
    var passwordLength by remember { mutableStateOf(0) }
    var password2Length by remember { mutableStateOf(0) }
    var isTextFieldEmpty by remember { mutableStateOf(true) }
    val isPasswordEmpty = remember { mutableStateOf(false) }
    val isPassword2Empty = remember { mutableStateOf(false) }
    val isFirstNameEmpty = remember { mutableStateOf(false) }
    val isEmailEmpty = remember { mutableStateOf(false) }
    val isUserNameEmpty = remember { mutableStateOf(false) }


    passwordLength = password.length
    password2Length = password2.length



    Column(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(

            value = firstName,
            onValueChange = {firstName = it},
            label ={Text("First Name", color = MaterialTheme.colorScheme.onBackground)},
            keyboardOptions = KeyboardOptions.Default,
            placeholder = { Text(text = "Enter First Name", color = MaterialTheme.colorScheme.onBackground)},
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 20.dp)
                .height(60.dp),
            colors = TextFieldDefaults.textFieldColors(containerColor = Color.Transparent,
                focusedLabelColor = MaterialTheme.colorScheme.primary,
                unfocusedLabelColor = MaterialTheme.colorScheme.primary,
                focusedIndicatorColor = if(isFirstNameEmpty.value){
                    Color.Red
                                                                 }else{
                    MaterialTheme.colorScheme.primary
                                                                      },
                unfocusedIndicatorColor =  if(isFirstNameEmpty.value){
                    Color.Red
                }else{
                    MaterialTheme.colorScheme.primary
                })



        )
        OutlinedTextField(

            value = lastName,
            onValueChange = {lastName = it},
            label ={Text("Last Name", color = MaterialTheme.colorScheme.onBackground)},
            placeholder = { Text(text = "Enter Last Name", color = MaterialTheme.colorScheme.onBackground)},
            keyboardOptions = KeyboardOptions.Default,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 20.dp)
                .height(60.dp),
            colors = TextFieldDefaults.textFieldColors(containerColor = Color.Transparent,
                focusedLabelColor = MaterialTheme.colorScheme.primary,
                unfocusedLabelColor = MaterialTheme.colorScheme.primary,
                focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.primary)

        )
        OutlinedTextField(

            value = userName,
            onValueChange = {userName = it},
            label ={Text("Username", color = MaterialTheme.colorScheme.onBackground)},
            placeholder = { Text(text = "Enter your username", color = MaterialTheme.colorScheme.onBackground)},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
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
                unfocusedIndicatorColor =  if(isUserNameEmpty.value){
                    Color.Red
                }else{
                    MaterialTheme.colorScheme.primary
                })

        )


        OutlinedTextField(

            value = email,
            onValueChange = {email = it},
            label ={Text("Email", color = MaterialTheme.colorScheme.onBackground)},
            placeholder = { Text(text = "Enter your email address", color = MaterialTheme.colorScheme.onBackground)},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 20.dp)
                .height(60.dp),
            colors = TextFieldDefaults.textFieldColors(containerColor = Color.Transparent,
                focusedLabelColor = MaterialTheme.colorScheme.primary,
                unfocusedLabelColor = MaterialTheme.colorScheme.primary,
                focusedIndicatorColor = if(isEmailEmpty.value){
                    Color.Red
                }else{
                    MaterialTheme.colorScheme.primary
                },
                unfocusedIndicatorColor =  if(isEmailEmpty.value){
                    Color.Red
                }else{
                    MaterialTheme.colorScheme.primary
                })

        )

        OutlinedTextField(

            value = password,
            onValueChange = {password = it},
            label ={Text("Password", color = MaterialTheme.colorScheme.onBackground)},
            placeholder = { Text(text = "Type your password", color = MaterialTheme.colorScheme.onBackground)},
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

        OutlinedTextField(

            value = password2,
            onValueChange = {password2 = it},
            label ={Text("Confirm Password", color = MaterialTheme.colorScheme.onBackground)},
            placeholder = { Text(text = "Confirm your password", color = MaterialTheme.colorScheme.onBackground)},
            visualTransformation = if(showPassword2){
                VisualTransformation.None
            } else{
                PasswordVisualTransformation()
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                if(showPassword2){
                    IconButton(onClick = {
                        showPassword2 = false
                    } , modifier = Modifier.size(20.dp)) {
                        Icon(painter = painterResource(id = R.drawable.visibility), contentDescription = "visible", tint = MaterialTheme.colorScheme.onBackground)
                    }
                }else{
                    IconButton(onClick = {
                        showPassword2 = true
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
                focusedIndicatorColor = if(isPassword2Empty.value){
                    Color.Red
                }else{
                    MaterialTheme.colorScheme.primary
                },
                unfocusedIndicatorColor =  if(isPassword2Empty.value){
                    Color.Red
                }else{
                    MaterialTheme.colorScheme.primary
                }),


        )

        Text(
            text = if(password2Length > 0 && password2 != password){
                "The passwords don't match"
            }else{
                ""
            },
            fontSize = 10.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            color = Color.Red
        )


        Spacer(modifier = Modifier.size(20.dp))

        ButtonSection(
            firstName = firstName,
            lastName = lastName,
            email = email,
            password = password,
            password2 = password2,
            isPasswordempty = isPasswordEmpty,
            isfirstNameEmpty= isFirstNameEmpty,
            ispassword2Empty = isPassword2Empty,
            isemailEmpty = isEmailEmpty,
            isuserNameEmpty = isUserNameEmpty,
            username = userName,
            registrationViewModel = registrationViewModel,
            navController = navController
        )



    }

}

@Composable
fun ButtonSection(
    registrationViewModel: RegistrationViewModel,
    firstName: String,
    lastName: String,
    email: String,
    password: String,
    password2: String,
    isPasswordempty:MutableState<Boolean>,
    isfirstNameEmpty:MutableState<Boolean>,
    ispassword2Empty:MutableState<Boolean>,
    isemailEmpty:MutableState<Boolean>,
    isuserNameEmpty:MutableState<Boolean>,
    username: String,
    navController: NavController){

    var currentTime by remember { mutableStateOf("") }

    val context = LocalContext.current
Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {

    ElevatedButton(
        onClick = {
            //if user clicks button, set the isfirstNameEmpty.value to either true or false in regards to
            //if firstName string is empty or not
            //if firstName.isEmpty is Empty it will set it to true else it will be false
            isfirstNameEmpty.value = firstName.isEmpty()


            //if user clicks button, set the isPasswordEmpty.value to either true or false in regards to
            //if password string is empty or not
            //if password.isEmpty() is Empty it will set it to true else it will be false
            isPasswordempty.value = password.isEmpty()

            //if user clicks button, set the isPasswordE2mpty.value to either true or false in regards to
            //if password2 string is empty or not
            //if password2.isEmpty() is Empty it will set it to true else it will be false
            ispassword2Empty.value = password2.isEmpty()

            //if user clicks button, set the isemailEmpty.value to either true or false in regards to
            //if email string is empty or not
            //if email.isEmpty() is Empty it will set it to true else it will be false
            isemailEmpty.value = email.isEmpty()

            isuserNameEmpty.value = username.isEmpty()


            if (isemailEmpty.value == true){
                Toast.makeText(context, "Please enter the email", Toast.LENGTH_SHORT).show()
            }else if(isPasswordempty.value == true){
                Toast.makeText(context, "Please enter the password", Toast.LENGTH_SHORT).show()
            }else if(isfirstNameEmpty.value == true){
                Toast.makeText(context, "Please enter your first name", Toast.LENGTH_SHORT).show()
            }else if(ispassword2Empty.value == true){
                Toast.makeText(context, "Please Confirm the password", Toast.LENGTH_SHORT).show()
            } else{
                currentTime = getCurrentTime().toString()
                registrationViewModel.getRegistrationResponse(
                    createdTime = currentTime,
                    email = email,
                    password= password,
                    firstName = firstName,
                    lastName = lastName,
                    userName = username,
                    context = context
                    )

            }



        },
        modifier = Modifier
            .width(120.dp)
            .height(50.dp),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)

    ) {
        Text(text = "Sign Up", fontWeight = FontWeight.Bold, fontSize = 18.sp)
    }

    Row(modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Have an Account?",
            modifier = Modifier
                .padding(top = 30.dp)
                .offset(10.dp, 0.dp),
            color = Color.Gray
        )



        Text(
            text = "Sign In",
            modifier = Modifier
                .padding(top = 30.dp)
                .offset(10.dp, 0.dp)
                .clickable(enabled = true) {
                    //Go to the login page when clicked
                    navController.navigate(NavItem.Login.route)
                },
            color = MaterialTheme.colorScheme.primary,

            )

    }


    Text(
        text = "By Signing up you agree to our Terms & Privacy Policy",
        modifier = Modifier
            .width(150.dp)
            .padding(top = 30.dp)
            .offset(10.dp, 0.dp),
        color = Color.Gray
    )

    Text(text = "Or",
        modifier = Modifier
            .width(150.dp)
            .padding(top = 30.dp)
            .offset(80.dp, 0.dp)
            .align(Alignment.CenterHorizontally),
        color = Color.Gray)

    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 40.dp)){
        Card (
            modifier = Modifier
                .size(60.dp),
            shape = CircleShape,
            elevation = CardDefaults.cardElevation(8.dp),
            colors = CardDefaults.cardColors(Color.White),



        ){

            Image(
                painter = painterResource(id = R.drawable.google),
                contentDescription = "google logo",
                modifier = Modifier
                    .fillMaxSize()
            )

        }

        Card (
            modifier = Modifier
                .size(60.dp),
            shape = CircleShape,
            elevation = CardDefaults.cardElevation(8.dp),
            colors = CardDefaults.cardColors(Color.White),



            ){

            Image(
                painter = painterResource(id = R.drawable.facebook),
                contentDescription = "facebook logo",
                modifier = Modifier
                    .fillMaxSize()
            )

        }
        Card (
            modifier = Modifier
                .size(60.dp),
            shape = CircleShape,
            elevation = CardDefaults.cardElevation(8.dp),
            colors = CardDefaults.cardColors(Color.White),



            ){

            Image(
                painter = painterResource(id = R.drawable.xcom),
                contentDescription = "twitter logo",
                modifier = Modifier
                    .fillMaxSize()
            )

        }



    }
 }
}


@Preview(showBackground = true, widthDp = 392  , heightDp = 775)
@Composable
fun GreetingPreview() {
    StocksAppTheme {
        Column {
            //LoginMain()
        }
    }
}
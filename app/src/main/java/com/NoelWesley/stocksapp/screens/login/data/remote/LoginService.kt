package com.NoelWesley.stocksapp.screens.login.data.remote

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.NoelWesley.stocksapp.screens.login.model.request.LoginRequest
import com.NoelWesley.stocksapp.screens.login.model.response.LoginResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

//Service class for our LoginApi
//It will handle the requests
class LoginService @Inject constructor(private val loginApi: LoginApi) {

    //suspend Method to return a LoginResponse object after logging in a user
    suspend fun userLogin(loginRequest: LoginRequest): LoginResponse {

        return withContext(Dispatchers.IO){
            //Dispatchers.IO is used for offloading blocking IO tasks to a shared pool of threads.
            //This line is making a network request to login a user and return a response using the loginApi object.
            val status = loginApi.userLogin(loginRequest)
            // This line is getting the body of the response.
            // If the body is null, it returns an empty list.
            status.body()?: LoginResponse("empty", false)

            //In summary, this block of code is switching the coroutine context to Dispatchers.IO,
            // making a network request to login a user and return a response,
            // and returning the body of the response or an empty list if the body is null

        }
    }


}
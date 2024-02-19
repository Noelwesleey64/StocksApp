package com.NoelWesley.stocksapp.screens.login.repo

import android.content.Context
import com.NoelWesley.stocksapp.screens.login.data.remote.LoginService
import com.NoelWesley.stocksapp.screens.login.model.request.LoginRequest
import com.NoelWesley.stocksapp.screens.login.model.response.LoginResponse
import javax.inject.Inject

//Dependency injecting loginService instance to the constructor
class LoginRepository @Inject constructor(private val loginService: LoginService) {

    //method to call the userlogin method in loginService object so that we can login the user and return a response
    suspend fun userLogin(loginRequest: LoginRequest): LoginResponse {
        return loginService.userLogin(loginRequest)
    }
}
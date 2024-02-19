package com.NoelWesley.stocksapp.screens.login.data.remote

import com.NoelWesley.stocksapp.screens.login.model.request.LoginRequest
import com.NoelWesley.stocksapp.screens.login.model.response.LoginResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface LoginApi {



    //The @Headers annotation in Retrofit allows you to add custom headers to your HTTP requests.
    //Headers are key-value pairs that provide additional information about the request or the response
    //The Content-Type header specifies the format of the request body, which is the data that you send to the server.
    // In this case, the value of the header is application/json, which means that the request body is a JSON object.
    @Headers("Content-Type:application/json")
    @POST("login")
    //This code is a Kotlin function that sends a POST request to the server’s /login endpoint.
    // The function is called LoginUser and takes a LoginRequest object as its input parameter.
    // The function returns a Response object that wraps a LoginResponse object.
    suspend fun userLogin(@Body loginRequest: LoginRequest): Response<LoginResponse>


}
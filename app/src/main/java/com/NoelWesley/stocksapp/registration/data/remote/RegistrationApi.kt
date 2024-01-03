package com.NoelWesley.stocksapp.registration.data.remote

import com.NoelWesley.stocksapp.registration.model.request.RegisterRequest
import com.NoelWesley.stocksapp.registration.model.response.RegistrationResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface RegistrationApi {

   //The @Headers annotation in Retrofit allows you to add custom headers to your HTTP requests.
   //Headers are key-value pairs that provide additional information about the request or the response
   //The Content-Type header specifies the format of the request body, which is the data that you send to the server.
   // In this case, the value of the header is application/json, which means that the request body is a JSON object.
   @Headers("Content-Type:application/json")
   @POST("register")
   //The generic type String indicates that the expected response body is a string.
   //The @Body annotation indicates that the info parameter is the request body, which is the data that you send to the server.
   fun registerUser(@Body registerRequest: RegisterRequest): Call<RegistrationResponse>


}
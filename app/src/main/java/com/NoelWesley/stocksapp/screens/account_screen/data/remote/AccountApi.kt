package com.NoelWesley.stocksapp.screens.account_screen.data.remote

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AccountApi {


    @GET("user/getProfile")
    suspend fun getImage(): Response<ByteArray>

    @GET("user/getProfile")
    fun fetchProfile(): Call<ByteArray>


    @GET("user/getHello/{username}")
    suspend fun getProfile(@Path("username") username: String): Response<String>


}
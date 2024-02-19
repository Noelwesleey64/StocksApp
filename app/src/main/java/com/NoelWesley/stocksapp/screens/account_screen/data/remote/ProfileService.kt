package com.NoelWesley.stocksapp.screens.account_screen.data.remote

import androidx.compose.runtime.Composable
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class ProfileService @Inject constructor(private val accountApi: AccountApi) {

    var profile : String = ""
   suspend fun getImage(): ByteArray{

        return withContext(Dispatchers.IO){
            val image = accountApi.getImage()

            image.body()?: byteArrayOf(0x2E, 0x38)
        }
    }

    suspend fun getHello(): String{

        return withContext(Dispatchers.IO){
            val image = accountApi.getProfile("noelwesley64@gmail.com")

            image.body()?: "fuck off"
        }
    }

    fun fetchImage(){
        accountApi.fetchProfile().enqueue(
            object : Callback<ByteArray>{
                override fun onResponse(call: Call<ByteArray>, response: Response<ByteArray>) {

                     profile = response.body().toString()


                }

                override fun onFailure(call: Call<ByteArray>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            }
        )
    }

}
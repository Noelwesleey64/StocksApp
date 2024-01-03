package com.NoelWesley.stocksapp.registration.data.remote

import android.content.Context
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import com.NoelWesley.stocksapp.registration.model.request.RegisterRequest
import com.NoelWesley.stocksapp.registration.model.response.RegistrationResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime
import javax.inject.Inject

class RegistrationService @Inject constructor(private val registrationApi:RegistrationApi) {


    fun registerUser(registerRequest: RegisterRequest, context: Context){

        registrationApi.registerUser(registerRequest).enqueue(
            object: Callback<RegistrationResponse>{
                //In this case, the callback object implements the Callback<ResponseBody> interface,
                // which has two methods: onResponse() and onFailure().
                //The onResponse() method is invoked when the request is successfully executed and a response is received.
                override fun onResponse(
                    //represents the original request that was executed.
                    call: Call<RegistrationResponse>,
                    //parameter is an object that contains the status code, headers, and body of the HTTP response.
                    response: Response<RegistrationResponse>
                ) {

                    val message = response.body()?.message.toString()
                    val status  = response.body()?.status
                    //This is the text to be displayed on the toast.
                    // The code uses the safe call operator (?.) to access the message property of the response body,
                    // which is an object that represents the data returned by the web service.
                    Toast.makeText(context, "$message and status is $status", Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<RegistrationResponse>, t: Throwable) {
                    //t.message: This is the text to be displayed on the toast.
                    // The code uses the message property of the Throwable object,
                    // which returns a short description of the cause of the failure.
                    Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
                }

            }
        )

    }
}
package com.NoelWesley.stocksapp.registration.repo

import android.adservices.measurement.WebSourceRegistrationRequest
import android.content.Context
import com.NoelWesley.stocksapp.registration.data.remote.RegistrationService
import com.NoelWesley.stocksapp.registration.model.request.RegisterRequest
import java.time.LocalDateTime
import javax.inject.Inject

class RegistrationRepository @Inject constructor(private val registrationService: RegistrationService){

    //function to register User
    fun registerUser(
        //takes registerRequest object which contains the fields we want to send through the post request
        registrationRequest: RegisterRequest,
        //takes the context
        context: Context
    ){
        //pass the RegisterRequest object to the registerUser method in RegistrationService class
        registrationService.registerUser(registrationRequest, context)

    }

}
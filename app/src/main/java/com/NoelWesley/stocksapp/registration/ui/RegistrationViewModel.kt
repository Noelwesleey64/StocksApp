package com.NoelWesley.stocksapp.registration.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import com.NoelWesley.stocksapp.repo.RegistrationRepository
import com.NoelWesley.stocksapp.registration.model.request.RegisterRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(private val registrationRepository: RegistrationRepository): ViewModel() {

    // The code declares a property named registerRequest of type RegisterRequest
    //A property can be either a val (read-only) or a var (mutable). In this case,
    // the code uses a var, which means the value of the property can be changed after initialization.
    private lateinit var registerRequest: RegisterRequest


    //defining a Function with parameters that can be mapped into the a RegisterRequest Object so that it can be sent through a post request
    fun getRegistrationResponse(
        createdTime: String,
        email: String,
        firstName: String,
        lastName: String,
        password: String,
        userName:String,
        context: Context
    ){
        //passing the arguements gotten from the register user function into a constructor of an object of the data class RegisterRequest
        registerRequest = RegisterRequest(createdTime, email, firstName, lastName, password, userName)

        ////Passing the RegisterRequest object created and context of the current activity into the method in the register user method in repository
        registrationRepository.registerUser(registerRequest, context)



    }


}
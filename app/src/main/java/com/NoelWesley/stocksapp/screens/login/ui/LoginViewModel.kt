package com.NoelWesley.stocksapp.screens.login.ui

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.NoelWesley.stocksapp.screens.login.model.request.LoginRequest
import com.NoelWesley.stocksapp.screens.login.model.response.LoginResponse
import com.NoelWesley.stocksapp.screens.login.repo.LoginRepository
import com.NoelWesley.stocksapp.screens.registration.model.request.RegisterRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginRepository: LoginRepository): ViewModel()
{
    // The code declares a property named loginRequest of type LoginRequest
    //A property can be either a val (read-only) or a var (mutable). In this case,
    // the code uses a var, which means the value of the property can be changed after initialization.
    private lateinit var loginRequest: LoginRequest

    // This line declares a private variable named _status of type MutableLiveData<LoginResponse>
// MutableLiveData is a subclass of LiveData that allows you to modify its value
// LoginResponse is a custom data class that holds a string and a boolean value
// The initial value of _status is a LoginResponse object with an empty string and false
    private var _status = MutableLiveData(LoginResponse("", false))

    // The value of status is the same as the value of _status, but it is exposed as LiveData, not MutableLiveData
    //// This means that other classes can only observe the value of status, but not change it directly
    var status: LiveData<LoginResponse> = _status


    //Method to receive user login info and map them into a LoginRequest object
    fun userLogin(
        userNameOrEmail: String,
        password: String,
    ){
        // This line creates a coroutine scope that is bound to the lifecycle of the ViewModel
        // This means that any coroutine launched in this scope will be automatically canceled when the ViewModel is cleared
        viewModelScope.launch {
            try {
                // This line creates a LoginRequest object with the password and userNameOrEmail parameters
                loginRequest = LoginRequest(password, userNameOrEmail)

                // This line sets the value of the _status variable to the result of the userLogin function from the loginRepository
                // The userLogin function is a suspend function that performs a network request to authenticate the user
                // The result is a LoginResponse object that contains a string and a boolean value
                _status.value = loginRepository.userLogin(loginRequest)

            }catch (_: Exception){
                // This block catches any exception that might occur during the network request
                // You can add some error handling logic here, such as logging the exception or showing a message to the user
            }


        }




    }

}
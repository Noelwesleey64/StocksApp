package com.NoelWesley.stocksapp.screens.account_screen.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.NoelWesley.stocksapp.screens.account_screen.data.remote.AccountApi
import com.NoelWesley.stocksapp.screens.account_screen.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(private val accountRepository: ProfileRepository, private val accountApi: AccountApi): ViewModel(){




    private var _profile = MutableLiveData("")

    var profile : LiveData<String> = _profile

    init {

    }


    private fun getProfile(){
        viewModelScope.launch{
            try{
                  //_profile.value = accountRepository.getImage()
            } catch (_: Exception){

            }
        }
    }






}
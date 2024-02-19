package com.NoelWesley.stocksapp.screens.account_screen.repository

import com.NoelWesley.stocksapp.screens.account_screen.data.remote.AccountApi
import com.NoelWesley.stocksapp.screens.account_screen.data.remote.ProfileService
import javax.inject.Inject

class ProfileRepository @Inject constructor(private val profileService: ProfileService) {
    suspend fun getProfile(): String{
        return  profileService.getHello()
    }

    suspend fun getImage(): ByteArray{
        return  profileService.getImage()
    }

    fun fetchImage(){
        profileService.fetchImage()
    }

    var profile = profileService.profile
}
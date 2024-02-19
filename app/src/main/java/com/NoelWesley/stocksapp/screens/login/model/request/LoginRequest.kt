package com.NoelWesley.stocksapp.screens.login.model.request

data class LoginRequest(
    val password: String,
    val userNameOrEmail: String
)
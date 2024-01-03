package com.NoelWesley.stocksapp.registration.model.request

import java.time.LocalDateTime

data class RegisterRequest(
    val createdTime: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val password: String,
    val userName: String
)
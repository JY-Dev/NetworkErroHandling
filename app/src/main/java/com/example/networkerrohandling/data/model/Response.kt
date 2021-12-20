package com.example.networkerrohandling.data.model

data class Response<T>(
    val status: Boolean,
    val code: String,
    val message: String,
    val data: T
)
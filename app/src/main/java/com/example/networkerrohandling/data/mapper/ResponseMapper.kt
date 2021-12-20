package com.example.networkerrohandling.data.mapper

import com.example.networkerrohandling.data.model.Response

fun <T,R> Response<T>.toResponse(data : R) : Response<R>
    = Response(status, code, message, data)
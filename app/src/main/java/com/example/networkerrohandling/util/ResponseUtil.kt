package com.example.networkerrohandling

import com.example.networkerrohandling.data.model.Response
import com.example.networkerrohandling.util.REST_CODE_ERROR_JWT_REFRESH
import com.example.networkerrohandling.util.REST_CODE_ERROR_TOKEN_EXPIRED
import com.example.networkerrohandling.util.REST_CODE_NO_SEARCH_MOVIE
import com.example.networkerrohandling.util.REST_SUCCESS

fun <T> T.getTokenExpireResponse() : Response<T> {
    return Response(false, REST_CODE_ERROR_TOKEN_EXPIRED,"",this)
}

fun <T> T.getJwtRefreshResponse() : Response<T> {
    return Response(false, REST_CODE_ERROR_JWT_REFRESH,"",this)
}

fun <T> T.getSuccessResponse() : Response<T> {
    return Response(true, REST_SUCCESS,"",this)
}

fun <T> T.getFailMessageResponse() : Response<T> {
    return Response(true, REST_CODE_NO_SEARCH_MOVIE,"영화가 없습니다.",this)
}


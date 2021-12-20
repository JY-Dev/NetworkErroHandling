package com.example.networkerrohandling.data.datasource

import com.example.networkerrohandling.data.model.MovieResponse
import com.example.networkerrohandling.data.model.Response
import com.example.networkerrohandling.getFailMessageResponse
import com.example.networkerrohandling.getJwtRefreshResponse
import com.example.networkerrohandling.getSuccessResponse
import com.example.networkerrohandling.getTokenExpireResponse
import java.net.UnknownHostException

class MovieDataSource {
    private val movieResponse = MovieResponse("영화", "영화입니다.")
    suspend fun getMovies(): Response<MovieResponse> {
        return movieResponse.getSuccessResponse()
    }

    suspend fun getMoviesJwtRefresh(): Response<MovieResponse> {
        return if (JwtRefresh.isJwtRefresh) movieResponse.getSuccessResponse() else movieResponse.getJwtRefreshResponse()
    }

    suspend fun getMoviesTokenExpire(): Response<MovieResponse> {
        return movieResponse.getTokenExpireResponse()
    }

    suspend fun getMoviesFailMessage(): Response<MovieResponse> {
        return movieResponse.getFailMessageResponse()
    }

    suspend fun getMovieException(): Response<MovieResponse> {
        throw UnknownHostException("UnknownException")
    }
}
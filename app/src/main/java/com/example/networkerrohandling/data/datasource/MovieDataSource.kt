package com.example.networkerrohandling.data.datasource

import com.example.networkerrohandling.data.model.MovieDetailResponse
import com.example.networkerrohandling.data.model.MovieResponse
import com.example.networkerrohandling.data.model.NetworkResult
import com.example.networkerrohandling.util.getFailMessageResponse
import com.example.networkerrohandling.util.getJwtRefreshResponse
import com.example.networkerrohandling.util.getSuccessResponse
import com.example.networkerrohandling.util.getTokenExpireResponse
import com.example.networkerrohandling.util.toNetworkResult
import java.net.UnknownHostException

class MovieDataSource {
    private val movieResponse = MovieResponse(1,"영화", "영화입니다.")
    private val movieDetailResponse = MovieDetailResponse("저자", "출판사",4.5f)
    suspend fun getMovies(): NetworkResult<MovieResponse> {
        return movieResponse.getSuccessResponse().toNetworkResult()
    }

    suspend fun getMovieDetail(): NetworkResult<MovieDetailResponse> {
        return movieDetailResponse.getSuccessResponse().toNetworkResult()
    }

    suspend fun getMoviesJwtRefresh(): NetworkResult<MovieResponse> {
        return if (JwtRefresh.isJwtRefresh) movieResponse.getSuccessResponse().toNetworkResult() else movieResponse.getJwtRefreshResponse().toNetworkResult()
    }

    suspend fun getMoviesTokenExpire(): NetworkResult<MovieResponse> {
        return movieResponse.getTokenExpireResponse().toNetworkResult()
    }

    suspend fun getMovieDetailTokenExpire(): NetworkResult<MovieDetailResponse> {
        return movieDetailResponse.getTokenExpireResponse().toNetworkResult()
    }

    suspend fun getMoviesFailMessage(): NetworkResult<MovieResponse> {
        return movieResponse.getFailMessageResponse().toNetworkResult()
    }

    suspend fun getMovieException(): NetworkResult<MovieResponse> {
        throw UnknownHostException("UnknownException")
    }

    suspend fun getMovieDetailException(): NetworkResult<MovieDetailResponse> {
        throw UnknownHostException("UnknownException")
    }
}
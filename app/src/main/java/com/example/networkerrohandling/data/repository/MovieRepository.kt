package com.example.networkerrohandling.data.repository

import com.example.networkerrohandling.data.model.NetworkResult
import com.example.networkerrohandling.data.datasource.MovieDataSource
import com.example.networkerrohandling.data.mapper.toDomainModel
import com.example.networkerrohandling.domain.model.Movie
import com.example.networkerrohandling.domain.model.MovieDetail
import com.example.networkerrohandling.util.*
import java.net.UnknownHostException

class MovieRepository(private val movieDataSource: MovieDataSource) {
    suspend fun getMovie() : NetworkResult<Movie> {
        val movieResponseNetworkResult = movieDataSource.getMovies()
        return movieResponseNetworkResult.mapNetworkResult {
                it.toDomainModel()
        }
    }

    suspend fun getMovieDetail() : NetworkResult<MovieDetail> {
        val movieResponseNetworkResult = movieDataSource.getMovieDetail()
        return movieResponseNetworkResult.mapNetworkResult {
            it.toDomainModel()
        }
    }

    suspend fun getMovieDetailException(): NetworkResult<MovieDetail> {
        throw UnknownHostException("UnknownException")
    }

    suspend fun getMovieDetailTokenExpire(): NetworkResult<MovieDetail> {
        val movieResponseNetworkResult = movieDataSource.getMovieDetailTokenExpire()
        return movieResponseNetworkResult.mapNetworkResult {
            it.toDomainModel()
        }
    }

    suspend fun getMovieJwtRefresh() : NetworkResult<Movie> {
        val movieResponseNetworkResult = movieDataSource.getMoviesJwtRefresh()
        return movieResponseNetworkResult.mapNetworkResult {
            it.toDomainModel()
        }
    }

    suspend fun getMovieTokenExpire() : NetworkResult<Movie> {
        val movieResponseNetworkResult = movieDataSource.getMoviesTokenExpire()
        return movieResponseNetworkResult.mapNetworkResult {
            it.toDomainModel()
        }
    }

    suspend fun getMovieFailMessage() : NetworkResult<Movie> {
        val movieResponseNetworkResult = movieDataSource.getMoviesFailMessage()
        return movieResponseNetworkResult.mapNetworkResult {
            it.toDomainModel()
        }
    }

    suspend fun getMovieException() : NetworkResult<Movie> {
        val movieResponseNetworkResult = movieDataSource.getMovieException()
        return movieResponseNetworkResult.mapNetworkResult {
            it.toDomainModel()
        }
    }
}
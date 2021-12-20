package com.example.networkerrohandling.data.repository

import com.example.networkerrohandling.data.model.NetworkResult
import com.example.networkerrohandling.data.datasource.MovieDataSource
import com.example.networkerrohandling.data.mapper.toDomainModel
import com.example.networkerrohandling.data.mapper.toResponse
import com.example.networkerrohandling.domain.model.Movie
import com.example.networkerrohandling.util.networkHandling
import com.example.networkerrohandling.util.toNetworkResult

class MovieRepository(private val movieDataSource: MovieDataSource) {
    suspend fun getMovie() : NetworkResult<Movie> {
        return networkHandling {
            val movieResponse = movieDataSource.getMovies()
            val movieData = movieResponse.data.toDomainModel()
            movieResponse.toResponse(movieData).toNetworkResult()
        }
    }

    suspend fun getMovieJwtRefresh() : NetworkResult<Movie> {
        return networkHandling {
            val movieResponse = movieDataSource.getMoviesJwtRefresh()
            val movieData = movieResponse.data.toDomainModel()
            movieResponse.toResponse(movieData).toNetworkResult()
        }
    }

    suspend fun getMovieTokenExpire() : NetworkResult<Movie> {
        return networkHandling {
            val movieResponse = movieDataSource.getMoviesTokenExpire()
            val movieData = movieResponse.data.toDomainModel()
            movieResponse.toResponse(movieData).toNetworkResult()
        }
    }

    suspend fun getMovieFailMessage() : NetworkResult<Movie> {
        return networkHandling {
            val movieResponse = movieDataSource.getMoviesFailMessage()
            val movieData = movieResponse.data.toDomainModel()
            movieResponse.toResponse(movieData).toNetworkResult()
        }
    }

    suspend fun getMovieException() : NetworkResult<Movie> {
        return networkHandling {
            val movieResponse = movieDataSource.getMovieException()
            val movieData = movieResponse.data.toDomainModel()
            movieResponse.toResponse(movieData).toNetworkResult()
        }
    }
}
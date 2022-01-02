package com.example.networkerrohandling.domain.usecase

import com.example.networkerrohandling.data.model.NetworkResult
import com.example.networkerrohandling.data.repository.MovieRepository
import com.example.networkerrohandling.domain.model.Movie
import com.example.networkerrohandling.util.networkHandling
import com.example.networkerrohandling.util.toModel

class MovieExceptionUseCase(private val movieRepository: MovieRepository) {
    suspend operator fun invoke() : NetworkResult<Movie> {
        return networkHandling {
            movieRepository.getMovieException().toModel()
        }
    }
}
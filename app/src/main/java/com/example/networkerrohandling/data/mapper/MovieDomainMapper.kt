package com.example.networkerrohandling.data.mapper

import com.example.networkerrohandling.data.model.MovieDetailResponse
import com.example.networkerrohandling.data.model.MovieResponse
import com.example.networkerrohandling.domain.model.Movie
import com.example.networkerrohandling.domain.model.MovieDetail

fun MovieResponse.toDomainModel() : Movie
    = Movie(movieId,title, description)

fun MovieDetailResponse.toDomainModel() : MovieDetail
        = MovieDetail(author, publisher, rate)
package com.example.networkerrohandling.data.mapper

import com.example.networkerrohandling.data.model.MovieResponse
import com.example.networkerrohandling.domain.model.Movie

fun MovieResponse.toDomainModel() : Movie
    = Movie(title, description)
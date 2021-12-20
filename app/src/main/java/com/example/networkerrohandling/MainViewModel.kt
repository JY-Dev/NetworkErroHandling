package com.example.networkerrohandling

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.networkerrohandling.data.repository.MovieRepository
import com.example.networkerrohandling.domain.model.Movie

class MainViewModel(private val movieRepository: MovieRepository) : BaseViewModel() {
    private val _movie = MutableLiveData<Movie>()
    val movie : LiveData<Movie> = _movie
    fun getMovie() {
        getApiResult({
            movieRepository.getMovie()
        }, success = {
            _movie.value = it
        }, title = "영화 가져오기")
    }

    fun getMovieJwtRefresh() {
        getApiResult({
            movieRepository.getMovieJwtRefresh()
        }, success = {
            _movie.value = it
        }, refresh = {
            getMovieJwtRefresh()
        }, "영화 가져오기")
    }

    fun getMovieTokenExpire() {
        getApiResult({
            movieRepository.getMovieTokenExpire()
        }, success = {
            _movie.value = it
        }, title ="영화 가져오기")
    }

    fun getMovieFailMessage() {
        getApiResult({
            movieRepository.getMovieFailMessage()
        }, success = {
            _movie.value = it
        }, title ="영화 가져오기")
    }

    fun getMovieException() {
        getApiResult({
            movieRepository.getMovieException()
        }, success = {
            _movie.value = it
        }, title ="영화 가져오기")
    }
}
package com.example.networkerrohandling

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.networkerrohandling.data.repository.MovieRepository
import com.example.networkerrohandling.domain.model.Movie
import com.example.networkerrohandling.domain.model.MovieInfo
import com.example.networkerrohandling.domain.usecase.*

class MainViewModel(
    private val movieRepository: MovieRepository,
    private val movieInfoUseCase: MovieInfoUseCase,
    private val movieFailUseCase: MovieFailUseCase,
    private val movieTokenExpireUseCase: MovieTokenExpireUseCase,
    private val movieJwtRefreshUseCase: MovieJwtRefreshUseCase,
    private val movieExceptionUseCase: MovieExceptionUseCase
) : BaseViewModel() {
    private val _movie = MutableLiveData<Movie>()
    val movie: LiveData<Movie> = _movie
    private val _movieInfo = MutableLiveData<MovieInfo>()
    val movieInfo: LiveData<MovieInfo> = _movieInfo
    fun getMovie() {
        getApiResult({
            movieRepository.getMovie()
        }, success = {
            _movie.value = it
        },showLoading = true, title = "영화 가져오기")
    }

    fun getMovieInfo() {
        getApiResult({
            movieInfoUseCase()
        }, success = {
            _movieInfo.value = it
        },showLoading = false, title = "영화 상세 가져오기")
    }

    fun getMovieJwtRefresh() {
        getApiResult({
            movieJwtRefreshUseCase()
        }, success = {
            _movie.value = it
        },showLoading = true, "영화 가져오기")
    }

    fun getMovieTokenExpire() {
        getApiResult({
            movieTokenExpireUseCase()
        }, success = {
            _movie.value = it
        },showLoading = true, title = "영화 가져오기")
    }

    fun getMovieFailMessage() {
        getApiResult({
            movieFailUseCase()
        }, success = {
            _movie.value = it
        },showLoading = true, title = "영화 가져오기")
    }

    fun getMovieException() {
        getApiResult({
            movieExceptionUseCase()
        }, success = {
            _movie.value = it
        },showLoading = true, title = "영화 가져오기")
    }
}
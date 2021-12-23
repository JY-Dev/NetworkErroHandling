package com.example.networkerrohandling

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.networkerrohandling.data.datasource.MovieDataSource
import com.example.networkerrohandling.data.repository.MovieRepository
import com.example.networkerrohandling.domain.usecase.MovieInfoUseCase
import java.lang.IllegalArgumentException

class MainViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(MainViewModel::class.java))
            MainViewModel(MovieRepository(MovieDataSource()),MovieInfoUseCase(MovieRepository(MovieDataSource()))) as T
        else {
            throw IllegalArgumentException()
        }
    }
}
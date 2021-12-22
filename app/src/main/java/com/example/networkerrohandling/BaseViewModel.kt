package com.example.networkerrohandling

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.networkerrohandling.data.model.NetworkResult
import com.example.networkerrohandling.util.SingleLiveEvent
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {
    //Pair<String,String> Title , Message
    private val _errorMessage = MutableLiveData<Pair<String, String>>()
    val errorMessage: LiveData<Pair<String, String>> = _errorMessage
    private val _tokenExpire = SingleLiveEvent<Unit>()
    val tokenExpire: LiveData<Unit> = _tokenExpire

    fun <T> getApiResult(apiResult: suspend () -> NetworkResult<T>, success : (T) -> Unit,title : String) {
        viewModelScope.launch {
            when(val networkResult = apiResult()){
                is NetworkResult.Success -> success(networkResult.data)
                is NetworkResult.TokenExpired -> _tokenExpire.call()
                is NetworkResult.Exception -> {
                    val errorMessage = networkResult.exception.message?:""
                    _errorMessage.value = "예외발생" to errorMessage
                }
                is NetworkResult.Fail -> {
                    _errorMessage.value = title to networkResult.message
                }
            }
        }
    }
}
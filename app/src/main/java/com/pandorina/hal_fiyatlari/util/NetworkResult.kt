package com.pandorina.hal_fiyatlari.util

sealed class NetworkResult<T>(val data: T? = null, val errorMessage: String? = null){
    class Loading<T>: NetworkResult<T>()
    class Success<T>(data: T): NetworkResult<T>(data = data)
    class Error<T>(errorMessage: String?): NetworkResult<T>(errorMessage = errorMessage)
}

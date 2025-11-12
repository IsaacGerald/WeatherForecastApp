package com.senri.weatherforecastapp.common.util

sealed class Resource<T>(val data: T? = null, val message: String? = null){
    class Success<T>(data: T? = null, message: String? = null): Resource<T>(data = data, message = message)
    class Error<T>(data: T? = null, message: String):Resource<T>(data = data, message = message)
    class Loading<T>(data: T?): Resource<T>(data = data)
}

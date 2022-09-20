package com.practice.nyschools.network

sealed class NetworkResult<T>(
    val data : T? = null,
    val message: String? = null
    ) {
    class Success<T>(data: T) : NetworkResult<T>(data)
    class Error<T>(message: String) : NetworkResult<T>(null, message)
}
package com.example.rick_and_morty.domain.module.error_handler

sealed class ApiResult<out T> {
    data class Success<T>(val data: T) : ApiResult<T>()
    data class Error(
        val type: ErrorType,
        val message: String? = null,
        val code: Int
    ) : ApiResult<Nothing>()
}
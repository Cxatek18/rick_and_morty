package com.example.rick_and_morty.domain.module.error_handler

import com.example.rick_and_morty.R
import com.example.rick_and_morty.core.ui.utils.AndroidResourceResolver
import com.example.rick_and_morty.core.ui.utils.Resource

sealed class ApiResult<out T> {
    data class Success<T>(val data: T) : ApiResult<T>()
    data class Error(
        val type: ErrorType,
        val message: String? = null,
        val code: Int
    ) : ApiResult<Nothing>()
}
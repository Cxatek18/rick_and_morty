package com.example.rick_and_morty.data.remote.error_handler

import com.example.rick_and_morty.domain.module.error_handler.ApiResult
import com.example.rick_and_morty.domain.module.error_handler.ErrorType
import retrofit2.Response
import java.io.IOException

suspend fun <T> safeApiCall(
    apiCall: suspend () -> Response<T>
): ApiResult<T> {
    return try {
        val response = apiCall()
        if (response.isSuccessful) {
            response.body()?.let {
                ApiResult.Success(it)
            } ?: ApiResult.Error(
                ErrorType.HTTP,
                "Empty response body",
                ErrorType.HTTP.code
            )
        } else {
            when (response.code()) {
                400 -> {
                    ApiResult.Error(
                        ErrorType.ERROR_CLIENT_400,
                        "Code: ${response.code()}, Message: ${response.message()}",
                        response.code()
                    )
                }

                401 -> {
                    ApiResult.Error(
                        ErrorType.ERROR_CLIENT_401,
                        "Code: ${response.code()}, Message: ${response.message()}",
                        response.code()
                    )
                }

                403 -> {
                    ApiResult.Error(
                        ErrorType.ERROR_CLIENT_403,
                        "Code: ${response.code()}, Message: ${response.message()}",
                        response.code()
                    )
                }

                404 -> {
                    ApiResult.Error(
                        ErrorType.ERROR_CLIENT_404,
                        "Code: ${response.code()}, Message: ${response.message()}",
                        response.code()
                    )
                }

                405 -> {
                    ApiResult.Error(
                        ErrorType.ERROR_CLIENT_405,
                        "Code: ${response.code()}, Message: ${response.message()}",
                        response.code()
                    )
                }

                413 -> {
                    ApiResult.Error(
                        ErrorType.ERROR_CLIENT_413,
                        "Code: ${response.code()}, Message: ${response.message()}",
                        response.code()
                    )
                }

                422 -> {
                    ApiResult.Error(
                        ErrorType.ERROR_CLIENT_422,
                        "Code: ${response.code()}, Message: ${response.message()}",
                        response.code()
                    )
                }


                500 -> {
                    ApiResult.Error(
                        ErrorType.ERROR_SERVER_500,
                        "Code: ${response.code()}, Message: ${response.message()}",
                        response.code()
                    )
                }

                501 -> {
                    ApiResult.Error(
                        ErrorType.ERROR_SERVER_501,
                        "Code: ${response.code()}, Message: ${response.message()}",
                        response.code()
                    )
                }

                502 -> {
                    ApiResult.Error(
                        ErrorType.ERROR_SERVER_502,
                        "Code: ${response.code()}, Message: ${response.message()}",
                        response.code()
                    )
                }

                503 -> {
                    ApiResult.Error(
                        ErrorType.ERROR_SERVER_503,
                        "Code: ${response.code()}, Message: ${response.message()}",
                        response.code()
                    )
                }

                504 -> {
                    ApiResult.Error(
                        ErrorType.ERROR_SERVER_504,
                        "Code: ${response.code()}, Message: ${response.message()}",
                        response.code()
                    )
                }

                505 -> {
                    ApiResult.Error(
                        ErrorType.ERROR_SERVER_505,
                        "Code: ${response.code()}, Message: ${response.message()}",
                        response.code()
                    )
                }

                else -> {
                    ApiResult.Error(
                        ErrorType.UNKNOWN,
                        "Message: error is UNKNOWN",
                        ErrorType.UNKNOWN.code
                    )
                }
            }
        }
    } catch (e: IOException) {
        ApiResult.Error(ErrorType.NETWORK, e.localizedMessage, ErrorType.NETWORK.code)
    } catch (e: Exception) {
        ApiResult.Error(ErrorType.SYSTEM, e.localizedMessage, ErrorType.SYSTEM.code)
    }
}

fun <T, R> ApiResult<T>.mapOnSuccess(transform: (T) -> R): ApiResult<R> {
    return when (this) {
        is ApiResult.Success -> ApiResult.Success(transform(data))
        is ApiResult.Error -> this
    }
}
package com.example.rick_and_morty.domain.module.error_handler

import com.example.rick_and_morty.R
import com.example.rick_and_morty.core.ui.utils.AndroidResourceResolver
import com.example.rick_and_morty.core.ui.utils.Resource

enum class ErrorType(
    val code: Int
) {
    NETWORK(-400),
    HTTP(-401),
    SYSTEM(-402),
    NULL_TYPE(-403),
    UNKNOWN(-1000),

    ERROR_CLIENT_400(400),
    ERROR_CLIENT_401(401),
    ERROR_CLIENT_403(403),
    ERROR_CLIENT_404(404),
    ERROR_CLIENT_405(405),
    ERROR_CLIENT_413(413),
    ERROR_CLIENT_422(422),

    ERROR_SERVER_500(500),
    ERROR_SERVER_501(501),
    ERROR_SERVER_502(502),
    ERROR_SERVER_503(503),
    ERROR_SERVER_504(504),
    ERROR_SERVER_505(505),
}

fun ErrorType.getStringSystemErrorError(resolver: AndroidResourceResolver): String? {
    return when (this) {
        ErrorType.NETWORK -> resolver.resolve(Resource.String(R.string.text_error_network))
        ErrorType.HTTP -> resolver.resolve(Resource.String(R.string.text_error_http))
        ErrorType.SYSTEM -> resolver.resolve(Resource.String(R.string.text_error_system))
        ErrorType.NULL_TYPE -> resolver.resolve(Resource.String(R.string.text_error_null_type))
        ErrorType.UNKNOWN -> {
            String.format(
                resolver.resolve(Resource.String(R.string.text_error_unknown)),
                this.code
            )
        }

        else -> {
            null
        }
    }
}
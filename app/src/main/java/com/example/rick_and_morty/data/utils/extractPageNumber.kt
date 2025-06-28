package com.example.rick_and_morty.data.utils

import android.net.Uri

fun String?.extractPageNumber(): Int? {
    return this?.let { url ->
        Uri.parse(url).getQueryParameter("page")?.toIntOrNull()
    }
}
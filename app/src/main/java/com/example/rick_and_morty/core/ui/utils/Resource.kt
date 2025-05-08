package com.example.rick_and_morty.core.ui.utils

import android.content.Context
import androidx.annotation.ColorRes
import androidx.annotation.StringRes

sealed interface Resource<out T> {

    @JvmInline
    value class String(@StringRes val resId: Int) : Resource<kotlin.String>

    @JvmInline
    value class Color(@ColorRes val resId: Int) : Resource<Int>
}

interface ResourceResolver {

    fun <T: Any> resolve(resource: Resource<T>): T
}

class AndroidResourceResolver(private val context: Context): ResourceResolver {

    @Suppress("UNCHECKED_CAST")
    override fun <T : Any> resolve(resource: Resource<T>): T = when (resource) {
        is Resource.String -> context.getString(resource.resId) as T
        is Resource.Color -> context.getColor(resource.resId) as T
    }
}

@Suppress("UNCHECKED_CAST")
fun <T: Any> Context.resolve(resource: Resource<T>): T = when(resource) {
    is Resource.String -> getString(resource.resId) as T
    is Resource.Color -> getColor(resource.resId) as T
}
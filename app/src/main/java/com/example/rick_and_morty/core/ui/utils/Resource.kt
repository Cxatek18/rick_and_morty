package com.example.rick_and_morty.core.ui.utils

import android.content.Context
import androidx.annotation.StringRes
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

interface IResourceService {

    fun getString(@StringRes resId: Int): String
}

class ResourcesService @Inject constructor(
    @ApplicationContext private val context: Context
) : IResourceService {

    private val resources = context.resources

    override fun getString(resId: Int): String = resources.getString(resId)
}
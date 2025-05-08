package com.example.rick_and_morty.di

import android.content.Context
import com.example.rick_and_morty.core.ui.utils.AndroidResourceResolver
import com.example.rick_and_morty.core.ui.utils.ResourceResolver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class PresentationModule {
    @Provides
    fun provideResourceResolverAndroid(
        @ApplicationContext context: Context
    ): AndroidResourceResolver = AndroidResourceResolver(context)
}
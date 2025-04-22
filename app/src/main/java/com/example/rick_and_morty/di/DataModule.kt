package com.example.rick_and_morty.di

import com.example.rick_and_morty.BuildConfig
import com.example.rick_and_morty.data.remote.network.ApiService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun provideBaseUrl(): String = BuildConfig.API_BASE_URL

    @Provides
    fun provideApiService(
        baseApiUrl: String
    ): ApiService {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
            .followRedirects(false)
            .addInterceptor(httpLoggingInterceptor)
            .build()
        val gson = GsonBuilder()
            .serializeNulls()
            .create()
        val retrofit = Retrofit.Builder()
            .baseUrl(baseApiUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        return retrofit.create(ApiService::class.java)
    }
}
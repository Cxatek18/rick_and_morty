package com.example.rick_and_morty.di

import com.example.rick_and_morty.data.remote.network.ApiService
import com.example.rick_and_morty.data.repository.character.CharacterDetailRepositoryImpl
import com.example.rick_and_morty.data.repository.characters.CharactersRepositoryImpl
import com.example.rick_and_morty.domain.repository.character.ICharacterDetailRepository
import com.example.rick_and_morty.domain.repository.characters.ICharactersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {
    // Repository
    @Provides
    @Singleton
    fun provideCharactersRepository(
        apiService: ApiService
    ): ICharactersRepository {
        return CharactersRepositoryImpl(
            apiService = apiService
        )
    }

    @Provides
    @Singleton
    fun provideCharacterDetailRepository(
        apiService: ApiService
    ): ICharacterDetailRepository {
        return CharacterDetailRepositoryImpl(
            apiService = apiService
        )
    }
    // Repository
}
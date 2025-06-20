package com.example.rick_and_morty.di

import com.example.rick_and_morty.data.repository.character.CharacterDetailRepositoryImpl
import com.example.rick_and_morty.data.repository.characters.CharactersRepositoryImpl
import com.example.rick_and_morty.data.repository.episode.EpisodeRepositoryImpl
import com.example.rick_and_morty.data.repository.episodes.EpisodesRepositoryImpl
import com.example.rick_and_morty.domain.repository.character.ICharacterDetailRepository
import com.example.rick_and_morty.domain.repository.characters.ICharactersRepository
import com.example.rick_and_morty.domain.repository.episode.IEpisodeRepository
import com.example.rick_and_morty.domain.repository.episodes.IEpisodesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DomainModule {
    // Repository
    @Binds
    @Singleton
    fun bindCharactersRepository(
        impl: CharactersRepositoryImpl
    ): ICharactersRepository

    @Binds
    @Singleton
    fun bindCharacterDetailRepository(
        impl: CharacterDetailRepositoryImpl
    ): ICharacterDetailRepository

    @Binds
    @Singleton
    fun bindEpisodesRepository(
        impl: EpisodesRepositoryImpl
    ): IEpisodesRepository

    @Binds
    @Singleton
    fun bindEpisodeRepository(
        impl: EpisodeRepositoryImpl
    ): IEpisodeRepository
    // Repository
}
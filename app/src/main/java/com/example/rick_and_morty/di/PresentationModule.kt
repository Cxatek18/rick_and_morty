package com.example.rick_and_morty.di

import com.example.rick_and_morty.core.ui.utils.IResourceService
import com.example.rick_and_morty.core.ui.utils.ResourcesService
import com.example.rick_and_morty.domain.interactor.character.CharacterDetailManagementInteractor
import com.example.rick_and_morty.domain.interactor.character.ICharacterDetailManagementInteractor
import com.example.rick_and_morty.domain.interactor.characters.CharactersListManagementInteractor
import com.example.rick_and_morty.domain.interactor.characters.ICharactersListManagementInteractor
import com.example.rick_and_morty.domain.interactor.episode.EpisodeDetailInteractor
import com.example.rick_and_morty.domain.interactor.episode.IEpisodeDetailInteractor
import com.example.rick_and_morty.domain.interactor.episodes.EpisodesListManagerInteractor
import com.example.rick_and_morty.domain.interactor.episodes.IEpisodesListManagerInteractor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface PresentationModule {

    @Binds
    fun bindResourceService(impl: ResourcesService): IResourceService

    @Binds
    fun bindCharacterDetailManagementInteractor(
        impl: CharacterDetailManagementInteractor
    ): ICharacterDetailManagementInteractor

    @Binds
    fun bindCharactersListManagementInteractor(
        impl: CharactersListManagementInteractor
    ): ICharactersListManagementInteractor

    @Binds
    fun bindEpisodesListManagementInteractor(
        impl: EpisodesListManagerInteractor
    ): IEpisodesListManagerInteractor

    @Binds
    fun bindEpisodeDetailInteractor(
        impl: EpisodeDetailInteractor
    ): IEpisodeDetailInteractor
}
package com.example.rick_and_morty.di

import com.example.rick_and_morty.core.ui.utils.IResourceService
import com.example.rick_and_morty.core.ui.utils.ResourcesService
import com.example.rick_and_morty.domain.interactor.character.CharacterDetailManagementInteractor
import com.example.rick_and_morty.domain.interactor.character.ICharacterDetailManagementInteractor
import com.example.rick_and_morty.domain.interactor.characters.CharactersListManagementInteractor
import com.example.rick_and_morty.domain.interactor.characters.ICharactersListManagementInteractor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface PresentationModule {

    @Binds
    fun provideResourceService(impl: ResourcesService): IResourceService

    @Binds
    fun provideCharacterDetailManagementInteractor(
        impl: CharacterDetailManagementInteractor
    ): ICharacterDetailManagementInteractor

    @Binds
    fun provideCharactersListManagementInteractor(
        impl: CharactersListManagementInteractor
    ): ICharactersListManagementInteractor
}
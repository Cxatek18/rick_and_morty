package com.example.rick_and_morty.presentation.screens.character_detail.state

import com.example.rick_and_morty.domain.module.character.CharacterDetailResultModel
import com.example.rick_and_morty.domain.module.characters.CharacterInfoModel
import com.example.rick_and_morty.domain.module.characters.CharacterItemModel
import com.example.rick_and_morty.presentation.screens.characters_list.state.CharactersListManagementState

sealed interface CharacterDetailManagementState {

    data object Loading : CharacterDetailManagementState

    data class Success(
        val character: CharacterDetailResultModel
    ) : CharacterDetailManagementState

    data class Error(
        val errorText: String
    ) : CharacterDetailManagementState
}
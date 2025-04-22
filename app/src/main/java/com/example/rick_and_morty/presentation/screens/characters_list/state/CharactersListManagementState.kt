package com.example.rick_and_morty.presentation.screens.characters_list.state

import com.example.rick_and_morty.domain.module.characters.CharacterInfoModel
import com.example.rick_and_morty.domain.module.characters.CharacterItemModel
import com.example.rick_and_morty.domain.module.error_handler.ErrorType

sealed interface CharactersListManagementState {

    data object Loading : CharactersListManagementState

    data class Success(
        val characters: List<CharacterItemModel>,
        val info: CharacterInfoModel
    ) : CharactersListManagementState

    data class Error(
        val errorType: ErrorType
    ) : CharactersListManagementState
}
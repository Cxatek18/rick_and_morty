package com.example.rick_and_morty.presentation.screens.characters_list

import com.example.rick_and_morty.domain.module.characters.CharacterInfo
import com.example.rick_and_morty.domain.module.characters.CharacterItem
import com.example.rick_and_morty.domain.module.characters.ErrorType

sealed interface CharactersListScreenState {

    data object Loading : CharactersListScreenState

    data class Success(
        val characters: List<CharacterItem>,
        val info: CharacterInfo
    ) : CharactersListScreenState

    data class Error(
        val errorType: ErrorType,
    ) : CharactersListScreenState
}
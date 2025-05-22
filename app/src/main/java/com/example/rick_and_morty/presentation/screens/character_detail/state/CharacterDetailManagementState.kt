package com.example.rick_and_morty.presentation.screens.character_detail.state

import com.example.rick_and_morty.domain.module.character.CharacterDetailResultModel

sealed interface CharacterDetailManagementState {

    data object Loading : CharacterDetailManagementState

    data class Success(
        val character: CharacterDetailResultModel,
        val isRefreshing: Boolean = false
    ) : CharacterDetailManagementState

    data class Error(
        val errorText: String,
        val isRefreshing: Boolean = false
    ) : CharacterDetailManagementState
}
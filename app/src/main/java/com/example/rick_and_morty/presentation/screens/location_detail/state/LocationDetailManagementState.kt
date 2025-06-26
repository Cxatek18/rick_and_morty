package com.example.rick_and_morty.presentation.screens.location_detail.state

import com.example.rick_and_morty.domain.module.characters.CharacterItemModel
import com.example.rick_and_morty.domain.module.locations.LocationItemModel

sealed interface LocationDetailManagementState {

    data object Loading : LocationDetailManagementState

    data class Success(
        val location: LocationItemModel,
        val isRefreshing: Boolean = false,
        val characters: List<CharacterItemModel> = emptyList(),
    ) : LocationDetailManagementState

    data class Error(
        val errorText: String,
        val isRefreshing: Boolean = false
    ) : LocationDetailManagementState
}
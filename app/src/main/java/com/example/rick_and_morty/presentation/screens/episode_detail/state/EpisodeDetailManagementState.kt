package com.example.rick_and_morty.presentation.screens.episode_detail.state

import com.example.rick_and_morty.domain.module.characters.CharacterItemModel
import com.example.rick_and_morty.domain.module.episodes.EpisodeItemModel

sealed interface EpisodeDetailManagementState {

    data object Loading : EpisodeDetailManagementState

    data class Success(
        val episode: EpisodeItemModel,
        val isRefreshing: Boolean = false,
        val characters: List<CharacterItemModel> = emptyList(),
    ) : EpisodeDetailManagementState

    data class Error(
        val errorText: String,
        val isRefreshing: Boolean = false
    ) : EpisodeDetailManagementState
}
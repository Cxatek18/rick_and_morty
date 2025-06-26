package com.example.rick_and_morty.presentation.screens.episodes_list.state

import com.example.rick_and_morty.domain.module.episodes.EpisodeItemModel
import com.example.rick_and_morty.domain.module.episodes.InfoModel

sealed interface EpisodesListManagementState {

    data object Loading : EpisodesListManagementState

    data class Success(
        val episodes: List<EpisodeItemModel>,
        val info: InfoModel,
        val textNameSearchEpisode: String? = null,
        val textCodeEpisodeSearch: String? = null,
        val isRefreshing: Boolean = false
    ) : EpisodesListManagementState

    data class Error(
        val errorText: String,
        val isRefreshing: Boolean = false
    ) : EpisodesListManagementState
}
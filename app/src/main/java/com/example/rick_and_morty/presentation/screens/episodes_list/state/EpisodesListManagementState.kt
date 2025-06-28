package com.example.rick_and_morty.presentation.screens.episodes_list.state

import androidx.paging.PagingData
import com.example.rick_and_morty.domain.module.episodes.EpisodeItemModel
import com.example.rick_and_morty.domain.module.episodes.InfoModel
import kotlinx.coroutines.flow.Flow

sealed interface EpisodesListManagementState {

    data object Loading : EpisodesListManagementState

    data class Success(
        val episodes: Flow<PagingData<EpisodeItemModel>>,
        val textNameSearchEpisode: String? = null,
        val textCodeEpisodeSearch: String? = null,
        val isRefreshing: Boolean = false
    ) : EpisodesListManagementState

    data class Error(
        val errorText: String,
        val isRefreshing: Boolean = false
    ) : EpisodesListManagementState
}
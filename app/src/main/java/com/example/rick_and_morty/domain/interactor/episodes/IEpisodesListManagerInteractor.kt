package com.example.rick_and_morty.domain.interactor.episodes

import androidx.paging.PagingData
import com.example.rick_and_morty.domain.module.episodes.EpisodeItemModel
import kotlinx.coroutines.flow.Flow

interface IEpisodesListManagerInteractor {

    fun getAllEpisodes(
        nameEpisode: String? = null,
        episodeCode: String? = null
    ): Flow<PagingData<EpisodeItemModel>>
}
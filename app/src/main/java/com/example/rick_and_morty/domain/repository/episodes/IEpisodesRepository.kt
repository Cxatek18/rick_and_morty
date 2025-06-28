package com.example.rick_and_morty.domain.repository.episodes

import androidx.paging.PagingData
import com.example.rick_and_morty.domain.module.episodes.EpisodeItemModel
import kotlinx.coroutines.flow.Flow

interface IEpisodesRepository {

    fun getAllEpisodes(
        nameEpisode: String? = null,
        episodeCode: String? = null
    ): Flow<PagingData<EpisodeItemModel>>
}

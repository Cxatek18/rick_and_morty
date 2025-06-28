package com.example.rick_and_morty.domain.interactor.episodes

import androidx.paging.PagingData
import com.example.rick_and_morty.domain.module.episodes.EpisodeItemModel
import com.example.rick_and_morty.domain.repository.episodes.IEpisodesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EpisodesListManagerInteractor @Inject constructor(
    private val repository: IEpisodesRepository
) : IEpisodesListManagerInteractor {

    override fun getAllEpisodes(
        nameEpisode: String?,
        episodeCode: String?
    ): Flow<PagingData<EpisodeItemModel>> {
        return repository.getAllEpisodes(
            nameEpisode = nameEpisode,
            episodeCode = episodeCode
        )
    }
}
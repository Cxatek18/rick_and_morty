package com.example.rick_and_morty.domain.interactor.episodes

import com.example.rick_and_morty.domain.module.episodes.EpisodesResultModel
import com.example.rick_and_morty.domain.module.error_handler.ApiResult
import com.example.rick_and_morty.domain.repository.episodes.IEpisodesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EpisodesListManagerInteractor @Inject constructor(
    private val repository: IEpisodesRepository
) : IEpisodesListManagerInteractor {

    override fun getAllEpisodes(
        nameEpisode: String?,
        episodeCode: String?
    ): Flow<ApiResult<EpisodesResultModel>> {
        return repository.getAllEpisodes(
            nameEpisode = nameEpisode,
            episodeCode = episodeCode
        )
    }
}
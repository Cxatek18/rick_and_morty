package com.example.rick_and_morty.domain.repository.episodes

import com.example.rick_and_morty.domain.module.episodes.EpisodesResultModel
import com.example.rick_and_morty.domain.module.error_handler.ApiResult
import kotlinx.coroutines.flow.Flow

interface IEpisodesRepository {

    fun getAllEpisodes(
        nameEpisode: String? = null,
        episodeCode: String? = null
    ): Flow<ApiResult<EpisodesResultModel>>
}
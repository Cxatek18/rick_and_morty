package com.example.rick_and_morty.data.repository.episodes

import com.example.rick_and_morty.data.remote.error_handler.mapOnSuccess
import com.example.rick_and_morty.data.remote.error_handler.safeApiCall
import com.example.rick_and_morty.data.remote.network.ApiService
import com.example.rick_and_morty.domain.module.episodes.EpisodesResultModel
import com.example.rick_and_morty.domain.module.error_handler.ApiResult
import com.example.rick_and_morty.domain.repository.episodes.IEpisodesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class EpisodesRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : IEpisodesRepository {

    override fun getAllEpisodes(
        nameEpisode: String?,
        episodeCode: String?
    ): Flow<ApiResult<EpisodesResultModel>> = flow {
        emit(
            safeApiCall {
                apiService.getListEpisodes(
                    name = nameEpisode,
                    episode = episodeCode,
                )
            }
                .mapOnSuccess { response ->
                    EpisodesResultModel(
                        info = response.info,
                        results = response.results
                    )
                }
        )
    }
}
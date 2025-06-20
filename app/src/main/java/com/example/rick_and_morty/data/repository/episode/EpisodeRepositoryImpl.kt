package com.example.rick_and_morty.data.repository.episode

import com.example.rick_and_morty.data.remote.error_handler.mapOnSuccess
import com.example.rick_and_morty.data.remote.error_handler.safeApiCall
import com.example.rick_and_morty.data.remote.network.ApiService
import com.example.rick_and_morty.domain.module.characters.CharacterItemModel
import com.example.rick_and_morty.domain.module.episodes.EpisodeItemModel
import com.example.rick_and_morty.domain.module.error_handler.ApiResult
import com.example.rick_and_morty.domain.repository.episode.IEpisodeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class EpisodeRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : IEpisodeRepository {

    override fun getDetailEpisode(
        idEpisode: Int
    ): Flow<ApiResult<EpisodeItemModel>> = flow {
        emit(
            safeApiCall { apiService.getEpisodeDetail(idEpisode) }
                .mapOnSuccess { response ->
                    response
                }
        )
    }

    override fun getCharacterInEpisode(listCharacterId: List<Int>): Flow<ApiResult<List<CharacterItemModel>>> =
        flow {
            emit(
                safeApiCall { apiService.getMultipleCharacter(listCharacterId.toString()) }
                    .mapOnSuccess { response ->
                        response
                    }
            )
        }
}
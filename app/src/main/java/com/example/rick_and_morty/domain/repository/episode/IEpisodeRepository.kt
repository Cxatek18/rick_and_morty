package com.example.rick_and_morty.domain.repository.episode

import com.example.rick_and_morty.domain.module.characters.CharacterItemModel
import com.example.rick_and_morty.domain.module.episodes.EpisodeItemModel
import com.example.rick_and_morty.domain.module.error_handler.ApiResult
import kotlinx.coroutines.flow.Flow

interface IEpisodeRepository {

    fun getDetailEpisode(idEpisode: Int): Flow<ApiResult<EpisodeItemModel>>

    fun getCharacterInEpisode(listCharacterId: List<Int>): Flow<ApiResult<List<CharacterItemModel>>>
}
package com.example.rick_and_morty.domain.interactor.episode

import com.example.rick_and_morty.domain.module.characters.CharacterItemModel
import com.example.rick_and_morty.domain.module.episodes.EpisodeItemModel
import com.example.rick_and_morty.domain.module.error_handler.ApiResult
import com.example.rick_and_morty.domain.repository.episode.IEpisodeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EpisodeDetailInteractor @Inject constructor(
    private val repository: IEpisodeRepository
) : IEpisodeDetailInteractor {

    override fun getDetailEpisode(idEpisode: Int): Flow<ApiResult<EpisodeItemModel>> {
        return repository.getDetailEpisode(idEpisode)
    }

    override fun getCharacterInEpisode(listCharacterId: List<Int>): Flow<ApiResult<List<CharacterItemModel>>> {
        return repository.getCharacterInEpisode(listCharacterId)
    }
}
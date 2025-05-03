package com.example.rick_and_morty.domain.repository.character

import com.example.rick_and_morty.domain.module.character.CharacterDetailResultModel
import com.example.rick_and_morty.domain.module.error_handler.ApiResult
import kotlinx.coroutines.flow.Flow

interface ICharacterDetailRepository {

    suspend fun getCharacterDetail(
        characterID: Int
    ): Flow<ApiResult<CharacterDetailResultModel>>
}
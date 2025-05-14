package com.example.rick_and_morty.data.repository.character

import com.example.rick_and_morty.data.remote.error_handler.mapOnSuccess
import com.example.rick_and_morty.data.remote.error_handler.safeApiCall
import com.example.rick_and_morty.data.remote.network.ApiService
import com.example.rick_and_morty.domain.module.character.CharacterDetailResultModel
import com.example.rick_and_morty.domain.module.error_handler.ApiResult
import com.example.rick_and_morty.domain.repository.character.ICharacterDetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CharacterDetailRepositoryImpl @Inject constructor(
    private val apiService: ApiService
): ICharacterDetailRepository {

    override suspend fun getCharacterDetail(
        characterID: Int
    ): Flow<ApiResult<CharacterDetailResultModel>> = flow {
        emit(
            safeApiCall { apiService.getCharacterDetail(characterID) }
                .mapOnSuccess { response ->
                    response
                }
        )
    }
}
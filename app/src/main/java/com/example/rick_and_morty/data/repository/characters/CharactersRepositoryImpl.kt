package com.example.rick_and_morty.data.repository.characters

import com.example.rick_and_morty.data.remote.error_handler.mapOnSuccess
import com.example.rick_and_morty.data.remote.error_handler.safeApiCall
import com.example.rick_and_morty.data.remote.network.ApiService
import com.example.rick_and_morty.domain.module.characters.CharactersResultModel
import com.example.rick_and_morty.domain.module.error_handler.ApiResult
import com.example.rick_and_morty.domain.repository.characters.ICharactersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CharactersRepositoryImpl(
    private val apiService: ApiService
) : ICharactersRepository {

    override fun getListAllCharacters(): Flow<ApiResult<CharactersResultModel>> = flow {
        emit(
            safeApiCall { apiService.getListCharacter() }
                .mapOnSuccess { response ->
                    CharactersResultModel(
                        info = response.info,
                        characterList = response.characterList
                    )
                }
        )
    }
}
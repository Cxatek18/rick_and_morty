package com.example.rick_and_morty.domain.repository.characters

import com.example.rick_and_morty.domain.module.characters.CharactersResultModel
import com.example.rick_and_morty.domain.module.error_handler.ApiResult
import kotlinx.coroutines.flow.Flow

interface ICharactersRepository {

    fun getListAllCharacters(): Flow<ApiResult<CharactersResultModel>>
}
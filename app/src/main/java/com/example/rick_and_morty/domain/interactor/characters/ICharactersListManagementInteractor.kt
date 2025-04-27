package com.example.rick_and_morty.domain.interactor.characters

import com.example.rick_and_morty.domain.module.characters.CharactersResultModel
import com.example.rick_and_morty.domain.module.error_handler.ApiResult
import kotlinx.coroutines.flow.Flow

interface ICharactersListManagementInteractor {

    fun getListAllCharacters(): Flow<ApiResult<CharactersResultModel>>
}
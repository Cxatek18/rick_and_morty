package com.example.rick_and_morty.domain.interactor.character

import com.example.rick_and_morty.domain.module.character.CharacterDetailResultModel
import com.example.rick_and_morty.domain.module.error_handler.ApiResult
import com.example.rick_and_morty.domain.repository.character.ICharacterDetailRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharacterDetailManagementInteractor @Inject constructor(
    private val repository: ICharacterDetailRepository
): ICharacterDetailManagementInteractor {
    override suspend fun getCharacterDetail(
        characterID: Int
    ): Flow<ApiResult<CharacterDetailResultModel>> {
        return repository.getCharacterDetail(
            characterID = characterID
        )
    }
}
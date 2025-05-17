package com.example.rick_and_morty.domain.repository.characters

import com.example.rick_and_morty.domain.module.characters.CharactersResultModel
import com.example.rick_and_morty.domain.module.characters.GenderCharacterFilterModel
import com.example.rick_and_morty.domain.module.characters.SpeciesCharacterFilterModel
import com.example.rick_and_morty.domain.module.characters.StatusCharacterFilterModel
import com.example.rick_and_morty.domain.module.error_handler.ApiResult
import kotlinx.coroutines.flow.Flow

interface ICharactersRepository {

    fun getListAllCharacters(
        nameQuery: String? = null,
        statusQuery: StatusCharacterFilterModel? = null,
        speciesQuery: SpeciesCharacterFilterModel? = null,
        typeQuery: String? = null,
        genderQuery: GenderCharacterFilterModel? = null
    ): Flow<ApiResult<CharactersResultModel>>
}
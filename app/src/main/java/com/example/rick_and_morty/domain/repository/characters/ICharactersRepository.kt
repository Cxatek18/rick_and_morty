package com.example.rick_and_morty.domain.repository.characters

import androidx.paging.PagingData
import com.example.rick_and_morty.domain.module.characters.CharacterItemModel
import com.example.rick_and_morty.domain.module.characters.GenderCharacterFilterModel
import com.example.rick_and_morty.domain.module.characters.SpeciesCharacterFilterModel
import com.example.rick_and_morty.domain.module.characters.StatusCharacterFilterModel
import kotlinx.coroutines.flow.Flow

interface ICharactersRepository {

    fun getCharactersPaging(
        nameQuery: String? = null,
        statusQuery: StatusCharacterFilterModel? = null,
        speciesQuery: SpeciesCharacterFilterModel? = null,
        typeQuery: String? = null,
        genderQuery: GenderCharacterFilterModel? = null
    ): Flow<PagingData<CharacterItemModel>>
}
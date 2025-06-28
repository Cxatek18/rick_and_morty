package com.example.rick_and_morty.domain.interactor.characters

import androidx.paging.PagingData
import com.example.rick_and_morty.domain.module.characters.CharacterItemModel
import com.example.rick_and_morty.domain.module.characters.GenderCharacterFilterModel
import com.example.rick_and_morty.domain.module.characters.SpeciesCharacterFilterModel
import com.example.rick_and_morty.domain.module.characters.StatusCharacterFilterModel
import com.example.rick_and_morty.domain.repository.characters.ICharactersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharactersListManagementInteractor @Inject constructor(
    private val repository: ICharactersRepository
) : ICharactersListManagementInteractor {

    override fun getCharactersPaging(
        nameQuery: String?,
        statusQuery: StatusCharacterFilterModel?,
        speciesQuery: SpeciesCharacterFilterModel?,
        typeQuery: String?,
        genderQuery: GenderCharacterFilterModel?
    ): Flow<PagingData<CharacterItemModel>> {
        return repository.getCharactersPaging(
            nameQuery = nameQuery,
            statusQuery = statusQuery,
            speciesQuery = speciesQuery,
            typeQuery = typeQuery,
            genderQuery = genderQuery
        )
    }
}
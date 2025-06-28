package com.example.rick_and_morty.data.repository.characters

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.rick_and_morty.data.remote.network.ApiService
import com.example.rick_and_morty.domain.module.characters.CharacterItemModel
import com.example.rick_and_morty.domain.module.characters.GenderCharacterFilterModel
import com.example.rick_and_morty.domain.module.characters.SpeciesCharacterFilterModel
import com.example.rick_and_morty.domain.module.characters.StatusCharacterFilterModel
import com.example.rick_and_morty.domain.repository.characters.ICharactersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : ICharactersRepository {

    override fun getCharactersPaging(
        nameQuery: String?,
        statusQuery: StatusCharacterFilterModel?,
        speciesQuery: SpeciesCharacterFilterModel?,
        typeQuery: String?,
        genderQuery: GenderCharacterFilterModel?
    ): Flow<PagingData<CharacterItemModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 5,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                CharactersPagingSource(
                    apiService,
                    nameQuery,
                    statusQuery?.title,
                    speciesQuery?.title,
                    typeQuery,
                    genderQuery?.title
                )
            }
        ).flow
    }
}
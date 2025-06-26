package com.example.rick_and_morty.domain.interactor.location

import com.example.rick_and_morty.domain.module.characters.CharacterItemModel
import com.example.rick_and_morty.domain.module.error_handler.ApiResult
import com.example.rick_and_morty.domain.module.locations.LocationItemModel
import com.example.rick_and_morty.domain.repository.location.ILocationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocationDetailInteractor @Inject constructor(
    private val repository: ILocationRepository
) : ILocationDetailInteractor {

    override fun getDetailLocation(idLocation: Int): Flow<ApiResult<LocationItemModel>> {
        return repository.getDetailLocation(
            idLocation = idLocation
        )
    }

    override fun getCharacterInLocation(listCharacterId: List<Int>): Flow<ApiResult<List<CharacterItemModel>>> {
        return repository.getCharacterInLocation(
            listCharacterId = listCharacterId
        )
    }
}
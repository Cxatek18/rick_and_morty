package com.example.rick_and_morty.domain.interactor.location

import com.example.rick_and_morty.domain.module.characters.CharacterItemModel
import com.example.rick_and_morty.domain.module.error_handler.ApiResult
import com.example.rick_and_morty.domain.module.locations.LocationItemModel
import kotlinx.coroutines.flow.Flow

interface ILocationDetailInteractor {
    fun getDetailLocation(
        idLocation: Int
    ): Flow<ApiResult<LocationItemModel>>

    fun getCharacterInLocation(
        listCharacterId: List<Int>
    ): Flow<ApiResult<List<CharacterItemModel>>>
}
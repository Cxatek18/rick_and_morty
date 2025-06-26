package com.example.rick_and_morty.data.repository.location

import com.example.rick_and_morty.data.remote.error_handler.mapOnSuccess
import com.example.rick_and_morty.data.remote.error_handler.safeApiCall
import com.example.rick_and_morty.data.remote.network.ApiService
import com.example.rick_and_morty.domain.module.characters.CharacterItemModel
import com.example.rick_and_morty.domain.module.error_handler.ApiResult
import com.example.rick_and_morty.domain.module.locations.LocationItemModel
import com.example.rick_and_morty.domain.repository.location.ILocationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : ILocationRepository {
    override fun getDetailLocation(idLocation: Int): Flow<ApiResult<LocationItemModel>> = flow {
        emit(
            safeApiCall { apiService.getLocationDetail(idLocation) }
                .mapOnSuccess { response ->
                    response
                }
        )
    }

    override fun getCharacterInLocation(listCharacterId: List<Int>): Flow<ApiResult<List<CharacterItemModel>>> =
        flow {
            emit(
                safeApiCall { apiService.getMultipleCharacter(listCharacterId.toString()) }
                    .mapOnSuccess { response ->
                        response
                    }
            )
        }
}
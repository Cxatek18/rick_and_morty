package com.example.rick_and_morty.data.repository.locations

import com.example.rick_and_morty.data.remote.error_handler.mapOnSuccess
import com.example.rick_and_morty.data.remote.error_handler.safeApiCall
import com.example.rick_and_morty.data.remote.network.ApiService
import com.example.rick_and_morty.domain.module.error_handler.ApiResult
import com.example.rick_and_morty.domain.module.locations.LocationsResultModel
import com.example.rick_and_morty.domain.repository.locations.ILocationsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LocationsRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : ILocationsRepository {

    override fun getAllLocations(
        nameLocation: String?,
        typeLocation: String?,
        dimensionLocation: String?
    ): Flow<ApiResult<LocationsResultModel>> = flow {
        emit(
            safeApiCall {
                apiService.getListLocations(
                    name = nameLocation,
                    type = typeLocation,
                    dimension = dimensionLocation
                )
            }
                .mapOnSuccess { response ->
                    LocationsResultModel(
                        info = response.info,
                        results = response.results
                    )
                }
        )
    }
}
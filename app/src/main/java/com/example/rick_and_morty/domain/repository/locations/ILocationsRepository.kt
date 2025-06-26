package com.example.rick_and_morty.domain.repository.locations

import com.example.rick_and_morty.domain.module.error_handler.ApiResult
import com.example.rick_and_morty.domain.module.locations.LocationsResultModel
import kotlinx.coroutines.flow.Flow

interface ILocationsRepository {

    fun getAllLocations(
        nameLocation: String? = null,
        typeLocation: String? = null,
        dimensionLocation: String? = null
    ): Flow<ApiResult<LocationsResultModel>>
}
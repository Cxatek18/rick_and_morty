package com.example.rick_and_morty.domain.interactor.locations

import androidx.paging.PagingData
import com.example.rick_and_morty.domain.module.locations.LocationItemModel
import kotlinx.coroutines.flow.Flow

interface ILocationsListManagerInteractor {

    fun getAllLocations(
        nameLocation: String? = null,
        typeLocation: String? = null,
        dimensionLocation: String? = null
    ): Flow<PagingData<LocationItemModel>>
}
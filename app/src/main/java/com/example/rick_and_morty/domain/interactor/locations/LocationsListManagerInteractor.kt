package com.example.rick_and_morty.domain.interactor.locations

import androidx.paging.PagingData
import com.example.rick_and_morty.domain.module.locations.LocationItemModel
import com.example.rick_and_morty.domain.repository.locations.ILocationsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocationsListManagerInteractor @Inject constructor(
    private val repository: ILocationsRepository
) : ILocationsListManagerInteractor {
    override fun getAllLocations(
        nameLocation: String?,
        typeLocation: String?,
        dimensionLocation: String?
    ): Flow<PagingData<LocationItemModel>> {
        return repository.getAllLocations(
            nameLocation = nameLocation,
            typeLocation = typeLocation,
            dimensionLocation = dimensionLocation
        )
    }
}
package com.example.rick_and_morty.data.repository.locations

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.rick_and_morty.data.remote.network.ApiService
import com.example.rick_and_morty.domain.module.locations.LocationItemModel
import com.example.rick_and_morty.domain.repository.locations.ILocationsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocationsRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : ILocationsRepository {

    override fun getAllLocations(
        nameLocation: String?,
        typeLocation: String?,
        dimensionLocation: String?
    ): Flow<PagingData<LocationItemModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 5,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                LocationDataSource(
                    apiService,
                    nameLocation,
                    typeLocation,
                    dimensionLocation
                )
            }
        ).flow
    }
}
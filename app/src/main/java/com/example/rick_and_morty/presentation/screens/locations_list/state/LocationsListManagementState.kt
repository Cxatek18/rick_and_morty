package com.example.rick_and_morty.presentation.screens.locations_list.state

import androidx.paging.PagingData
import com.example.rick_and_morty.domain.module.locations.LocationItemModel
import kotlinx.coroutines.flow.Flow

sealed interface LocationsListManagementState {

    data object Loading : LocationsListManagementState

    data class Success(
        val locations: Flow<PagingData<LocationItemModel>>,
        val textNameSearchLocation: String? = null,
        val textTypeLocationSearch: String? = null,
        val textDimensionLocationSearch: String? = null,
        val isRefreshing: Boolean = false
    ) : LocationsListManagementState

    data class Error(
        val errorText: String,
        val isRefreshing: Boolean = false
    ) : LocationsListManagementState
}
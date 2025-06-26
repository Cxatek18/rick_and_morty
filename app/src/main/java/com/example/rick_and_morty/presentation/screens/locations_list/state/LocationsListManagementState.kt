package com.example.rick_and_morty.presentation.screens.locations_list.state

import com.example.rick_and_morty.domain.module.episodes.InfoModel
import com.example.rick_and_morty.domain.module.locations.LocationItemModel

sealed interface LocationsListManagementState {

    data object Loading : LocationsListManagementState

    data class Success(
        val locations: List<LocationItemModel>,
        val info: InfoModel,
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
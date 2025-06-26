package com.example.rick_and_morty.presentation.screens.locations_list.navigation

import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.rick_and_morty.R
import com.example.rick_and_morty.presentation.screens.location_detail.navigation.locationDetail
import com.example.rick_and_morty.presentation.screens.locations_list.LocationsListManagement
import com.example.rick_and_morty.presentation.screens.locations_list.view_model.LocationsListManagementViewModel
import kotlinx.serialization.Serializable

@Serializable
object LocationsGraph

@Serializable
object LocationsListManagementDestination

fun NavGraphBuilder.listLocationsGraph(
    modifier: Modifier = Modifier,
    changeNameTopBar: (nameTopBar: String) -> Unit,
    changeIsVisibleBackIcon: (isVisible: Boolean) -> Unit,
    navigateToDetail: (Int) -> Unit
) {
    navigation<LocationsGraph>(startDestination = LocationsListManagementDestination) {
        locationsList(
            modifier = modifier,
            changeNameTopBar = {
                changeNameTopBar(it)
            },
            navigateToDetail = {
                navigateToDetail(it)
            },
            changeIsVisibleBackIcon = {
                changeIsVisibleBackIcon(it)
            }
        )
        locationDetail(
            modifier = modifier,
            changeNameTopBar = {
                changeNameTopBar(it)
            },
            changeIsVisibleBackIcon = {
                changeIsVisibleBackIcon(it)
            }
        )
    }
}

fun NavGraphBuilder.locationsList(
    modifier: Modifier = Modifier,
    changeNameTopBar: (nameTopBar: String) -> Unit,
    changeIsVisibleBackIcon: (isVisible: Boolean) -> Unit,
    navigateToDetail: (Int) -> Unit
) {
    composable<LocationsListManagementDestination> {
        val viewModel: LocationsListManagementViewModel = hiltViewModel()
        val state by viewModel.state.collectAsStateWithLifecycle()
        LocationsListManagement(
            modifier = modifier,
            state = state,
            viewModel = viewModel,
            onClickNavigationToLocationDetail = {
                navigateToDetail(it)
            }
        )
        changeNameTopBar(stringResource(R.string.text_top_bar_list_locations))
        changeIsVisibleBackIcon(false)
    }
}
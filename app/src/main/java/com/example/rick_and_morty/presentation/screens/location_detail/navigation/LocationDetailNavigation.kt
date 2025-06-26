package com.example.rick_and_morty.presentation.screens.location_detail.navigation

import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.rick_and_morty.R
import com.example.rick_and_morty.presentation.screens.location_detail.LocationDetailManagement
import com.example.rick_and_morty.presentation.screens.location_detail.view_model.LocationDetailManagementViewModel
import kotlinx.serialization.Serializable

@Serializable
data class LocationDetailDestination(
    val locationId: Int
)

fun NavHostController.navigateToDetailLocation(locationId: Int) {
    navigate(LocationDetailDestination(locationId = locationId))
}

fun NavGraphBuilder.locationDetail(
    modifier: Modifier = Modifier,
    changeNameTopBar: (nameTopBar: String) -> Unit,
    changeIsVisibleBackIcon: (isVisible: Boolean) -> Unit,
) {
    composable<LocationDetailDestination> { backStackEntry ->
        val args = backStackEntry.toRoute<LocationDetailDestination>()
        val viewModel: LocationDetailManagementViewModel = hiltViewModel()
        val state by viewModel.state.collectAsStateWithLifecycle()
        LocationDetailManagement(
            modifier = modifier,
            state = state,
            viewModel = viewModel,
            locationId = args.locationId,
        )
        changeNameTopBar(stringResource(R.string.text_top_bar_detail_location))
        changeIsVisibleBackIcon(true)
    }
}
package com.example.rick_and_morty.presentation.screens.location_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.example.rick_and_morty.core.ui.components.characters.ErrorScreen
import com.example.rick_and_morty.core.ui.components.characters.LoadingScreen
import com.example.rick_and_morty.presentation.screens.location_detail.state.LocationDetailManagementState
import com.example.rick_and_morty.presentation.screens.location_detail.view_model.LocationDetailManagementViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationDetailManagement(
    modifier: Modifier = Modifier,
    state: LocationDetailManagementState,
    viewModel: LocationDetailManagementViewModel,
    locationId: Int
) {

    LaunchedEffect(Unit) {
        viewModel.getLocationDetail(locationId)
    }

    val refreshState = rememberPullToRefreshState()

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primary)
    ) {
        when (state) {
            is LocationDetailManagementState.Error -> {
                PullToRefreshBox(
                    state = refreshState,
                    isRefreshing = state.isRefreshing,
                    onRefresh = {
                        viewModel.pullToRefresh(locationId)
                    }
                ) {
                    ErrorScreen(
                        errorText = state.errorText
                    )
                }
            }

            LocationDetailManagementState.Loading -> {
                LoadingScreen()
            }

            is LocationDetailManagementState.Success -> {
                PullToRefreshBox(
                    state = refreshState,
                    isRefreshing = state.isRefreshing,
                    onRefresh = {
                        viewModel.pullToRefresh(locationId)
                    }
                ) {
                    LocationDetailManagementSuccess(
                        location = state.location,
                        listCharacterInLocation = state.characters
                    )
                }
            }
        }
    }

}
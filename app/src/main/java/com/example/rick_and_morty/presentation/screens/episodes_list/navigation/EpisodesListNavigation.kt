package com.example.rick_and_morty.presentation.screens.episodes_list.navigation

import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.rick_and_morty.R
import com.example.rick_and_morty.presentation.screens.episodes_list.EpisodesListManagement
import com.example.rick_and_morty.presentation.screens.episodes_list.view_model.EpisodesListManagementViewModel
import kotlinx.serialization.Serializable

@Serializable
object EpisodesGraph

@Serializable
object EpisodesListManagementDestination

fun NavGraphBuilder.listEpisodesGraph(
    modifier: Modifier = Modifier,
    changeNameTopBar: (nameTopBar: String) -> Unit,
    changeIsVisibleBackIcon: (isVisible: Boolean) -> Unit,
    navigateToDetail: (Int) -> Unit
) {
    navigation<EpisodesGraph>(startDestination = EpisodesListManagementDestination) {
        episodesList(
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
    }
}

fun NavGraphBuilder.episodesList(
    modifier: Modifier = Modifier,
    changeNameTopBar: (nameTopBar: String) -> Unit,
    changeIsVisibleBackIcon: (isVisible: Boolean) -> Unit,
    navigateToDetail: (Int) -> Unit
) {
    composable<EpisodesListManagementDestination> {
        val viewModel: EpisodesListManagementViewModel = hiltViewModel()
        val state by viewModel.state.collectAsStateWithLifecycle()
        EpisodesListManagement(
            modifier = modifier,
            state = state,
            viewModel = viewModel
        )
        changeNameTopBar(stringResource(R.string.text_top_bar_list_episodes))
        changeIsVisibleBackIcon(false)
    }
}
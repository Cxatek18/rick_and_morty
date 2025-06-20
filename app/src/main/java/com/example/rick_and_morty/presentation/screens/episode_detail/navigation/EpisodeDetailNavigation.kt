package com.example.rick_and_morty.presentation.screens.episode_detail.navigation

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
import com.example.rick_and_morty.presentation.screens.episode_detail.EpisodeDetailManagement
import com.example.rick_and_morty.presentation.screens.episode_detail.view_model.EpisodeDetailManagementViewModel
import kotlinx.serialization.Serializable

@Serializable
data class EpisodeDetailDestination(
    val episodeId: Int
)

fun NavHostController.navigateToDetailEpisode(episodeId: Int) {
    navigate(EpisodeDetailDestination(episodeId = episodeId))
}

fun NavGraphBuilder.episodeDetail(
    modifier: Modifier = Modifier,
    changeNameTopBar: (nameTopBar: String) -> Unit,
    changeIsVisibleBackIcon: (isVisible: Boolean) -> Unit,
) {
    composable<EpisodeDetailDestination> { backStackEntry ->
        val args = backStackEntry.toRoute<EpisodeDetailDestination>()
        val viewModel: EpisodeDetailManagementViewModel = hiltViewModel()
        val state by viewModel.state.collectAsStateWithLifecycle()
        EpisodeDetailManagement(
            modifier = modifier,
            state = state,
            viewModel = viewModel,
            episodeId = args.episodeId,
        )
        changeNameTopBar(stringResource(R.string.text_top_bar_detail_episode))
        changeIsVisibleBackIcon(true)
    }
}
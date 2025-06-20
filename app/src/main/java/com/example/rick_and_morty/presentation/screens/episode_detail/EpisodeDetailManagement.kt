package com.example.rick_and_morty.presentation.screens.episode_detail

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
import com.example.rick_and_morty.presentation.screens.episode_detail.state.EpisodeDetailManagementState
import com.example.rick_and_morty.presentation.screens.episode_detail.view_model.EpisodeDetailManagementViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EpisodeDetailManagement(
    modifier: Modifier = Modifier,
    state: EpisodeDetailManagementState,
    viewModel: EpisodeDetailManagementViewModel,
    episodeId: Int
) {
    LaunchedEffect(Unit) {
        viewModel.getEpisodeDetail(episodeId)
    }

    val refreshState = rememberPullToRefreshState()

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primary)
    ) {
        when (state) {
            is EpisodeDetailManagementState.Error -> {
                PullToRefreshBox(
                    state = refreshState,
                    isRefreshing = state.isRefreshing,
                    onRefresh = {
                        viewModel.pullToRefresh(episodeId)
                    }
                ) {
                    ErrorScreen(
                        errorText = state.errorText
                    )
                }
            }

            EpisodeDetailManagementState.Loading -> {
                LoadingScreen()
            }

            is EpisodeDetailManagementState.Success -> {
                PullToRefreshBox(
                    state = refreshState,
                    isRefreshing = state.isRefreshing,
                    onRefresh = {
                        viewModel.pullToRefresh(episodeId)
                    }
                ) {
                    EpisodeDetailManagementSuccess(
                        episode = state.episode,
                        listCharacterInEpisode = state.characters
                    )
                }
            }
        }
    }
}
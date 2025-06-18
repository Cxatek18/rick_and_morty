package com.example.rick_and_morty.presentation.screens.character_detail

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
import com.example.rick_and_morty.presentation.screens.character_detail.state.CharacterDetailManagementState
import com.example.rick_and_morty.presentation.screens.character_detail.view_model.CharacterDetailManagementViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailManagement(
    modifier: Modifier = Modifier,
    state: CharacterDetailManagementState,
    viewModel: CharacterDetailManagementViewModel,
    characterId: Int,
    onClickBtnViewAllEpisodes: () -> Unit
) {

    LaunchedEffect(Unit) {
        viewModel.getCharacterDetail(characterId)
    }

    val refreshState = rememberPullToRefreshState()

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primary)
    ) {
        when (state) {
            is CharacterDetailManagementState.Error -> {
                PullToRefreshBox(
                    state = refreshState,
                    isRefreshing = state.isRefreshing,
                    onRefresh = {
                        viewModel.pullToRefresh(characterId)
                    }
                ) {
                    ErrorScreen(
                        errorText = state.errorText
                    )
                }
            }

            CharacterDetailManagementState.Loading -> {
                LoadingScreen()
            }

            is CharacterDetailManagementState.Success -> {
                PullToRefreshBox(
                    state = refreshState,
                    isRefreshing = state.isRefreshing,
                    onRefresh = {
                        viewModel.pullToRefresh(characterId)
                    }
                ) {
                    CharacterDetailManagementSuccessScreen(
                        character = state.character,
                        onClickBtnViewAllEpisodes = onClickBtnViewAllEpisodes
                    )
                }
            }
        }
    }
}
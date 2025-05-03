package com.example.rick_and_morty.presentation.screens.character_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.example.rick_and_morty.core.ui.components.characters.CharactersErrorScreen
import com.example.rick_and_morty.core.ui.components.characters.CharactersLoadingScreen
import com.example.rick_and_morty.presentation.screens.character_detail.state.CharacterDetailManagementState
import com.example.rick_and_morty.presentation.screens.character_detail.view_model.CharacterDetailManagementViewModel
import kotlinx.coroutines.launch

@Composable
fun CharacterDetailManagement(
    modifier: Modifier = Modifier,
    state: CharacterDetailManagementState,
    viewModel: CharacterDetailManagementViewModel,
    characterId: Int,
    onClickBtnViewAllEpisodes: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            viewModel.getCharacterDetail(characterId)
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primary)
    ) {
        when(state) {
            is CharacterDetailManagementState.Error -> {
                CharactersErrorScreen(
                    errorText = state.errorText
                )
            }
            CharacterDetailManagementState.Loading -> {
                CharactersLoadingScreen()
            }
            is CharacterDetailManagementState.Success -> {
                CharacterDetailManagementSuccessScreen(
                    character = state.character,
                    onClickBtnViewAllEpisodes = onClickBtnViewAllEpisodes
                )
            }
        }
    }
}
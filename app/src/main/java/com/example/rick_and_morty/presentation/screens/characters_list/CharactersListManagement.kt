package com.example.rick_and_morty.presentation.screens.characters_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.rick_and_morty.core.ui.components.characters.CharactersErrorScreen
import com.example.rick_and_morty.core.ui.components.characters.CharactersLoadingScreen
import com.example.rick_and_morty.core.ui.theme.Rick_and_mortyTheme
import com.example.rick_and_morty.presentation.screens.characters_list.state.CharactersListManagementState
import com.example.rick_and_morty.presentation.screens.characters_list.view_model.CharactersListManagementViewModel

@Composable
fun CharactersListManagement(
    modifier: Modifier = Modifier,
    state: CharactersListManagementState,
    viewModel: CharactersListManagementViewModel,
    onClickCharacter: (characterID: Int) -> Unit
) {

    LaunchedEffect(Unit) {
        viewModel.getListAllCharacters()
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primary)
    ) {
        when (state) {
            is CharactersListManagementState.Error -> {
                CharactersErrorScreen(
                    errorText = state.errorText
                )
            }

            CharactersListManagementState.Loading -> {
                CharactersLoadingScreen()
            }

            is CharactersListManagementState.Success -> {
                CharactersListManagementSuccessScreen(
                    characters = state.characters,
                    onClickCharacter = {
                        onClickCharacter(it)
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PreviewCharactersListScreen() {
    Rick_and_mortyTheme {

    }
}
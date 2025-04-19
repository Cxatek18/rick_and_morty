package com.example.rick_and_morty.presentation.screens.characters_list.navigation

import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.rick_and_morty.R
import com.example.rick_and_morty.presentation.screens.characters_list.CharactersListScreen
import com.example.rick_and_morty.presentation.screens.characters_list.CharactersListScreenViewModel
import kotlinx.serialization.Serializable

@Serializable
object HomeGraph

@Serializable
object CharacterListDestination

fun NavGraphBuilder.homeGraph(
    modifier: Modifier,
    changeNameTopBar: (nameTopBar: String) -> Unit
) {
    navigation<HomeGraph>(startDestination = CharacterListDestination) {
        composable<CharacterListDestination> {
            val viewModel: CharactersListScreenViewModel = hiltViewModel()
            val state by viewModel.state.collectAsStateWithLifecycle()
            CharactersListScreen(
                modifier = modifier,
                state = state,
                viewModel = viewModel
            )
            changeNameTopBar(stringResource(R.string.text_top_bar_home))
        }
    }
}

fun NavHostController.navigateToListCharacter() {
    navigate(CharacterListDestination)
}
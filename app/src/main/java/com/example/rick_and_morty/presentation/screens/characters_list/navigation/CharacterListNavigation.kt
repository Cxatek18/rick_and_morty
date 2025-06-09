package com.example.rick_and_morty.presentation.screens.characters_list.navigation

import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.rick_and_morty.R
import com.example.rick_and_morty.presentation.screens.character_detail.navigation.characterDetail
import com.example.rick_and_morty.presentation.screens.characters_list.CharactersListManagement
import com.example.rick_and_morty.presentation.screens.characters_list.view_model.CharactersListManagementViewModel
import kotlinx.serialization.Serializable

@Serializable
object HomeGraph

@Serializable
object CharactersListManagementDestination

fun NavGraphBuilder.homeGraph(
    modifier: Modifier = Modifier,
    changeNameTopBar: (nameTopBar: String) -> Unit,
    changeIsVisibleBackIcon: (isVisible: Boolean) -> Unit,
    navigateToDetail: (Int) -> Unit
) {
    navigation<HomeGraph>(startDestination = CharactersListManagementDestination) {
        characterList(
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
        characterDetail(
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

fun NavGraphBuilder.characterList(
    modifier: Modifier = Modifier,
    changeNameTopBar: (nameTopBar: String) -> Unit,
    changeIsVisibleBackIcon: (isVisible: Boolean) -> Unit,
    navigateToDetail: (Int) -> Unit
) {
    composable<CharactersListManagementDestination> {
        val viewModel: CharactersListManagementViewModel = hiltViewModel()
        val state by viewModel.state.collectAsStateWithLifecycle()
        CharactersListManagement(
            modifier = modifier,
            state = state,
            viewModel = viewModel,
            onClickCharacter = {
                navigateToDetail(it)
            }
        )
        changeNameTopBar(stringResource(R.string.text_top_bar_home))
        changeIsVisibleBackIcon(false)
    }
}
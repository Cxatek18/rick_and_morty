package com.example.rick_and_morty.presentation.screens.character_detail.navigation

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
import com.example.rick_and_morty.presentation.screens.character_detail.CharacterDetailManagement
import com.example.rick_and_morty.presentation.screens.character_detail.view_model.CharacterDetailManagementViewModel
import kotlinx.serialization.Serializable

@Serializable
data class CharacterDetailDestination(
    val characterID: Int
)

fun NavHostController.navigateToDetailCharacter(characterID: Int) {
    navigate(CharacterDetailDestination(characterID = characterID))
}

fun NavGraphBuilder.characterDetail(
    modifier: Modifier = Modifier,
    changeNameTopBar: (nameTopBar: String) -> Unit,
    changeIsVisibleBackIcon: (isVisible: Boolean) -> Unit,
) {
    composable<CharacterDetailDestination> { backStackEntry ->
        val args = backStackEntry.toRoute<CharacterDetailDestination>()
        val viewModel: CharacterDetailManagementViewModel = hiltViewModel()
        val state by viewModel.state.collectAsStateWithLifecycle()
        CharacterDetailManagement(
            modifier = modifier,
            state = state,
            viewModel = viewModel,
            characterId = args.characterID,
            onClickBtnViewAllEpisodes = {}
        )
        changeNameTopBar(stringResource(R.string.text_top_bar_detail_character))
        changeIsVisibleBackIcon(true)
    }
}
package com.example.rick_and_morty.presentation.screens.characters_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.rick_and_morty.R
import com.example.rick_and_morty.core.ui.components.characters.CharactersErrorScreen
import com.example.rick_and_morty.core.ui.components.characters.CharactersLoadingScreen
import com.example.rick_and_morty.core.ui.theme.Rick_and_mortyTheme
import com.example.rick_and_morty.core.ui.theme.font_size_12
import com.example.rick_and_morty.core.ui.theme.line_height_20
import com.example.rick_and_morty.core.ui.theme.padding_10
import com.example.rick_and_morty.core.ui.widgets.CustomEditText
import com.example.rick_and_morty.core.ui.widgets.FilterStringItem
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
                Column (
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = padding_10),
                    verticalArrangement = Arrangement.spacedBy(padding_10),
                ) {
                    Text(
                        text = stringResource(R.string.text_search_to_name),
                        color = MaterialTheme.colorScheme.secondary,
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.W500,
                        fontSize = font_size_12,
                        lineHeight = line_height_20
                    )

                    CustomEditText(
                        modifier = Modifier.fillMaxWidth(),
                        value = state.textNameSearchCharacterFilter ?: "",
                        onChange = viewModel::onTextNameSearchChange,
                        placeholder = stringResource(R.string.placeholder_et_search_character)
                    )

                    Text(
                        text = stringResource(R.string.text_search_to_type),
                        color = MaterialTheme.colorScheme.secondary,
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.W500,
                        fontSize = font_size_12,
                        lineHeight = line_height_20
                    )

                    CustomEditText(
                        modifier = Modifier.fillMaxWidth(),
                        value = state.textTypeCharacterFilter ?: "",
                        onChange = viewModel::onTextTypeFilterChange,
                        placeholder = stringResource(R.string.placeholder_et_search_type)
                    )

                    Text(
                        text = stringResource(R.string.text_filter_status),
                        color = MaterialTheme.colorScheme.secondary,
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.W500,
                        fontSize = font_size_12,
                        lineHeight = line_height_20
                    )

                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(space = padding_10)
                    ) {
                        items(state.listStatusCharacterFilter) { filter ->
                            FilterStringItem(
                                filterTitle = filter.title,
                                isActiveFilter = filter.isActive,
                                onClickToFilter = {
                                    viewModel.onActiveStatusFilterChange(filter)
                                }
                            )
                        }
                    }

                    Text(
                        text = stringResource(R.string.text_species_status),
                        color = MaterialTheme.colorScheme.secondary,
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.W500,
                        fontSize = font_size_12,
                        lineHeight = line_height_20
                    )

                    LazyRow(
                        modifier = Modifier.fillMaxWidth(), // Отступ сверху здесь не нужен
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(space = padding_10)
                    ) {
                        items(state.listSpeciesCharacterFilter) { filter ->
                            FilterStringItem(
                                filterTitle = filter.title,
                                isActiveFilter = filter.isActive,
                                onClickToFilter = {
                                    viewModel.onActiveSpeciesFilterChange(filter)
                                }
                            )
                        }
                    }

                    Text(
                        text = stringResource(R.string.text_gender_status),
                        color = MaterialTheme.colorScheme.secondary,
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.W500,
                        fontSize = font_size_12,
                        lineHeight = line_height_20
                    )

                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(space = padding_10)
                    ) {
                        items(state.listGenderCharacterFilter) { filter ->
                            FilterStringItem(
                                filterTitle = filter.title,
                                isActiveFilter = filter.isActive,
                                onClickToFilter = {
                                    viewModel.onActiveGenderFilterChange(filter)
                                }
                            )
                        }
                    }

                    CharactersListManagementSuccessScreen(
                        modifier = Modifier.padding(top = padding_10),
                        characters = state.characters,
                        onClickCharacter = {
                            onClickCharacter(it)
                        }
                    )

                }
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
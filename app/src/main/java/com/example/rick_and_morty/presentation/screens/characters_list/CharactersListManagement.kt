package com.example.rick_and_morty.presentation.screens.characters_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.rick_and_morty.R
import com.example.rick_and_morty.core.ui.components.characters.ErrorScreen
import com.example.rick_and_morty.core.ui.components.characters.LoadingScreen
import com.example.rick_and_morty.core.ui.theme.font_size_12
import com.example.rick_and_morty.core.ui.theme.line_height_20
import com.example.rick_and_morty.core.ui.theme.padding_10
import com.example.rick_and_morty.core.ui.theme.padding_20
import com.example.rick_and_morty.core.ui.theme.padding_7
import com.example.rick_and_morty.core.ui.widgets.CustomEditText
import com.example.rick_and_morty.core.ui.widgets.FilterStringItem
import com.example.rick_and_morty.core.ui.widgets.ModalDrawerCharactersFilters
import com.example.rick_and_morty.presentation.screens.characters_list.state.CharactersListManagementState
import com.example.rick_and_morty.presentation.screens.characters_list.view_model.CharactersListManagementViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
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

    val refreshState = rememberPullToRefreshState()

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primary)
    ) {
        when (state) {
            is CharactersListManagementState.Error -> {
                PullToRefreshBox(
                    state = refreshState,
                    isRefreshing = state.isRefreshing,
                    onRefresh = {
                        viewModel.pullToRefresh()
                    }
                ) {
                    ErrorScreen(
                        errorText = state.errorText
                    )
                }
            }

            CharactersListManagementState.Loading -> {
                LoadingScreen()
            }

            is CharactersListManagementState.Success -> {
                val drawerState = rememberDrawerState(DrawerValue.Closed)
                val scopeDrawer = rememberCoroutineScope()
                PullToRefreshBox(
                    state = refreshState,
                    isRefreshing = state.isRefreshing,
                    onRefresh = {
                        viewModel.pullToRefresh()
                    }
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(padding_10),
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(),
                            contentAlignment = Alignment.TopEnd
                        ) {
                            Button(
                                onClick = {
                                    drawerState.isOpen
                                    scopeDrawer.launch {
                                        if (drawerState.isOpen) {
                                            drawerState.close()
                                        } else {
                                            drawerState.open()
                                        }
                                    }
                                }
                            ) {
                                val iconDrawer = if (drawerState.isOpen) {
                                    Icons.Default.Close
                                } else {
                                    Icons.Default.Menu
                                }
                                Icon(
                                    imageVector = iconDrawer,
                                    contentDescription = stringResource(R.string.text_content_desc_icon_menu),
                                    tint = MaterialTheme.colorScheme.tertiary
                                )
                            }
                        }

                        ModalDrawerCharactersFilters(
                            drawerState = drawerState,
                            contentInDrawer = {
                                Column(
                                    modifier = Modifier
                                        .verticalScroll(rememberScrollState())
                                        .padding(horizontal = padding_20),
                                ) {
                                    Text(
                                        modifier = Modifier,
                                        text = stringResource(R.string.text_search_to_type),
                                        color = MaterialTheme.colorScheme.secondary,
                                        fontFamily = FontFamily.Monospace,
                                        fontWeight = FontWeight.W500,
                                        fontSize = font_size_12,
                                        lineHeight = line_height_20
                                    )

                                    Spacer(modifier = Modifier.height(height = padding_7))

                                    CustomEditText(
                                        modifier = Modifier.fillMaxWidth(),
                                        value = state.textTypeCharacterFilter ?: "",
                                        onChange = viewModel::onTextTypeFilterChange,
                                        placeholder = stringResource(R.string.placeholder_et_search_type)
                                    )

                                    Spacer(modifier = Modifier.height(height = padding_20))

                                    Text(
                                        text = stringResource(R.string.text_filter_status),
                                        color = MaterialTheme.colorScheme.secondary,
                                        fontFamily = FontFamily.Monospace,
                                        fontWeight = FontWeight.W500,
                                        fontSize = font_size_12,
                                        lineHeight = line_height_20
                                    )

                                    Spacer(modifier = Modifier.height(height = padding_7))

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

                                    Spacer(modifier = Modifier.height(height = padding_20))

                                    Text(
                                        text = stringResource(R.string.text_species_status),
                                        color = MaterialTheme.colorScheme.secondary,
                                        fontFamily = FontFamily.Monospace,
                                        fontWeight = FontWeight.W500,
                                        fontSize = font_size_12,
                                        lineHeight = line_height_20
                                    )

                                    Spacer(modifier = Modifier.height(height = padding_7))

                                    LazyRow(
                                        modifier = Modifier.fillMaxWidth(),
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

                                    Spacer(modifier = Modifier.height(height = padding_20))

                                    Text(
                                        text = stringResource(R.string.text_gender_status),
                                        color = MaterialTheme.colorScheme.secondary,
                                        fontFamily = FontFamily.Monospace,
                                        fontWeight = FontWeight.W500,
                                        fontSize = font_size_12,
                                        lineHeight = line_height_20
                                    )

                                    Spacer(modifier = Modifier.height(height = padding_7))

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
                                }
                            },
                            content = {
                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                ) {
                                    Text(
                                        modifier = Modifier
                                            .padding(bottom = padding_7, start = padding_20),
                                        text = stringResource(R.string.text_search_to_name),
                                        color = MaterialTheme.colorScheme.secondary,
                                        fontFamily = FontFamily.Monospace,
                                        fontWeight = FontWeight.W500,
                                        fontSize = font_size_12,
                                        lineHeight = line_height_20
                                    )

                                    CustomEditText(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(horizontal = padding_20),
                                        value = state.textNameSearchCharacterFilter ?: "",
                                        onChange = viewModel::onTextNameSearchChange,
                                        placeholder = stringResource(R.string.placeholder_et_search_character)
                                    )

                                    CharactersListManagementSuccessScreen(
                                        modifier = Modifier.padding(horizontal = padding_10),
                                        characters = state.characters,
                                        onClickCharacter = {
                                            onClickCharacter(it)
                                        }
                                    )
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}
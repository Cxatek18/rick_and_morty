package com.example.rick_and_morty.presentation.screens.episodes_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.example.rick_and_morty.core.ui.widgets.ModalDrawerCharactersFilters
import com.example.rick_and_morty.presentation.screens.episodes_list.state.EpisodesListManagementState
import com.example.rick_and_morty.presentation.screens.episodes_list.view_model.EpisodesListManagementViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EpisodesListManagement(
    modifier: Modifier = Modifier,
    state: EpisodesListManagementState,
    viewModel: EpisodesListManagementViewModel,
    onClickNavigateToEpisodeDetail: (episodeId: Int) -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.getListEpisodes()
    }

    val refreshState = rememberPullToRefreshState()

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primary)
    ) {
        when (state) {
            is EpisodesListManagementState.Error -> {
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

            EpisodesListManagementState.Loading -> {
                LoadingScreen()
            }

            is EpisodesListManagementState.Success -> {
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
                                        text = stringResource(R.string.text_search_to_name_episode),
                                        color = MaterialTheme.colorScheme.secondary,
                                        fontFamily = FontFamily.Monospace,
                                        fontWeight = FontWeight.W500,
                                        fontSize = font_size_12,
                                        lineHeight = line_height_20
                                    )

                                    Spacer(modifier = Modifier.height(height = padding_7))

                                    CustomEditText(
                                        modifier = Modifier.fillMaxWidth(),
                                        value = state.textNameSearchEpisode ?: "",
                                        onChange = viewModel::onChangeTextNameSearchEpisode,
                                        placeholder = stringResource(R.string.placeholder_et_search_name_episode)
                                    )

                                    Spacer(modifier = Modifier.height(height = padding_20))

                                    Text(
                                        modifier = Modifier,
                                        text = stringResource(R.string.text_search_to_code_episode),
                                        color = MaterialTheme.colorScheme.secondary,
                                        fontFamily = FontFamily.Monospace,
                                        fontWeight = FontWeight.W500,
                                        fontSize = font_size_12,
                                        lineHeight = line_height_20
                                    )

                                    Spacer(modifier = Modifier.height(height = padding_7))

                                    CustomEditText(
                                        modifier = Modifier.fillMaxWidth(),
                                        value = state.textCodeEpisodeSearch ?: "",
                                        onChange = viewModel::onChangeTextCodeSearchEpisode,
                                        placeholder = stringResource(R.string.placeholder_et_search_code_episode)
                                    )
                                }
                            },
                            content = {
                                Column(
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    EpisodesListManagementSuccess(
                                        modifier = Modifier,
                                        episodes = state.episodes,
                                        onClickNavigateToEpisodeDetail = {
                                            onClickNavigateToEpisodeDetail(it)
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
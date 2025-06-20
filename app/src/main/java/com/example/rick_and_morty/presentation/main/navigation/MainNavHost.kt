package com.example.rick_and_morty.presentation.main.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.rick_and_morty.R
import com.example.rick_and_morty.core.ui.widgets.BottomBar
import com.example.rick_and_morty.core.ui.widgets.TopBar
import com.example.rick_and_morty.presentation.main.extensions.switchingTheActiveElement
import com.example.rick_and_morty.presentation.screens.character_detail.navigation.navigateToDetailCharacter
import com.example.rick_and_morty.presentation.screens.characters_list.navigation.HomeGraph
import com.example.rick_and_morty.presentation.screens.characters_list.navigation.homeGraph
import com.example.rick_and_morty.presentation.screens.episode_detail.navigation.navigateToDetailEpisode
import com.example.rick_and_morty.presentation.screens.episodes_list.navigation.EpisodesGraph
import com.example.rick_and_morty.presentation.screens.episodes_list.navigation.listEpisodesGraph

@Composable
fun MainNavHost(
    navController: NavHostController = rememberNavController(),
) {
    val homeLabel = stringResource(R.string.text_bottom_bar_home)
    val episodesLabel = stringResource(R.string.text_bottom_bar_episodes)
    val searchLabel = stringResource(R.string.text_bottom_bar_search)

    val nameTopBarState: MutableState<String> = remember {
        mutableStateOf("")
    }

    val isVisibleNavigateBack: MutableState<Boolean> = remember {
        mutableStateOf(false)
    }

    val listNavItem: MutableState<List<NavItem>> = remember {
        mutableStateOf(
            listOf(
                NavItem(
                    label = homeLabel,
                    image = Icons.Default.Home,
                    contentDescription = homeLabel,
                    isSelected = true,
                    bottomNavigationItem = BottomNavigationItem.ListCharacter
                ),
                NavItem(
                    label = episodesLabel,
                    image = Icons.Default.PlayArrow,
                    contentDescription = episodesLabel,
                    isSelected = false,
                    bottomNavigationItem = BottomNavigationItem.ListEpisodes
                ),
                NavItem(
                    label = searchLabel,
                    image = Icons.Default.Search,
                    contentDescription = searchLabel,
                    isSelected = false,
                    bottomNavigationItem = BottomNavigationItem.ListLocation
                ),
            )
        )
    }

    val activeItemNavBar: MutableState<BottomNavigationItem> = remember {
        mutableStateOf(BottomNavigationItem.ListCharacter)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBar(
                nameTopBarState = nameTopBarState.value,
                isVisibleBackIcon = isVisibleNavigateBack.value,
                onCLickBack = {
                    navController.popBackStack()
                }
            )
        },
        bottomBar = {
            BottomBar(
                navItemList = listNavItem.value,
                onClickToItem = {
                    if (it != activeItemNavBar.value) {
                        activeItemNavBar.value = it
                        when (activeItemNavBar.value) {
                            BottomNavigationItem.ListCharacter -> {
                                navController.navigate(HomeGraph) {
                                    popUpTo(HomeGraph) { inclusive = true }
                                }
                            }

                            BottomNavigationItem.ListEpisodes -> {
                                navController.navigate(EpisodesGraph) {
                                    popUpTo(EpisodesGraph) { inclusive = true }
                                }
                            }

                            BottomNavigationItem.ListLocation -> {}
                        }
                        listNavItem.value = listNavItem.value.switchingTheActiveElement(
                            elementIsActivated = it
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = HomeGraph
        ) {
            homeGraph(
                modifier = Modifier
                    .padding(paddingValues = paddingValues),
                changeNameTopBar = { nameTopBar ->
                    nameTopBarState.value = nameTopBar
                },
                changeIsVisibleBackIcon = { isVisible ->
                    isVisibleNavigateBack.value = isVisible
                },
                navigateToDetail = {
                    navController.navigateToDetailCharacter(it)
                }
            )

            listEpisodesGraph(
                modifier = Modifier
                    .padding(paddingValues = paddingValues),
                changeNameTopBar = { nameTopBar ->
                    nameTopBarState.value = nameTopBar
                },
                changeIsVisibleBackIcon = { isVisible ->
                    isVisibleNavigateBack.value = isVisible
                },
                navigateToDetail = {
                    navController.navigateToDetailEpisode(it)
                }
            )
        }
    }
}
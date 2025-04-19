package com.example.rick_and_morty.presentation.main.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
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
import com.example.rick_and_morty.presentation.screens.characters_list.navigation.HomeGraph
import com.example.rick_and_morty.presentation.screens.characters_list.navigation.homeGraph

@Composable
fun MainNavHost(
    navController: NavHostController = rememberNavController()
) {
    val nameTopBarState = remember {
        mutableStateOf("")
    }

    val listNavItem = listOf(
        NavItem(
            label = stringResource(R.string.text_bottom_bar_home),
            image = Icons.Default.Home,
            contentDescription = stringResource(R.string.text_bottom_bar_home),
            isSelected = true
        ),
        NavItem(
            label = stringResource(R.string.text_bottom_bar_episodes),
            image = Icons.Default.PlayArrow,
            contentDescription = stringResource(R.string.text_bottom_bar_episodes),
            isSelected = false
        ),
        NavItem(
            label = stringResource(R.string.text_bottom_bar_search),
            image = Icons.Default.Search,
            contentDescription = stringResource(R.string.text_bottom_bar_search),
            isSelected = false
        ),
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBar(
                nameTopBarState = nameTopBarState.value
            )
        },
        bottomBar = {
            BottomBar(
                navItemList = listNavItem
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
                }
            )
        }
    }
}
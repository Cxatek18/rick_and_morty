package com.example.rick_and_morty.core.ui.widgets

import androidx.compose.material3.DrawerState
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.runtime.Composable

@Suppress("UNUSED_EXPRESSION")
@Composable
fun ModalDrawerCharactersFilters(
    drawerState: DrawerState,
    contentInDrawer: @Composable () -> Unit,
    content: @Composable () -> Unit
) {
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(drawerState) {
                contentInDrawer()
            }
        },
        content = {
            content()
        },
    )
}
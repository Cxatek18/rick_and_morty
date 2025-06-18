package com.example.rick_and_morty.presentation.main.navigation

sealed interface BottomNavigationItem {

    data object ListCharacter : BottomNavigationItem

    data object ListEpisodes : BottomNavigationItem

    data object ListLocation : BottomNavigationItem
}
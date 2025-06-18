package com.example.rick_and_morty.presentation.main.extensions

import com.example.rick_and_morty.presentation.main.navigation.BottomNavigationItem
import com.example.rick_and_morty.presentation.main.navigation.NavItem

fun List<NavItem>.switchingTheActiveElement(
    elementIsActivated: BottomNavigationItem
): List<NavItem> {
    val copyList = this.toMutableList()
    return copyList.map { navItem ->
        if (navItem.bottomNavigationItem == elementIsActivated) {
            navItem.copy(isSelected = true)
        } else {
            navItem.copy(isSelected = false)
        }
    }.toList()
}
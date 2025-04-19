package com.example.rick_and_morty.presentation.main.navigation

import androidx.compose.ui.graphics.vector.ImageVector

data class NavItem(
    val label: String,
    val image: ImageVector,
    val contentDescription: String,
    val isSelected: Boolean
)

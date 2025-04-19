package com.example.rick_and_morty.core.ui.widgets

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.example.rick_and_morty.core.ui.theme.font_size_16
import com.example.rick_and_morty.presentation.main.navigation.NavItem

@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    navItemList: List<NavItem>
) {
    NavigationBar {
        navItemList.forEachIndexed { index, navItem ->
            NavigationBarItem(
                selected = navItem.isSelected,
                onClick = {},
                icon = {
                    Icon(
                        imageVector = navItem.image,
                        contentDescription = navItem.contentDescription,
                        tint = MaterialTheme.colorScheme.secondary
                    )
                },
                label = {
                    Text(
                        text = navItem.label,
                        color = MaterialTheme.colorScheme.secondary,
                        fontSize = font_size_16,
                        fontWeight = FontWeight.W500
                    )
                }
            )
        }
    }
}
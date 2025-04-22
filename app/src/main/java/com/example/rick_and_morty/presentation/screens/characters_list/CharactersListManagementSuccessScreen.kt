package com.example.rick_and_morty.presentation.screens.characters_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.min
import com.example.rick_and_morty.core.ui.components.characters.CharactersCard
import com.example.rick_and_morty.core.ui.theme.padding_10
import com.example.rick_and_morty.core.ui.theme.size_height_card
import com.example.rick_and_morty.core.ui.theme.size_width_card
import com.example.rick_and_morty.domain.module.characters.CharacterItemModel

@Composable
fun CharactersListManagementSuccessScreen(
    characters: List<CharacterItemModel>
) {
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = padding_10),
        columns = GridCells.Adaptive(min(a = size_width_card, b = size_height_card)),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        items(characters) { character ->
            CharactersCard(
                imageCharacter = character.image,
                nameCharacter = character.name,
                statusCharacter = character.status.statusName
            )
        }
    }
}
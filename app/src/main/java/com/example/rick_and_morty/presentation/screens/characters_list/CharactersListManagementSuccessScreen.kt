package com.example.rick_and_morty.presentation.screens.characters_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.min
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.rick_and_morty.core.ui.components.characters.CharactersCard
import com.example.rick_and_morty.core.ui.theme.size_height_card
import com.example.rick_and_morty.core.ui.theme.size_width_card
import com.example.rick_and_morty.domain.module.characters.CharacterItemModel
import kotlinx.coroutines.flow.Flow

@Composable
fun CharactersListManagementSuccessScreen(
    modifier: Modifier = Modifier,
    characters: Flow<PagingData<CharacterItemModel>>,
    onClickCharacter: (characterID: Int) -> Unit
) {
    val lazyPagingItems = characters.collectAsLazyPagingItems()

    Column(
        modifier = modifier
            .fillMaxSize(),
    ) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(min(a = size_width_card, b = size_height_card)),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            items(lazyPagingItems.itemCount) { number ->
                val character = lazyPagingItems[number]
                if (character != null) {
                    CharactersCard(
                        modifier = Modifier
                            .testTag("CharactersCard_${character.id}"),
                        characterID = character.id,
                        imageCharacter = character.image,
                        nameCharacter = character.name,
                        statusCharacter = character.status,
                        onClickCharacter = {
                            onClickCharacter(it)
                        }
                    )
                }
            }
        }
    }
}
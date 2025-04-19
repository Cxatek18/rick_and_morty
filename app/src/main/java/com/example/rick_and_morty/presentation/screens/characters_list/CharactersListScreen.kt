package com.example.rick_and_morty.presentation.screens.characters_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.min
import com.example.rick_and_morty.R
import com.example.rick_and_morty.core.ui.components.characters.CharactersCard
import com.example.rick_and_morty.core.ui.theme.Rick_and_mortyTheme
import com.example.rick_and_morty.core.ui.theme.font_size_16
import com.example.rick_and_morty.core.ui.theme.padding_10
import com.example.rick_and_morty.core.ui.theme.size_40
import com.example.rick_and_morty.core.ui.theme.size_border_card_2
import com.example.rick_and_morty.core.ui.theme.size_height_card
import com.example.rick_and_morty.core.ui.theme.size_width_card
import com.example.rick_and_morty.domain.module.characters.CharacterItem
import com.example.rick_and_morty.domain.module.characters.ErrorType

@Composable
fun CharactersListScreen(
    modifier: Modifier = Modifier,
    state: CharactersListScreenState,
    viewModel: CharactersListScreenViewModel
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primary)
    ) {
        when (state) {
            is CharactersListScreenState.Error -> {
                ListCharacterError(
                    errorType = state.errorType
                )
            }

            CharactersListScreenState.Loading -> {
                ListCharacterLoading()
            }

            is CharactersListScreenState.Success -> {
                ListCharacter(
                    characters = state.characters
                )
            }
        }
    }
}

@Composable
private fun ListCharacter(
    characters: List<CharacterItem>
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
                statusCharacter = character.status
            )
        }
    }
}

@Composable
private fun ListCharacterLoading() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(size_40),
            color = MaterialTheme.colorScheme.secondary,
            strokeWidth = size_border_card_2,
            trackColor = Color(0xFF5E4B8B),
        )
    }
}

@Composable
private fun ListCharacterError(
    errorType: ErrorType
) {
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        val errorText = when (errorType) {
            ErrorType.NETWORK -> stringResource(R.string.text_error_network)
            ErrorType.HTTP -> stringResource(R.string.text_error_http)
            ErrorType.SYSTEM -> stringResource(R.string.text_error_system)
            ErrorType.NULL_TYPE -> stringResource(R.string.text_error_null_type)
            ErrorType.UNKNOWN -> stringResource(R.string.text_error_unknown)
        }

        Text(
            text = errorText,
            color = MaterialTheme.colorScheme.secondary,
            fontFamily = FontFamily.Monospace,
            fontSize = font_size_16,
            fontWeight = FontWeight.W700
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PreviewCharactersListScreen() {
    Rick_and_mortyTheme {

    }
}
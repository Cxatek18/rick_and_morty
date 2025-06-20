package com.example.rick_and_morty.presentation.screens.episode_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.example.rick_and_morty.R
import com.example.rick_and_morty.core.ui.components.episode.CharacterInEpisodeItem
import com.example.rick_and_morty.core.ui.theme.font_size_16
import com.example.rick_and_morty.core.ui.theme.letter_spacing_1_2
import com.example.rick_and_morty.core.ui.theme.padding_10
import com.example.rick_and_morty.core.ui.theme.padding_5
import com.example.rick_and_morty.core.ui.theme.padding_7
import com.example.rick_and_morty.domain.module.characters.CharacterItemModel
import com.example.rick_and_morty.domain.module.episodes.EpisodeItemModel

@Composable
fun EpisodeDetailManagementSuccess(
    modifier: Modifier = Modifier,
    episode: EpisodeItemModel,
    listCharacterInEpisode: List<CharacterItemModel>
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(
                all = padding_10
            ),
    ) {
        Text(
            modifier = Modifier
                .padding(vertical = padding_5)
                .fillMaxWidth(),
            text = String.format(stringResource(R.string.text_episode_name), episode.name),
            color = MaterialTheme.colorScheme.tertiary,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.W500,
            fontSize = font_size_16,
            letterSpacing = letter_spacing_1_2,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Start
        )

        Text(
            modifier = Modifier
                .padding(vertical = padding_5)
                .fillMaxWidth(),
            text = String.format(stringResource(R.string.text_episode_air_date), episode.airDate),
            color = MaterialTheme.colorScheme.tertiary,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.W500,
            fontSize = font_size_16,
            letterSpacing = letter_spacing_1_2,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Start
        )

        Text(
            modifier = Modifier
                .padding(vertical = padding_5)
                .fillMaxWidth(),
            text = String.format(stringResource(R.string.text_episode_code), episode.episode),
            color = MaterialTheme.colorScheme.tertiary,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.W500,
            fontSize = font_size_16,
            letterSpacing = letter_spacing_1_2,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Start
        )

        Text(
            modifier = Modifier
                .padding(vertical = padding_10)
                .fillMaxWidth(),
            text = stringResource(R.string.text_episode_characters),
            color = MaterialTheme.colorScheme.tertiary,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.W500,
            fontSize = font_size_16,
            letterSpacing = letter_spacing_1_2,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Start
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(padding_7)
        ) {
            items(listCharacterInEpisode, key = { it.id }) {
                CharacterInEpisodeItem(
                    character = it
                )
            }
        }
    }
}
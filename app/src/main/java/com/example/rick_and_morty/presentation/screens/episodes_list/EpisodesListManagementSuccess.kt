package com.example.rick_and_morty.presentation.screens.episodes_list

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.rick_and_morty.core.ui.components.characters.EpisodeCard
import com.example.rick_and_morty.domain.module.episodes.EpisodeItemModel

@Composable
fun EpisodesListManagementSuccess(
    modifier: Modifier = Modifier,
    episodes: List<EpisodeItemModel>
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth()
    ) {
        items(episodes) { episode ->
            EpisodeCard(
                modifier = Modifier,
                episodeName = episode.name,
                episodeCode = episode.episode,
                episodeRelease = episode.airDate
            )
        }
    }
}
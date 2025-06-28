package com.example.rick_and_morty.presentation.screens.episodes_list

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.rick_and_morty.core.ui.components.episode.EpisodeCard
import com.example.rick_and_morty.domain.module.episodes.EpisodeItemModel
import kotlinx.coroutines.flow.Flow

@Composable
fun EpisodesListManagementSuccess(
    modifier: Modifier = Modifier,
    episodes: Flow<PagingData<EpisodeItemModel>>,
    onClickNavigateToEpisodeDetail: (episodeId: Int) -> Unit
) {
    val lazyPagingItems = episodes.collectAsLazyPagingItems()

    LazyColumn(
        modifier = modifier.fillMaxWidth()
    ) {
        items(lazyPagingItems.itemCount, key = { it }) { number ->
            val episode = lazyPagingItems[number]
            if (episode != null) {
                EpisodeCard(
                    modifier = Modifier,
                    episodeId = episode.id,
                    episodeName = episode.name,
                    episodeCode = episode.episode,
                    episodeRelease = episode.airDate,
                    onClickNavigateToEpisodeDetail = {
                        onClickNavigateToEpisodeDetail(it)
                    }
                )
            }
        }
    }
}

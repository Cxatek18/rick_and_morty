package com.example.rick_and_morty.data.repository.episodes

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.rick_and_morty.data.remote.network.ApiService
import com.example.rick_and_morty.domain.module.episodes.EpisodeItemModel
import com.example.rick_and_morty.domain.repository.episodes.IEpisodesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EpisodesRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : IEpisodesRepository {

    override fun getAllEpisodes(
        nameEpisode: String?,
        episodeCode: String?
    ): Flow<PagingData<EpisodeItemModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 5,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                EpisodeDataSource(
                    apiService,
                    nameEpisode,
                    episodeCode
                )
            }
        ).flow
    }
}
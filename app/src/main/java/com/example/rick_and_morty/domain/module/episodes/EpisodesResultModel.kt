package com.example.rick_and_morty.domain.module.episodes

data class EpisodesResultModel(
    val info: InfoListEpisodesModel,
    val results: List<EpisodeItemModel>
)

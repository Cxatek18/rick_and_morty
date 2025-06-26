package com.example.rick_and_morty.domain.module.episodes

data class EpisodesResultModel(
    val info: InfoModel,
    val results: List<EpisodeItemModel>
)

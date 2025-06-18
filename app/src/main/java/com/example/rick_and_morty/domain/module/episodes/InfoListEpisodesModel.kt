package com.example.rick_and_morty.domain.module.episodes

data class InfoListEpisodesModel(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)

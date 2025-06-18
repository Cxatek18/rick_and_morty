package com.example.rick_and_morty.domain.module.episodes

import com.google.gson.annotations.SerializedName

data class EpisodeItemModel(
    val id: Int,
    val name: String,
    @SerializedName("air_date")
    val airDate: String,
    val episode: String,
    @SerializedName("characters")
    val listUrlCharacter: List<String>,
    val url: String,
    val created: String
)

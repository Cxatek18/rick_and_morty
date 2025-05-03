package com.example.rick_and_morty.domain.module.characters

import com.google.gson.annotations.SerializedName

data class CharactersResultModel(
    val info: CharacterInfoModel,
    @SerializedName("results")
    val characterList: List<CharacterItemModel>
)
package com.example.rick_and_morty.data.remote.model.characters

import com.google.gson.annotations.SerializedName

data class CharacterListInApi(
    @SerializedName("info")
    val info: InfoCharacterListInApi,
    @SerializedName("results")
    val results: List<CharacterItemInApi>
)

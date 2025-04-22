package com.example.rick_and_morty.data.remote.model.characters

import com.google.gson.annotations.SerializedName

data class CharacterItemInResponse(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    @SerializedName("origin")
    val originCharacter: OriginCharacterInResponse,
    @SerializedName("location")
    val locationCharacter: LocationCharacterInResponse,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String
)

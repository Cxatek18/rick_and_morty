package com.example.rick_and_morty.data.remote.model.characters

import com.google.gson.annotations.SerializedName

data class LocationCharacterInApi(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)

package com.example.rick_and_morty.data.remote.model.characters

import com.google.gson.annotations.SerializedName

data class InfoCharacterListInApi(
    @SerializedName("count")
    val count: Int,
    @SerializedName("pages")
    val pages: Int,
    @SerializedName("next")
    val next: String?,
    @SerializedName("prev")
    val prev: String?
)

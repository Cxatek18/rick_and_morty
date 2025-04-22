package com.example.rick_and_morty.data.remote.model.characters

data class InfoCharacterListInResponse(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)

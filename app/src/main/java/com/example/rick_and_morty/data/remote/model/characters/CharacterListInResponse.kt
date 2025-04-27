package com.example.rick_and_morty.data.remote.model.characters

data class CharacterListInResponse(
    val info: InfoCharacterListInResponse,
    val results: List<CharacterItemInResponse>
)

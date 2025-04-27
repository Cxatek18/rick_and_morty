package com.example.rick_and_morty.domain.module.characters

data class CharacterItemModel(
    val id: Int,
    val name: String,
    val status: CharacterInfoStatus,
    val image: String
)

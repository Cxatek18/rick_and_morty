package com.example.rick_and_morty.domain.module.characters

data class CharacterInfo(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)

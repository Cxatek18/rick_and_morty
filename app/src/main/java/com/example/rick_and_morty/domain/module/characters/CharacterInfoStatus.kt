package com.example.rick_and_morty.domain.module.characters

enum class CharacterInfoStatus(val statusName: String) {
    Alive(
        statusName = "Alive"
    ),
    Dead(
        statusName = "Dead"
    ),
    Unknown(
        statusName = "unknown"
    )
}
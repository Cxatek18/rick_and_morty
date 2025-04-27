package com.example.rick_and_morty.domain.module.characters

data class CharactersResultModel(
    val info: CharacterInfoModel,
    val characterList: List<CharacterItemModel>
)
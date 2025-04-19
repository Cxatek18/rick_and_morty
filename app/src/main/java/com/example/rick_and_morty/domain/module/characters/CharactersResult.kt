package com.example.rick_and_morty.domain.module.characters

sealed interface CharactersResult {

    data class Error(
        val errorType: ErrorType
    ) : CharactersResult

    data class CharacterListSuccess(
        val info: CharacterInfo,
        val characterList: List<CharacterItem>
    ) : CharactersResult
}
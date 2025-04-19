package com.example.rick_and_morty.domain.repository.characters

import com.example.rick_and_morty.domain.module.characters.CharactersResult
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {

    fun getListAllCharacters(): Flow<CharactersResult>
}
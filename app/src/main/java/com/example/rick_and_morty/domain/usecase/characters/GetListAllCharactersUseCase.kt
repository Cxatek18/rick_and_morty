package com.example.rick_and_morty.domain.usecase.characters

import com.example.rick_and_morty.domain.module.characters.CharactersResult
import com.example.rick_and_morty.domain.repository.characters.CharactersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetListAllCharactersUseCase @Inject constructor(
    private val repository: CharactersRepository
) {
    operator fun invoke(): Flow<CharactersResult> {
        return repository.getListAllCharacters()
    }
}
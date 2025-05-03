package com.example.rick_and_morty.domain.repository.characters

interface ICharacterDetailRepository {

    suspend fun getCharacterDetail(): Flow<>
}
package com.example.rick_and_morty.data.remote.network

import com.example.rick_and_morty.domain.module.character.CharacterDetailResultModel
import com.example.rick_and_morty.domain.module.characters.CharactersResultModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface ApiService {
    @GET("character")
    @Headers(
        "Accept-Language: ru,en;q=0.9",
        "Accept: application/json",
        "Content-Type: application/json"
    )
    suspend fun getListCharacter(): Response<CharactersResultModel>

    @GET("character/{characterId}")
    @Headers(
        "Accept-Language: ru,en;q=0.9",
        "Accept: application/json",
        "Content-Type: application/json"
    )
    suspend fun getCharacterDetail(
        @Path("characterId") characterId: Int
    ): Response<CharacterDetailResultModel>
}
package com.example.rick_and_morty.data.remote.network

import com.example.rick_and_morty.data.remote.model.characters.CharacterListInApi
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiService {
    @GET("character")
    @Headers(
        "Accept-Language: ru,en;q=0.9",
        "Accept: application/json",
        "Content-Type: application/json"
    )
    suspend fun getListCharacter(
    ): Response<CharacterListInApi>
}
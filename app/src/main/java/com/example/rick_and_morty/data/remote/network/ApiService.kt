package com.example.rick_and_morty.data.remote.network

import com.example.rick_and_morty.domain.module.character.CharacterDetailResultModel
import com.example.rick_and_morty.domain.module.characters.CharacterItemModel
import com.example.rick_and_morty.domain.module.characters.CharactersResultModel
import com.example.rick_and_morty.domain.module.episodes.EpisodeItemModel
import com.example.rick_and_morty.domain.module.episodes.EpisodesResultModel
import com.example.rick_and_morty.domain.module.locations.LocationItemModel
import com.example.rick_and_morty.domain.module.locations.LocationsResultModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("character")
    @Headers(
        "Accept-Language: ru,en;q=0.9",
        "Accept: application/json",
        "Content-Type: application/json"
    )
    suspend fun getListCharacter(
        @Query("name") name: String? = null,
        @Query("status") status: String? = null,
        @Query("species") species: String? = null,
        @Query("type") type: String? = null,
        @Query("gender") gender: String? = null,
        @Query("page") page: Int = 1
    ): Response<CharactersResultModel>

    @GET("character/{characterId}")
    @Headers(
        "Accept-Language: ru,en;q=0.9",
        "Accept: application/json",
        "Content-Type: application/json"
    )
    suspend fun getCharacterDetail(
        @Path("characterId") characterId: Int
    ): Response<CharacterDetailResultModel>

    @GET("episode")
    @Headers(
        "Accept-Language: ru,en;q=0.9",
        "Accept: application/json",
        "Content-Type: application/json"
    )
    suspend fun getListEpisodes(
        @Query("name") name: String? = null,
        @Query("episode") episode: String? = null,
    ): Response<EpisodesResultModel>

    @GET("episode/{episodeId}")
    @Headers(
        "Accept-Language: ru,en;q=0.9",
        "Accept: application/json",
        "Content-Type: application/json"
    )
    suspend fun getEpisodeDetail(
        @Path("episodeId") episodeId: Int
    ): Response<EpisodeItemModel>

    @GET("character/{listCharacterIdToString}")
    @Headers(
        "Accept-Language: ru,en;q=0.9",
        "Accept: application/json",
        "Content-Type: application/json"
    )
    suspend fun getMultipleCharacter(
        @Path("listCharacterIdToString") listCharacterIdToString: String
    ): Response<List<CharacterItemModel>>

    @GET("location")
    @Headers(
        "Accept-Language: ru,en;q=0.9",
        "Accept: application/json",
        "Content-Type: application/json"
    )
    suspend fun getListLocations(
        @Query("name") name: String? = null,
        @Query("type") type: String? = null,
        @Query("dimension") dimension: String? = null,
    ): Response<LocationsResultModel>

    @GET("location/{locationId}")
    @Headers(
        "Accept-Language: ru,en;q=0.9",
        "Accept: application/json",
        "Content-Type: application/json"
    )
    suspend fun getLocationDetail(
        @Path("locationId") locationId: Int
    ): Response<LocationItemModel>
}
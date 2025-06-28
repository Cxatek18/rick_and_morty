package com.example.rick_and_morty.data.repository.characters

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rick_and_morty.data.remote.network.ApiService
import com.example.rick_and_morty.data.utils.extractPageNumber
import com.example.rick_and_morty.domain.module.characters.CharacterItemModel

class CharactersPagingSource(
    private val apiService: ApiService,
    private val nameQuery: String? = null,
    private val statusQuery: String? = null,
    private val speciesQuery: String? = null,
    private val typeQuery: String? = null,
    private val genderQuery: String? = null
) : PagingSource<Int, CharacterItemModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterItemModel> {
        val page = params.key ?: 1
        return try {
            val response = apiService.getListCharacter(
                name = nameQuery,
                status = statusQuery,
                species = speciesQuery,
                type = typeQuery,
                gender = genderQuery,
                page = page
            )

            if (response.isSuccessful) {
                val body = response.body()!!
                val nextPage = body.info.next?.extractPageNumber()
                val prevPage = body.info.prev?.extractPageNumber()

                LoadResult.Page(
                    data = body.characterList,
                    prevKey = prevPage,
                    nextKey = nextPage
                )
            } else {
                LoadResult.Error(Exception("HTTP ${response.code()}"))
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CharacterItemModel>): Int? {
        return state.anchorPosition
            ?.let { pos -> state.closestPageToPosition(pos) }
            ?.prevKey
            ?.plus(1)
            ?: state.closestPageToPosition(state.anchorPosition ?: 0)
                ?.nextKey
                ?.minus(1)
    }
}
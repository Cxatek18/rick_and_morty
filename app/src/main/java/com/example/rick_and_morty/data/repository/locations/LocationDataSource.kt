package com.example.rick_and_morty.data.repository.locations

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rick_and_morty.data.remote.network.ApiService
import com.example.rick_and_morty.data.utils.extractPageNumber
import com.example.rick_and_morty.domain.module.locations.LocationItemModel

class LocationDataSource(
    private val apiService: ApiService,
    private val nameLocation: String? = null,
    private val typeLocation: String? = null,
    private val dimensionLocation: String? = null
) : PagingSource<Int, LocationItemModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, LocationItemModel> {
        val page = params.key ?: 1
        return try {
            val response = apiService.getListLocations(
                name = nameLocation,
                type = typeLocation,
                dimension = dimensionLocation,
                page = page
            )

            if (response.isSuccessful) {
                val body = response.body()!!
                val nextPage = body.info.next?.extractPageNumber()
                val prevPage = body.info.prev?.extractPageNumber()

                LoadResult.Page(
                    data = body.results,
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

    override fun getRefreshKey(state: PagingState<Int, LocationItemModel>): Int? {
        return state.anchorPosition
            ?.let { pos -> state.closestPageToPosition(pos) }
            ?.prevKey
            ?.plus(1)
            ?: state.closestPageToPosition(state.anchorPosition ?: 0)
                ?.nextKey
                ?.minus(1)
    }
}
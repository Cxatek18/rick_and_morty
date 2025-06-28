package com.example.rick_and_morty.presentation.screens.locations_list

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.rick_and_morty.core.ui.components.locations.LocationsCard
import com.example.rick_and_morty.domain.module.locations.LocationItemModel
import kotlinx.coroutines.flow.Flow

@Composable
fun LocationsListManagementSuccess(
    modifier: Modifier = Modifier,
    locations: Flow<PagingData<LocationItemModel>>,
    onClickNavigateToLocationsDetail: (episodeId: Int) -> Unit
) {
    val lazyPagingItems = locations.collectAsLazyPagingItems()

    LazyColumn(
        modifier = modifier.fillMaxWidth()
    ) {
        items(lazyPagingItems.itemCount, key = { it }) { number ->
            val location = lazyPagingItems[number]
            if (location != null) {
                LocationsCard(
                    modifier = Modifier,
                    locationId = location.id,
                    locationName = location.name,
                    locationType = location.type,
                    locationDimension = location.dimension,
                    onClickNavigateToLocationsDetail = {
                        onClickNavigateToLocationsDetail(it)
                    }
                )
            }
        }
    }
}
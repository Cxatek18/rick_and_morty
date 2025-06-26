package com.example.rick_and_morty.presentation.screens.locations_list

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.rick_and_morty.core.ui.components.locations.LocationsCard
import com.example.rick_and_morty.domain.module.locations.LocationItemModel

@Composable
fun LocationsListManagementSuccess(
    modifier: Modifier = Modifier,
    locations: List<LocationItemModel>,
    onClickNavigateToLocationsDetail: (episodeId: Int) -> Unit
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth()
    ) {
        items(locations, key = { it.id }) { location ->
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
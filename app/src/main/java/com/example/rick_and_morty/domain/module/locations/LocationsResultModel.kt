package com.example.rick_and_morty.domain.module.locations

import com.example.rick_and_morty.domain.module.episodes.InfoModel

data class LocationsResultModel(
    val info: InfoModel,
    val results: List<LocationItemModel>
)

package com.example.rick_and_morty.presentation.screens.characters_list.state

import com.example.rick_and_morty.domain.module.characters.CharacterInfoModel
import com.example.rick_and_morty.domain.module.characters.CharacterItemModel
import com.example.rick_and_morty.domain.module.characters.GenderCharacterFilterModel
import com.example.rick_and_morty.domain.module.characters.SpeciesCharacterFilterModel
import com.example.rick_and_morty.domain.module.characters.StatusCharacterFilterModel

sealed interface CharactersListManagementState {

    data object Loading : CharactersListManagementState

    data class Success(
        val characters: List<CharacterItemModel>,
        val info: CharacterInfoModel,
        val textNameSearchCharacterFilter: String? = null,
        val activeStatusCharacterFilter: StatusCharacterFilterModel? = null,
        val listStatusCharacterFilter: List<StatusCharacterFilterModel> = listOf(),
        val activeSpeciesCharacterFilter: SpeciesCharacterFilterModel? = null,
        val listSpeciesCharacterFilter: List<SpeciesCharacterFilterModel> = listOf(),
        val textTypeCharacterFilter: String? = null,
        val activeGenderCharacterFilter: GenderCharacterFilterModel? = null,
        val listGenderCharacterFilter: List<GenderCharacterFilterModel> = listOf()
    ) : CharactersListManagementState

    data class Error(
        val errorText: String
    ) : CharactersListManagementState
}
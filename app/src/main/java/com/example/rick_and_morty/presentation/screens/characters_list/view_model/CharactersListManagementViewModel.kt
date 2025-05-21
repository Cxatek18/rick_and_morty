package com.example.rick_and_morty.presentation.screens.characters_list.view_model

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rick_and_morty.R
import com.example.rick_and_morty.core.ui.utils.IResourceService
import com.example.rick_and_morty.domain.interactor.characters.ICharactersListManagementInteractor
import com.example.rick_and_morty.domain.module.characters.GenderCharacterFilterModel
import com.example.rick_and_morty.domain.module.characters.SpeciesCharacterFilterModel
import com.example.rick_and_morty.domain.module.characters.StatusCharacterFilterModel
import com.example.rick_and_morty.domain.module.error_handler.ApiResult
import com.example.rick_and_morty.domain.module.error_handler.getStringSystemError
import com.example.rick_and_morty.presentation.screens.characters_list.state.CharactersListManagementState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersListManagementViewModel @Inject constructor(
    private val resolver: IResourceService,
    private val charactersListManagementInteractor: ICharactersListManagementInteractor
) : ViewModel() {

    private var _state = MutableStateFlow<CharactersListManagementState>(
        CharactersListManagementState.Loading
    )
    val state: StateFlow<CharactersListManagementState> = _state.asStateFlow()


    @SuppressLint("StringFormatMatches")
    fun getListAllCharacters(
        name: String? = null,
        status: StatusCharacterFilterModel? = null,
        species: SpeciesCharacterFilterModel? = null,
        type: String? = null,
        gender: GenderCharacterFilterModel? = null
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            if (_state.value::class.java != CharactersListManagementState.Success::class.java) {
                _state.value = CharactersListManagementState.Loading
                delay(timeMillis = 500)
            }
            charactersListManagementInteractor.getListAllCharacters(
                nameQuery = name,
                statusQuery = status,
                speciesQuery = species,
                typeQuery = type,
                genderQuery = gender
            )
                .catch {
                    CharactersListManagementState.Error(
                        errorText = resolver.getString(R.string.text_error_system),
                    )
                }
                .collect { result ->
                    when (result) {
                        is ApiResult.Error -> {
                            val systemErrorText = result.type.getStringSystemError(resolver)
                            if (systemErrorText != resolver.getString(R.string.text_error_in_server)) {
                                _state.value = CharactersListManagementState.Error(
                                    errorText = systemErrorText
                                )
                            } else {
                                // Тут как раз можем уже обработать те исключения которые нам отдал бэк по запрсоу
                            }
                        }

                        is ApiResult.Success -> {
                            if (_state.value::class.java != CharactersListManagementState.Success::class.java) {
                                _state.value = CharactersListManagementState.Success(
                                    characters = result.data.characterList,
                                    info = result.data.info,
                                    listStatusCharacterFilter = listOf(
                                        StatusCharacterFilterModel(
                                            title = resolver.getString(R.string.text_character_status_alive)
                                        ),
                                        StatusCharacterFilterModel(
                                            title = resolver.getString(R.string.text_character_status_dead)
                                        ),
                                        StatusCharacterFilterModel(
                                            title = resolver.getString(R.string.text_character_status_unknown)
                                        )
                                    ),
                                    listGenderCharacterFilter = listOf(
                                        GenderCharacterFilterModel(
                                            title = resolver.getString(R.string.text_character_gender_male)
                                        ),
                                        GenderCharacterFilterModel(
                                            title = resolver.getString(R.string.text_character_gender_female)
                                        )
                                    ),
                                    listSpeciesCharacterFilter = listOf(
                                        SpeciesCharacterFilterModel(
                                            title = resolver.getString(R.string.text_character_species_human)
                                        ),
                                        SpeciesCharacterFilterModel(
                                            title = resolver.getString(R.string.text_character_species_alien)
                                        )
                                    )
                                )
                            } else {
                                when (_state.value) {
                                    is CharactersListManagementState.Error, CharactersListManagementState.Loading -> {}
                                    is CharactersListManagementState.Success -> {
                                        val currentState =
                                            _state.value as CharactersListManagementState.Success
                                        val updateState = currentState.copy(
                                            characters = result.data.characterList,
                                            info = result.data.info
                                        )
                                        _state.value = updateState
                                    }
                                }
                            }
                        }
                    }
                }

        }
    }

    fun onTextNameSearchChange(name: String) {
        when (_state.value) {
            is CharactersListManagementState.Error -> {}
            CharactersListManagementState.Loading -> {}
            is CharactersListManagementState.Success -> {
                val currentState = _state.value as CharactersListManagementState.Success
                val updatedState = currentState.copy(
                    textNameSearchCharacterFilter = name.ifEmpty { null }
                )
                _state.value = updatedState
                getListAllCharacters(
                    name = updatedState.textNameSearchCharacterFilter,
                    status = updatedState.activeStatusCharacterFilter,
                    species = updatedState.activeSpeciesCharacterFilter,
                    type = updatedState.textTypeCharacterFilter,
                    gender = updatedState.activeGenderCharacterFilter
                )
            }
        }
    }

    fun onActiveStatusFilterChange(status: StatusCharacterFilterModel?) {
        when (_state.value) {
            is CharactersListManagementState.Error -> {}
            CharactersListManagementState.Loading -> {}
            is CharactersListManagementState.Success -> {
                val currentState = _state.value as CharactersListManagementState.Success
                var copyListStatusCharacterFilter: List<StatusCharacterFilterModel>
                val currentStatus = if (status?.isActive == true) {
                    copyListStatusCharacterFilter = currentState
                        .listStatusCharacterFilter
                        .map {
                            it.copy(isActive = false)
                        }
                        .toList()
                    null
                } else {
                    copyListStatusCharacterFilter = currentState
                        .listStatusCharacterFilter
                        .map {
                            it.copy(isActive = false)
                        }
                        .toMutableList()

                    val searchFilter =
                        copyListStatusCharacterFilter.find { it.title == status?.title }
                    val indexFilter = copyListStatusCharacterFilter.indexOf(searchFilter)
                    copyListStatusCharacterFilter.removeAt(indexFilter)
                    searchFilter?.copy(isActive = !searchFilter.isActive)
                        ?.let { copyListStatusCharacterFilter.add(indexFilter, it) }
                    status
                }

                val updatedState = currentState.copy(
                    activeStatusCharacterFilter = currentStatus,
                    listStatusCharacterFilter = copyListStatusCharacterFilter
                )
                _state.value = updatedState
                getListAllCharacters(
                    name = updatedState.textNameSearchCharacterFilter,
                    status = updatedState.activeStatusCharacterFilter,
                    species = updatedState.activeSpeciesCharacterFilter,
                    type = updatedState.textTypeCharacterFilter,
                    gender = updatedState.activeGenderCharacterFilter
                )
            }
        }
    }

    fun onActiveSpeciesFilterChange(species: SpeciesCharacterFilterModel?) {
        when (_state.value) {
            is CharactersListManagementState.Error -> {}
            CharactersListManagementState.Loading -> {}
            is CharactersListManagementState.Success -> {
                val currentState = _state.value as CharactersListManagementState.Success
                var copyListSpeciesCharacterFilter: List<SpeciesCharacterFilterModel>
                val currentStatus = if (species?.isActive == true) {
                    copyListSpeciesCharacterFilter = currentState
                        .listSpeciesCharacterFilter
                        .map {
                            it.copy(isActive = false)
                        }
                        .toList()
                    null
                } else {
                    copyListSpeciesCharacterFilter = currentState
                        .listSpeciesCharacterFilter
                        .map {
                            it.copy(isActive = false)
                        }
                        .toMutableList()

                    val searchFilter =
                        copyListSpeciesCharacterFilter.find { it.title == species?.title }
                    val indexFilter = copyListSpeciesCharacterFilter.indexOf(searchFilter)
                    copyListSpeciesCharacterFilter.removeAt(indexFilter)
                    searchFilter?.copy(isActive = !searchFilter.isActive)
                        ?.let { copyListSpeciesCharacterFilter.add(indexFilter, it) }
                    species
                }

                val updatedState = currentState.copy(
                    activeSpeciesCharacterFilter = currentStatus,
                    listSpeciesCharacterFilter = copyListSpeciesCharacterFilter
                )
                _state.value = updatedState
                getListAllCharacters(
                    name = updatedState.textNameSearchCharacterFilter,
                    status = updatedState.activeStatusCharacterFilter,
                    species = updatedState.activeSpeciesCharacterFilter,
                    type = updatedState.textTypeCharacterFilter,
                    gender = updatedState.activeGenderCharacterFilter
                )
            }
        }
    }

    fun onTextTypeFilterChange(type: String) {
        when (_state.value) {
            is CharactersListManagementState.Error -> {}
            CharactersListManagementState.Loading -> {}
            is CharactersListManagementState.Success -> {
                val currentState = _state.value as CharactersListManagementState.Success
                val updatedState = currentState.copy(
                    textTypeCharacterFilter = type.ifEmpty { null }
                )
                _state.value = updatedState
                getListAllCharacters(
                    name = updatedState.textNameSearchCharacterFilter,
                    status = updatedState.activeStatusCharacterFilter,
                    species = updatedState.activeSpeciesCharacterFilter,
                    type = updatedState.textTypeCharacterFilter,
                    gender = updatedState.activeGenderCharacterFilter
                )
            }
        }
    }

    fun onActiveGenderFilterChange(gender: GenderCharacterFilterModel?) {
        when (_state.value) {
            is CharactersListManagementState.Error, CharactersListManagementState.Loading -> {}
            is CharactersListManagementState.Success -> {
                val currentState = _state.value as CharactersListManagementState.Success
                var copyListGenderCharacterFilter: List<GenderCharacterFilterModel>
                val currentStatus = if (gender?.isActive == true) {
                    copyListGenderCharacterFilter = currentState
                        .listGenderCharacterFilter
                        .map {
                            it.copy(isActive = false)
                        }
                        .toList()
                    null
                } else {
                    copyListGenderCharacterFilter = currentState
                        .listGenderCharacterFilter
                        .map {
                            it.copy(isActive = false)
                        }
                        .toMutableList()

                    val searchFilter =
                        copyListGenderCharacterFilter.find { it.title == gender?.title }
                    val indexFilter = copyListGenderCharacterFilter.indexOf(searchFilter)
                    copyListGenderCharacterFilter.removeAt(indexFilter)
                    searchFilter?.copy(isActive = !searchFilter.isActive)
                        ?.let { copyListGenderCharacterFilter.add(indexFilter, it) }
                    gender
                }

                val updatedState = currentState.copy(
                    activeGenderCharacterFilter = currentStatus,
                    listGenderCharacterFilter = copyListGenderCharacterFilter
                )
                _state.value = updatedState
                getListAllCharacters(
                    name = updatedState.textNameSearchCharacterFilter,
                    status = updatedState.activeStatusCharacterFilter,
                    species = updatedState.activeSpeciesCharacterFilter,
                    type = updatedState.textTypeCharacterFilter,
                    gender = updatedState.activeGenderCharacterFilter
                )
            }
        }
    }

    fun clearAllFilter() {
        when (_state.value) {
            is CharactersListManagementState.Error -> {}
            CharactersListManagementState.Loading -> {}
            is CharactersListManagementState.Success -> {
                val currentState = _state.value as CharactersListManagementState.Success
                val updatedState = currentState.copy(
                    textNameSearchCharacterFilter = null,
                    activeStatusCharacterFilter = null,
                    activeSpeciesCharacterFilter = null,
                    textTypeCharacterFilter = null,
                    activeGenderCharacterFilter = null
                )
                _state.value = updatedState
                getListAllCharacters(
                    name = updatedState.textNameSearchCharacterFilter,
                    status = updatedState.activeStatusCharacterFilter,
                    species = updatedState.activeSpeciesCharacterFilter,
                    type = updatedState.textTypeCharacterFilter,
                    gender = updatedState.activeGenderCharacterFilter
                )
            }
        }
    }
}
package com.example.rick_and_morty.presentation.screens.locations_list.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.rick_and_morty.R
import com.example.rick_and_morty.core.ui.utils.IResourceService
import com.example.rick_and_morty.domain.interactor.locations.ILocationsListManagerInteractor
import com.example.rick_and_morty.presentation.screens.locations_list.state.LocationsListManagementState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationsListManagementViewModel @Inject constructor(
    private val resolver: IResourceService,
    private val locationsListManagerInteractor: ILocationsListManagerInteractor
) : ViewModel() {

    private val _state = MutableStateFlow<LocationsListManagementState>(
        LocationsListManagementState.Loading
    )
    val state: StateFlow<LocationsListManagementState> = _state.asStateFlow()

    private var searchNameJob: Job? = null
    private var searchTypeJob: Job? = null
    private var searchDimensionJob: Job? = null

    fun getListLocations(
        nameLocation: String? = null,
        typeLocation: String? = null,
        dimensionLocation: String? = null
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            if (_state.value::class.java != LocationsListManagementState.Success::class.java) {
                _state.value = LocationsListManagementState.Loading
                delay(timeMillis = 500)
            }
            locationsListManagerInteractor.getAllLocations(
                nameLocation,
                typeLocation,
                dimensionLocation
            )
                .catch {
                    LocationsListManagementState.Error(
                        errorText = resolver.getString(R.string.text_error_system),
                    )
                }
                .collect { result ->
                    if (_state.value::class.java != LocationsListManagementState.Success::class.java) {
                        _state.value = LocationsListManagementState.Success(
                            locations = flowOf(result).cachedIn(viewModelScope)
                        )
                    } else {
                        when (_state.value) {
                            is LocationsListManagementState.Error, LocationsListManagementState.Loading -> {}
                            is LocationsListManagementState.Success -> {
                                val currentState =
                                    _state.value as LocationsListManagementState.Success
                                val updateState = currentState.copy(
                                    locations = flowOf(result).cachedIn(viewModelScope),
                                    isRefreshing = false
                                )
                                _state.value = updateState
                            }
                        }
                    }
                }
        }
    }

    fun onChangeTextNameSearchLocations(name: String) {
        searchNameJob?.cancel()
        updateStateTextNameSearch(name)
        searchNameJob = viewModelScope.launch {
            delay(3000)
            executeSearchTextSearch()
        }
    }

    fun onChangeTextTypeSearchLocations(type: String) {
        searchTypeJob?.cancel()
        updateStateTextTypeSearch(type)
        searchTypeJob = viewModelScope.launch {
            delay(3000)
            executeSearchTextSearch()
        }
    }

    fun onChangeTextDimensionSearchLocations(type: String) {
        searchDimensionJob?.cancel()
        updateStateTextDimensionSearch(type)
        searchDimensionJob = viewModelScope.launch {
            delay(3000)
            executeSearchTextSearch()
        }
    }

    fun pullToRefresh() {
        when (_state.value) {
            is LocationsListManagementState.Error -> {
                val currentState = _state.value as LocationsListManagementState.Error
                _state.value = currentState.copy(
                    isRefreshing = true
                )
                getListLocations(
                    nameLocation = null,
                    typeLocation = null,
                    dimensionLocation = null
                )
            }

            LocationsListManagementState.Loading -> {}
            is LocationsListManagementState.Success -> {
                val currentState = _state.value as LocationsListManagementState.Success
                _state.value = currentState.copy(
                    isRefreshing = true
                )
                getListLocations(
                    nameLocation = currentState.textNameSearchLocation,
                    typeLocation = currentState.textTypeLocationSearch,
                    dimensionLocation = currentState.textDimensionLocationSearch
                )
            }
        }
    }

    private fun updateStateTextTypeSearch(text: String) {
        val currentState = _state.value as LocationsListManagementState.Success
        val updatedState = currentState.copy(
            textTypeLocationSearch = text.ifEmpty { null }
        )
        _state.value = updatedState
    }

    private fun updateStateTextDimensionSearch(text: String) {
        val currentState = _state.value as LocationsListManagementState.Success
        val updatedState = currentState.copy(
            textDimensionLocationSearch = text.ifEmpty { null }
        )
        _state.value = updatedState
    }

    private fun updateStateTextNameSearch(text: String) {
        val currentState = _state.value as LocationsListManagementState.Success
        val updatedState = currentState.copy(
            textNameSearchLocation = text.ifEmpty { null }
        )
        _state.value = updatedState
    }

    private fun executeSearchTextSearch() {
        val currentState = _state.value as LocationsListManagementState.Success
        getListLocations(
            nameLocation = currentState.textNameSearchLocation,
            typeLocation = currentState.textTypeLocationSearch,
            dimensionLocation = currentState.textDimensionLocationSearch
        )
    }
}
package com.example.rick_and_morty.presentation.screens.location_detail.view_model

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rick_and_morty.R
import com.example.rick_and_morty.core.ui.utils.IResourceService
import com.example.rick_and_morty.domain.interactor.location.ILocationDetailInteractor
import com.example.rick_and_morty.domain.module.error_handler.ApiResult
import com.example.rick_and_morty.domain.module.error_handler.getStringSystemError
import com.example.rick_and_morty.presentation.screens.location_detail.state.LocationDetailManagementState
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
class LocationDetailManagementViewModel @Inject constructor(
    private val resolver: IResourceService,
    private val locationDetailInteractor: ILocationDetailInteractor
) : ViewModel() {

    private val _state = MutableStateFlow<LocationDetailManagementState>(
        LocationDetailManagementState.Loading
    )
    val state: StateFlow<LocationDetailManagementState> = _state.asStateFlow()

    @SuppressLint("StringFormatMatches")
    fun getLocationDetail(locationId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            if (_state.value::class.java != LocationDetailManagementState.Success::class.java) {
                _state.value = LocationDetailManagementState.Loading
                delay(timeMillis = 500)
            }
            locationDetailInteractor.getDetailLocation(locationId)
                .catch {
                    LocationDetailManagementState.Error(
                        errorText = resolver.getString(R.string.text_error_system),
                    )
                }
                .collect { result ->
                    when (result) {
                        is ApiResult.Error -> {
                            val errorText = result.type.getStringSystemError(resolver)
                            if (errorText != resolver.getString(R.string.text_error_in_server)) {
                                _state.value = LocationDetailManagementState.Error(
                                    errorText = errorText,
                                    isRefreshing = false
                                )
                            } else {

                            }
                        }

                        is ApiResult.Success -> {
                            _state.value = LocationDetailManagementState.Success(
                                location = result.data,
                                isRefreshing = false
                            )
                            getCharacterInLocation()
                        }
                    }
                }
        }
    }

    fun pullToRefresh(locationId: Int) {
        when (_state.value) {
            is LocationDetailManagementState.Error -> {
                val currentState = _state.value as LocationDetailManagementState.Error
                _state.value = currentState.copy(
                    isRefreshing = true
                )
                getLocationDetail(locationId)
            }

            LocationDetailManagementState.Loading -> {}
            is LocationDetailManagementState.Success -> {
                val currentState = _state.value as LocationDetailManagementState.Success
                _state.value = currentState.copy(
                    isRefreshing = true
                )
                getLocationDetail(locationId)
            }
        }
    }

    private fun getCharacterInLocation() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val currentState = _state.value) {
                is LocationDetailManagementState.Error -> {}
                LocationDetailManagementState.Loading -> {}
                is LocationDetailManagementState.Success -> {
                    locationDetailInteractor.getCharacterInLocation(
                        creatingListOfCharacterIDS(currentState.location.residents)
                    )
                        .catch {
                            _state.value = LocationDetailManagementState.Error(
                                errorText = it.message.toString(),
                                isRefreshing = false
                            )
                        }
                        .collect { result ->
                            when (result) {
                                is ApiResult.Error -> {
                                    val errorText = result.type.getStringSystemError(resolver)
                                    if (errorText != resolver.getString(R.string.text_error_in_server)) {
                                        _state.value = LocationDetailManagementState.Error(
                                            errorText = errorText,
                                            isRefreshing = false
                                        )
                                    } else {
                                        // обработать те исключения
                                    }
                                }

                                is ApiResult.Success -> {
                                    _state.value = currentState.copy(
                                        characters = result.data
                                    )
                                }
                            }
                        }
                }
            }
        }
    }

    private fun creatingListOfCharacterIDS(
        listUrlCharacters: List<String>
    ): List<Int> {
        return listUrlCharacters.map {
            it.substringAfterLast("/").toInt()
        }
    }
}
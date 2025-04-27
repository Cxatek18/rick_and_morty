package com.example.rick_and_morty.presentation.screens.characters_list.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rick_and_morty.domain.interactor.characters.CharactersListManagementInteractor
import com.example.rick_and_morty.domain.module.error_handler.ApiResult
import com.example.rick_and_morty.domain.module.error_handler.ErrorType
import com.example.rick_and_morty.presentation.screens.characters_list.state.CharactersListManagementState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersListManagementViewModel @Inject constructor(
    private val charactersListManagementInteractor: CharactersListManagementInteractor
) : ViewModel() {

    private var _state = MutableStateFlow<CharactersListManagementState>(
        CharactersListManagementState.Loading
    )
    val state: StateFlow<CharactersListManagementState> = _state.asStateFlow()

    init {
        getListAllCharacters()
    }

    private fun getListAllCharacters() {
        viewModelScope.launch {
            _state.value = CharactersListManagementState.Loading
            delay(timeMillis = 1000)
            charactersListManagementInteractor.getListAllCharacters()
                .catch { error ->
                    CharactersListManagementState.Error(
                        errorType = ErrorType.SYSTEM,
                    )
                }
                .collect { result ->
                    when (result) {
                        is ApiResult.Error -> {
                            _state.value = CharactersListManagementState.Error(
                                errorType = result.type
                            )
                        }

                        is ApiResult.Success -> {
                            _state.value = CharactersListManagementState.Success(
                                characters = result.data.characterList,
                                info = result.data.info
                            )
                        }
                    }
                }
        }
    }
}
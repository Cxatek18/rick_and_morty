package com.example.rick_and_morty.presentation.screens.characters_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rick_and_morty.domain.module.characters.CharactersResult
import com.example.rick_and_morty.domain.module.characters.ErrorType
import com.example.rick_and_morty.domain.usecase.characters.GetListAllCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersListScreenViewModel @Inject constructor(
    private val getListAllCharactersUseCase: GetListAllCharactersUseCase
) : ViewModel() {

    private var _state = MutableStateFlow<CharactersListScreenState>(
        CharactersListScreenState.Loading
    )
    val state: StateFlow<CharactersListScreenState> = _state.asStateFlow()

    init {
        getListAllCharacters()
    }

    fun getListAllCharacters() {
        viewModelScope.launch {
            _state.value = CharactersListScreenState.Loading
            delay(timeMillis = 1000)
            getListAllCharactersUseCase()
                .catch { error ->
                    CharactersListScreenState.Error(
                        errorType = ErrorType.SYSTEM,
                    )
                }
                .collect { result ->
                    when (result) {
                        is CharactersResult.CharacterListSuccess -> {
                            _state.value = CharactersListScreenState.Success(
                                characters = result.characterList,
                                info = result.info
                            )
                        }

                        is CharactersResult.Error ->
                            _state.value = CharactersListScreenState.Error(
                                errorType = result.errorType,
                            )
                    }
                }
        }
    }
}
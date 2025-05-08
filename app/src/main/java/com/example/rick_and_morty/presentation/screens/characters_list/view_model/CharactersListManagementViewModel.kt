package com.example.rick_and_morty.presentation.screens.characters_list.view_model

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rick_and_morty.R
import com.example.rick_and_morty.core.ui.utils.AndroidResourceResolver
import com.example.rick_and_morty.core.ui.utils.Resource
import com.example.rick_and_morty.domain.interactor.characters.CharactersListManagementInteractor
import com.example.rick_and_morty.domain.module.error_handler.ApiResult
import com.example.rick_and_morty.domain.module.error_handler.getStringSystemErrorError
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
    private val resolver: AndroidResourceResolver,
    private val charactersListManagementInteractor: CharactersListManagementInteractor
) : ViewModel() {

    private var _state = MutableStateFlow<CharactersListManagementState>(
        CharactersListManagementState.Loading
    )
    val state: StateFlow<CharactersListManagementState> = _state.asStateFlow()

    init {
        getListAllCharacters()
    }

    @SuppressLint("StringFormatMatches")
    private fun getListAllCharacters() {
        viewModelScope.launch {
            _state.value = CharactersListManagementState.Loading
            delay(timeMillis = 1000)
            charactersListManagementInteractor.getListAllCharacters()
                .catch {
                    CharactersListManagementState.Error(
                        errorText = resolver.resolve(Resource.String(R.string.text_error_system)),
                    )
                }
                .collect { result ->
                    when (result) {
                        is ApiResult.Error -> {
                            val systemErrorText = result.type.getStringSystemErrorError(resolver)
                            if (systemErrorText != null) {
                                _state.value = CharactersListManagementState.Error(
                                    errorText = systemErrorText
                                )
                            } else {
                                // Тут как раз можем уже обработать те исключения которые нам отдал бэк по запрсоу
                            }
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
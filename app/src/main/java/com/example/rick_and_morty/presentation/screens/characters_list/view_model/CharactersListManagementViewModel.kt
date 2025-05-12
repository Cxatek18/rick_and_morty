package com.example.rick_and_morty.presentation.screens.characters_list.view_model

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rick_and_morty.R
import com.example.rick_and_morty.core.ui.utils.IResourceService
import com.example.rick_and_morty.domain.interactor.characters.ICharactersListManagementInteractor
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
    fun getListAllCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = CharactersListManagementState.Loading
            delay(timeMillis = 1000)
            charactersListManagementInteractor.getListAllCharacters()
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
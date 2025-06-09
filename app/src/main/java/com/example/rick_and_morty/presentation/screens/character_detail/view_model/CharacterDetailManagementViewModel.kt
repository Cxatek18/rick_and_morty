package com.example.rick_and_morty.presentation.screens.character_detail.view_model

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rick_and_morty.R
import com.example.rick_and_morty.core.ui.utils.IResourceService
import com.example.rick_and_morty.domain.interactor.character.ICharacterDetailManagementInteractor
import com.example.rick_and_morty.domain.module.error_handler.ApiResult
import com.example.rick_and_morty.domain.module.error_handler.getStringSystemError
import com.example.rick_and_morty.presentation.screens.character_detail.state.CharacterDetailManagementState
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
class CharacterDetailManagementViewModel @Inject constructor(
    private val resolver: IResourceService,
    private val characterDetailManagementInteractor: ICharacterDetailManagementInteractor
) : ViewModel() {

    private var _state = MutableStateFlow<CharacterDetailManagementState>(
        CharacterDetailManagementState.Loading
    )
    val state: StateFlow<CharacterDetailManagementState> = _state.asStateFlow()


    @SuppressLint("StringFormatMatches")
    fun getCharacterDetail(characterID: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            if (_state.value::class.java != CharacterDetailManagementState.Success::class.java) {
                _state.value = CharacterDetailManagementState.Loading
                delay(timeMillis = 500)
            }
            characterDetailManagementInteractor.getCharacterDetail(characterID)
                .catch {
                    CharacterDetailManagementState.Error(
                        errorText = resolver.getString(R.string.text_error_system),
                    )
                }
                .collect { result ->
                    when (result) {
                        is ApiResult.Error -> {
                            val errorText = result.type.getStringSystemError(resolver)
                            if (errorText != resolver.getString(R.string.text_error_in_server)) {
                                _state.value = CharacterDetailManagementState.Error(
                                    errorText = errorText,
                                    isRefreshing = false
                                )
                            } else {
                                // Тут как раз можем уже обработать те исключения которые нам отдал бэк по запрсоу
                            }
                        }

                        is ApiResult.Success -> {
                            _state.value = CharacterDetailManagementState.Success(
                                character = result.data,
                                isRefreshing = false
                            )
                        }
                    }
                }
        }
    }

    fun pullToRefresh(characterID: Int) {
        when (_state.value) {
            is CharacterDetailManagementState.Error -> {
                val currentState = _state.value as CharacterDetailManagementState.Error
                _state.value = currentState.copy(
                    isRefreshing = true
                )
                getCharacterDetail(characterID)
            }

            CharacterDetailManagementState.Loading -> {}
            is CharacterDetailManagementState.Success -> {
                val currentState = _state.value as CharacterDetailManagementState.Success
                _state.value = currentState.copy(
                    isRefreshing = true
                )
                getCharacterDetail(characterID)
            }
        }
    }
}
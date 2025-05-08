package com.example.rick_and_morty.presentation.screens.character_detail.view_model

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rick_and_morty.R
import com.example.rick_and_morty.core.ui.utils.AndroidResourceResolver
import com.example.rick_and_morty.core.ui.utils.Resource
import com.example.rick_and_morty.domain.interactor.character.CharacterDetailManagementInteractor
import com.example.rick_and_morty.domain.module.error_handler.ApiResult
import com.example.rick_and_morty.domain.module.error_handler.getStringSystemErrorError
import com.example.rick_and_morty.presentation.screens.character_detail.state.CharacterDetailManagementState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailManagementViewModel @Inject constructor(
    private val resolver: AndroidResourceResolver,
    private val characterDetailManagementInteractor: CharacterDetailManagementInteractor
) : ViewModel() {

    private var _state = MutableStateFlow<CharacterDetailManagementState>(
        CharacterDetailManagementState.Loading
    )
    val state: StateFlow<CharacterDetailManagementState> = _state.asStateFlow()


    @SuppressLint("StringFormatMatches")
    fun getCharacterDetail(characterID: Int) {
        viewModelScope.launch {
            _state.value = CharacterDetailManagementState.Loading
            delay(timeMillis = 1000)
            characterDetailManagementInteractor.getCharacterDetail(characterID)
                .catch {
                    CharacterDetailManagementState.Error(
                        errorText = resolver.resolve(Resource.String(R.string.text_error_system)),
                    )
                }
                .collect { result ->
                    when (result) {
                        is ApiResult.Error -> {
                            val errorText = result.type.getStringSystemErrorError(resolver)
                            if (errorText != null) {
                                _state.value = CharacterDetailManagementState.Error(
                                    errorText = errorText
                                )
                            } else {
                                // Тут как раз можем уже обработать те исключения которые нам отдал бэк по запрсоу
                            }
                        }

                        is ApiResult.Success -> {
                            _state.value = CharacterDetailManagementState.Success(
                                character = result.data
                            )
                        }
                    }
                }
        }
    }
}
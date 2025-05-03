package com.example.rick_and_morty.presentation.screens.character_detail.view_model

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rick_and_morty.R
import com.example.rick_and_morty.domain.interactor.character.CharacterDetailManagementInteractor
import com.example.rick_and_morty.domain.module.error_handler.ApiResult
import com.example.rick_and_morty.domain.module.error_handler.ErrorType
import com.example.rick_and_morty.presentation.screens.character_detail.state.CharacterDetailManagementState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailManagementViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
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
                        errorText = context.getString(R.string.text_error_system),
                    )
                }
                .collect { result ->
                    when (result) {
                        is ApiResult.Error -> {
                            val errorText = when (result.type) {
                                ErrorType.NETWORK -> context.getString(R.string.text_error_network)
                                ErrorType.HTTP -> context.getString(R.string.text_error_http)
                                ErrorType.SYSTEM -> context.getString(R.string.text_error_system)
                                ErrorType.NULL_TYPE -> context.getString(R.string.text_error_null_type)
                                ErrorType.UNKNOWN -> {
                                    String.format(
                                        context.getString(R.string.text_error_unknown),
                                        result.code
                                    )
                                }

                                else -> {
                                    String.format(
                                        context.getString(R.string.text_error_unknown),
                                        result.code
                                    )
                                }
                            }
                            _state.value = CharacterDetailManagementState.Error(
                                errorText = errorText
                            )
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
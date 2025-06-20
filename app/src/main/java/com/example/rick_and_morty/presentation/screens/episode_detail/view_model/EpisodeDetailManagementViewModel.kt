package com.example.rick_and_morty.presentation.screens.episode_detail.view_model

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rick_and_morty.R
import com.example.rick_and_morty.core.ui.utils.IResourceService
import com.example.rick_and_morty.domain.interactor.episode.IEpisodeDetailInteractor
import com.example.rick_and_morty.domain.module.error_handler.ApiResult
import com.example.rick_and_morty.domain.module.error_handler.getStringSystemError
import com.example.rick_and_morty.presentation.screens.episode_detail.state.EpisodeDetailManagementState
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
class EpisodeDetailManagementViewModel @Inject constructor(
    private val resolver: IResourceService,
    private val episodeDetailInteractor: IEpisodeDetailInteractor
) : ViewModel() {

    private val _state = MutableStateFlow<EpisodeDetailManagementState>(
        EpisodeDetailManagementState.Loading
    )
    val state: StateFlow<EpisodeDetailManagementState> = _state.asStateFlow()

    @SuppressLint("StringFormatMatches")
    fun getEpisodeDetail(episodeId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            if (_state.value::class.java != EpisodeDetailManagementState.Success::class.java) {
                _state.value = EpisodeDetailManagementState.Loading
                delay(timeMillis = 500)
            }
            episodeDetailInteractor.getDetailEpisode(episodeId)
                .catch {
                    EpisodeDetailManagementState.Error(
                        errorText = resolver.getString(R.string.text_error_system),
                    )
                }
                .collect { result ->
                    when (result) {
                        is ApiResult.Error -> {
                            val errorText = result.type.getStringSystemError(resolver)
                            if (errorText != resolver.getString(R.string.text_error_in_server)) {
                                _state.value = EpisodeDetailManagementState.Error(
                                    errorText = errorText,
                                    isRefreshing = false
                                )
                            } else {
                                // обработать те исключения
                            }
                        }

                        is ApiResult.Success -> {
                            _state.value = EpisodeDetailManagementState.Success(
                                episode = result.data,
                                isRefreshing = false
                            )
                            getCharacterInEpisode()
                        }
                    }
                }
        }
    }

    fun pullToRefresh(episodeId: Int) {
        when (_state.value) {
            is EpisodeDetailManagementState.Error -> {
                val currentState = _state.value as EpisodeDetailManagementState.Error
                _state.value = currentState.copy(
                    isRefreshing = true
                )
                getEpisodeDetail(episodeId)
            }

            EpisodeDetailManagementState.Loading -> {}
            is EpisodeDetailManagementState.Success -> {
                val currentState = _state.value as EpisodeDetailManagementState.Success
                _state.value = currentState.copy(
                    isRefreshing = true
                )
                getEpisodeDetail(episodeId)
            }
        }
    }

    private fun getCharacterInEpisode() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val currentState = _state.value) {
                is EpisodeDetailManagementState.Error -> {}
                EpisodeDetailManagementState.Loading -> {}
                is EpisodeDetailManagementState.Success -> {
                    episodeDetailInteractor.getCharacterInEpisode(
                        creatingListOfCharacterIDS(currentState.episode.listUrlCharacter)
                    )
                        .catch {
                            _state.value = EpisodeDetailManagementState.Error(
                                errorText = it.message.toString(),
                                isRefreshing = false
                            )
                        }
                        .collect { result ->
                            when (result) {
                                is ApiResult.Error -> {
                                    val errorText = result.type.getStringSystemError(resolver)
                                    if (errorText != resolver.getString(R.string.text_error_in_server)) {
                                        _state.value = EpisodeDetailManagementState.Error(
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